package com.mall.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 订单商品详情表
 * </p>
 *
 * @author xujiping
 * @since 2018-06-26
 */
public class MallOrderDetail extends Model<MallOrderDetail> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long orderDetailId;
    /**
     * 订单编号
     */
    private Long orderId;
    /**
     * 商品编号
     */
    private Long goodsSkuId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;
    /**
     * 商品型号
     */
    private String goodsMarque;
    /**
     * 商品条码
     */
    private String goodsStoreBarcode;
    /**
     * 商品型号信息
     */
    private String goodsSku;
    /**
     * 折扣比例
     */
    private Double discountRate;
    /**
     * 折扣金额
     */
    private BigDecimal discountAmount;
    /**
     * 购买数量
     */
    private Integer number;
    /**
     * 小计金额
     */
    private BigDecimal subtotal;
    /**
     * 商品是否有效
     */
    private Integer isGoodsExists;
    /**
     * 客户商品备注
     */
    private String remark;


    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsSkuId() {
        return goodsSkuId;
    }

    public void setGoodsSkuId(Long goodsSkuId) {
        this.goodsSkuId = goodsSkuId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsMarque() {
        return goodsMarque;
    }

    public void setGoodsMarque(String goodsMarque) {
        this.goodsMarque = goodsMarque;
    }

    public String getGoodsStoreBarcode() {
        return goodsStoreBarcode;
    }

    public void setGoodsStoreBarcode(String goodsStoreBarcode) {
        this.goodsStoreBarcode = goodsStoreBarcode;
    }

    public String getGoodsSku() {
        return goodsSku;
    }

    public void setGoodsSku(String goodsSku) {
        this.goodsSku = goodsSku;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getIsGoodsExists() {
        return isGoodsExists;
    }

    public void setIsGoodsExists(Integer isGoodsExists) {
        this.isGoodsExists = isGoodsExists;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.orderDetailId;
    }

    @Override
    public String toString() {
        return "MallOrderDetail{" +
        "orderDetailId=" + orderDetailId +
        ", orderId=" + orderId +
        ", goodsSkuId=" + goodsSkuId +
        ", goodsName=" + goodsName +
        ", goodsPrice=" + goodsPrice +
        ", goodsMarque=" + goodsMarque +
        ", goodsStoreBarcode=" + goodsStoreBarcode +
        ", goodsSku=" + goodsSku +
        ", discountRate=" + discountRate +
        ", discountAmount=" + discountAmount +
        ", number=" + number +
        ", subtotal=" + subtotal +
        ", isGoodsExists=" + isGoodsExists +
        ", remark=" + remark +
        "}";
    }
}
