package com.rb.cms.entity.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xujiping
 * @date 2018/6/22 17:43
 */
public class SkuDto {

    private String id;
    /**
     * 商品编号
     */
    private Long goodsId;

    private String goodsName;
    /**
     * SKU属性
     */
    private String sku;

    private String skuName;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 库存
     */
    private Long inventory;
    /**
     * 销量
     */
    private Long salesVolume;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 最后更新时间
     */
    private Date updateTime;
    /**
     * 最后更新者
     */
    private String updateBy;
    /**
     * 状态：0不可用 1正常
     */
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getInventory() {
        return inventory;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }

    public Long getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Long salesVolume) {
        this.salesVolume = salesVolume;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SkuDto{" +
                "id='" + id + '\'' +
                ", goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", sku='" + sku + '\'' +
                ", skuName='" + skuName + '\'' +
                ", price=" + price +
                ", inventory=" + inventory +
                ", salesVolume=" + salesVolume +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                ", status=" + status +
                '}';
    }
}
