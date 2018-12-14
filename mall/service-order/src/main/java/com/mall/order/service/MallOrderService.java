package com.mall.order.service;

import com.mall.order.entity.MallOrder;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-06-26
 */
public interface MallOrderService extends IService<MallOrder> {

    /**
     * 根据订单号查询订单信息
     * @param orderNo
     * @return
     */
    MallOrder selectByOrderNo(String orderNo);

}
