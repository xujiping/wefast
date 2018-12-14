package com.frontend.media.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.base.constant.Constants;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.common.base.util.DateTimeUtil;
import com.common.base.util.SpageUtil;
import com.frontend.media.controller.dto.ContentDto;
import com.frontend.media.entity.ClientListen;
import com.frontend.media.entity.ClientRelease;
import com.frontend.media.entity.ResContent;
import com.frontend.media.mapper.ResContentMapper;
import com.frontend.media.service.*;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.frontend.media.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 发布者发布的资源 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-07-24
 */
@Service
public class ResContentServiceImpl extends ServiceImpl<ResContentMapper, ResContent> implements ResContentService {

    @Autowired
    private ClientReleaseService releaseService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private ContentStatisticalService contentStatisticalService;

    @Autowired
    private ClientListenService listenService;

    @Override
    public boolean add(String title, String intro, Long releaseId, JSONObject contentUrl) {
        ClientListen curListen = listenService.getCurListen();
        String curListenId = curListen.getId();
        Date date = new Date();
        if (StringUtils.isEmpty(title)) {
            title = contentUrl.getString("oldName");
        }
        contentUrl.remove("oldName");
        ClientRelease clientRelease = releaseService.selectById(releaseId);
        String releaseField = clientRelease.getField();
        String releaseName = clientRelease.getName();
        ResContent resContent = new ResContent();
        resContent.setClientReleaseId(releaseId);
        resContent.setClientReleaseName(releaseName);
        resContent.setTitle(title);
        resContent.setSummary(intro);
        resContent.setType(releaseField);
        resContent.setContentUrl(contentUrl.toJSONString());
        resContent.setStat(Constants.STAT_AUDIT);
        resContent.setSubmitTime(date);
        resContent.setSeq(System.currentTimeMillis());
        resContent.setIsactive(true);
        resContent.setClientListenId(curListenId);
        return resContent.insert();
    }

    @Override
    public Long coutByRelease(Long releaseId) {
        EntityWrapper<ResContent> wrapper = new EntityWrapper<>();
        wrapper.eq("client_release_id", releaseId);
        int count = selectCount(wrapper);
        return Long.valueOf(count);
    }

    @Override
    public boolean updateReleaseName(Long releaseId, String newReleaseName, String field) {
        ResContent resContent = new ResContent();
        resContent.setClientReleaseName(newReleaseName);
        resContent.setType(field);
        EntityWrapper<ResContent> wrapper = new EntityWrapper<>();
        wrapper.eq("client_release_id", releaseId);
        boolean update = update(resContent, wrapper);
        return update;
    }

    @Override
    public SpageUtil<ResContent> listByPage(Map<String, Object> params, SpageUtil<ResContent> spageUtil) {
        //条件
        Wrapper<ResContent> wrapper = new EntityWrapper<>();
        if (params.containsKey("releaseId")) {
            long releaseId = (long) params.get("releaseId");
            wrapper.eq("client_release_id", releaseId);
        }
        if (params.containsKey("stat")) {
            String stat = (String) params.get("stat");
            wrapper.eq("stat", stat);
        }
        wrapper.orderBy("seq");
        //分页
        Page<ResContent> objectPage = new Page<>(spageUtil.getPage(), spageUtil.getStep());
        List<ResContent> contentList = selectPage(objectPage, wrapper).getRecords();
        if (contentList != null && contentList.size() > 0) {
            int total = selectCount(wrapper);
            spageUtil.setTotal(total);
            spageUtil.setRows(contentList);
        }
        return spageUtil;
    }

    @Override
    public ContentDto wrapperDto(ResContent resContent) {
        ContentDto contentDto = new ContentDto();
        if (resContent == null) {
            return contentDto;
        }
        BeanUtils.copyProperties(resContent, contentDto);
        Long contentId = resContent.getId();
        Date submitTime = resContent.getSubmitTime();
        contentDto.setSubmitTime(DateTimeUtil.getDate(submitTime));
        //喜欢数量
        Object likedCache = redisService.hmGet(redisService.CLIENT_CONTENT_LIKE_COUNT, contentId.toString());
        if (likedCache != null) {
            contentDto.setLikeCount(Long.valueOf(likedCache.toString()));
        }
        //播放量
        Long playCount = contentStatisticalService.playCount(contentId);
        contentDto.setPlayCount(playCount);
        return contentDto;
    }

    @Override
    public List<ResContent> getCurUserContents() {
        ClientListen curListen = listenService.getCurListen();
        String curListenId = curListen.getId();
        Wrapper<ResContent> wrapper = new EntityWrapper<>();
        wrapper.eq("client_listen_id", curListenId);
        List<ResContent> contentList = selectList(wrapper);
        return contentList;
    }

    @Override
    public Long getMinSeq(Long releaseId) {
        Wrapper<ResContent> wrapper = new EntityWrapper<>();
        wrapper.eq("client_release_id", releaseId);
        wrapper.orderBy("seq");
        ResContent resContent = selectOne(wrapper);
        return resContent.getSeq();
    }

    @Override
    public boolean setTop(Long id) {
        ResContent resContent = selectById(id);
        Long clientReleaseId = resContent.getClientReleaseId();
        Long minSeq = getMinSeq(clientReleaseId);
        resContent.setSeq(minSeq - 1);
        resContent.setUpdatedDate(new Date());
        boolean update = updateById(resContent);
        return update;
    }

    @Override
    public void updateTypeIdsCache(long type, boolean add, String contentId) {
        String redisKey = RedisService.TYPE_CONTENT_IDS + type;
        Object object = redisService.get(redisKey);
        if (object != null) {
            List<String> ids = (List<String>) object;
            if (add) {
                ids.add(contentId);
            } else {
                ids.remove(contentId);
            }
            redisService.set(redisKey, ids);
        }
    }

    @Override
    public boolean delete(long id) {
        ResContent resContent = selectById(id);
        resContent.setStat(Constants.STAT_BLOCK);
        boolean delete = updateById(resContent);
        if (delete) {
            String type = resContent.getType();
            //更新缓存类别音频ID列表
            updateTypeIdsCache(Long.valueOf(type), false, String.valueOf(id));
            return true;
        }
        return false;
    }

    @Override
    public List<ResContent> getByRelease(long releaseId) {
        Wrapper<ResContent> wrapper = new EntityWrapper<>();
        wrapper.eq("client_release_id", releaseId);
        List<ResContent> contentList = selectList(wrapper);
        return contentList;
    }

}
