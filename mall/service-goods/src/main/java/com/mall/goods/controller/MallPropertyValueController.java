package com.mall.goods.controller;


import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.mall.goods.entity.MallPropertyValue;
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
 * 属性值表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-06-14
 */
@Controller
@RequestMapping("/mallPropertyValue")
@Api(tags = "商品属性值接口")
public class MallPropertyValueController {

    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "value", value = "商品属性值", required = true, dataType = "String", paramType =
                    "query"),
            @ApiImplicitParam(name = "propertyKeyId", value = "商品属性ID", required = true, dataType = "int", paramType =
                    "query")
    })
    @PostMapping("key")
    public String add(@RequestParam String value,
                      @RequestParam Integer propertyKeyId) {
        MallPropertyValue propertyValue = new MallPropertyValue(null, value, propertyKeyId);
        boolean insert = propertyValue.insert();
        if (!insert){
            throw new BusinessException(ReturnCode.FAIL);
        }
        return new ReturnBean().toJsonString();
    }

}

