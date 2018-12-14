package com.rb.cms.entity;

import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author xujiping
 * @since 2018-11-09
 */
public class CmsPermission extends Model<CmsPermission> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 所属系统
     */
    private Integer systemId;
    /**
     * 所属上级
     */
    private Integer pid;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型(1:目录,2:菜单,3:按钮)
     */
    private Integer type;
    /**
     * 权限值
     */
    private String permissionValue;
    /**
     * 路径
     */
    private String uri;
    /**
     * 图标
     */
    private String icon;
    /**
     * 状态(0:禁止,1:正常)
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date ctime;
    /**
     * 排序
     */
    private Long orders;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(String permissionValue) {
        this.permissionValue = permissionValue;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Long getOrders() {
        return orders;
    }

    public void setOrders(Long orders) {
        this.orders = orders;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CmsPermission{" +
        "id=" + id +
        ", systemId=" + systemId +
        ", pid=" + pid +
        ", name=" + name +
        ", type=" + type +
        ", permissionValue=" + permissionValue +
        ", uri=" + uri +
        ", icon=" + icon +
        ", status=" + status +
        ", ctime=" + ctime +
        ", orders=" + orders +
        "}";
    }
}
