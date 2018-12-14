package com.rb.cms.service.impl;

import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.rb.cms.entity.AlipayToken;
import com.rb.cms.entity.AlipayUserInfo;
import com.rb.cms.mapper.AlipayTokenMapper;
import com.rb.cms.service.AlipayTokenService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rb.cms.util.alipay.AlipayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 支付宝授权码表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-11-15
 */
@Service
public class AlipayTokenServiceImpl extends ServiceImpl<AlipayTokenMapper, AlipayToken> implements AlipayTokenService {

    private final Logger LOGGER = LoggerFactory.getLogger(AlipayTokenServiceImpl.class);

    @Override
    public AlipayToken getByUserId(String userId) {
        Wrapper<AlipayToken> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId);
        return selectOne(wrapper);
    }

    @Override
    public AlipayUserInfo getInfoByUserId(String userId) {
        AlipayToken alipayToken = getByUserId(userId);
        if (alipayToken == null){
            return null;
        }
        String accessToken = alipayToken.getAccessToken();
        String clientListenId = alipayToken.getClientListenId();
        AlipayUserInfoShareResponse infoShare = AlipayUtil.infoShare(accessToken);
        if (!infoShare.isSuccess()) {
            String code = infoShare.getCode();
            String msg = infoShare.getMsg();
            String subCode = infoShare.getSubCode();
            String subMsg = infoShare.getSubMsg();
            LOGGER.error("支付宝会员授权信息查询失败： {code=" + code + ", msg=" + msg + "}");
            return null;
        }
        AlipayUserInfo userInfo = new AlipayUserInfo();
        userInfo.setClientListenId(clientListenId);
        userInfo.setUserId(infoShare.getUserId());
        userInfo.setAvatar(infoShare.getAvatar());
        userInfo.setProvince(infoShare.getProvince());
        userInfo.setCity(infoShare.getCity());
        userInfo.setNickName(infoShare.getNickName());
        userInfo.setIsStudentCertified(infoShare.getIsStudentCertified());
        userInfo.setUserType(infoShare.getUserType());
        userInfo.setUserStatus(infoShare.getUserStatus());
        userInfo.setIsCertified(infoShare.getIsCertified());
        userInfo.setGender(infoShare.getGender());
        return userInfo;
    }
}
