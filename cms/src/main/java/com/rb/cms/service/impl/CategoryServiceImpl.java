package com.rb.cms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.base.constant.Constants;
import com.common.base.util.SpageUtil;
import com.rb.cms.entity.Category;
import com.rb.cms.entity.dto.CategoryDto2;
import com.rb.cms.mapper.CategoryMapper;
import com.rb.cms.service.CategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rb.cms.util.StrUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
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
 * @since 2018-11-20
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Category> getLevel(int level, String subject) {
        Wrapper<Category> wrapper = new EntityWrapper<>();
        wrapper.eq("level", level);
        if (StringUtils.isNotEmpty(subject) && !subject.equals(Constants.CATEGORY_ALL)) {
            wrapper.eq("subject", subject);
        }
        return selectList(wrapper);
    }

    @Override
    public SpageUtil<Category> listByMap(Map<String, Object> map, SpageUtil<Category> spageUtil) {
        String sort = spageUtil.getSort();
        Wrapper<Category> wrapper = new EntityWrapper<>();
        String value = (String) map.get("value");
        if (!StringUtils.isEmpty(sort)) {
            wrapper.orderBy(sort + ", seq asc");
        } else {
            wrapper.orderBy("seq");
        }
        if (StringUtils.isNotEmpty(value)) {
            wrapper.like("value", StrUtil.wrapLike(value));
        }
        Integer level = (Integer) map.get("level");
        if (level == 1) {
            wrapper.eq("level", 1);
        } else if (level == 2) {
            wrapper.eq("level", level);
            wrapper.eq("parent", map.get("parent"));
        }
        if (map.containsKey("stat")) {
            wrapper.eq("stat", map.get("stat"));
        }
        if (map.containsKey("subject")) {
            wrapper.eq("subject", map.get("subject"));
        }
        Page<Category> pageObject = new Page<>(spageUtil.getPage(), spageUtil.getStep());
        pageObject = selectPage(pageObject, wrapper);
        List<Category> fieldList = pageObject.getRecords();
        long total = selectCount(wrapper);
        if (fieldList != null && fieldList.size() > 0) {
            spageUtil.setRows(fieldList);
            spageUtil.setTotal(total);
        }
        return spageUtil;
    }

    @Override
    public CategoryDto2 wrapper(Category category) {
        CategoryDto2 categoryDto = new CategoryDto2();
        if (category == null) {
            return categoryDto;
        }
        BeanUtils.copyProperties(category, categoryDto);
        Integer parentId = category.getParentId();
        if (parentId != null) {
            Category parentInfo = categoryService.selectById(parentId);
            if (parentInfo != null) {
                categoryDto.setParent(parentInfo.getName());
            }
        }
        return categoryDto;
    }
}
