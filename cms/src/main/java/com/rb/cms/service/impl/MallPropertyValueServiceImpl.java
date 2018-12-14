package com.rb.cms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.rb.cms.entity.MallPropertyValue;
import com.rb.cms.mapper.MallPropertyValueMapper;
import com.rb.cms.service.MallPropertyValueService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 属性值表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-06-19
 */
@Service
public class MallPropertyValueServiceImpl extends ServiceImpl<MallPropertyValueMapper, MallPropertyValue> implements
        MallPropertyValueService {

    @Override
    public void updateValue(String propertyKeyId, String[] values) {
        //删除所有
        Wrapper<MallPropertyValue> wrapper = new EntityWrapper<>();
        wrapper.eq("property_key_id", propertyKeyId);
        boolean delete = delete(wrapper);
        if (!delete) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        //新增
        MallPropertyValue propertyValue = new MallPropertyValue();
        propertyValue.setPropertyKeyId(Integer.parseInt(propertyKeyId));
        Date date = new Date();
        propertyValue.setCreateTime(date);
        propertyValue.setUpdateTime(date);
        propertyValue.setStatus(1);
        for (String value : values) {
            propertyValue.setValue(value);
            boolean insert = insert(propertyValue);
            if (!insert) {
                throw new BusinessException(ReturnCode.FAIL);
            }
        }
    }

    @Override
    public void deleteByKeyId(String propertyKeyId) {
        Wrapper<MallPropertyValue> wrapper = new EntityWrapper<>();
        wrapper.eq("property_key_id", propertyKeyId);
        boolean delete = delete(wrapper);
        if (!delete) {
            throw new BusinessException(ReturnCode.FAIL);
        }
    }
}