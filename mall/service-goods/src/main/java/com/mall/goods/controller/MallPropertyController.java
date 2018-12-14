package com.mall.goods.controller;

import com.mall.goods.service.MallPropertyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品属性
 * @author xujiping
 * @date 2018/6/15 11:17
 */
@RestController
@RequestMapping("/mallProperty")
@Api(tags = "商品属性接口")
public class MallPropertyController {

    @Autowired private MallPropertyService propertyService;

}
