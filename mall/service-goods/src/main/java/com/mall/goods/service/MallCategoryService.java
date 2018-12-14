package com.mall.goods.service;

import com.mall.goods.entity.MallCategory;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 商品分类表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-06-14
 */
public interface MallCategoryService extends IService<MallCategory> {

    MallCategory selectById(int id);

}
