package com.rb.cms.service;

import com.common.base.util.SpageUtil;
import com.rb.cms.entity.ResContent;
import com.baomidou.mybatisplus.service.IService;
import com.rb.cms.entity.dto.ContentDto;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 发布者发布的资源 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-11-17
 */
public interface ResContentService extends IService<ResContent> {

    /**
     * 条件查询
     * @param map 条件
     * @param spageUtil 页面工具类
     * @return SpageUtil<ResContent>
     */
    SpageUtil<ResContent> listByMap(Map<String, Object> map, SpageUtil<ResContent> spageUtil);

    /**
     * 批量设置状态
     *
     * @param ids 分隔符"-"
     * @param stat 状态
     * @param labels 标签
     * @return String
     */
    String setStat(String ids, String stat, List<String> labels);

    /**
     * 查询分类的音频数量
     * @param params 条件
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> countByField(Map<String, Object> params);

    /**
     * 封装
     *
     * @param resContent 音频
     * @return ContentDto
     */
    ContentDto wrapperContentDto(ResContent resContent);

    /**
     * 更新所属专辑的音频状态
     * @param releaseId 专辑ID
     * @param stat 状态
     */
    void updateStatByRelease(Long releaseId, String stat);

    /**
     * 更新redis中类别音频ID列表
     * @param type 类别ID
     * @param add 是否增加
     * @param contentId 内容ID
     */
    void updateTypeIdsCache(long type, boolean add, String contentId);

}
