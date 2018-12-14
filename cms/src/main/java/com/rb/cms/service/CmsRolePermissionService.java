package com.rb.cms.service;

import com.rb.cms.entity.CmsRolePermission;
import com.baomidou.mybatisplus.service.IService;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-11-09
 */
public interface CmsRolePermissionService extends IService<CmsRolePermission> {

    /**
     * 获取角色的权限列表
     * @param roleId
     * @return
     */
    Set<String> getRolePermissions(int roleId);

}
