package com.mall.order.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.mall.order.entity.MallOrder;
import com.mall.order.mapper.MallOrderMapper;
import com.mall.order.service.MallOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-06-26
 */
@Service
public class MallOrderServiceImpl extends ServiceImpl<MallOrderMapper, MallOrder> implements MallOrderService {

    @Override
    public MallOrder selectByOrderNo(String orderNo) {
        Wrapper<MallOrder> wrapper = new EntityWrapper<>();
        wrapper.eq("order_no", orderNo);
        MallOrder order = selectOne(wrapper);
        return order;
    }

}
