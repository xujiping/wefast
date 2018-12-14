package com.mall.goods.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author xujiping
 * @since 2018-06-14
 */
public class MallGoods extends Model<MallGoods> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品分类ID
     */
    private Integer categoryId;
    /**
     * 卖家编号：默认0，表示自有商品
     */
    private Long sellerId;
    /**
     * SPU销量
     */
    private Long spu;
    /**
     * 评论数
     */
    private Long commentCount;


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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MallGoods{" +
        "id=" + id +
        ", name=" + name +
        ", categoryId=" + categoryId +
        ", sellerId=" + sellerId +
        ", spu=" + spu +
        ", commentCount=" + commentCount +
        "}";
    }
}
