package com.cloud.configer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置中心
 *
 * @author xujiping
 * @date 2018/6/19 9:31
 */
@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigerApplication.class, args);
    }
}
