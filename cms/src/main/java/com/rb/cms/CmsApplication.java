package com.rb.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xujiping
 * @date 2018/6/9 16:41
 */
@SpringBootApplication(scanBasePackages = {"com.rb.cms", "com.common"})
@MapperScan(basePackages = {"com.rb.cms.mapper"})
public class CmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
