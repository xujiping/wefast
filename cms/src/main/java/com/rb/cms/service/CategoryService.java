package com.rb.cms.service;

import com.common.base.util.SpageUtil;
import com.rb.cms.entity.Category;
import com.baomidou.mybatisplus.service.IService;
import com.rb.cms.entity.dto.CategoryDto2;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-11-20
 */
public interface CategoryService extends IService<Category> {


    /**
     * 获取列表
     * @param level 级别
     * @return
     */
    List<Category> getLevel(int level, String subject);

    /**
     * 条件查询
     *
     * @param map
     * @return
     */
    SpageUtil<Category> listByMap(Map<String, Object> map, SpageUtil<Category> spageUtil);

    CategoryDto2 wrapper(Category category);
}
