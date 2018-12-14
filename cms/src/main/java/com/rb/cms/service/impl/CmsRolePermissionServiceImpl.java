package com.rb.cms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.rb.cms.entity.CmsPermission;
import com.rb.cms.entity.CmsRolePermission;
import com.rb.cms.mapper.CmsRolePermissionMapper;
import com.rb.cms.service.CmsPermissionService;
import com.rb.cms.service.CmsRolePermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-11-09
 */
@Service
public class CmsRolePermissionServiceImpl extends ServiceImpl<CmsRolePermissionMapper, CmsRolePermission> implements
        CmsRolePermissionService {

    @Autowired private CmsPermissionService permissionService;

    @Override
    public Set<String> getRolePermissions(int roleId) {
        Set<String> set = new HashSet<>();
        Wrapper<CmsRolePermission> wrapper = new EntityWrapper<>();
        wrapper.eq("role_id", roleId);
        List<CmsRolePermission> list = selectList(wrapper);
        if (list != null && list.size() > 0) {
            // 权限ID列表
            List<Integer> pIds = new ArrayList<>();
            for (CmsRolePermission rolePermission : list) {
                pIds.add(rolePermission.getPermissionId());
            }
            //权限列表
            List<CmsPermission> permissions = permissionService.selectBatchIds(pIds);
            for (CmsPermission p : permissions) {
                set.add(p.getPermissionValue());
            }
        }
        return set;
    }
}
