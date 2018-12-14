package com.frontend.media.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 发布者发布的资源
 * </p>
 *
 * @author xujiping
 * @since 2018-07-24
 */
public class ResContent extends Model<ResContent> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 发布者id
     */
    private Long clientReleaseId;
    /**
     * 发布号名称
     */
    private String clientReleaseName;
    /**
     * 标题
     */
    private String title;
    /**
     * 简介
     */
    private String summary;
    /**
     * 分类
     */
    private String type;
    /**
     * 标签（最多五个、以英文逗号间隔）
     */
    private String tag;
    /**
     * 音频URL（包含绝对路径、头像名称、扩展名）
     */
    private String contentUrl;
    /**
     * 状态（normal：正常、audit：待审核、pass：通过、failed：未通过、block：冻结）
     */
    private String stat;
    /**
     * 提交时间
     */
    private Date submitTime;
    /**
     * 通过时间
     */
    private Date passTime;
    /**
     * 时间的毫秒数
     */
    private Long seq;
    private Boolean isactive;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
    private String coverImageUrl;

    private String clientListenId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientReleaseId() {
        return clientReleaseId;
    }

    public void setClientReleaseId(Long clientReleaseId) {
        this.clientReleaseId = clientReleaseId;
    }

    public String getClientReleaseName() {
        return clientReleaseName;
    }

    public void setClientReleaseName(String clientReleaseName) {
        this.clientReleaseName = clientReleaseName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getPassTime() {
        return passTime;
    }

    public void setPassTime(Date passTime) {
        this.passTime = passTime;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getClientListenId() {
        return clientListenId;
    }

    public void setClientListenId(String clientListenId) {
        this.clientListenId = clientListenId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ResContent{" +
                "id=" + id +
                ", clientReleaseId=" + clientReleaseId +
                ", clientReleaseName='" + clientReleaseName + '\'' +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", type='" + type + '\'' +
                ", tag='" + tag + '\'' +
                ", contentUrl='" + contentUrl + '\'' +
                ", stat='" + stat + '\'' +
                ", submitTime=" + submitTime +
                ", passTime=" + passTime +
                ", seq=" + seq +
                ", isactive=" + isactive +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedDate=" + updatedDate +
                ", coverImageUrl='" + coverImageUrl + '\'' +
                ", clientListenId='" + clientListenId + '\'' +
                '}';
    }
}
