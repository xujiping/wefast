package com.mall.gelunbu.entity;

import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 福利详情表
 * </p>
 *
 * @author xujiping
 * @since 2018-06-26
 */
public class WealDetail extends Model<WealDetail> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 简介
     */
    private String introduce;
    /**
     * 收支
     */
    private Integer income;
    /**
     * 金额
     */
    private Double size;
    /**
     * 正负表示  1：金币 2：零钱 3：福豆
     */
    private Integer type;
    /**
     * 用户ID
     */
    private String clientListenId;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 状态
     */
    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getClientListenId() {
        return clientListenId;
    }

    public void setClientListenId(String clientListenId) {
        this.clientListenId = clientListenId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        return "WealDetail{" +
        "id=" + id +
        ", title=" + title +
        ", introduce=" + introduce +
        ", income=" + income +
        ", size=" + size +
        ", type=" + type +
        ", clientListenId=" + clientListenId +
        ", createTime=" + createTime +
        ", status=" + status +
        "}";
    }
}
