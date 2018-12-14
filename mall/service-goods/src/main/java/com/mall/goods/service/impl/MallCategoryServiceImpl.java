package com.mall.goods.service.impl;

import com.mall.goods.entity.MallCategory;
import com.mall.goods.mapper.MallCategoryMapper;
import com.mall.goods.service.MallCategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品分类表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-06-14
 */
@Service
public class MallCategoryServiceImpl extends ServiceImpl<MallCategoryMapper, MallCategory> implements MallCategoryService {

    @Override
    public MallCategory selectById(int id) {
        MallCategory category = new MallCategory();
        category.setId(id);
        return category.selectById();
    }
}
