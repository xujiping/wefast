package com.rb.cms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.rb.cms.entity.LoggingEventException;
import com.rb.cms.mapper.LoggingEventExceptionMapper;
import com.rb.cms.service.LoggingEventExceptionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-11-16
 */
@Service
public class LoggingEventExceptionServiceImpl extends ServiceImpl<LoggingEventExceptionMapper, LoggingEventException> implements LoggingEventExceptionService {

    @Override
    public List<LoggingEventException> getExceptions(long eventId) {
        Wrapper<LoggingEventException> wrapper = new EntityWrapper<>();
        wrapper.eq("event_id", eventId);
        return selectList(wrapper);
    }
}
