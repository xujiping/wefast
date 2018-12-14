package com.cloud.oauth.result;

import java.io.Serializable;

/**
 * @author xujiping
 * @date 2018/6/8 15:42
 */
public class OauthCode implements Serializable {

    private static final long serialVersionUID = -1162012356270919784L;

    /**
     * 授权码
     */
    private String code;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 重定向URL
     */
    private String redirectUri;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    @Override
    public String toString() {
        return "OauthCode{" +
                "code='" + code + '\'' +
                ", clientId='" + clientId + '\'' +
                ", userId='" + userId + '\'' +
                ", redirectUri='" + redirectUri + '\'' +
                '}';
    }
}
