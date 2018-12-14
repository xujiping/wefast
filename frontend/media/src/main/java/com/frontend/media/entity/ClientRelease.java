package com.frontend.media.entity;

import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 专辑表
 * </p>
 *
 * @author xujiping
 * @since 2018-08-25
 */
public class ClientRelease extends Model<ClientRelease> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 头条号账户名
     */
    private String username;
    /**
     * 头条号账户密码
     */
    private String password;
    /**
     * 类型（个人、媒体、国家机构、企业、其他组织）
     */
    private String type;
    /**
     * 发布号名称
     */
    private String name;
    /**
     * 头条号介绍
     */
    private String introduce;
    /**
     * 头像全路径（包含绝对路径、头像名称、扩展名）
     */
    private String headUrl;
    /**
     * 辅助材料
     */
    private String auxiliary;
    /**
     * 所在地
     */
    private String location;
    /**
     * 领域
     */
    private String field;
    /**
     * 领域子类
     */
    private String subField;
    /**
     * 身份证号
     */
    private String card;
    /**
     * 其他资质（json格式存储资质url）
     */
    private String other;
    /**
     * 联系邮箱
     */
    private String email;
    /**
     * 其他联系方式
     */
    private String contact;
    /**
     * 是否同意注册协议（同意、不同意）
     */
    private String agree;
    /**
     * 状态（normal：正常、audit：待审核、pass：通过、failed：未通过、block：冻结）
     */
    private String stat;
    /**
     * 提交时间
     */
    private Date submitTime;
    /**
     * 通过时间
     */
    private Date passTime;
    private Boolean isactive;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
    /**
     * 1精品 0普通
     */
    private Integer boutique;
    /**
     * 费用
     */
    private BigDecimal cost;
    /**
     * 0不需要会员
     */
    private Integer vip;
    /**
     * 被关注数
     */
    private Long follow;
    /**
     * 是否默认的关注专辑
     */
    private Boolean defaultFollow;
    /**
     * 所属用户
     */
    private String clientListenId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getAuxiliary() {
        return auxiliary;
    }

    public void setAuxiliary(String auxiliary) {
        this.auxiliary = auxiliary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getSubField() {
        return subField;
    }

    public void setSubField(String subField) {
        this.subField = subField;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getPassTime() {
        return passTime;
    }

    public void setPassTime(Date passTime) {
        this.passTime = passTime;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getBoutique() {
        return boutique;
    }

    public void setBoutique(Integer boutique) {
        this.boutique = boutique;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getVip() {
        return vip;
    }

    public void setVip(Integer vip) {
        this.vip = vip;
    }

    public Long getFollow() {
        return follow;
    }

    public void setFollow(Long follow) {
        this.follow = follow;
    }

    public Boolean getDefaultFollow() {
        return defaultFollow;
    }

    public void setDefaultFollow(Boolean defaultFollow) {
        this.defaultFollow = defaultFollow;
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
        return "ClientRelease{" +
        "id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", type=" + type +
        ", name=" + name +
        ", introduce=" + introduce +
        ", headUrl=" + headUrl +
        ", auxiliary=" + auxiliary +
        ", location=" + location +
        ", field=" + field +
        ", subField=" + subField +
        ", card=" + card +
        ", other=" + other +
        ", email=" + email +
        ", contact=" + contact +
        ", agree=" + agree +
        ", stat=" + stat +
        ", submitTime=" + submitTime +
        ", passTime=" + passTime +
        ", isactive=" + isactive +
        ", createdBy=" + createdBy +
        ", createdDate=" + createdDate +
        ", updatedBy=" + updatedBy +
        ", updatedDate=" + updatedDate +
        ", boutique=" + boutique +
        ", cost=" + cost +
        ", vip=" + vip +
        ", follow=" + follow +
        ", defaultFollow=" + defaultFollow +
        ", clientListenId=" + clientListenId +
        "}";
    }
}
