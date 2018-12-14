package com.mall.goods.service.impl;

import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.mall.goods.entity.MallGoods;
import com.mall.goods.mapper.MallGoodsMapper;
import com.mall.goods.service.MallGoodsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-06-14
 */
@Service
public class MallGoodsServiceImpl extends ServiceImpl<MallGoodsMapper, MallGoods> implements MallGoodsService {

    @Override
    public boolean addSpu(long id, int count) {
        MallGoods goods = selectById(id);
        if (goods == null) {
            throw new BusinessException(ReturnCode.GOODS_NULL);
        }
        if (count <= 0) {
            throw new BusinessException(ReturnCode.PARAMS_ERROR);
        }
        Long newSpu = goods.getSpu() + count;
        MallGoods newGoods = new MallGoods();
        newGoods.setId(id);
        newGoods.setSpu(newSpu);
        boolean update = newGoods.updateById();
        if (!update) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        return true;
    }
}
