package com.rb.oauth.config;

import com.rb.oauth.listener.AuthcSessionListener;
import com.rb.oauth.realm.Oauth2Realm;
import com.rb.oauth.session.AuthcServerSessionFactory;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * shiro配置.
 *
 * @author xujiping 2017-10-18 14:34
 */
@Configuration
public class ShiroConfiguration {

    @Autowired
    private Realm permissionRealm;

    @Autowired
    private AuthenticationFilter myAuthenticationFilter;

    @Autowired
    private AuthcSessionListener authcSessionListener;

    @Autowired
    private AuthcServerSessionFactory authcServerSessionFactory;

    /**
     * 缓存管理器
     *
     * @return
     */
    @Bean
    public EhCacheManager cacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache/ehcache.xml");
        return cacheManager;
    }

//    @Bean
//    public DefaultWebSecurityManager securityManager() {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        //设置realm
//        securityManager.setRealm(permissionRealm);
//        //设置会话管理器
//        securityManager.setSessionManager(sessionManager(sessionDao));
//        return securityManager;
//    }

    /**
     * 会话Cookie模板.
     */
    @Bean
    public SimpleCookie sessionIdCookie() {
        SimpleCookie cookie = new SimpleCookie("sid");
        //不会暴露给客户端
        cookie.setHttpOnly(true);
        //设置Cookie的过期时间，秒为单位，默认-1表示关闭浏览器时过期Cookie
        cookie.setMaxAge(-1);
        return cookie;
    }

    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setHttpOnly(true);
        //30天
        cookie.setMaxAge(2592000);
        return cookie;
    }

    /**
     * rememberMe管理器
     *
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        rememberMeManager.setCookie(rememberMeCookie());
        return rememberMeManager;
    }

    /**
     * 会话DAO
     *
     * @return
     */
    @Bean
    public EnterpriseCacheSessionDAO sessionDAO(JavaUuidSessionIdGenerator sessionIdGenerator) {
        EnterpriseCacheSessionDAO dao = new EnterpriseCacheSessionDAO();
        dao.setActiveSessionsCacheName("shiro-activeSessionCache");
        dao.setSessionIdGenerator(sessionIdGenerator);
        return dao;
    }

    /**
     * 会话验证调度器
     *
     * @return
     */
    public QuartzSessionValidationScheduler sessionValidationScheduler(SessionManager sessionManager) {
        QuartzSessionValidationScheduler sessionValidationScheduler = new QuartzSessionValidationScheduler();
        sessionValidationScheduler.setSessionValidationInterval(1800000);
        sessionValidationScheduler.setSessionManager((ValidatingSessionManager) sessionManager);
        return sessionValidationScheduler;
    }

    /**
     * 会话ID生成器
     *
     * @return
     */
    @Bean
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    /**
     * 会话管理器.
     */
    @Bean
    public SessionManager sessionManager(EnterpriseCacheSessionDAO sessionDao) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //全局session超时时间
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setSessionDAO(sessionDao);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(sessionIdCookie());
        sessionManager.setSessionValidationSchedulerEnabled(false);
        sessionManager.setDeleteInvalidSessions(true);
        Collection<SessionListener> listeners = new ArrayList<>();
        listeners.add(authcSessionListener);
        sessionManager.setSessionListeners(listeners);
        sessionManager.setSessionFactory(authcServerSessionFactory);
        return sessionManager;
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
