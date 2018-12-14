package com.frontend.media.service;

import com.alibaba.fastjson.JSONArray;
import com.common.base.util.SpageUtil;
import com.frontend.media.controller.dto.AtlasDto;
import com.frontend.media.entity.Atlas;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 * 图集 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-10-17
 */
public interface AtlasService extends IService<Atlas> {

    /**
     * 获取当前用户的列表
     * @return
     */
    SpageUtil<Atlas> getUserlist(SpageUtil<Atlas> spageUtil, Map<String, Object> params);

    /**
     * 根据标题和用户查询
     * @param title 标题
     * @param clientListenId 用户ID
     * @return Atlas
     */
    Atlas getOne(String title, String clientListenId, String stat);

    /**
     * 创建图集和图片
     * @param title 标题
     * @param categoryId 分类ID
     * @param imgJson 图片JSON数组
     */
    void add(String title, int categoryId, JSONArray imgJson, String fileServer);

    /**
     * 更新图集和图片
     * @param atlasId 图集ID
     * @param title 标题
     * @param categoryId 分类ID
     * @param imgJson 图片JSON数组
     */
    void update(long atlasId, String title, int categoryId, JSONArray imgJson, String fileServer);

    /**
     * 看是否包含权限
     * @param atlasId
     * @return
     */
    boolean checkPermission(Long atlasId);

    /**
     * 获取用户的图集信息
     * @param atlasId 图集ID
     * @return
     */
    Atlas getUserAtlas(Long atlasId);

    AtlasDto wrapper(Atlas atlas, String fileServer);

    /**
     * 移除封面
     * @param imageId
     */
    void removeCover(String imageId);

    /**
     * 查询用户图集的第一张图的ID
     * @param atlasId 图集ID
     * @return 0则表示没有图片
     */
    long getFirstImageId(long atlasId);

    /**
     * 移除专辑和图片
     * @param atlasId 图集ID
     */
    void remove(long atlasId, String fileServer);

}
