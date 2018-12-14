package com.rb.cms.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 商品详情表
 * </p>
 *
 * @author xujiping
 * @since 2018-06-19
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
        return "MallGoodsDetail{" +
        "id=" + id +
        ", name=" + name +
        ", banner=" + banner +
        ", description=" + description +
        ", createTime=" + createTime +
        ", createBy=" + createBy +
        ", updateTime=" + updateTime +
        ", updateBy=" + updateBy +
        ", status=" + status +
        "}";
    }
}
