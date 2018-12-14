package com.mall.goods.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.KeySequence;

/**
 * <p>
 * SKU表（库存表）
 * </p>
 *
 * @author xujiping
 * @since 2018-06-14
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
     * 原价
     */
    private BigDecimal oldPrice;

    /**
     * 库存
     */
    private Long inventory;
    /**
     * 销量
     */
    private Long salesVolume;


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

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MallSku{" +
                "id='" + id + '\'' +
                ", goodsId=" + goodsId +
                ", sku='" + sku + '\'' +
                ", price=" + price +
                ", oldPrice=" + oldPrice +
                ", inventory=" + inventory +
                ", salesVolume=" + salesVolume +
                '}';
    }
}
