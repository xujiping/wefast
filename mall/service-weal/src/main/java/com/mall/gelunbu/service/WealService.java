package com.mall.gelunbu.service;

import com.mall.gelunbu.entity.Weal;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 福利表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-06-26
 */
public interface WealService extends IService<Weal> {

    /**
     * 查询用户福利信息
     * @param clientListenId
     * @return
     */
    Weal selectByClientListenId(String clientListenId);

}
