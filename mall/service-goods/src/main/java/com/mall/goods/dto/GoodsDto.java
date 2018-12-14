package com.mall.goods.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品
 * @author xujiping
 * @date 2018/6/15 18:08
 */
public class GoodsDto implements Serializable {

    private static final long serialVersionUID = -6129531631128008191L;

    private Long id;
    private String name;
    private Integer categoryId;
    private Long sellerId;
    private Long spu;
    private Long commentCount;
    private String banner;
    private String description;
    private double price;
    /**
     * 原价
     */
    private double oldPrice;
    /**
     * 用户零钱
     */
    private double userCoin;
    /**
     * 可以支付的零钱
     */
    private Double needCoin;
    /**
     * 需要支付的钱
     */
    private Double needPay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getSpu() {
        return spu;
    }

    public void setSpu(Long spu) {
        this.spu = spu;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public double getUserCoin() {
        return userCoin;
    }

    public void setUserCoin(double userCoin) {
        this.userCoin = userCoin;
    }

    public Double getNeedCoin() {
        return needCoin;
    }

    public void setNeedCoin(Double needCoin) {
        this.needCoin = needCoin;
    }

    public Double getNeedPay() {
        return needPay;
    }

    public void setNeedPay(Double needPay) {
        this.needPay = needPay;
    }

    @Override
    public String toString() {
        return "GoodsDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", sellerId=" + sellerId +
                ", spu=" + spu +
                ", commentCount=" + commentCount +
                ", banner='" + banner + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", oldPrice=" + oldPrice +
                ", userCoin=" + userCoin +
                ", needCoin=" + needCoin +
                ", needPay=" + needPay +
                '}';
    }
}
