package com.demo.oauth.oauth;

import org.apache.shiro.authc.AuthenticationException;

/**
 * @author xujiping
 * @date 2018/6/9 10:56
 */
public class OAuth2AuthenticationException extends AuthenticationException {

    public OAuth2AuthenticationException(Throwable cause) {
        super(cause);
    }
}
