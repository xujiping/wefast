package com.frontend.media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 听多多自媒体平台
 * @author xujiping
 * @date 2018/7/13 17:20
 */
@SpringBootApplication
@ComponentScan(value = {"com.common", "com.frontend.media"})
public class MediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediaApplication.class, args);
    }
}
