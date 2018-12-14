package com.rb.cms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.base.util.SpageUtil;
import com.rb.cms.entity.CmsRole;
import com.rb.cms.entity.CmsUserRole;
import com.rb.cms.mapper.CmsRoleMapper;
import com.rb.cms.mapper.CmsUserRoleMapper;
import com.rb.cms.service.CmsRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rb.cms.service.CmsUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-11-09
 */
@SuppressWarnings("ALL")
@Service
public class CmsRoleServiceImpl extends ServiceImpl<CmsRoleMapper, CmsRole> implements CmsRoleService {

    @Autowired
    private CmsRoleMapper roleMapper;

    @Autowired
    private CmsUserRoleService userRoleService;

    @Override
    public SpageUtil<CmsRole> listByPage(Map<String, Object> params, SpageUtil<CmsRole> spageUtil) {
        //条件
        Wrapper<CmsRole> wrapper = new EntityWrapper<>();
        //分页
        Page<CmsRole> pageObject = new Page<>(spageUtil.getPage(), spageUtil.getStep());
        List<CmsRole> rows = roleMapper.selectPage(pageObject, wrapper);
        if (rows != null && rows.size() > 0) {
            spageUtil.setRows(rows);
        }
        int total = roleMapper.selectCount(wrapper);
        spageUtil.setTotal(total);
        return spageUtil;
    }

    @Override
    public List<CmsRole> selectUserRoles(Integer userId) {
        List<Integer> roleIds = new ArrayList<>();
        List<CmsRole> roles = null;
        List<CmsUserRole> userRoles = userRoleService.selectRoleId(userId);
        if (userRoles != null && userRoles.size() > 0) {
            for (CmsUserRole userRole : userRoles) {
                roleIds.add(userRole.getRoleId());
            }
            roles = selectBatchIds(roleIds);
        }
        return roles;
    }

}
