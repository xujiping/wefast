package com.mall.goods.service;

import com.mall.goods.entity.MallSku;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * SKU表（库存表） 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-06-14
 */
public interface MallSkuService extends IService<MallSku> {

    /**
     * 销售sku商品
     * @param skuId 库存ID
     * @param count 数量
     * @return
     */
    boolean sell(String skuId, int count);

    /**
     * 查询商品sku信息
     * @param goodsId
     * @return
     */
    MallSku selectByGoodsId(Long goodsId);
}
