package com.rb.cms.service;

import com.rb.cms.entity.MallCategory;
import com.baomidou.mybatisplus.service.IService;
import com.rb.cms.entity.dto.CategoryDto;


/**
 * <p>
 * 商品分类表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-06-20
 */
public interface MallCategoryService extends IService<MallCategory> {

    CategoryDto getDtoById(String id);

}
