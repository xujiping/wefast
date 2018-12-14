package com.mall.goods.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 属性值表
 * </p>
 *
 * @author xujiping
 * @since 2018-06-14
 */
public class MallPropertyValue extends Model<MallPropertyValue> {

    private static final long serialVersionUID = 1L;

    /**
     * 属性值ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 属性值
     */
    private String value;
    /**
     * 属性名ID
     */
    private Integer propertyKeyId;

    public MallPropertyValue() {
    }

    public MallPropertyValue(Integer id, String value, Integer propertyKeyId) {
        this.id = id;
        this.value = value;
        this.propertyKeyId = propertyKeyId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getPropertyKeyId() {
        return propertyKeyId;
    }

    public void setPropertyKeyId(Integer propertyKeyId) {
        this.propertyKeyId = propertyKeyId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MallPropertyValue{" +
        "id=" + id +
        ", value=" + value +
        ", propertyKeyId=" + propertyKeyId +
        "}";
    }
}
