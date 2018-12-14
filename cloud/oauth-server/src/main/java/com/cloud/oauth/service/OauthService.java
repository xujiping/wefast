package com.cloud.oauth.service;

/**
 * @author xujiping
 * @date 2018/6/8 16:46
 */
public interface OauthService {

    /**
     * 添加 auth code
     * @param authCode
     * @param username
     */
    void addAuthCode(String authCode, String username);

    /**
     * 验证auth code是否有效
     * @param authCode
     * @return
     */
    boolean checkAuthCode(String authCode);

    /**
     * 添加 access token
     * @param accessToken
     * @param username
     */
    void addAccessToken(String accessToken, String username);

    /**
     * 根据auth code获取用户名
     * @param authCode
     * @return
     */
    String getUsernameByAuthCode(String authCode);

    /**
     * 获取auth code过期时间
     * @return
     */
    long getCodeExpireIn();

    /**
     * 验证access token是否有效
     * @param accessToken
     * @return
     */
    boolean checkAccessToken(String accessToken);

    /**
     * 根据access token获取用户名
     * @param accessToken
     * @return
     */
    String getUsernameByAccessToken(String accessToken);

    /**
     * access token 过期时间
     * @return
     */
    long getTokenExpireIn();

    /**
     * 检查客户端id是否存在
     * @param clientId
     * @return
     */
    boolean checkClientId(String clientId);

    /**
     * 检查客户端安全KEY是否存在
     * @param clientSecret
     * @return
     */
    boolean checkClientSecret(String clientSecret);
}
