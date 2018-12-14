package com.cloud.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xujiping
 * @date 2018/12/3 14:05
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulJokerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulJokerApplication.class, args);
    }
}
