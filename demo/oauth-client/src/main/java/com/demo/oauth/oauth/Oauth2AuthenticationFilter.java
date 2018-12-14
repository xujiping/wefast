package com.demo.oauth.oauth;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author xujiping
 * @date 2018/6/9 10:10
 */
public class Oauth2AuthenticationFilter extends AuthenticatingFilter {

    private String authcCodeParam = "code";
    private String clientId;
    private String redirectUrl;
    private String responseType = "code";
    private String failureUrl;

    public void setAuthcCodeParam(String authcCodeParam) {
        this.authcCodeParam = authcCodeParam;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws
            Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String code = request.getParameter(authcCodeParam);
        return new Oauth2Token(code);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String error = servletRequest.getParameter("error");
        String errorDescription = servletRequest.getParameter("error_description");
        if (!StringUtils.isEmpty(error)) {
            WebUtils.issueRedirect(servletRequest, servletResponse, failureUrl + "?error=" + error +
                    "error_description=" + errorDescription);
            return false;
        }
        Subject subject = getSubject(servletRequest, servletResponse);
        if (!subject.isAuthenticated()) {
            if (StringUtils.isEmpty(servletRequest.getParameter(authcCodeParam))) {
                //如果用户没有身份验证，且没有auth code，则重定向到服务端授权
                saveRequestAndRedirectToLogin(servletRequest, servletResponse);
                return false;
            }
        }
        return executeLogin(servletRequest, servletResponse);
    }

    /**
     * 登录成功后的回调方法 重定向到成功页面
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
                                     ServletResponse response) throws Exception {
        issueSuccessRedirect(request, response);
        return false;
    }

    /**
     * 登录失败回调
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
                                     ServletResponse response) {
        Subject subject = getSubject(request, response);
        if (subject.isAuthenticated() || subject.isRemembered()) {
            try {
                issueSuccessRedirect(request, response);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            try {
                WebUtils.issueRedirect(request, response, failureUrl);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }
}
