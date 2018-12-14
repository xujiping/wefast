package com.rb.cms.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 支付宝授权码表
 * </p>
 *
 * @author xujiping
 * @since 2018-11-15
 */
public class AlipayToken extends Model<AlipayToken> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端用户ID
     */
    private String clientListenId;
    /**
     * 授权码
     */
    @TableField("authCode")
    private String authCode;
    /**
     * 支付宝用户的唯一userId
     */
    private String userId;
    /**
     * 访问令牌
     */
    private String accessToken;
    /**
     * 访问令牌的有效时间，单位：秒
     */
    private String expiresIn;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 刷新令牌的有效时间，单位：秒
     */
    private String reExpiresIn;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date updateTime;
    /**
     * 超时日期
     */
    private Date expireTime;


    public String getClientListenId() {
        return clientListenId;
    }

    public void setClientListenId(String clientListenId) {
        this.clientListenId = clientListenId;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getReExpiresIn() {
        return reExpiresIn;
    }

    public void setReExpiresIn(String reExpiresIn) {
        this.reExpiresIn = reExpiresIn;
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

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.clientListenId;
    }

    @Override
    public String toString() {
        return "AlipayToken{" +
        "clientListenId=" + clientListenId +
        ", authCode=" + authCode +
        ", userId=" + userId +
        ", accessToken=" + accessToken +
        ", expiresIn=" + expiresIn +
        ", refreshToken=" + refreshToken +
        ", reExpiresIn=" + reExpiresIn +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", expireTime=" + expireTime +
        "}";
    }
}
