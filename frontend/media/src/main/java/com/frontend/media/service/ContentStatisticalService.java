package com.frontend.media.service;

import com.frontend.media.entity.ContentStatistical;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 内容访问记录表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-08-22
 */
public interface ContentStatisticalService extends IService<ContentStatistical> {

    /**
     * 音频播放量
     * @param contentId
     * @return
     */
    Long playCount(Long contentId);

    /**
     * 当前用户发布的音频昨日播放量
     * @return
     */
    Long yestodayPlayCount(String listenId);
}
