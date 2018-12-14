package com.frontend.media.entity;

import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 图集
 * </p>
 *
 * @author xujiping
 * @since 2018-10-17
 */
public class Atlas extends Model<Atlas> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 分类ID
     */
    private Integer categoryId;
    /**
     * 图片数量
     */
    private Integer imgCount;
    /**
     * 封面ID例如：1,3
     */
    private String coverImgIds;
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

    /**
     * 发布者ID
     */
    private String clientListenId;


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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getImgCount() {
        return imgCount;
    }

    public void setImgCount(Integer imgCount) {
        this.imgCount = imgCount;
    }

    public String getCoverImgIds() {
        return coverImgIds;
    }

    public void setCoverImgIds(String coverImgIds) {
        this.coverImgIds = coverImgIds;
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
        return "Atlas{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", categoryId=" + categoryId +
                ", imgCount=" + imgCount +
                ", coverImgIds='" + coverImgIds + '\'' +
                ", stat='" + stat + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", clientListenId='" + clientListenId + '\'' +
                '}';
    }
}
