package com.rb.cms.task;

import com.rb.cms.service.LoggingEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 *
 * @author xujiping
 * @date 2018/4/28 16:14
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Component
@EnableScheduling
public class QuartzTask {

    private static final Logger LOG = LoggerFactory.getLogger(QuartzTask.class);

    @Autowired
    private LoggingEventService eventService;

    /**
     * 每天3点整执行    @Scheduled(cron = "0 0 3 * * ?")
     * 执行审核通过的提现操作
     *
     * @throws Exception
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void transfer() {
    }

    /**
     * 删除错误日志备份
     */
    @Scheduled(cron = "0 0 4 * * ?")
    public void deleteLogging() {
        eventService.deleteExpireTime(-7);
    }

}
