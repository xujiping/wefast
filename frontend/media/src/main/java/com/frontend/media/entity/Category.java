package com.frontend.media.entity;

import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 分类表
 * </p>
 *
 * @author xujiping
 * @since 2018-10-17
 */
public class Category extends Model<Category> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 级别
     */
    private Integer level;
    /**
     * 父ID
     */
    private Integer parentId;
    /**
     * 主体  "img"
     */
    private String subject;
    /**
     * 排序
     */
    private Integer seq;
    /**
     * 图标
     */
    private String imgUrl;
    /**
     * 编码值
     */
    private String code;
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Category{" +
        "id=" + id +
        ", name=" + name +
        ", level=" + level +
        ", parentId=" + parentId +
        ", subject=" + subject +
        ", seq=" + seq +
        ", imgUrl=" + imgUrl +
        ", code=" + code +
        ", stat=" + stat +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
