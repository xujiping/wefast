package com.rb.cms.service;

import com.common.base.util.SpageUtil;
import com.rb.cms.entity.CmsPermission;
import com.baomidou.mybatisplus.service.IService;
import com.rb.cms.entity.dto.PermissionDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-11-09
 */
public interface CmsPermissionService extends IService<CmsPermission> {

    /**
     * 查询用户权限列表
     * @param userId
     * @return
     */
    Set<String> selectUserPermissiones(int userId);

    /**
     * 分页查询
     *
     * @param params    参数
     * @param spageUtil 分页工具类
     * @return spageUtil
     */
    SpageUtil<CmsPermission> listByPage(Map<String, Object> params, SpageUtil<CmsPermission> spageUtil);

    /**
     * 封装
     * @param permission
     * @return
     */
    PermissionDto wrapper(CmsPermission permission);
}
