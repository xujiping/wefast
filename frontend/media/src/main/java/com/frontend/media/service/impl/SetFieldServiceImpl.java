package com.frontend.media.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.frontend.media.entity.SetField;
import com.frontend.media.mapper.SetFieldMapper;
import com.frontend.media.service.SetFieldService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 领域设置 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-07-26
 */
@Service
public class SetFieldServiceImpl extends ServiceImpl<SetFieldMapper, SetField> implements SetFieldService {

    @Override
    public List<SetField> getFields() {
        SetField field = new SetField();
        EntityWrapper<SetField> wrapper = new EntityWrapper<>();
        wrapper.eq("stat", "1");
        wrapper.eq("level", 1);
        wrapper.orderBy("seq");
        List<SetField> fields = selectList(wrapper);
        return fields;
    }
}
