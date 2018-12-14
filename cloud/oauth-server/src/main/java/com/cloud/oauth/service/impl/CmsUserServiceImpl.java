package com.cloud.oauth.service.impl;

import com.cloud.oauth.entity.CmsUser;
import com.cloud.oauth.mapper.CmsUserMapper;
import com.cloud.oauth.service.CmsUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-06-19
 */
@Service
public class CmsUserServiceImpl extends ServiceImpl<CmsUserMapper, CmsUser> implements CmsUserService {

}
