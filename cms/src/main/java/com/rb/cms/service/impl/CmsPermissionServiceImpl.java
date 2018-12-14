package com.rb.cms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.base.util.SpageUtil;
import com.rb.cms.common.Constants;
import com.rb.cms.entity.CmsPermission;
import com.rb.cms.entity.CmsRole;
import com.rb.cms.entity.dto.PermissionDto;
import com.rb.cms.mapper.CmsPermissionMapper;
import com.rb.cms.service.CmsPermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rb.cms.service.CmsRolePermissionService;
import com.rb.cms.service.CmsRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-11-09
 */
@SuppressWarnings("ALL")
@Service
public class CmsPermissionServiceImpl extends ServiceImpl<CmsPermissionMapper, CmsPermission> implements
        CmsPermissionService {

    @Autowired
    private CmsRoleService roleService;

    @Autowired
    private CmsRolePermissionService rolePermissionService;

    @Autowired
    private CmsPermissionMapper permissionMapper;

    @Override
    public Set<String> selectUserPermissiones(int userId) {
        Set<String> permissions = new HashSet<>();
        Set<String> rolePermissions;
        List<CmsRole> roles = roleService.selectUserRoles(userId);
        if (roles != null && roles.size() > 0) {
            for (CmsRole role : roles) {
                // 一个角色的权限集合
                rolePermissions = rolePermissionService.getRolePermissions(role.getId());
                permissions.addAll(rolePermissions);
            }
        }
        return permissions;
    }

    @Override
    public SpageUtil<CmsPermission> listByPage(Map<String, Object> params, SpageUtil<CmsPermission> spageUtil) {
        //条件
        Wrapper<CmsPermission> wrapper = new EntityWrapper<>();
        //分页
        Page<CmsPermission> pageObject = new Page<>(spageUtil.getPage(), spageUtil.getStep());
        List<CmsPermission> rows = permissionMapper.selectPage(pageObject, wrapper);
        if (rows != null && rows.size() > 0) {
            spageUtil.setRows(rows);
        }
        int total = permissionMapper.selectCount(wrapper);
        spageUtil.setTotal(total);
        return spageUtil;
    }

    @Override
    public PermissionDto wrapper(CmsPermission permission) {
        PermissionDto permissionDto = new PermissionDto();
        if (permission == null) {
            return permissionDto;
        }
        BeanUtils.copyProperties(permission, permissionDto);
        Integer systemId = permission.getSystemId();
        Integer type = permission.getType();
        Integer status = permission.getStatus();
        //转换
        permissionDto.setSystem(Constants.SYSTEM[systemId]);
        permissionDto.setType(Constants.CMS_PERMISSION_TYPE[type]);
        permissionDto.setStatus(Constants.STATUS[status]);
        return permissionDto;
    }
}
