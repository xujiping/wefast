package com.rb.cms.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * SKU表（库存表）
 * </p>
 *
 * @author xujiping
 * @since 2018-06-19
 */
public class MallSku extends Model<MallSku> {

    private static final long serialVersionUID = 1L;

    /**
     * SKU编号
     */
    private String id;
    /**
     * 商品编号
     */
    private Long goodsId;
    /**
     * SKU属性
     */
    private String sku;
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MallSku{" +
        "id=" + id +
        ", goodsId=" + goodsId +
        ", sku=" + sku +
        ", price=" + price +
        ", inventory=" + inventory +
        ", salesVolume=" + salesVolume +
        ", createTime=" + createTime +
        ", createBy=" + createBy +
        ", updateTime=" + updateTime +
        ", updateBy=" + updateBy +
        ", status=" + status +
        "}";
    }
}
