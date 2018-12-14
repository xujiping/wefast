package com.mall.gelunbu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 听多多音频APP服务
 * @author xujiping
 * @date 2018/6/26 11:58
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableSwagger2
@ComponentScan(value = {"com.common", "com.mall.gelunbu"})
public class GelunbuApplication {

    public static void main(String[] args) {
        SpringApplication.run(GelunbuApplication.class, args);
    }
}
