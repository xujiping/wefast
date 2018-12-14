package com.rb.cms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.rb.cms.entity.CmsUserRole;
import com.rb.cms.mapper.CmsUserRoleMapper;
import com.rb.cms.service.CmsUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 后台管理-用户角色表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-11-09
 */
@SuppressWarnings("ALL")
@Service
public class CmsUserRoleServiceImpl extends ServiceImpl<CmsUserRoleMapper, CmsUserRole> implements CmsUserRoleService {

    @Autowired private CmsUserRoleMapper userRoleMapper;

    @Override
    public List<CmsUserRole> selectRoleId(int userId) {
        Wrapper<CmsUserRole> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId);
        return selectList(wrapper);
    }
}
