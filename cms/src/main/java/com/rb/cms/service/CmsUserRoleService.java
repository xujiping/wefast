package com.rb.cms.service;

import com.rb.cms.entity.CmsUserRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 后台管理-用户角色表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-11-09
 */
public interface CmsUserRoleService extends IService<CmsUserRole> {

    /**
     * 查询角色ID
     *
     * @param userId
     * @return
     */
    List<CmsUserRole> selectRoleId(int userId);

}
