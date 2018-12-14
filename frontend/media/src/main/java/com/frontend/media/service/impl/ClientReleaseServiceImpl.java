package com.frontend.media.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.base.constant.Constants;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.common.base.util.DateTimeUtil;
import com.common.base.util.SpageUtil;
import com.frontend.media.controller.dto.ReleaseDto;
import com.frontend.media.entity.ClientListen;
import com.frontend.media.entity.ClientListenFollow;
import com.frontend.media.entity.ClientRelease;
import com.frontend.media.mapper.ClientReleaseMapper;
import com.frontend.media.service.*;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.frontend.media.util.AliyunOssUtil;
import com.frontend.media.util.GsonUtil;
import com.frontend.media.util.UrlUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 注册头条号的资源发布者 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-07-16
 */
@Service
public class ClientReleaseServiceImpl extends ServiceImpl<ClientReleaseMapper, ClientRelease> implements
        ClientReleaseService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private ClientListenService listenService;

    @Autowired
    private ResContentService contentService;

    @Autowired
    private ClientListenFollowService followService;

    @Autowired
    private ClientReleaseService releaseService;

    @Value("${file.server}")
    private String fileServer;

    @Override
    public ClientRelease getByUsername(String username) {
        ClientRelease release = new ClientRelease();
        release.setUsername(username);
        EntityWrapper<ClientRelease> wrapper = new EntityWrapper<>();
        wrapper.eq("username", username);
        ClientRelease clientRelease = release.selectOne(wrapper);
        if (clientRelease == null) {
            throw new BusinessException(ReturnCode.RELEASE_NOT_EXIST);
        }
        return clientRelease;
    }

    @Override
    public ClientRelease getCurRelease() {
        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ClientRelease clientRelease = (ClientRelease) session.getAttribute("clientRelease");
        if (clientRelease == null) {
            throw new BusinessException(ReturnCode.RELEASE_NOT_EXIST);
        }
        return clientRelease;
    }

    @Override
    public String getCurReleaseHeadUrl() {
        ClientRelease curRelease = getCurRelease();
        String headUrl = curRelease.getHeadUrl();
        String s = UrlUtil.mapToUrl(headUrl);
        if (StringUtils.isNotEmpty(s)) {
            headUrl = fileServer + s.substring(1);
        }
        return headUrl;
    }

    @Override
    public boolean updateCurRelease(ClientRelease release) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("clientRelease", release);
        return true;
    }

    @Override
    public boolean updateHeadUrl(String url, String name, String type) {
        if (StringUtils.isEmpty(url)) {
            return false;
        }
        String newUrl = "/" + url;
        ClientRelease curRelease = getCurRelease();
        String headUrl = curRelease.getHeadUrl();
        JSONObject parse = JSON.parseObject(headUrl);
        parse.put("url", newUrl);
        parse.put("name", name);
        parse.put("type", type.replace(".", ""));
        curRelease.setHeadUrl(parse.toJSONString());
        updateCurRelease(curRelease);
        boolean update = updateById(curRelease);
        if (!update) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        //更新redis缓存
        Long curReleaseId = curRelease.getId();
        redisService.hmSet(Constants.REDIS_CLIENT_RELEASE, String.valueOf(curReleaseId), GsonUtil.toStr(curRelease));
        return true;
    }

    @Override
    public SpageUtil<ClientRelease> getUserReleases(SpageUtil<ClientRelease> spageUtil, Map<String, Object> params) {
        ClientListen curListen = listenService.getCurListen();
        String listenId = curListen.getId();
        Wrapper<ClientRelease> wrapper = new EntityWrapper<>();
        wrapper.eq("client_listen_id", listenId);
        String sort = spageUtil.getSort();
        if (StringUtils.isNotEmpty(sort)) {
            wrapper.orderBy(sort);
        }
        if (params != null && params.containsKey("name")){
            String name = (String) params.get("name");
            wrapper.like("name", "%" + name + "%");
        }
        if (params != null && params.containsKey("stat")){
            String stat = (String) params.get("stat");
            wrapper.eq("stat", stat);
        }
        Page<ClientRelease> objectPage = new Page<>(spageUtil.getPage(), spageUtil.getStep());
        List<ClientRelease> releaseList = selectPage(objectPage, wrapper).getRecords();
        int total = selectCount(wrapper);
        if (releaseList != null) {
            spageUtil.setRows(releaseList);
            spageUtil.setTotal(total);
        }
        return spageUtil;
    }

    @Override
    public ReleaseDto wrapperDto(ClientRelease release) {
        ReleaseDto releaseDto = new ReleaseDto();
        if (release == null) {
            return releaseDto;
        }
        BeanUtils.copyProperties(release, releaseDto);
        Long releaseId = release.getId();
        String headUrl = release.getHeadUrl();
        Date submitTime = release.getSubmitTime();
        headUrl = UrlUtil.mapToUrl(headUrl);
        headUrl = AliyunOssUtil.getImgUrl(headUrl, 100, 100);
        releaseDto.setHeadUrl(headUrl);
        releaseDto.setSubmitTime(DateTimeUtil.getDate(submitTime));
        //专辑音频数量
        Long contentSize = contentService.coutByRelease(releaseId);
        releaseDto.setContentSize(contentSize);
        return releaseDto;
    }

    @Override
    public boolean add(String clientListenId, String name, String head, String field, String intro) {
        Date date = new Date();
        ClientRelease clientRelease = new ClientRelease();
        clientRelease.setName(name);
        clientRelease.setHeadUrl(UrlUtil.toMapStr(head));
        clientRelease.setField(field);
        clientRelease.setIntroduce(intro);
        clientRelease.setType(Constants.ORIGIN_TYPE_PERSON);
        clientRelease.setAgree(Constants.AGREE);
        clientRelease.setStat(Constants.STAT_AUDIT);
        clientRelease.setSubmitTime(date);
        clientRelease.setIsactive(true);
        clientRelease.setUpdatedDate(date);
        clientRelease.setClientListenId(clientListenId);
        boolean insert = insert(clientRelease);
        return insert;
    }

    @Override
    public List<ClientRelease> getUserAllReleases() {
        ClientListen curListen = listenService.getCurListen();
        String listenId = curListen.getId();
        Wrapper<ClientRelease> wrapper = new EntityWrapper<>();
        wrapper.eq("client_listen_id", listenId);
        List<ClientRelease> releaseList = selectList(wrapper);
        return releaseList;
    }

    @Override
    public Long yestodayFollowCount() {
        //in条件最大数量，数量太大，cpu性能下降厉害。
        int maxSelect = 200;
        long total = 0;
        int start = 0;
        int end = 0;
        List<ClientRelease> userAllReleases = releaseService.getUserAllReleases();
        if (userAllReleases != null && userAllReleases.size() > 0) {
            List<String> releaseList = new ArrayList<>();
            for (ClientRelease release : userAllReleases) {
                Long releaseId = release.getId();
                releaseList.add(String.valueOf(releaseId));
            }
            int size = releaseList.size();
            if (size > maxSelect) {
                int count = size / maxSelect + 1;
                for (int i = 0; i < count; i++) {
                    if (i == 0) {
                        start = i * maxSelect;
                    } else {
                        start = i * maxSelect - 1;
                    }
                    end = (i + 1) * maxSelect - 1;
                    Wrapper<ClientListenFollow> wrapper = new EntityWrapper<>();
                    if (i == count - 1) {
                        wrapper.in("client_release_id", releaseList.subList(start, size));
                    } else {
                        wrapper.in("client_release_id", releaseList.subList(start, end));
                    }
                    Date todayStart = DateTimeUtil.getTodayStart();
                    Date yestodayStart = DateTimeUtil.getDateOffset(todayStart, -1);
                    wrapper.between("create_time", DateTimeUtil.getDateTime(yestodayStart), DateTimeUtil.getDateTime
                            (todayStart));
                    total += followService.selectCount(wrapper);
                }
            }
        }
        return total;
    }

    @Override
    public boolean update(Long releaseId, String name, String field, String intro) {
        ClientRelease clientRelease = new ClientRelease();
        clientRelease.setId(releaseId);
        clientRelease.setName(name);
        clientRelease.setField(field);
        clientRelease.setIntroduce(intro);
        boolean update = updateById(clientRelease);
        if (update) {
            //更新redis
            redisService.hmSet(Constants.REDIS_CLIENT_RELEASE, String.valueOf(releaseId), GsonUtil.toStr
                    (clientRelease));
            //更新所属专辑音频信息
            contentService.updateReleaseName(releaseId, name, field);
        }
        return update;
    }
}
