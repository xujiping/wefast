package com.rb.cms.service;

import com.rb.cms.entity.Image;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 图片表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-11-20
 */
public interface ImageService extends IService<Image> {

    /**
     * 查询图集的图片
     * @param atlasId
     * @return
     */
    List<Image> getByAtlas(long atlasId);
}
