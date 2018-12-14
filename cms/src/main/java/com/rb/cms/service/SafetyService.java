package com.rb.cms.service;

import com.rb.cms.entity.Safety;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户提现安全机制表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-11-10
 */
public interface SafetyService extends IService<Safety> {

    /**
     * 根据用户ID查询
     * @param clientListenId
     * @return
     */
    Safety getByListenId(String clientListenId);

    /**
     * 新增或更新
     * @param clientListenId
     * @param level
     * @param remark
     * @return
     */
    boolean addOrUpdate(String clientListenId, int level, String remark);

    /**
     * 删除
     * @param clientListenId
     * @return
     */
    void delete(String clientListenId);

}
