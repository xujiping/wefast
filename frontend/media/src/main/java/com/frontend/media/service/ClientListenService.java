package com.frontend.media.service;

import com.frontend.media.entity.ClientListen;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 资源收听者 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-08-16
 */
public interface ClientListenService extends IService<ClientListen> {

    /**
     * 校验是否已经注册过
     * @param phone
     * @return
     */
    boolean checkSignedUp(String phone);

    /**
     * 注册用户
     * @param phone 手机号
     * @param password 密码
     * @return
     */
    boolean signUpPersonal(String phone, String password);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    ClientListen getByUsername(String username);

    /**
     * 获取当前用户信息
     *
     * @return
     */
    ClientListen getCurListen();

    /**
     * 获取当前专辑头像地址
     * @return
     */
    String getCurListenHeadUrl();

    /**
     * 更新用户头像
     * @param filePath
     * @param s
     * @param suffix
     * @return
     */
    boolean updateHeadUrl(String filePath, String s, String suffix);
}
