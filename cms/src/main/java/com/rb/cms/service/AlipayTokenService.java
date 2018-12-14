package com.rb.cms.service;

import com.rb.cms.entity.AlipayToken;
import com.baomidou.mybatisplus.service.IService;
import com.rb.cms.entity.AlipayUserInfo;

/**
 * <p>
 * 支付宝授权码表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-11-15
 */
public interface AlipayTokenService extends IService<AlipayToken> {

    /**
     * 根据支付宝用户ID查询
     * @param userId
     * @return
     */
    AlipayToken getByUserId(String userId);

    /**
     * 获取用户的支付宝信息
     * @param userId
     * @return
     */
    AlipayUserInfo getInfoByUserId(String userId);

}
