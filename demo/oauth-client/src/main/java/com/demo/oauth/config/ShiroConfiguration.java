package com.demo.oauth.config;

import com.demo.oauth.listener.AuthcSessionListener;
import com.demo.oauth.oauth.Oauth2AuthenticationFilter;
import com.demo.oauth.oauth.Oauth2Realm;
import com.demo.oauth.session.AuthcServerSessionFactory;
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
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
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
     * 自定义请求过滤
     * @param securityManager
     * @param oauth2AuthenticationFilter
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, Filter oauth2AuthenticationFilter) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //必须设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //如果不设置默认会自动寻找根目录/login.jsp页面
        shiroFilterFactoryBean.setLoginUrl("http://localhost:1002/authorize?client_id=100&response_type=code" +
                "&redirect_uri=http://localhost:1003/oauth2-login");
        //登录成功后跳转
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        //自定义Filter
        Map<String, Filter> filters = new HashMap<>(1);
        filters.put("oauth2Authc", oauth2AuthenticationFilter);
        shiroFilterFactoryBean.setFilters(filters);
        //设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/login/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/lib/**", "anon");
        //自定义拦截器
        filterChainDefinitionMap.put("/oauth2-login", "oauth2Authc");
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
     * 安全管理器
     *
     * @param oauth2Realm
     * @param sessionManager
     * @param cacheManager
     * @param rememberMeManager
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager2(Oauth2Realm oauth2Realm, SessionManager sessionManager,
                                                      EhCacheManager cacheManager, RememberMeManager
                                                                  rememberMeManager) {
        oauth2Realm.setCachingEnabled(true);
        oauth2Realm.setAuthenticationCachingEnabled(true);
        oauth2Realm.setAuthenticationCacheName("authenticationCache");
        oauth2Realm.setAuthorizationCachingEnabled(true);
        oauth2Realm.setAuthorizationCacheName("authorizationCache");
        //客户端ID
        oauth2Realm.setClientId("c1ebe466-1cdc-4bd3-ab69-77c3561b9dee");
        //客户端密码
        oauth2Realm.setClientSecret("d8346ea2-6017-43ed-ad68-19c0f971738b");
        oauth2Realm.setAccessTokenUrl("http://localhost:1002/accessToken");
        oauth2Realm.setUserInfoUrl("http://localhost:1002/user");
        //重定向客户端地址
        oauth2Realm.setRedirectUrl("http://localhost:1002/oauth2-login");

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(oauth2Realm);
        securityManager.setSessionManager(sessionManager);
        securityManager.setCacheManager(cacheManager);
        securityManager.setRememberMeManager(rememberMeManager);
        return securityManager;
    }

    /**
     * 开启Shiro Spring AOP权限注解@RequiresPermissions的支持.
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager2) {
        AuthorizationAttributeSourceAdvisor advisor = new
                AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager2);
        return advisor;
    }

    /**
     * Oauth2身份验证过滤器
     *
     * @return
     */
    @Bean
    public Oauth2AuthenticationFilter oauth2AuthenticationFilter() {
        Oauth2AuthenticationFilter filter = new Oauth2AuthenticationFilter();
        filter.setAuthcCodeParam("code");
        filter.setFailureUrl("/oauth2Failure.html");
        return filter;
    }

}
