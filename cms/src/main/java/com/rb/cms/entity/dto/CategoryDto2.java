package com.rb.cms.entity.dto;

import java.io.Serializable;

/**
 * 视频/图集类别
 *
 * @author xujiping
 * @date 2018/12/11 15:29
 */
public class CategoryDto2 implements Serializable {

    private static final long serialVersionUID = 9058504080274874605L;
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
     * 父类别名称
     */
    private String parent;
    /**
     * 主体  "atlas","all"
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

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
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

    @Override
    public String toString() {
        return "CategoryDto2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", parent='" + parent + '\'' +
                ", subject='" + subject + '\'' +
                ", seq=" + seq +
                ", imgUrl='" + imgUrl + '\'' +
                ", code='" + code + '\'' +
                ", stat='" + stat + '\'' +
                '}';
    }
}
