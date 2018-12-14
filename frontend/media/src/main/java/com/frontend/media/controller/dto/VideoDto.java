package com.frontend.media.controller.dto;

import java.util.Date;

/**
 * @author xujiping
 * @date 2018/11/7 16:04
 */
public class VideoDto {

    private Long id;
    /**
     * 视频标题
     */
    private String title;
    /**
     * 视频地址
     */
    private String url;
    /**
     * 创建日期
     */
    private String createTime;
    /**
     * 状态
     */
    private String stat;
    /**
     * 播放量
     */
    private Long playCount;
    /**
     * 点赞量
     */
    private Long likeCount;

    /**
     * 封面
     */
    private String coverUrl;

    /**
     * 分类
     */
    private Integer categoryId;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public Long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Long playCount) {
        this.playCount = playCount;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "VideoDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", createTime=" + createTime +
                ", stat='" + stat + '\'' +
                ", playCount=" + playCount +
                ", likeCount=" + likeCount +
                ", coverUrl='" + coverUrl + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
