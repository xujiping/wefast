package com.rb.cms.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xujiping
 * @since 2018-06-19
 */
public class MallGoodsExitProperty extends Model<MallGoodsExitProperty> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    private Long goodsId;
    /**
     * 商品具有的属性值ID
     */
    private String properties;


    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "MallGoodsExitProperty{" +
        "goodsId=" + goodsId +
        ", properties=" + properties +
        "}";
    }
}
