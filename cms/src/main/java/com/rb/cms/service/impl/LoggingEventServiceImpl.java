package com.rb.cms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.base.util.DateTimeUtil;
import com.common.base.util.SpageUtil;
import com.rb.cms.entity.LoggingEvent;
import com.rb.cms.mapper.LoggingEventMapper;
import com.rb.cms.service.LoggingEventExceptionService;
import com.rb.cms.service.LoggingEventService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-11-16
 */
@Service
public class LoggingEventServiceImpl extends ServiceImpl<LoggingEventMapper, LoggingEvent> implements
        LoggingEventService {

    @Autowired private LoggingEventExceptionService eventExceptionService;

    @Override
    public SpageUtil<LoggingEvent> listByPage(Map<String, Object> params, SpageUtil<LoggingEvent> spageUtil) {
        Wrapper<LoggingEvent> wrapper = new EntityWrapper<>();
        String sort = spageUtil.getSort();
        if (StringUtils.isNotEmpty(sort)) {
            wrapper.orderBy(sort);
        }
        Page<LoggingEvent> pageObject = new Page<>(spageUtil.getPage(), spageUtil.getStep());
        pageObject = selectPage(pageObject, wrapper);
        List<LoggingEvent> loggingEvents = pageObject.getRecords();
        spageUtil.setRows(loggingEvents);
        int total = selectCount(wrapper);
        spageUtil.setTotal(total);
        return spageUtil;
    }

    @Override
    public void deleteExpireTime(int offset) {
        Date todayStart = DateTimeUtil.getTodayStart();
        Date dateOffset = DateTimeUtil.getDateOffset(todayStart, offset);
        long time = dateOffset.getTime();
        Wrapper<LoggingEvent> wrapper = new EntityWrapper<>();
        wrapper.lt("timestmp", time);
        List<LoggingEvent> events = selectList(wrapper);
        for (LoggingEvent event : events) {
            Long eventId = event.getEventId();
            eventExceptionService.deleteById(eventId);
            deleteById(eventId);
        }
    }
}
