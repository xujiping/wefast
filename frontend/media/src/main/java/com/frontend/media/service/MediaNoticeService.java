package com.frontend.media.service;

import com.common.base.util.SpageUtil;
import com.frontend.media.entity.MediaNotice;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 * 自媒体平台公告 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-09-03
 */
public interface MediaNoticeService extends IService<MediaNotice> {

    /**
     * 分页查询
     * @return
     */
    SpageUtil<MediaNotice> listByPage(Map<String, Object> params, SpageUtil<MediaNotice> spageUtil);

}
