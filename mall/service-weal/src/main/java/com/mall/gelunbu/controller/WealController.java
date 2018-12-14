package com.mall.gelunbu.controller;


import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.mall.gelunbu.entity.Weal;
import com.mall.gelunbu.service.WealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 福利表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-06-26
 */
@Controller
@RequestMapping("/weal")
@Api(tags = "用户福利接口")
@Transactional(rollbackFor = BusinessException.class)
public class WealController {

    @Autowired
    private WealService wealService;

    @ApiOperation(value = "查询听众用户福利信息", notes = "根据听众用户ID查询用户福利信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clientListenId", value = "客户端收听用户ID", required = true, dataType = "String",
                    paramType = "path")
    })
    @GetMapping("{clientListenId}")
    @ResponseBody
    public String info(@PathVariable(value = "clientListenId") String clientListenId) {
        Weal weal = wealService.selectByClientListenId(clientListenId);
        return new ReturnBean(ReturnCode.SUCCESS, weal).toJsonString();
    }

}

