package com.rb.cms.entity;

import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 内容访问记录表
 * </p>
 *
 * @author xujiping
 * @since 2018-11-10
 */
public class ContentStatistical extends Model<ContentStatistical> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户ID
     */
    private String clientListenId;
    /**
     * 内容ID
     */
    private Long resContentId;
    /**
     * 使用内容时长（秒）
     */
    private Integer useTime;
    /**
     * 内容总时长（秒）
     */
    private Integer contentTime;
    /**
     * IP
     */
    private String ip;
    /**
     * 访问时间
     */
    private Date createTime;
    /**
     * 状态：1正常
     */
    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientListenId() {
        return clientListenId;
    }

    public void setClientListenId(String clientListenId) {
        this.clientListenId = clientListenId;
    }

    public Long getResContentId() {
        return resContentId;
    }

    public void setResContentId(Long resContentId) {
        this.resContentId = resContentId;
    }

    public Integer getUseTime() {
        return useTime;
    }

    public void setUseTime(Integer useTime) {
        this.useTime = useTime;
    }

    public Integer getContentTime() {
        return contentTime;
    }

    public void setContentTime(Integer contentTime) {
        this.contentTime = contentTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
        return "ContentStatistical{" +
        "id=" + id +
        ", clientListenId=" + clientListenId +
        ", resContentId=" + resContentId +
        ", useTime=" + useTime +
        ", contentTime=" + contentTime +
        ", ip=" + ip +
        ", createTime=" + createTime +
        ", status=" + status +
        "}";
    }
}
