package com.rb.cms.entity.dto;

import java.util.Arrays;
import java.util.Date;

/**
 * @author xujiping
 * @date 2018/6/21 16:40
 */
public class PropertyDto {

    private Integer id;
    /**
     * 属性名
     */
    private String name;

    /**
     * 属性值
     */
    private String[] values;

    /**
     * 商品分类编号
     */
    private Integer categoryId;
    /**
     * 商品分类名称
     */
    private String categoryName;

    /**
     * 父属性ID
     */
    private Integer pid;
    /**
     * 父属性名称
     */
    private String pName;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
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

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    @Override
    public String toString() {
        return "PropertyDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", values=" + Arrays.toString(values) +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", pid=" + pid +
                ", pName='" + pName + '\'' +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                ", status=" + status +
                '}';
    }
}
