package com.mall.goods.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.mall.goods.entity.MallSku;
import com.mall.goods.mapper.MallSkuMapper;
import com.mall.goods.service.MallGoodsService;
import com.mall.goods.service.MallSkuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * SKU表（库存表） 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-06-14
 */
@Service
public class MallSkuServiceImpl extends ServiceImpl<MallSkuMapper, MallSku> implements MallSkuService {

    @Autowired
    private MallGoodsService goodsService;

    @Override
    public boolean sell(String skuId, int count) {
        MallSku mallSku = new MallSku();
        mallSku.setId(skuId);
        MallSku sku = mallSku.selectById();
        //检验商品sku
        if (sku == null) {
            throw new BusinessException(ReturnCode.GOODS_NULL);
        }
        //校验库存
        if (sku.getInventory() < count) {
            throw new BusinessException(ReturnCode.SKU_NULL);
        }
        //修改库存，更新销量
        long newInventory = sku.getInventory() - count;
        long newSalesVolume = sku.getSalesVolume() + count;
        MallSku newSku = new MallSku();
        newSku.setId(skuId);
        newSku.setInventory(newInventory);
        newSku.setSalesVolume(newSalesVolume);
        boolean update = newSku.updateById();
        if (!update) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        //更新商品SPU销量
        goodsService.addSpu(sku.getGoodsId(), count);
        return true;
    }

    @Override
    public MallSku selectByGoodsId(Long goodsId) {
        Wrapper<MallSku> wrapper = new EntityWrapper<>();
        wrapper.eq("goods_id", goodsId);
        MallSku mallSku = selectOne(wrapper);
        if (mallSku == null){
            throw new BusinessException(ReturnCode.GOODS_NULL);
        }
        return mallSku;
    }
}
