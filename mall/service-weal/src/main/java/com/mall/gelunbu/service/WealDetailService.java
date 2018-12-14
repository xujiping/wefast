package com.mall.gelunbu.service;

import com.mall.gelunbu.entity.Weal;
import com.mall.gelunbu.entity.WealDetail;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 福利详情表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-06-26
 */
public interface WealDetailService extends IService<WealDetail> {

    /**
     * 更新用户福利和福利详情
     * @param wealDetail
     * @return
     */
    boolean updateDetailAndWeal(WealDetail wealDetail, Weal weal);

}
