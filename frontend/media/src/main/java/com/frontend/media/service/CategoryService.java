package com.frontend.media.service;

import com.frontend.media.entity.Category;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-10-17
 */
public interface CategoryService extends IService<Category> {

    /**
     * 查询列表
     *
     * @param params
     * @return
     */
    List<Category> list(Map<String, Object> params);

}
