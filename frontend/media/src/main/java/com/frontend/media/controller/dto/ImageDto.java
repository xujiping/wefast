package com.frontend.media.controller.dto;

import java.util.Date;

/**
 * @author xujiping
 * @date 2018/10/19 17:26
 */
public class ImageDto {

    private Long id;
    /**
     * 图集ID
     */
    private Long atlasId;
    /**
     * 图片地址
     */
    private String imgUrl;
    /**
     * 文字描述
     */
    private String content;
    /**
     * 排序
     */
    private Integer seq;
    /**
     * 状态（normal：正常、audit：待审核、pass：通过、failed：未通过、block：冻结）
     */
    private String stat;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAtlasId() {
        return atlasId;
    }

    public void setAtlasId(Long atlasId) {
        this.atlasId = atlasId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
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

    @Override
    public String toString() {
        return "ImageDto{" +
                "id=" + id +
                ", atlasId=" + atlasId +
                ", imgUrl='" + imgUrl + '\'' +
                ", content='" + content + '\'' +
                ", seq=" + seq +
                ", stat='" + stat + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
