package com.rb.cms.service;

import com.rb.cms.entity.ContentStatistical;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 内容访问记录表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-11-10
 */
public interface ContentStatisticalService extends IService<ContentStatistical> {

    /**
     * 用户收听音频个数
     *
     * @param clientListenId
     * @return
     */
    Integer countByListenId(String clientListenId);
}
