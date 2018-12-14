package com.rb.cms.service;

import com.common.base.util.SpageUtil;
import com.rb.cms.entity.LoggingEvent;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-11-16
 */
public interface LoggingEventService extends IService<LoggingEvent> {

    /**
     * 分页查询
     *
     * @param params
     * @param spageUtil
     * @return
     */
    SpageUtil<LoggingEvent> listByPage(Map<String, Object> params, SpageUtil<LoggingEvent> spageUtil);

    /**
     * 删除多少天之前的日志
     *
     * @param offset 天数
     */
    void deleteExpireTime(int offset);

}
