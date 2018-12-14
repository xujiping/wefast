package com.rb.cms.service;

import com.common.base.util.SpageUtil;
import com.rb.cms.entity.CmsRole;
import com.baomidou.mybatisplus.service.IService;
import com.rb.cms.entity.CmsUser;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-11-09
 */
public interface CmsRoleService extends IService<CmsRole> {

    /**
     * 分页查询
     *
     * @param params    参数
     * @param spageUtil 分页工具类
     * @return spageUtil
     */
    SpageUtil<CmsRole> listByPage(Map<String, Object> params, SpageUtil<CmsRole> spageUtil);

    /**
     * 获取用户的角色列表
     * @param userId 用户ID
     * @return
     */
    List<CmsRole> selectUserRoles(Integer userId);

}
