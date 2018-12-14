package com.frontend.media.controller.dto;

import java.util.Date;

/**
 * @author xujiping
 * @date 2018/8/22 16:18
 */
public class ContentDto {

    private Long id;
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
    private String submitTime;
    /**
     * 通过时间
     */
    private Date passTime;
    /**
     * 时间的毫秒数
     */
    private Long seq;

    private Boolean isactive;

    private long likeCount;

    private long playCount;

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

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
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

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(long playCount) {
        this.playCount = playCount;
    }

    @Override
    public String toString() {
        return "ContentDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", type='" + type + '\'' +
                ", tag='" + tag + '\'' +
                ", contentUrl='" + contentUrl + '\'' +
                ", stat='" + stat + '\'' +
                ", submitTime='" + submitTime + '\'' +
                ", passTime=" + passTime +
                ", seq=" + seq +
                ", isactive=" + isactive +
                ", likeCount=" + likeCount +
                ", playCount=" + playCount +
                '}';
    }
}
