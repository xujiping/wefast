package com.frontend.media.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 领域设置
 * </p>
 *
 * @author xujiping
 * @since 2018-07-26
 */
public class SetField extends Model<SetField> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 领域等级（1、2）
     */
    private Integer level;
    /**
     * 父类id
     */
    private Long parent;
    /**
     * 编码值
     */
    private String code;
    /**
     * 中文值
     */
    private String value;
    /**
     * 显示顺序
     */
    private Integer seq;
    private Boolean isactive;
    /**
     * 分类图标
     */
    private String imageUrl;
    /**
     * 1为启用，0为停用
     */
    private String stat;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SetField{" +
        "id=" + id +
        ", level=" + level +
        ", parent=" + parent +
        ", code=" + code +
        ", value=" + value +
        ", seq=" + seq +
        ", isactive=" + isactive +
        ", imageUrl=" + imageUrl +
        ", stat=" + stat +
        "}";
    }
}
