package com.frontend.media.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.frontend.media.entity.Category;
import com.frontend.media.mapper.CategoryMapper;
import com.frontend.media.service.CategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-10-17
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> list(Map<String, Object> params) {
        Wrapper<Category> wrapper = new EntityWrapper<>();
        if (params.containsKey("order")) {
            wrapper.orderBy(String.valueOf(params.get("order")));
        }
        if (params.containsKey("level")) {
            wrapper.eq("level", params.get("level"));
        }
        if (params.containsKey("stat")) {
            wrapper.eq("stat", params.get("stat"));
        }
        if (params.containsKey("subject")) {
            wrapper.eq("subject", params.get("subject"));
        }
        return categoryMapper.selectList(wrapper);
    }
}
