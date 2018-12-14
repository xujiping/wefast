package com.cloud.oauth.web;

import com.cloud.oauth.service.CmsClientService;
import com.cloud.oauth.service.OauthService;
import com.common.base.constant.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 授权控制器
 *
 * @author xujiping
 * @date 2018/6/8 16:47
 */
@Controller
public class AuthorizeController {

    private static final String OAUTH_CLIENT_REDICT_URL = "OAuth callback url needs to be provided by client!!!";

    @Autowired
    private OauthService oauthService;
    @Autowired
    private CmsClientService cmsClientService;

    @GetMapping("/authorize")
    public Object authorize(Model model, HttpServletRequest request) throws URISyntaxException, OAuthSystemException {
        try {
            //构建Oauth 授权请求
            OAuthAuthzRequest authzRequest = new OAuthAuthzRequest(request);
            //检查传入得客户端id是否正确
            if (!oauthService.checkClientId(authzRequest.getClientId())) {
                OAuthResponse response = OAuthResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).setError
                        (OAuthError.TokenResponse.INVALID_CLIENT).setErrorDescription(Constants
                        .INVALID_CLIENT_DESCRIPTION)
                        .buildJSONMessage();
                return new ResponseEntity<>(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
            }
            Subject subject = SecurityUtils.getSubject();
            //如果用户没有登录，跳转到登录页面
            if (!subject.isAuthenticated()) {
                if (!login(subject, request)) {
                    //登录失败跳转到登录页面
                    model.addAttribute("client", cmsClientService.findByCmsClientId(authzRequest.getClientId()));
                    return "login";
                }
            }
            //用户登录成功
            String username = (String) subject.getPrincipal();
            //生成授权码
            String authorizationCode = null;
            //responseType目前仅支持CODE，另外还有TOKEN
            String responseType = authzRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
            if (responseType.equals(ResponseType.CODE.toString())) {
                OAuthIssuerImpl oAuthIssuer = new OAuthIssuerImpl(new MD5Generator());
                authorizationCode = oAuthIssuer.authorizationCode();
                oauthService.addAuthCode(authorizationCode, username);
            }
            //进行OAuth响应构建
            OAuthASResponse.OAuthAuthorizationResponseBuilder builder =
                    OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND);
            //设置授权码
            builder.setCode(authorizationCode);
            //得到到客户端重定向地址
            String redirectURI = authzRequest.getParam(OAuth.OAUTH_REDIRECT_URI);
            //构建响应
            final OAuthResponse response = builder.location(redirectURI).buildQueryMessage();
            //根据OAuthResponse返回ResponseEntity响应
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(new URI(response.getLocationUri()));
            return new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus()));
        } catch (OAuthProblemException e) {
            //出错处理
            String redirectUri = e.getRedirectUri();
            if (OAuthUtils.isEmpty(redirectUri)) {
                //告诉客户端没有传入redirectUri直接报错
                return new ResponseEntity(OAUTH_CLIENT_REDICT_URL, HttpStatus.NOT_FOUND);
            }
            //返回错误消息（如?error=）
            final OAuthResponse response =
                    OAuthASResponse.errorResponse(HttpServletResponse.SC_FOUND)
                            .error(e).location(redirectUri).buildQueryMessage();
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(new URI(response.getLocationUri()));
            return new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus()));
        }
    }

    /**
     * 登录
     *
     * @param subject subject
     * @param request request
     * @return
     */
    private boolean login(Subject subject, HttpServletRequest request) {
        String getStr = "get";
        if (getStr.equalsIgnoreCase(request.getMethod())) {
            return false;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return false;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            return true;
        } catch (Exception e) {
            request.setAttribute("error", "登录失败:" + e.getClass().getName());
            return false;
        }
    }
}
