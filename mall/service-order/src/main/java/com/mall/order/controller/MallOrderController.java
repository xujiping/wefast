package com.mall.order.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.base.constant.Constants;
import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.common.base.util.DateTimeUtil;
import com.common.base.util.OrderUtil;
import com.mall.order.entity.MallOrder;
import com.mall.order.entity.MallOrderDetail;
import com.mall.order.mapper.MallOrderMapper;
import com.mall.order.service.MallOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-06-26
 */
@RestController
@RequestMapping("/mallOrder")
@Api(tags = "订单接口")
public class MallOrderController {

    @Value("${serverno}")
    private String serverNo;

    @Autowired
    private MallOrderService orderService;

    @ApiOperation(value = "新增订单", notes = "创建新订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId", value = "商店ID", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "goodsJsonStr", value = "商品信息列表JSON字符串：{\"goodsId\":\"ID\",\"goodsName\":\"名称\"," +
                    "\"goodsPrice\":\"价格\",\"goodsMarque\":\"型号\",\"goodsStoreBarcode\":\"条码\",\"goodsSku\":\"型号信息\"," +
                    "\"discountRate\":\"折扣比例\",\"discountAmount\":\"折扣金额\",\"number\":\"购买数量\",\"subtotal\":\"小计金额\"," +
                    "\"isGoodsExists\":\"商品是否有效\",\"remark\":\"客户商品备注\"}", required = true, dataType = "String",
                    paramType = "query"),
            @ApiImplicitParam(name = "logisticsFee", value = "运费金额", required = true, dataType = "BigDecimal",
                    paramType = "query"),
            @ApiImplicitParam(name = "isUnpackingInspection", value = "是否开箱验货", required = true, dataType = "int",
                    paramType = "query"),
            @ApiImplicitParam(name = "isInvoice", value = "是否开票", required = true, dataType = "int", paramType =
                    "query"),
            @ApiImplicitParam(name = "addressId", value = "收货地址ID", required = true, dataType = "long", paramType =
                    "query"),
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "long", paramType =
                    "query"),
            @ApiImplicitParam(name = "userRemarks", value = "用户备注", required = true, dataType = "String", paramType =
                    "query"),
            @ApiImplicitParam(name = "orderSettlementStatus", value = "订单结算状态", required = true, dataType = "int",
                    paramType = "query"),
            @ApiImplicitParam(name = "coin", value = "抵扣的零钱", required = false, dataType = "BigDecimal",
                    paramType = "query")
    })
    @PostMapping("order")
    public String add(@RequestParam Long shopId,
                      @RequestParam String goodsJsonStr,
                      @RequestParam BigDecimal logisticsFee,
                      @RequestParam Integer isUnpackingInspection,
                      @RequestParam Integer isInvoice,
                      @RequestParam Long addressId,
                      @RequestParam Long userId,
                      @RequestParam String userRemarks,
                      @RequestParam Integer orderSettlementStatus,
                      @RequestParam(required = false) BigDecimal coin) {
        //商品总价
        double productAmountTotal = 0;
        //订单总价
        double orderAmountTotal = 0;
        // 生成订单号
        String orderNo = OrderUtil.getOrderNoByUUID(serverNo);
        //商品详情列表
        List<MallOrderDetail> orderDetailList = new ArrayList<>();
        JSONArray goodsJson = (JSONArray) JSON.parse(goodsJsonStr);
        for (Object object : goodsJson) {
            JSONObject jsonObject = (JSONObject) object;
            MallOrderDetail orderDetail = new MallOrderDetail();
            orderDetail.setGoodsSkuId(jsonObject.getLong("goodsSkuId"));
            orderDetail.setGoodsName(jsonObject.getString("goodsName"));
            BigDecimal goodsPrice = jsonObject.getBigDecimal("goodsPrice");
            orderDetail.setGoodsPrice(goodsPrice);
            orderDetail.setGoodsMarque(jsonObject.getString("goodsMarque"));
            orderDetail.setGoodsStoreBarcode(jsonObject.getString("goodsStoreBarcode"));
            orderDetail.setGoodsSku(jsonObject.getString("goodsSku"));
            orderDetail.setDiscountRate(jsonObject.getDouble("discountRate"));
            orderDetail.setDiscountAmount(jsonObject.getBigDecimal("discountAmount"));
            Integer number = jsonObject.getInteger("number");
            orderDetail.setNumber(number);
            BigDecimal subtotal = jsonObject.getBigDecimal("subtotal");
            orderDetail.setSubtotal(subtotal);
            orderDetail.setIsGoodsExists(jsonObject.getInteger("isGoodsExists"));
            orderDetail.setRemark(jsonObject.getString("remark"));
            orderDetailList.add(orderDetail);
            orderAmountTotal += subtotal.doubleValue();
            productAmountTotal += number * goodsPrice.doubleValue();
        }
        //创建订单
        MallOrder order = new MallOrder();
        if (coin != null && coin.doubleValue() > 0){
            //抵扣零钱
            orderAmountTotal -= coin.doubleValue();
            order.setCoin(coin);
        }
        Date date = new Date();
        order.setOrderNo(orderNo);
        order.setShopId(shopId);
        order.setOrderStatus(Constants.ORDER_STATUS_NOT_PAY);
        order.setProductCount(orderDetailList.size());
        order.setProductAmountTotal(new BigDecimal(productAmountTotal));
        order.setOrderAmountTotal(new BigDecimal(orderAmountTotal));
        order.setLogisticsFee(logisticsFee);
        order.setIsUnpackingInspection(isUnpackingInspection);
        order.setIsInvoice(isInvoice);
        // TODO 如果收货地址被修改则地址改变，应把地址信息加入订单表中
        order.setAddressId(addressId);
        order.setCreateTime(date);
        order.setUserId(userId);
        order.setUserRemarks(userRemarks);
        order.setOrderSettlementStatus(orderSettlementStatus);
        order.setOrderSettlementTime(date);
        boolean insert = order.insert();
        if (!insert) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        //订单创建成功，查询订单信息
        MallOrder mallOrder = orderService.selectByOrderNo(orderNo);
        Long orderId = mallOrder.getId();
        for (MallOrderDetail orderDetail : orderDetailList) {
            orderDetail.setOrderId(orderId);
            boolean insert1 = orderDetail.insert();
            if (!insert1) {
                throw new BusinessException(ReturnCode.FAIL);
            }
        }
        return new ReturnBean(mallOrder).toJsonString();
    }

    @ApiOperation(value = "更新订单", notes = "更新订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, dataType = "long", paramType =
                    "path"),
            @ApiImplicitParam(name = "orderlogisticsId", value = "订单物流ID", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "payChannel", value = "支付渠道", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "outTradeNo", value = "订单支付单号", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "payTime", value = "支付时间", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "deliveryTime", value = "发货时间", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "orderSettlementTime", value = "订单结算时间", dataType = "String", paramType = "query")
    })
    @PutMapping("order/{orderId}")
    public String update(@PathVariable(value = "orderId") Long orderId,
                         @RequestParam(required = false) Long orderlogisticsId,
                         @RequestParam(required = false) String payChannel,
                         @RequestParam(required = false) String outTradeNo,
                         @RequestParam(required = false) String payTime,
                         @RequestParam(required = false) String deliveryTime,
                         @RequestParam(required = false) String orderSettlementTime) {
        MallOrder mallOrder = orderService.selectById(orderId);
        if (mallOrder == null) {

            throw new BusinessException(ReturnCode.NULL_INFO);
        }
        if (orderlogisticsId != null){
            mallOrder.setOrderlogisticsId(orderlogisticsId);
        }
        if (StringUtils.isNotEmpty(payChannel)) {
            mallOrder.setPayChannel(payChannel);
        }
        if (StringUtils.isNotEmpty(outTradeNo)) {
            mallOrder.setOutTradeNo(outTradeNo);
        }
        if (payTime != null) {
            mallOrder.setPayTime(DateTimeUtil.strToDate(payTime));
        }
        if (deliveryTime != null) {
            mallOrder.setDeliveryTime(DateTimeUtil.strToDate(deliveryTime));
        }
        if (orderSettlementTime != null) {
            mallOrder.setOrderSettlementTime(DateTimeUtil.strToDate(orderSettlementTime));
        }
        boolean update = mallOrder.updateById();
        if (!update) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        return new ReturnBean().toJsonString();
    }

}

