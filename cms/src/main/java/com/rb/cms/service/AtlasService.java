package com.rb.cms.service;

import com.common.base.util.SpageUtil;
import com.rb.cms.entity.Atlas;
import com.baomidou.mybatisplus.service.IService;
import com.rb.cms.entity.dto.AtlasDto;

import java.util.Map;

/**
 * <p>
 * 图集 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-11-20
 */
public interface AtlasService extends IService<Atlas> {

    /**
     * 条件查询
     * @param map
     * @return
     */
    SpageUtil<Atlas> listByMap(Map<String, Object> map, SpageUtil<Atlas> spageUtil);

    /**
     * 封装
     * @param atlas
     * @param fileServer
     * @return
     */
    AtlasDto wrapperDto(Atlas atlas, String fileServer);

    /**
     * 更新状态
     * @param ids
     */
    void updateStat(String[] ids, String stat);

}
