package com.frontend.media.controller.dto;

/**
 * @author xujiping
 * @date 2018/10/18 10:46
 */
public class AtlasDto {

    private Long id;

    /**
     * 标题
     */
    private String title;
    /**
     * 图片数量
     */
    private Integer imgCount;
    /**
     * 封面
     */
    private String coverImgUrl;
    /**
     * 状态（normal：正常、audit：待审核、pass：通过、failed：未通过、block：冻结）
     */
    private String stat;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 最后更新时间
     */
    private String updateTime;

    /**
     * 浏览数量
     */
    private long checkCount;

    /**
     * 喜欢数量
     */
    private long likeCount;

    /**
     * 举报数量
     */
    private long reportCount;

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

    public Integer getImgCount() {
        return imgCount;
    }

    public void setImgCount(Integer imgCount) {
        this.imgCount = imgCount;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public long getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(long checkCount) {
        this.checkCount = checkCount;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public long getReportCount() {
        return reportCount;
    }

    public void setReportCount(long reportCount) {
        this.reportCount = reportCount;
    }

    @Override
    public String toString() {
        return "AtlasDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imgCount=" + imgCount +
                ", coverImgUrl='" + coverImgUrl + '\'' +
                ", stat='" + stat + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", checkCount=" + checkCount +
                ", likeCount=" + likeCount +
                ", reportCount=" + reportCount +
                '}';
    }
}
