package com.frontend.media.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.common.base.constant.Constants;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.frontend.media.entity.ClientListen;
import com.frontend.media.mapper.ClientListenMapper;
import com.frontend.media.service.ClientListenService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.frontend.media.util.GsonUtil;
import com.frontend.media.util.ReedomCodeUtil;
import com.frontend.media.util.UrlUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 资源收听者 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-08-16
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class ClientListenServiceImpl extends ServiceImpl<ClientListenMapper, ClientListen> implements
        ClientListenService {

    @Value("${file.server}")
    private String fileServer;

    @Autowired
    private ClientListenMapper clientListenMapper;

    @Override
    public boolean checkSignedUp(String phone) {
        Wrapper<ClientListen> wrapper = new EntityWrapper<>();
        wrapper.eq("username", phone);
        ClientListen clientListen = selectOne(wrapper);
        if (clientListen != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean signUpPersonal(String phone, String password) {
        String name = ReedomCodeUtil.radomStr(phone);
        Map<String, Object> headermap = Maps.newHashMapWithExpectedSize(3);
        headermap.put("name", "default");
        headermap.put("type", "png");
        headermap.put("url", Constants.HEADER_FILE_PATH);
        Date date = new Date();
        //邀请码
        String code = ReedomCodeUtil.generate(phone.substring(phone.length() - 4));
        ClientListen clientListen = new ClientListen();
        clientListen.setUsername(phone);
        clientListen.setPassword(password);
        clientListen.setName(name);
        clientListen.setHeadUrl(GsonUtil.toStr(headermap));
        clientListen.setAgree("1");
        clientListen.setStat(Constants.STAT_NORMAL);
        clientListen.setCreateTime(date);
        clientListen.setLastLoginTime(date);
        clientListen.setIsactive(true);
        clientListen.setInvitationCode(code);
        clientListen.setOriginType(Constants.ORIGIN_TYPE_PERSON);
        boolean insert = insert(clientListen);
        return insert;
    }

    @Override
    public ClientListen getByUsername(String username) {
        EntityWrapper<ClientListen> wrapper = new EntityWrapper<>();
        wrapper.eq("username", username);
        ClientListen clientListen = selectOne(wrapper);
        if (clientListen == null) {
            throw new BusinessException(ReturnCode.RELEASE_NOT_EXIST);
        }
        return clientListen;
    }

    @Override
    public ClientListen getCurListen() {
        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ClientListen clientListen = (ClientListen) session.getAttribute("clientListen");
        if (clientListen == null) {
            throw new BusinessException(ReturnCode.RELEASE_NOT_EXIST);
        }
        return clientListen;
    }

    @Override
    public String getCurListenHeadUrl() {
        ClientListen curListen = getCurListen();
        String headUrl = curListen.getHeadUrl();
        String s = UrlUtil.mapToUrl(headUrl);
        if (StringUtils.isNotEmpty(s)) {
            headUrl = fileServer + s.substring(1);
        }
        return headUrl;
    }

    @Override
    public boolean updateHeadUrl(String filePath, String s, String suffix) {
        return false;
    }
}
