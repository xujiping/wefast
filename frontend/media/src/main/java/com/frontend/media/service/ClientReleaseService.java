package com.frontend.media.service;

import com.common.base.util.SpageUtil;
import com.frontend.media.controller.dto.ReleaseDto;
import com.frontend.media.entity.ClientRelease;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 注册头条号的资源发布者 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-07-16
 */
public interface ClientReleaseService extends IService<ClientRelease> {

    /**
     * 根据专辑用户名查询专辑信息
     * @param username
     * @return
     */
    ClientRelease getByUsername(String username);

    /**
     * 获取当前专辑信息
     *
     * @return
     */
    ClientRelease getCurRelease();

    /**
     * 获取当前专辑头像地址
     * @return
     */
    String getCurReleaseHeadUrl();

    boolean updateCurRelease(ClientRelease release);

    /**
     * 更新专辑头像
     * @param url
     * @return
     */
    boolean updateHeadUrl(String url, String name, String type);

    /**
     * 获取当前用户的专辑列表
     * @return
     */
    SpageUtil<ClientRelease> getUserReleases(SpageUtil<ClientRelease> spageUtil, Map<String, Object> params);

    /**
     * 封装
     * @param release
     * @return
     */
    ReleaseDto wrapperDto(ClientRelease release);

    /**
     * 创建专辑
     * @param name
     * @param head
     * @param field
     * @param intro
     * @return
     */
    boolean add(String clientListenId, String name, String head, String field, String intro);

    /**
     * 获取用户的所有专辑列表
     * @return
     */
    List<ClientRelease> getUserAllReleases();

    /**
     * 昨日订阅量
     * @return
     */
    Long yestodayFollowCount();

    /**
     * 修改专辑信息
     * @param releaseId
     * @param name
     * @param field
     * @param intro
     * @return
     */
    boolean update(Long releaseId, String name, String field, String intro);

}
