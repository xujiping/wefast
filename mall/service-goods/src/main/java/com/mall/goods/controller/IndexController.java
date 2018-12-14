package com.mall.goods.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xujiping
 * @date 2018/6/14 16:12
 */
@RestController
@RefreshScope
@Api(tags = "默认接口")
public class IndexController {

    @Value("${profile}")
    private String profile;

    @ApiOperation(value = "信息", notes = "当前微服务信息接口")
    @GetMapping("/info")
    public String index() {
        return "<h2>商品微服务</h2><p><a href='/swagger-ui.html'>接口文档</a></p>";
    }

    @GetMapping("/profile")
    public String hello(){
        return profile;
    }
}
