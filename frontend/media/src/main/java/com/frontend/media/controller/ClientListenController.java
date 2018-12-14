package com.frontend.media.controller;


import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.frontend.media.entity.ClientListen;
import com.frontend.media.service.ClientListenService;
import com.frontend.media.service.FileService;
import com.frontend.media.util.FileUtil;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Base64;
import java.util.Date;

/**
 * <p>
 * 资源收听者 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-08-16
 */
@Controller
@RequestMapping("/clientListen")
public class ClientListenController {

    @Autowired private ClientListenService clientListenService;

    @Autowired private FileService fileService;

    @Value("${file.server}")
    private String fileServer;

    @GetMapping("/info")
    public String index(Model model){
        ClientListen curListen = clientListenService.getCurListen();
        model.addAttribute("curListen", curListen);
        return "account/index";
    }

    /**
     * 获取当前用户头像
     *
     * @param response response
     * @throws IOException
     */
    @GetMapping("/head")
    @ResponseBody
    public void getHead(HttpServletResponse response) throws IOException {
        FileInputStream fis = null;
        OutputStream os = null;
        fis = new FileInputStream(clientListenService.getCurListenHeadUrl());
        os = response.getOutputStream();
        int count = 0;
        byte[] buffer = new byte[1024 * 8];
        while ((count = fis.read(buffer)) != -1) {
            os.write(buffer, 0, count);
            os.flush();
        }
        fis.close();
        os.close();
    }

    @PostMapping("/head")
    @ResponseBody
    public String uploadHead(String dataURL) {
        String curListenHeadUrl = clientListenService.getCurListenHeadUrl();
        ReturnBean returnBean = new ReturnBean();
        String suffix = ".png";
        long timeMillis = System.currentTimeMillis();
        String imgName = timeMillis + suffix;
        Date date = new Date();
        String filePath = FileUtil.getContentPath(date, String.valueOf(timeMillis));
        String path = fileServer + filePath;
        String base64 = dataURL.substring(dataURL.indexOf(",") + 1);
        // todo 测试
        String ossPath = filePath + imgName;
        fileService.uploadFile(path, imgName, dataURL, ossPath);
        //更新头像数据
        clientListenService.updateHeadUrl(filePath, String.valueOf(timeMillis), suffix);
        returnBean.setData(path + imgName);
        return returnBean.toJsonString();
    }

}

