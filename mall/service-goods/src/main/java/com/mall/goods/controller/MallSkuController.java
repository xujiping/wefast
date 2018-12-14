package com.mall.goods.controller;


import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.common.base.util.UuidUtil;
import com.mall.goods.entity.MallGoods;
import com.mall.goods.entity.MallSku;
import com.mall.goods.service.MallGoodsService;
import com.mall.goods.service.MallSkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * <p>
 * SKU表（库存表） 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-06-14
 */
@RestController
@RequestMapping("/mallSku")
@Api(tags = "商品SKU（库存）接口")
@Transactional(rollbackFor = BusinessException.class)
public class MallSkuController {

    @Autowired
    private MallGoodsService goodsService;

    @Autowired
    private MallSkuService skuService;

    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", value = "商品ID", required = true, dataType = "long", paramType =
                    "query"),
            @ApiImplicitParam(name = "sku", value = "SKU属性，格式1:1,2:3", required = true, dataType = "String",
                    paramType = "query"),
            @ApiImplicitParam(name = "price", value = "价格", required = true, dataType = "double",
                    paramType = "query"),
            @ApiImplicitParam(name = "inventory", value = "库存", required = true, dataType = "long",
                    paramType = "query")
    })
    @PostMapping("")
    public String add(@RequestParam Long goodsId,
                      @RequestParam String sku,
                      @RequestParam Double price,
                      @RequestParam Long inventory) {
        MallGoods goods = goodsService.selectById(goodsId);
        if (goods == null) {
            throw new BusinessException(ReturnCode.GOODS_NULL);
        }
        MallSku mallSku = new MallSku();
        mallSku.setId(UuidUtil.uuid32());
        mallSku.setGoodsId(goodsId);
        mallSku.setPrice(BigDecimal.valueOf(price));
        mallSku.setInventory(inventory);
        mallSku.setSku(sku);
        boolean insert = mallSku.insert();
        if (!insert) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        return new ReturnBean().toJsonString();
    }

    @ApiOperation(value = "销售", notes = "销售")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "count", value = "数量", required = true, dataType = "long", paramType =
                    "query", defaultValue = "1")
    })
    @PutMapping("sell/{id}")
    public String sell(@PathVariable(value = "id") String id,
                       @RequestParam(required = false, defaultValue = "1") Integer count) {
        boolean sell = skuService.sell(id, count);
        if (!sell) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        return new ReturnBean().toJsonString();
    }

}

