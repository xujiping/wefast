package com.mall.goods.service;

import com.mall.goods.entity.MallPropertyKey;
import com.mall.goods.entity.MallPropertyValue;

/**
 * @author xujiping
 * @date 2018/6/15 11:27
 */
public interface MallPropertyService {

    /**
     * 新增属性
     * @param propertyKey
     * @param propertyValue
     * @return
     */
    boolean add(MallPropertyKey propertyKey, MallPropertyValue propertyValue);

}
