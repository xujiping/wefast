package com.cloud.oauth.config;

import java.util.LinkedHashMap;
import java.util.Map;

import com.cloud.oauth.listener.AuthcSessionListener;
import com.cloud.oauth.session.AuthcServerSessionDao;
import com.cloud.oauth.session.AuthcServerSessionFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shiro配置.
 *
 * @author xujiping 2017-10-18 14:34
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
public class ShiroConfiguration {

    @Autowired
    private Realm permissionRealm;

    @Autowired
    private AuthenticationFilter myAuthenticationFilter;

    @Autowired
    private AuthcServerSessionDao sessionDao;

    @Autowired
    private AuthcSessionListener authcSessionListener;

    @Autowired
    private AuthcServerSessionFactory authcServerSessionFactory;

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm
        securityManager.setRealm(permissionRealm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //必须设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //如果不设置默认会自动寻找根目录/login.jsp页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //登录成功后跳转
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        //设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/login/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/lib/**", "anon");
        filterChainDefinitionMap.put("/info", "anon");
        filterChainDefinitionMap.put("/authorize", "anon");
        filterChainDefinitionMap.put("/accessToken", "anon");
        //配置退出过滤器，具体代码Shiro已经是实现
        filterChainDefinitionMap.put("/logout", "logout");
        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    /**
     * 会话Cookie模板.
     */
    @Bean
    public SimpleCookie sessionIdCookie() {
        SimpleCookie cookie = new SimpleCookie();
        //不会暴露给客户端
        cookie.setHttpOnly(true);
        //设置Cookie的过期时间，秒为单位，默认-1表示关闭浏览器时过期Cookie
        cookie.setMaxAge(-1);
        cookie.setName("authc-server-shiro-com.rb.oauth.session-id");
        return cookie;
    }

    /**
     * 开启Shiro Spring AOP权限注解@RequiresPermissions的支持.
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new
                AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
