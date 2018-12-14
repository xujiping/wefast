package com.mall.goods.controller;


import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.mall.goods.entity.MallPropertyKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 属性名表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-06-14
 */
@Controller
@RequestMapping("/mallPropertyKey")
@Api(tags = "商品属性名接口")
public class MallPropertyKeyController {

    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "商品属性名称", required = true, dataType = "String", paramType =
                    "query"),
            @ApiImplicitParam(name = "categoryId", value = "商品分类ID", required = true, dataType = "int", paramType =
                    "query"),
            @ApiImplicitParam(name = "pid", value = "父属性ID", required = true, dataType = "int",
                    paramType = "query", defaultValue = "0")
    })
    @PostMapping("key")
    public String add(@RequestParam String name,
                      @RequestParam Integer categoryId,
                      @RequestParam Integer pid) {
        MallPropertyKey propertyKey = new MallPropertyKey(null, name, categoryId, pid);
        boolean insert = propertyKey.insert();
        if (!insert){
            throw new BusinessException(ReturnCode.FAIL);
        }
        return new ReturnBean().toJsonString();
    }

}

