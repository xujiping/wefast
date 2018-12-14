package com.cloud.oauth.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cloud.oauth.entity.CmsClient;
import com.cloud.oauth.entity.CmsUser;
import com.cloud.oauth.service.OauthService;
import com.cloud.oauth.service.RedisService;
import com.common.base.constant.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xujiping
 * @date 2018/6/8 17:02
 */
@Service
public class OauthServiceImpl implements OauthService {

    @Autowired
    private RedisService redisService;

    @Override
    public void addAuthCode(String authCode, String username) {
        String key = Constants.CODE_REDIS_KEY_PREFIX + authCode;
        redisService.set(key, username, Constants.CODE_EXPIRE_IN);
    }

    @Override
    public void addAccessToken(String accessToken, String username) {
        String key = Constants.TOKEN_REDIS_KEY_PREFIX + accessToken;
        redisService.set(key, username, Constants.TOKEN_EXPIRE_IN);
    }

    @Override
    public boolean checkAuthCode(String authCode) {
        String key = Constants.CODE_REDIS_KEY_PREFIX + authCode;
        Object username = redisService.get(key);
        return username != null;
    }

    @Override
    public boolean checkAccessToken(String accessToken) {
        String key = Constants.TOKEN_REDIS_KEY_PREFIX + accessToken;
        Object username = redisService.get(key);
        return username != null;
    }

    @Override
    public String getUsernameByAuthCode(String authCode) {
        String key = Constants.CODE_REDIS_KEY_PREFIX + authCode;
        Object username = redisService.get(key);
        return String.valueOf(username);
    }

    @Override
    public long getCodeExpireIn() {
        return Constants.CODE_EXPIRE_IN;
    }

    @Override
    public String getUsernameByAccessToken(String accessToken) {
        String key = Constants.TOKEN_REDIS_KEY_PREFIX + accessToken;
        Object object = redisService.get(key);
        return String.valueOf(object);
    }

    @Override
    public long getTokenExpireIn() {
        return Constants.TOKEN_EXPIRE_IN;
    }

    @Override
    public boolean checkClientId(String clientId) {
        CmsClient client = new CmsClient();
        Wrapper<CmsClient> wrapper = new EntityWrapper<>();
        wrapper.eq("client_id", clientId);
        CmsClient cmsClient = client.selectOne(wrapper);
        return cmsClient != null;
    }

    @Override
    public boolean checkClientSecret(String clientSecret) {
        CmsClient client = new CmsClient();
        Wrapper<CmsClient> wrapper = new EntityWrapper<>();
        wrapper.eq("secret", clientSecret);
        CmsClient cmsClient = client.selectOne(wrapper);
        return cmsClient != null;
    }
}
