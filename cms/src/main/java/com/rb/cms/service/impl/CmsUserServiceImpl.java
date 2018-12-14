package com.rb.cms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.rb.cms.entity.CmsUser;
import com.rb.cms.mapper.CmsUserMapper;
import com.rb.cms.service.CmsUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-06-19
 */
@Service
public class CmsUserServiceImpl extends ServiceImpl<CmsUserMapper, CmsUser> implements CmsUserService {

    @Override
    public CmsUser selectByUsername(String username) {
        Wrapper<CmsUser> wrapper = new EntityWrapper<>();
        wrapper.eq("username", username);
        return selectOne(wrapper);
    }

    @Override
    public CmsUser getCurUser() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Object object = session.getAttribute("user");
        if (object != null) {
            return (CmsUser) object;
        }
        return null;
    }
}
