package com.mall.goods.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 商品详情表
 * </p>
 *
 * @author xujiping
 * @since 2018-06-15
 */
public class MallGoodsDetail extends Model<MallGoodsDetail> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品轮播图，字符串数组
     */
    private String banner;
    /**
     * 商品详情
     */
    private String description;


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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MallGoodsDetail{" +
        "id=" + id +
        ", name=" + name +
        ", banner=" + banner +
        ", description=" + description +
        "}";
    }
}
