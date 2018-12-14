package com.rb.cms.service;

import com.rb.cms.entity.CmsUser;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-06-19
 */
public interface CmsUserService extends IService<CmsUser> {

    /**
     * 查询
     * @param username 用户名
     * @return
     */
    CmsUser selectByUsername(String username);

    /**
     * 获取当前用户信息
     * @return
     */
    CmsUser getCurUser();

}
