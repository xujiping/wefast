package com.frontend.media.entity;

import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 视频表
 * </p>
 *
 * @author xujiping
 * @since 2018-10-27
 */
public class Video extends Model<Video> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 发布者ID
     */
    private String clientListenId;
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
    private Date createTime;
    /**
     * 审核时间
     */
    private Date updateTime;
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

    public String getClientListenId() {
        return clientListenId;
    }

    public void setClientListenId(String clientListenId) {
        this.clientListenId = clientListenId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
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
        return "Video{" +
                "id=" + id +
                ", clientListenId='" + clientListenId + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", stat='" + stat + '\'' +
                ", playCount=" + playCount +
                ", likeCount=" + likeCount +
                ", coverUrl='" + coverUrl + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
