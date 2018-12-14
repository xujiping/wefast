package com.rb.oauth.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author xujiping
 * @date 2018/6/9 10:07
 */
public class Oauth2Token implements AuthenticationToken {
    private String authCode;
    private String principal;

    public Oauth2Token(String authCode) {
        this.authCode = authCode;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public Object getCredentials() {
        return authCode;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }
}
