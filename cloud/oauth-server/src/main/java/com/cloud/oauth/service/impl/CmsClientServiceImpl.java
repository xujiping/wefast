package com.cloud.oauth.service.impl;

import com.cloud.oauth.entity.CmsClient;
import com.cloud.oauth.mapper.CmsClientMapper;
import com.cloud.oauth.service.CmsClientService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-06-19
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class CmsClientServiceImpl extends ServiceImpl<CmsClientMapper, CmsClient> implements CmsClientService {

    @Autowired
    private CmsClientMapper clientMapper;

    @Override
    public CmsClient findByCmsClientId(String clientId) {
        CmsClient client = new CmsClient();
        client.setClientId(clientId);
        return clientMapper.selectOne(client);
    }
}
