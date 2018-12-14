package com.rb.cms.service;

import com.rb.cms.entity.LoggingEventException;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-11-16
 */
public interface LoggingEventExceptionService extends IService<LoggingEventException> {

    /**
     * 查询异常详情
     * @param eventId
     * @return
     */
    List<LoggingEventException> getExceptions(long eventId);

}
