package com.mall.goods.service;

import com.mall.goods.entity.MallGoods;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-06-14
 */
public interface MallGoodsService extends IService<MallGoods> {

    /**
     * 更新SPU销量
     * @param count 只能为正数
     * @return
     */
    boolean addSpu(long id, int count);

}
