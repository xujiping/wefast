package com.frontend.media.service.impl;

import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.frontend.media.service.FileService;
import com.frontend.media.util.AliyunOssUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * @author xujiping
 * @date 2018/10/18 15:36
 */
@Service
public class FileServiceImpl implements FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public boolean uploadFile(String path, String imgName, String data, String ossPathName) {
        String filePathName = path;
        String base64 = data.substring(data.indexOf(",") + 1);
        FileOutputStream fileOutputStream;
        try {
            File targetFile = new File(path);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            File newFile = new File(filePathName);
            if (newFile.exists()) {
                newFile.delete();
            }
            fileOutputStream = new FileOutputStream(filePathName);
            byte[] bytes = Base64.getDecoder().decode(base64);
            fileOutputStream.write(bytes);
            fileOutputStream.close();
            // 上传至阿里云oss服务器
            boolean uploadOss = AliyunOssUtil.putInputStream(null, ossPathName, new FileInputStream(filePathName));
            if (!uploadOss) {
                LOGGER.error("上传文件至阿里云OSS服务器失败：" + filePathName);
                throw new BusinessException(ReturnCode.FAIL);
            }
        } catch (IOException e) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        return true;
    }
}
