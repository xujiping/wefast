package com.cloud.oauth.service;

import com.cloud.oauth.entity.CmsClient;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-06-19
 */
public interface CmsClientService extends IService<CmsClient> {

    CmsClient findByCmsClientId(String clientId);
}
