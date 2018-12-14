package com.mall.goods.controller;


import com.alibaba.fastjson.JSONObject;
import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.mall.goods.dto.GoodsDto;
import com.mall.goods.entity.MallGoods;
import com.mall.goods.entity.MallGoodsDetail;
import com.mall.goods.entity.MallSku;
import com.mall.goods.service.MallGoodsDetailService;
import com.mall.goods.service.MallGoodsService;
import com.mall.goods.service.MallSkuService;
import com.mall.goods.service.WealService;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

/**
 * <p>
 * 商品详情表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-06-15
 */
@RestController
@RequestMapping("/mallGoodsDetail")
@Api(tags = "商品详情接口")
public class MallGoodsDetailController {

    @Autowired
    private MallGoodsDetailService goodsDetailService;
    @Autowired
    private MallGoodsService goodsService;

    @Autowired
    private WealService wealService;

    @Autowired
    private MallSkuService skuService;

    @ApiOperation(value = "商品详情", notes = "根据商品ID查看商品详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuId", value = "商品skuID", required = true, dataType = "String", paramType =
                    "path"),
            @ApiImplicitParam(name = "clientListenId", value = "客户端收听用户ID", required = false, dataType = "String",
                    paramType = "query")
    })
    @GetMapping("/{skuId}")
    public String get(@PathVariable(value = "skuId") String skuId,
                      @RequestParam(required = false) String clientListenId) {
        ReturnBean returnBean = new ReturnBean();
        //商品sku信息
        MallSku mallSku = skuService.selectById(skuId);
        double oldPrice = mallSku.getOldPrice().doubleValue();
        double price = mallSku.getPrice().doubleValue();
        Long goodsId = mallSku.getGoodsId();
        GoodsDto goodsDto = new GoodsDto();
        MallGoodsDetail detail = goodsDetailService.selectById(String.valueOf(goodsId));
        if (detail == null) {
            throw new BusinessException(ReturnCode.GOODS_NULL);
        }
        BeanUtils.copyProperties(detail, goodsDto);
        MallGoods goods = goodsService.selectById(String.valueOf(goodsId));
        if (goods == null) {
            throw new BusinessException(ReturnCode.GOODS_NULL);
        }
        BeanUtils.copyProperties(goods, goodsDto);
        double userCoin = 0;
        if (StringUtils.isNotEmpty(clientListenId)) {
            //获取用户福利信息
            String clientListenInfo = wealService.info(clientListenId);
            JSONObject jsonObject = JSONObject.parseObject(clientListenInfo);
            Integer code = jsonObject.getInteger("code");
            if (code != ReturnCode.SUCCESS.code()){
                throw new BusinessException(ReturnCode.NULL_INFO);
            }
            JSONObject clientListenJson = JSONObject.parseObject(jsonObject.getString("data"));
            userCoin = clientListenJson.getDouble("currentCoin");
            goodsDto.setUserCoin(userCoin);
        }
        //商品价格
        goodsDto.setOldPrice(oldPrice);
        goodsDto.setPrice(price);
        if (userCoin < price) {
            goodsDto.setNeedPay(price - userCoin);
        } else {
            goodsDto.setNeedPay(0d);
        }
        returnBean.setData(goodsDto);
        return returnBean.toJsonString();
    }

}

