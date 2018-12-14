package com.mall.order.entity;

import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author xujiping
 * @since 2018-06-29
 */
public class MallOrder extends Model<MallOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 订单单号
     */
    private String orderNo;
    /**
     * 商店编号
     */
    private Long shopId;
    /**
     * 订单状态（0不可用,1未付款,2已付款,3已发货,4已签收,5退货申请,6退货中,7已退货,8取消交易）
     */
    private Integer orderStatus;
    /**
     * 商品项目数量
     */
    private Integer productCount;
    /**
     * 商品总价
     */
    private BigDecimal productAmountTotal;
    /**
     * 订单金额
     */
    private BigDecimal orderAmountTotal;
    /**
     * 抵扣的零钱
     */
    private BigDecimal coin;
    /**
     * 运费金额
     */
    private BigDecimal logisticsFee;
    /**
     * 是否开箱验货
     */
    private Integer isUnpackingInspection;
    /**
     * 是否开票
     */
    private Integer isInvoice;
    /**
     * 收货地址编号
     */
    private Long addressId;
    /**
     * 订单物流编号
     */
    private Long orderlogisticsId;
    /**
     * 订单支付渠道
     */
    private String payChannel;
    /**
     * 订单支付单号
     */
    private String outTradeNo;
    /**
     * 下单时间
     */
    private Date createTime;
    /**
     * 付款时间
     */
    private Date payTime;
    /**
     * 发货时间
     */
    private Date deliveryTime;
    /**
     * 客户编号
     */
    private Long userId;
    /**
     * 客户备注
     */
    private String userRemarks;
    /**
     * 订单结算状态
     */
    private Integer orderSettlementStatus;
    /**
     * 订单结算时间
     */
    private Date orderSettlementTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getProductAmountTotal() {
        return productAmountTotal;
    }

    public void setProductAmountTotal(BigDecimal productAmountTotal) {
        this.productAmountTotal = productAmountTotal;
    }

    public BigDecimal getOrderAmountTotal() {
        return orderAmountTotal;
    }

    public void setOrderAmountTotal(BigDecimal orderAmountTotal) {
        this.orderAmountTotal = orderAmountTotal;
    }

    public BigDecimal getCoin() {
        return coin;
    }

    public void setCoin(BigDecimal coin) {
        this.coin = coin;
    }

    public BigDecimal getLogisticsFee() {
        return logisticsFee;
    }

    public void setLogisticsFee(BigDecimal logisticsFee) {
        this.logisticsFee = logisticsFee;
    }

    public Integer getIsUnpackingInspection() {
        return isUnpackingInspection;
    }

    public void setIsUnpackingInspection(Integer isUnpackingInspection) {
        this.isUnpackingInspection = isUnpackingInspection;
    }

    public Integer getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(Integer isInvoice) {
        this.isInvoice = isInvoice;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getOrderlogisticsId() {
        return orderlogisticsId;
    }

    public void setOrderlogisticsId(Long orderlogisticsId) {
        this.orderlogisticsId = orderlogisticsId;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserRemarks() {
        return userRemarks;
    }

    public void setUserRemarks(String userRemarks) {
        this.userRemarks = userRemarks;
    }

    public Integer getOrderSettlementStatus() {
        return orderSettlementStatus;
    }

    public void setOrderSettlementStatus(Integer orderSettlementStatus) {
        this.orderSettlementStatus = orderSettlementStatus;
    }

    public Date getOrderSettlementTime() {
        return orderSettlementTime;
    }

    public void setOrderSettlementTime(Date orderSettlementTime) {
        this.orderSettlementTime = orderSettlementTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MallOrder{" +
        "id=" + id +
        ", orderNo=" + orderNo +
        ", shopId=" + shopId +
        ", orderStatus=" + orderStatus +
        ", productCount=" + productCount +
        ", productAmountTotal=" + productAmountTotal +
        ", orderAmountTotal=" + orderAmountTotal +
        ", coin=" + coin +
        ", logisticsFee=" + logisticsFee +
        ", isUnpackingInspection=" + isUnpackingInspection +
        ", isInvoice=" + isInvoice +
        ", addressId=" + addressId +
        ", orderlogisticsId=" + orderlogisticsId +
        ", payChannel=" + payChannel +
        ", outTradeNo=" + outTradeNo +
        ", createTime=" + createTime +
        ", payTime=" + payTime +
        ", deliveryTime=" + deliveryTime +
        ", userId=" + userId +
        ", userRemarks=" + userRemarks +
        ", orderSettlementStatus=" + orderSettlementStatus +
        ", orderSettlementTime=" + orderSettlementTime +
        "}";
    }
}
