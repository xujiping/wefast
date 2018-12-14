package com.frontend.media.controller;

import com.alibaba.fastjson.JSONObject;
import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.common.base.util.FileUtil;
import com.frontend.media.entity.ClientListen;
import com.frontend.media.entity.ClientRelease;
import com.frontend.media.service.ClientListenService;
import com.frontend.media.service.ClientReleaseService;
import com.frontend.media.service.ResContentService;
import com.frontend.media.util.AliyunOssUtil;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * 发表
 *
 * @author xujiping
 * @date 2018/7/20 17:06
 */
@Controller
@RequestMapping("publish")
public class PublishController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublishController.class);

    @Autowired
    private ResContentService contentService;

    @Autowired
    private ClientReleaseService releaseService;

    @Autowired
    private ClientListenService clientListenService;

    @Value("${file.server}")
    private String fileServer;

    @GetMapping("")
    public String index(Model model) {
        ClientListen clientListen = clientListenService.getCurListen();
        model.addAttribute("curListen", clientListen);
        List<ClientRelease> releaseList = releaseService.getUserAllReleases();
        model.addAttribute("releaseList", releaseList);
        return "publish/index";
    }

    /**
     * 音频上传
     *
     * @param file    文件
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam String releaseId, @RequestParam("file") MultipartFile file) {
        ReturnBean rb = new ReturnBean();
        String fileName = file.getOriginalFilename();
        String newFileName = com.frontend.media.util.FileUtil.generateContentFileName(fileName);
        ClientRelease curRelease = releaseService.selectById(releaseId);
        String headUrl = curRelease.getHeadUrl();
        JSONObject headJson = JSONObject.parseObject(headUrl);
//        ClientRelease release = releaseService.getByUsername(releaseUsername);
//        String contentPath = com.frontend.media.util.FileUtil.getContentPath(release.getSubmitTime(), dir);
        String contentPath = headJson.getString("url");
        File newFile = null;
        try {
            newFile = FileUtil.uploadFile(file.getBytes(), newFileName, fileServer + contentPath);
            // 上传至阿里云oss服务器
            String ossPath = contentPath + newFileName;
            ossPath = ossPath.substring(1);
            FileInputStream inputStream = new FileInputStream(newFile);
            boolean uploadOss = AliyunOssUtil.putInputStream(null, ossPath, inputStream);
            if (!uploadOss) {
                LOGGER.error("上传文件至阿里云OSS服务器失败：" + newFileName);
                throw new BusinessException(ReturnCode.FAIL);
            }
        } catch (Exception e) {
            throw new BusinessException(ReturnCode.FILE_UPLOAD_FAIL);
        }
        Double contentTime = 0.00;
        Long fileSize = 0L;
        JSONObject jsonObject = null;
        try {
            //获取音频时长
            it.sauronsoftware.jave.Encoder encoder = new it.sauronsoftware.jave.Encoder();
            MultimediaInfo info = encoder.getInfo(newFile);
            contentTime = Double.valueOf(info.getDuration() / 1000);
            fileSize = newFile.length();
        } catch (Exception e) {
            throw new BusinessException(ReturnCode.VOICE_FILE_ERROR.code(), "文件（" + fileName +
                    "）错误，上传失败");
        }
        jsonObject = com.frontend.media.util.FileUtil.pathToJson(contentPath + newFileName, fileSize,
                contentTime, fileName.substring(0, fileName.indexOf(".")));
        rb.setData(jsonObject);
        return rb.toJsonString();
    }

    /**
     * 新增音频
     *
     * @param title
     * @param intro
     * @param contentUrl
     * @return
     */
    @PostMapping("/content")
    @ResponseBody
    public String addMedia(Long releaseId, String title, String intro, String contentUrl) {
        ReturnBean rb = new ReturnBean();
        JSONObject jsonObject = null;
        if (StringUtils.isNotEmpty(contentUrl)) {
            jsonObject = JSONObject.parseObject(contentUrl);
        }
        boolean addResult = contentService.add(title, intro, releaseId, jsonObject);
        if (!addResult) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        return rb.toJsonString();
    }

}
