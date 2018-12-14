package com.frontend.media.service;

import com.alibaba.fastjson.JSONObject;
import com.common.base.util.SpageUtil;
import com.frontend.media.controller.dto.ContentDto;
import com.frontend.media.entity.ResContent;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 发布者发布的资源 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-07-24
 */
public interface ResContentService extends IService<ResContent> {

    /**
     * 新增资源
     *
     * @param title     标题
     * @param intro     简介
     * @param releaseId 专辑ID
     * @param contentUrl  文件url，包括文件时长、大小等
     * @return
     */
    boolean add(String title, String intro, Long releaseId, JSONObject contentUrl);

    /**
     * 查询专辑的数量
     * @param releaseId
     * @return
     */
    Long coutByRelease(Long releaseId);

    /**
     * 修改专辑所属音频的专辑名
     * @param releaseId
     * @param newReleaseName
     * @param field
     * @return
     */
    boolean updateReleaseName(Long releaseId, String newReleaseName, String field);

    /**
     * 分页查询
     * @return
     */
    SpageUtil<ResContent> listByPage(Map<String, Object> params, SpageUtil<ResContent> spageUtil);

    ContentDto wrapperDto(ResContent resContent);

    /**
     * 获取当前用户发布的音频列表
     * @return list
     */
    List<ResContent> getCurUserContents();

    /**
     * 获取专辑最小的序号
     * @return
     */
    Long getMinSeq(Long releaseId);

    /**
     * 置顶
     * @param id
     * @return
     */
    boolean setTop(Long id);

    /**
     * 更新redis中类别音频ID列表
     */
    void updateTypeIdsCache(long type, boolean add, String contentId);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 查询专辑的音频列表
     * @return
     */
    List<ResContent> getByRelease(long releaseId);

}
