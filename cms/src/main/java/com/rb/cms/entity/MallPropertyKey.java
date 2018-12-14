package com.rb.cms.entity;

import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 属性名表
 * </p>
 *
 * @author xujiping
 * @since 2018-06-19
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
        return "MallPropertyKey{" +
        "id=" + id +
        ", name=" + name +
        ", categoryId=" + categoryId +
        ", pid=" + pid +
        ", createTime=" + createTime +
        ", createBy=" + createBy +
        ", updateTime=" + updateTime +
        ", updateBy=" + updateBy +
        ", status=" + status +
        "}";
    }
}
