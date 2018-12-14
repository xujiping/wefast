package com.frontend.media.controller;


import com.common.base.constant.Constants;
import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.common.base.util.FileUtil;
import com.common.base.util.SpageUtil;
import com.frontend.media.controller.dto.VideoDto;
import com.frontend.media.entity.Category;
import com.frontend.media.entity.ClientListen;
import com.frontend.media.entity.MyFile;
import com.frontend.media.entity.Video;
import com.frontend.media.service.CategoryService;
import com.frontend.media.service.ClientListenService;
import com.frontend.media.service.VideoService;
import com.frontend.media.util.AliyunOssUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 视频表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-10-27
 */
@Controller
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private ClientListenService listenService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private CategoryService categoryService;

    @Value("${file.server}")
    private String fileServer;

    @Value("${file.ffmpeg}")
    private String ffmpeg;

    @GetMapping("index")
    public String index() {
        return "video/index";
    }

    /**
     * 列表
     *
     * @param page
     * @param rows
     * @param name
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public String list(@RequestParam(value = "page", defaultValue = "1") int page,
                       @RequestParam(value = "rows", defaultValue = "14") int rows,
                       @RequestParam(value = "name", required = false) String name) {
        ReturnBean returnBean = new ReturnBean();
        SpageUtil<Video> spageUtil = new SpageUtil<>(page, rows);
        spageUtil.setSort("create_time desc");
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        if (StringUtils.isNotEmpty(name)) {
            params.put("name", name);
        }
        spageUtil = videoService.getUserlist(spageUtil, params);
        List<Video> atlases = spageUtil.getRows();
        List<VideoDto> atlasDtoList = new ArrayList<>();
        if (atlases != null && atlases.size() > 0) {
            atlasDtoList = atlases.stream().map(video -> videoService.wrapper(video, fileServer)).collect(Collectors
                    .toList());
        }
        long total = spageUtil.getTotal();
        returnBean.setData(atlasDtoList);
        returnBean.setCount(total);
        return returnBean.toJsonString();
    }

    /**
     * 创建视频页面
     *
     * @param model
     * @return
     */
    @GetMapping("add")
    public String add(Model model) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        params.put("order", "seq");
        params.put("stat", Constants.STAT_NORMAL);
        params.put("subject", Constants.CATEGORY_VIDEO);
        List<Category> categories = categoryService.list(params);
        model.addAttribute("categories", categories);
        return "video/add";
    }

    /**
     * 创建视频
     *
     * @param title 标题
     * @param url   路径
     * @return
     */
    @PostMapping("info")
    @ResponseBody
    public String add(String title, String url, int categoryId, String cover) {
        ReturnBean rb = new ReturnBean();
        if (StringUtils.isNotEmpty(cover)) {
            cover = cover.replaceAll(fileServer, "");
        }
        boolean add = videoService.add(title, url, categoryId, cover, fileServer);
        if (!add) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        return rb.toJsonString();
    }

    /**
     * 视频上传
     *
     * @param file    文件
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, double duration) {
        if (duration >= 60){
            throw new BusinessException(ReturnCode.TIME_TOO_LONG);
        }
        ReturnBean rb = new ReturnBean();
        ClientListen curListen = listenService.getCurListen();
        String username = curListen.getUsername();
        long timeMillis = System.currentTimeMillis();
        // 文件名（包含后缀）
        String fileName = timeMillis + "." + Constants.SHORT_VIDEO_SUFFIX;
        // 短视频相对文件目录
        String path = Constants.SHORT_VIDEO_PATH + Constants.PATH_SEPARATOR + username + Constants.PATH_SEPARATOR +
                timeMillis + Constants.PATH_SEPARATOR;
        // oss文件绝对路径
        String ossPath = path + fileName;
        // 本地文件路径
        String localPath = fileServer + path;
        String newFilePath;
        List<String> coverImageList;
        FileInputStream fileInputStream;
        try {
            //文件上传到本地
            File newFile = FileUtil.uploadFile(file.getBytes(), fileName, localPath);
            if (newFile == null) {
                throw new BusinessException(ReturnCode.FILE_UPLOAD_FAIL);
            }
            newFilePath = newFile.getCanonicalPath();
            //视频截图
            coverImageList = videoService.coverImage(ffmpeg, newFile, localPath, String.valueOf(timeMillis));
            for (int i = 0; i < coverImageList.size(); i++) {
                if (i == 0) {
                    //上传视频到oss文件服务器
                    boolean upload = AliyunOssUtil.putInputStream(AliyunOssUtil.TINGDUODUO_BUCKET_NAME, ossPath, file
                            .getInputStream());
                    if (!upload) {
                        throw new BusinessException(ReturnCode.FILE_UPLOAD_FAIL);
                    }
                } else {
                    //上传封面
                    String imagePath = coverImageList.get(i);
                    fileInputStream = new FileInputStream(imagePath);
                    if (StringUtils.isNotEmpty(imagePath)) {
                        imagePath = imagePath.replaceAll(fileServer, "");
                    }
                    AliyunOssUtil.putInputStream(AliyunOssUtil.TINGDUODUO_BUCKET_NAME, imagePath, fileInputStream);
                }
            }
        } catch (Exception e) {
            throw new BusinessException(ReturnCode.FILE_UPLOAD_FAIL);
        }
        rb.setData(coverImageList);
        return rb.toJsonString();
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public String delete(@PathVariable(value = "id") Long id) {
        ReturnBean rb = new ReturnBean();
        boolean delete = videoService.changeStat(id, Constants.STAT_BLOCK);
        if (!delete) {
            throw new BusinessException(ReturnCode.VIDEO_NOT_EXIST);
        }
        return rb.toJsonString();
    }

}

