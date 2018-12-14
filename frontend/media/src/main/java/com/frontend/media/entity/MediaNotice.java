package com.frontend.media.entity;

import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 自媒体平台公告
 * </p>
 *
 * @author xujiping
 * @since 2018-09-03
 */
public class MediaNotice extends Model<MediaNotice> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 标题
     */
    private String title;
    private String detail;
    /**
     * 热门
     */
    private Boolean isHot;
    /**
     * 最新
     */
    private Boolean isNew;
    /**
     * 发布日期
     */
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Boolean getHot() {
        return isHot;
    }

    public void setHot(Boolean isHot) {
        this.isHot = isHot;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MediaNotice{" +
        "id=" + id +
        ", title=" + title +
        ", detail=" + detail +
        ", isHot=" + isHot +
        ", isNew=" + isNew +
        ", createTime=" + createTime +
        "}";
    }
}
