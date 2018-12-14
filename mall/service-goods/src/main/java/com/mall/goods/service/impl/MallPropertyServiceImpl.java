package com.mall.goods.service.impl;

import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.mall.goods.entity.MallPropertyKey;
import com.mall.goods.entity.MallPropertyValue;
import com.mall.goods.service.MallPropertyService;
import org.springframework.stereotype.Service;

/**
 * @author xujiping
 * @date 2018/6/15 11:29
 */
@Service
public class MallPropertyServiceImpl implements MallPropertyService {

    @Override
    public boolean add(MallPropertyKey propertyKey, MallPropertyValue propertyValue) {
        boolean insert = propertyKey.insert();
        if (!insert){
            throw new BusinessException(ReturnCode.FAIL);
        }
        boolean insert1 = propertyValue.insert();
        if (!insert1){
            throw new BusinessException(ReturnCode.FAIL);
        }
        return true;
    }
}
