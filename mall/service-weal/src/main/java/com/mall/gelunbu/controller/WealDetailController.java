package com.mall.gelunbu.controller;


import com.common.base.constant.Constants;
import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.mall.gelunbu.entity.ClientListen;
import com.mall.gelunbu.entity.Weal;
import com.mall.gelunbu.entity.WealDetail;
import com.mall.gelunbu.service.ClientListenService;
import com.mall.gelunbu.service.WealDetailService;
import com.mall.gelunbu.service.WealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 福利详情表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-06-26
 */
@Controller
@RequestMapping("/wealDetail")
@Api(tags = "用户福利信息接口")
@Transactional(rollbackFor = BusinessException.class)
public class WealDetailController {

    @Autowired
    private ClientListenService clientListenService;
    @Autowired
    private WealService wealService;
    @Autowired
    private WealDetailService wealDetailService;

    @ApiOperation(value = "新增福利详情", notes = "根据客户端收听用户ID，新增福利详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clientListenId", value = "客户端收听用户ID", required = true, dataType = "String",
                    paramType = "path"),
            @ApiImplicitParam(name = "title", value = "标题", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "introduce", value = "简介", required = true, dataType = "String", paramType =
                    "query"),
            @ApiImplicitParam(name = "income", value = "收支：1收入 2支出", required = true, dataType = "int", paramType =
                    "query"),
            @ApiImplicitParam(name = "money", value = "金额", required = true, dataType = "double", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "类型：1金币 2零钱 3福豆", required = true, dataType = "int", paramType =
                    "query")
    })
    @PostMapping("{clientListenId}")
    @ResponseBody
    public String add(@PathVariable(value = "clientListenId") String clientListenId,
                      @RequestParam String title,
                      @RequestParam String introduce,
                      @RequestParam Integer income,
                      @RequestParam Double money,
                      @RequestParam Integer type) {
        //判断用户是否存在
        ClientListen clientListen = clientListenService.selectById(clientListenId);
        if (clientListen == null) {
            throw new BusinessException(ReturnCode.LISTEN_USER_NOT_EXISTS);
        }
        Weal weal = wealService.selectByClientListenId(clientListenId);
        if (income == Constants.WEAL_DETAIL_EXPEND) {
            //检查用户福利
            BigDecimal oldBean = weal.getCurrentBean();
            Double oldCoin = weal.getCurrentCoin();
            Double oldGold = weal.getCurrentGold();
            if (type == Constants.WEAL_DETAIL_TYPE_GOLD) {
                if (oldGold < money) {
                    throw new BusinessException(ReturnCode.FAIL.code(), "金币不足");
                }
            }
            if (type == Constants.WEAL_DETAIL_TYPE_COIN) {
                if (oldCoin < money) {
                    throw new BusinessException(ReturnCode.FAIL.code(), "零钱不足");
                }
            }
            if (type == Constants.WEAL_DETAIL_TYPE_BEAN) {
                if (oldBean.doubleValue() < money) {
                    throw new BusinessException(ReturnCode.FAIL.code(), "福豆不足");
                }
            }
        }
        WealDetail wealDetail = new WealDetail();
        wealDetail.setTitle(title);
        wealDetail.setIntroduce(introduce);
        wealDetail.setIncome(income);
        wealDetail.setSize(money);
        wealDetail.setType(type);
        wealDetail.setClientListenId(clientListenId);
        wealDetail.setCreateTime(new Date());
        wealDetail.setStatus(1);
        boolean update = wealDetailService.updateDetailAndWeal(wealDetail, weal);
        if (!update) {
            throw new BusinessException();
        }
        return new ReturnBean().toJsonString();
    }

}

