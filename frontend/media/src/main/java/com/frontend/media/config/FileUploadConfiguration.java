package com.frontend.media.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * 文件上传配置
 * @author xujiping
 * @date 2018/7/25 11:41
 */
@Configuration
public class FileUploadConfiguration {

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //设置文件大小限制
        factory.setMaxFileSize("40MB");
        //设置总上传数据总大小
        factory.setMaxRequestSize("40MB");
        return factory.createMultipartConfig();
    }
}
