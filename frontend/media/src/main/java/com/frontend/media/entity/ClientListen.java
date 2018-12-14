package com.frontend.media.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 资源收听者
 * </p>
 *
 * @author xujiping
 * @since 2018-08-16
 */
public class ClientListen extends Model<ClientListen> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 用户账户名
     */
    private String username;
    /**
     * 用户账户密码
     */
    private String password;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 身份证号
     */
    private String card;
    /**
     * 联系邮箱
     */
    private String email;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 性别：0未知 1男 2女
     */
    private Integer sex;
    /**
     * 头像全路径（包含绝对路径、头像名称、扩展名）
     */
    private String headUrl;
    /**
     * 所在地
     */
    private String location;
    /**
     * 其他联系方式
     */
    private String contact;
    /**
     * 是否同意注册协议（同意、不同意）
     */
    private String agree;
    /**
     * 包含领域
     */
    private String field;
    /**
     * 状态（normal：正常、audit：待审核、pass：通过、failed：未通过、block：冻结）
     */
    private String stat;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
    private String subField;
    private Boolean isactive;
    /**
     * 邀请码
     */
    private String invitationCode;

    /**
     * 组织类型
     */
    private String originType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
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

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getSubField() {
        return subField;
    }

    public void setSubField(String subField) {
        this.subField = subField;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getOriginType() {
        return originType;
    }

    public void setOriginType(String originType) {
        this.originType = originType;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ClientListen{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", card='" + card + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", sex=" + sex +
                ", headUrl='" + headUrl + '\'' +
                ", location='" + location + '\'' +
                ", contact='" + contact + '\'' +
                ", agree='" + agree + '\'' +
                ", field='" + field + '\'' +
                ", stat='" + stat + '\'' +
                ", createTime=" + createTime +
                ", lastLoginTime=" + lastLoginTime +
                ", subField='" + subField + '\'' +
                ", isactive=" + isactive +
                ", invitationCode='" + invitationCode + '\'' +
                ", originType='" + originType + '\'' +
                '}';
    }
}
