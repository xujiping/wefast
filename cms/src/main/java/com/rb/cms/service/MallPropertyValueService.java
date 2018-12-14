package com.rb.cms.service;

import com.rb.cms.entity.MallPropertyValue;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 属性值表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-06-19
 */
public interface MallPropertyValueService extends IService<MallPropertyValue> {

    /**
     * 更新属性值，不存在的则新增，存在的则删除
     * @param propertyKeyId
     * @param values
     */
    void updateValue(String propertyKeyId, String[] values);

    /**
     * 根据属性id删除属性值
     * @param propertyKeyId
     */
    void deleteByKeyId(String propertyKeyId);
}
