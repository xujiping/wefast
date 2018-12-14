package com.frontend.media.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.base.constant.Constants;
import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.common.base.util.SpageUtil;
import com.common.base.util.StringUtil;
import com.frontend.media.controller.dto.AtlasDto;
import com.frontend.media.controller.dto.ImageDto;
import com.frontend.media.controller.dto.ImageTab;
import com.frontend.media.controller.dto.ReleaseDto;
import com.frontend.media.entity.*;
import com.frontend.media.service.*;
import com.frontend.media.util.AliyunOssUtil;
import com.frontend.media.util.FileUtil;
import com.google.common.collect.Maps;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 图集 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-10-17
 */
@Controller
@RequestMapping("/atlas")
public class AtlasController {

    @Autowired
    private AtlasService atlasService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ClientListenService listenService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImageService imageService;

    @Value("${file.server}")
    private String fileServer;

    @GetMapping("index")
    public String index() {
        return "atlas/index";
    }

    @GetMapping("add")
    public String add(Model model) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        params.put("order", "seq");
        params.put("stat", Constants.STAT_NORMAL);
        params.put("subject", Constants.CATEGORY_ATLAS);
        List<Category> categories = categoryService.list(params);
        model.addAttribute("categories", categories);
        return "atlas/add";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable(value = "id") Long id, Model model) {
        Atlas atlas = atlasService.getUserAtlas(id);
        if (atlas == null) {
            throw new BusinessException(ReturnCode.NO_PERMISSION);
        }
        Long atlasId = atlas.getId();
        List<Image> images = imageService.getByAtlas(atlasId);
        List<ImageTab> imageTabs = new ArrayList<>();
        if (images != null && images.size() > 0) {
            imageTabs = images.stream().map(image -> imageService.wrapperTab(image, atlas, fileServer)).collect(Collectors
                    .toList());
        }
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        params.put("order", "seq");
        params.put("stat", Constants.STAT_NORMAL);
        params.put("subject", Constants.CATEGORY_ATLAS);
        List<Category> categories = categoryService.list(params);
        model.addAttribute("categories", categories);
        model.addAttribute("userAtlas", atlas);
        model.addAttribute("images", imageTabs);
        return "atlas/update";
    }

    @GetMapping("/info/{id}")
    public String preview(@PathVariable(value = "id") Long id, Model model) {
        boolean permission = atlasService.checkPermission(id);
        if (!permission) {
            throw new BusinessException(ReturnCode.NO_PERMISSION);
        }
        List<Image> images = imageService.getByAtlas(id);
        List<ImageDto> imageDtolist = new ArrayList<>();
        if (images != null && images.size() > 0) {
            imageDtolist = images.stream().map(image -> imageService.wrapper(image)).collect(Collectors.toList());
        }
        model.addAttribute("images", imageDtolist);
        return "atlas/preview";
    }

    @GetMapping("/list")
    @ResponseBody
    public String list(@RequestParam(value = "page", defaultValue = "1") int page,
                       @RequestParam(value = "rows", defaultValue = "14") int rows,
                       @RequestParam(value = "name", required = false) String name) {
        ReturnBean returnBean = new ReturnBean();
        SpageUtil<Atlas> spageUtil = new SpageUtil<>(page, rows);
        spageUtil.setSort("create_time desc");
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        if (StringUtils.isNotEmpty(name)) {
            params.put("name", name);
        }
        spageUtil = atlasService.getUserlist(spageUtil, params);
        List<Atlas> atlases = spageUtil.getRows();
        List<AtlasDto> atlasDtoList = new ArrayList<>();
        if (atlases != null && atlases.size() > 0) {
            atlasDtoList = atlases.stream().map(atlas -> atlasService.wrapper(atlas, fileServer)).collect(Collectors.toList());
        }
        long total = spageUtil.getTotal();
        returnBean.setData(atlasDtoList);
        returnBean.setCount(total);
        return returnBean.toJsonString();
    }

    @PostMapping("/image")
    @ResponseBody
    public String uploadHead(HttpServletRequest request, String dataURL) {
        if (StringUtils.isEmpty(dataURL)) {
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = request.getReader();) {
                char[] buff = new char[1024*1024];
                int len;
                while ((len = reader.read(buff)) != -1) {
                    sb.append(buff, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            dataURL = StringUtil.splitString(sb.toString(),  "dataURL=");
            try {
                dataURL = URLDecoder.decode(dataURL,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        ReturnBean rb = new ReturnBean();
        ClientListen curListen = listenService.getCurListen();
        String username = curListen.getUsername();
        long timeMillis = System.currentTimeMillis();
        // 文件名（包含后缀）
        String imgName = timeMillis + "." + Constants.PNG_SUFFIX;
        String ossPath = Constants.ATLAS_PATH + Constants.PATH_SEPARATOR + username + Constants
                .PATH_SEPARATOR + imgName;
        // 文件目录
        String path = fileServer + ossPath;
        fileService.uploadFile(path, imgName, dataURL, ossPath);
        rb.setData(path);
        return rb.toJsonString();
    }

    @PostMapping("/image/delete")
    @ResponseBody
    public String removeImage(String imageUrl, String imageId) {
        ReturnBean rb = new ReturnBean();
        if (StringUtils.isEmpty(imageUrl)) {
            rb.setReturnCode(ReturnCode.FAIL, null);
            return rb.toJsonString();
        }
        if (StringUtils.isNotEmpty(imageId)) {
            // 更新图集的封面ID
            atlasService.removeCover(imageId);
            // 删除数据库数据
            imageService.deleteById(Long.valueOf(imageId));
        }
        // 删除文件
        imageService.deleteFile(imageUrl, fileServer);
        return rb.toJsonString();
    }

    /**
     * 创建图集和图片
     *
     * @param title      标题
     * @param categoryId 类别ID
     * @param imgList    图片JSON数组
     * @return String
     */
    @PostMapping("info")
    @ResponseBody
    public String add(String title, int categoryId, String imgList) {
        ReturnBean rb = new ReturnBean();
        JSONArray imgJson = JSONArray.parseArray(imgList);
        atlasService.add(title, categoryId, imgJson, fileServer);
        return rb.toJsonString();
    }

    /**
     * 更新图集和图片
     *
     * @param id         图集ID
     * @param title      标题
     * @param categoryId 类别ID
     * @param imgList    图片JSON数组
     * @return String
     */
    @PutMapping("info")
    @ResponseBody
    public String update(long id, String title, int categoryId, String imgList) {
        ReturnBean rb = new ReturnBean();
        JSONArray imgJson = JSONArray.parseArray(imgList);
        atlasService.update(id, title, categoryId, imgJson, fileServer);
        return rb.toJsonString();
    }

    /**
     * 删除图集
     *
     * @param id
     * @return
     */
    @DeleteMapping("info/{id}")
    @ResponseBody
    public String delete(@PathVariable(value = "id") Long id) {
        ReturnBean rb = new ReturnBean();
        atlasService.remove(id, fileServer);
        return rb.toJsonString();
    }

}

