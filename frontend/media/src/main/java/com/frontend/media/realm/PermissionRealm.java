package com.frontend.media.realm;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.common.base.util.MD5Util;
import com.frontend.media.common.Constants;
import com.frontend.media.entity.ClientListen;
import com.frontend.media.entity.ClientRelease;
import com.frontend.media.service.ClientListenService;
import com.frontend.media.service.ClientReleaseService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 自定义权限认证
 * Created by xjpdy on 2017/7/31.
 */
@Component
public class PermissionRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ClientReleaseService cmsUserService;

    @Autowired private ClientListenService clientListenService;

    /**
     * 权限认证.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        String username = (String) principals.getPrimaryPrincipal();
//        User record = new User();
//        record.setUsername(username);
//        User user = userMapper.selectOne(record);
//        Set<String> permissions = new HashSet<>();
//        // TODO 用户权限实现
//        info.setStringPermissions(permissions);  //添加权限集合
//        //用户所属角色
//        Set<String> roles = new HashSet<>();
//        List<Role> roleList = roleService.selectUserRoles(user);
//        logger.info(this.getClass().getName() + "--用户所有角色：" + roleList);
//        if (roleList != null && roleList.size() > 0) {
//            for (Role role : roleList) {
//                roles.add(role.getName());
//            }
//        }
//        info.setStringPermissions(roles);//添加角色集合
        return info;
    }

    /**
     * 登录认证.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws
            AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        logger.info(this.getClass().getName() + "--获取认证的用户名：" + username + " 密码：" + password);
        Wrapper<ClientListen> wrapper = new EntityWrapper<>();
        wrapper.eq("username", username);
        ClientListen clientListen = clientListenService.selectOne(wrapper);
        if (clientListen == null) {
            throw new BusinessException(ReturnCode.LOGIN_FAIL);
        }
        String s = MD5Util.MD5(password + Constants.USER_PASSWORD_DEFAULT_SALT);
        if (!clientListen.getPassword().equals(password)){
            throw new BusinessException(ReturnCode.LOGIN_FAIL);
//            throw new IncorrectCredentialsException(ReturnCode.LOGIN_FAIL.msg());
        }
        //登录成功
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("clientListen", clientListen);
        return new SimpleAuthenticationInfo(username, password, getName());
    }

    @Override
    public String getName() {
        return "permissionRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持UsernamePasswordToken类型的token
        return token instanceof UsernamePasswordToken;
    }
}
