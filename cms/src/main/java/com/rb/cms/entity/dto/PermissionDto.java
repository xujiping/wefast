package com.rb.cms.entity.dto;

import java.util.Date;

/**
 * @author xujiping
 * @date 2018/11/9 17:48
 */
public class PermissionDto {

    private Integer id;
    /**
     * 所属系统
     */
    private String system;
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
    private String type;
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
    private String status;
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

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
    public String toString() {
        return "PermissionDto{" +
                "id=" + id +
                ", system=" + system +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", permissionValue='" + permissionValue + '\'' +
                ", uri='" + uri + '\'' +
                ", icon='" + icon + '\'' +
                ", status='" + status + '\'' +
                ", ctime=" + ctime +
                ", orders=" + orders +
                '}';
    }
}
