package com.rb.cms.service;

import com.common.base.util.SpageUtil;
import com.rb.cms.entity.ClientRelease;
import com.baomidou.mybatisplus.service.IService;
import com.rb.cms.entity.dto.ClientReleaseDto;

import java.util.Map;

/**
 * <p>
 * 专辑 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-11-17
 */
public interface ClientReleaseService extends IService<ClientRelease> {

    /**
     * 条件查询
     *
     * @param map
     * @return
     */
    SpageUtil<ClientRelease> listByMap(Map<String, Object> map, SpageUtil<ClientRelease> spageUtil);

    /**
     * 根据音频ID查询专辑信息
     * @param contentId
     * @return
     */
    ClientRelease getByContentId(Long contentId);

    /**
     * 封装dto
     * @param clientRelease 专辑信息
     * @return ClientReleaseDto
     */
    ClientReleaseDto wrapper(ClientRelease clientRelease);
}
