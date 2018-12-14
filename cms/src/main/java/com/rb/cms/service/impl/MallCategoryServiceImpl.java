package com.rb.cms.service.impl;

import com.rb.cms.entity.MallCategory;
import com.rb.cms.entity.dto.CategoryDto;
import com.rb.cms.mapper.MallCategoryMapper;
import com.rb.cms.service.MallCategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品分类表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-06-20
 */
@Service
public class MallCategoryServiceImpl extends ServiceImpl<MallCategoryMapper, MallCategory> implements MallCategoryService {

    @Override
    public CategoryDto getDtoById(String id) {
        MallCategory category = selectById(id);
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(category, categoryDto);
        Integer level = category.getLevel();
        if (level == 1){
            categoryDto.setFirstSelect(category.getId());
        }
        if (level == 2){
            categoryDto.setFirstSelect(category.getPid());
        }
        if (level == 3){
            Integer pid = category.getPid();
            categoryDto.setSecondSelect(pid);
            MallCategory parentCategory = selectById(pid);
            categoryDto.setFirstSelect(parentCategory.getPid());
        }
        return categoryDto;
    }
}
