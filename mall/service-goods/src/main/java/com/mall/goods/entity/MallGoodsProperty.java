package com.mall.goods.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 商品和属性关系表
 * </p>
 *
 * @author xujiping
 * @since 2018-06-14
 */
public class MallGoodsProperty extends Model<MallGoodsProperty> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 商品ID
     */
    private Long goodsId;
    /**
     * 属性名ID
     */
    private Integer propertyKeyId;
    /**
     * 属性值ID
     */
    private Integer propertyValueId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getPropertyKeyId() {
        return propertyKeyId;
    }

    public void setPropertyKeyId(Integer propertyKeyId) {
        this.propertyKeyId = propertyKeyId;
    }

    public Integer getPropertyValueId() {
        return propertyValueId;
    }

    public void setPropertyValueId(Integer propertyValueId) {
        this.propertyValueId = propertyValueId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MallGoodsProperty{" +
        "id=" + id +
        ", goodsId=" + goodsId +
        ", propertyKeyId=" + propertyKeyId +
        ", propertyValueId=" + propertyValueId +
        "}";
    }
}
