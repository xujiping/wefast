package com.cloud.gateway.filter;

import com.cloud.gateway.service.RedisService;
import com.common.base.constant.Constants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 认证过滤器
 * @author xujiping
 * @date 2018/6/13 16:55
 */
@Component
public class OauthFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(OauthFilter.class);

    @Value("${filter.oauth.services}")
    private String oauthService;

    @Autowired private RedisService redisService;

    @Override
    public String filterType() {
        //路由之前
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //顺序
        return 0;
    }

    /**
     * 是否要过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤逻辑
     * @return
     */
    @Override
    public Object run() {
        boolean needToken = false;
        String[] services = oauthService.split("\\,");
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String requestURL = request.getRequestURL().toString();
        for (String service : services) {
            if (requestURL.contains(service)){
                needToken = true;
            }
        }
        //校验token
        if (needToken){
            String token = request.getParameter("token");
            if (StringUtils.isEmpty(token)){
                logger.info("token为空，禁止访问!");
                context.setSendZuulResponse(false);
                context.setResponseStatusCode(401);
                return null;
            }
            // TODO 先直接从redis校验token，后续从oauth-server校验token有效性
            String key = Constants.TOKEN_REDIS_KEY_PREFIX + token;
            if (!redisService.exists(key)){
                logger.info("token不存在，禁止访问!");
                context.setSendZuulResponse(false);
                context.setResponseStatusCode(401);
                return null;
            }
        }
        return null;
    }
}
