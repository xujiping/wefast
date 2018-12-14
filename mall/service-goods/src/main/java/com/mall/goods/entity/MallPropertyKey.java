package com.mall.goods.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 属性名表
 * </p>
 *
 * @author xujiping
 * @since 2018-06-14
 */
public class MallPropertyKey extends Model<MallPropertyKey> {

    private static final long serialVersionUID = 1L;

    /**
     * 属性ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 属性名
     */
    private String name;
    /**
     * 商品分类编号
     */
    private Integer categoryId;
    /**
     * 父属性ID
     */
    private Integer pid;

    public MallPropertyKey() {
    }

    public MallPropertyKey(Integer id, String name, Integer categoryId, Integer pid) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.pid = pid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MallPropertyKey{" +
        "id=" + id +
        ", name=" + name +
        ", categoryId=" + categoryId +
        ", pid=" + pid +
        "}";
    }
}
