package com.rb.cms.controller;

import com.common.base.constant.Result;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.common.base.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * @author xujiping
 * @date 2018/6/11 14:43
 */
@Controller
public class IndexController {

    @Value("${file.server}")
    private String fileServer;


    @GetMapping(value = {"/index", ""})
    public String index(){
        return "index";
    }

    @GetMapping("/eureka")
    public String eureka(){
        return "redirect:http://localhost:1025";
    }

    /**
     * 上传文件
     * @param file
     * @param filePrefix 文件前缀
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file")MultipartFile file, String filePrefix){
        Result result = new Result();
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String newFileName = filePrefix + "_" + System.currentTimeMillis() + "." + suffix;
        try {
            FileUtil.uploadFile(file.getBytes(), newFileName, fileServer);
        } catch (Exception e){
            throw new BusinessException(ReturnCode.FAIL);
        }
        String[] uploadPath = new String[1];
        uploadPath[0] = fileServer;
        result.setErrno(0);
        result.setData(uploadPath);
        return "{\"errno\":0,\"data\":[\"/file?filename=" + newFileName + "\"]}";
    }

}
