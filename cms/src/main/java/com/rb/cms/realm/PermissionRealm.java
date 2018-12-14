package com.rb.cms.realm;

import com.rb.cms.entity.CmsRole;
import com.rb.cms.entity.CmsUser;
import com.rb.cms.service.CmsPermissionService;
import com.rb.cms.service.CmsRoleService;
import com.rb.cms.service.CmsUserService;
import com.rb.cms.util.MD5Util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 自定义权限认证
 * Created by xjpdy on 2017/7/31.
 */
@Component
public class PermissionRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CmsUserService userService;

    @Autowired
    private CmsRoleService roleService;

    @Autowired
    private CmsPermissionService permissionService;

    /**
     * 权限认证.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String username = (String) principals.getPrimaryPrincipal();
        CmsUser user = userService.selectByUsername(username);
        Integer userId = user.getId();
        //用户所属角色
        Set<String> roles = new HashSet<>();
        List<CmsRole> roleList = roleService.selectUserRoles(userId);
        if (roleList != null && roleList.size() > 0) {
            for (CmsRole role : roleList) {
                roles.add(role.getName());
            }
        }
        //添加角色集合
        info.setRoles(roles);
        //添加权限集合
        Set<String> permissions = permissionService.selectUserPermissiones(userId);
        info.setStringPermissions(permissions);
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
        CmsUser user = userService.selectByUsername(username);
        if (user == null) {
            throw new AccountException("该账号不存在");
        }
        String s = MD5Util.MD5(password + user.getSalt());
        if (!user.getPassword().equals(s)) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        //登录成功
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("user", user);
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
