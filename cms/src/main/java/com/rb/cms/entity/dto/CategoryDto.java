package com.rb.cms.entity.dto;

import java.util.Date;

/**
 * @author xujiping
 * @date 2018/6/21 15:51
 */
public class CategoryDto {

    private Integer id;
    private String name;
    private Integer pid;
    private Integer level;
    private Date createTime;
    private String createBy;
    private Date updateTime;
    private String updateBy;
    private Integer status;
    private Integer firstSelect;
    private Integer secondSelect;

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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public Integer getFirstSelect() {
        return firstSelect;
    }

    public void setFirstSelect(Integer firstSelect) {
        this.firstSelect = firstSelect;
    }

    public Integer getSecondSelect() {
        return secondSelect;
    }

    public void setSecondSelect(Integer secondSelect) {
        this.secondSelect = secondSelect;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", level=" + level +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                ", status=" + status +
                ", firstSelect=" + firstSelect +
                ", secondSelect=" + secondSelect +
                '}';
    }
}
