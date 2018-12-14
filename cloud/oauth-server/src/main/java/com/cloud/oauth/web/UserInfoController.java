package com.cloud.oauth.web;

import com.cloud.oauth.service.OauthService;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户信息
 *
 * @author xujiping
 * @date 2018/6/9 9:43
 */
@RestController
public class UserInfoController {

    @Autowired
    private OauthService oauthService;

//    @GetMapping("/user")
//    public HttpEntity userInfo(HttpServletRequest request) throws OAuthSystemException {
//        try {
//            OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(request, ParameterStyle.QUERY);
//            //获取Access Token
//            String accessToken = oauthRequest.getAccessToken();
//            //验证Access Token
//            if (!oauthService.checkAccessToken(accessToken)) {
//                //验证失败
//                OAuthResponse oauthResponse = OAuthRSResponse
//                        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
//                        .setRealm(Constants.RESOURCE_SERVER_NAME)
//                        .setError(OAuthError.ResourceResponse.INVALID_TOKEN)
//                        .buildHeaderMessage();
//                HttpHeaders headers = new HttpHeaders();
//                headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse.getHeader(OAuth.HeaderType
//                        .WWW_AUTHENTICATE));
//                return new ResponseEntity(headers, HttpStatus.UNAUTHORIZED);
//            }
//            //返回用户名
//            String username = oauthService.getUsernameByAccessToken(accessToken);
//            return new ResponseEntity(username, HttpStatus.OK);
//        } catch (OAuthProblemException e) {
//            //检查是否设置了错误码
//            String errorCode = e.getError();
//            if (OAuthUtils.isEmpty(errorCode)) {
//                OAuthResponse oauthResponse = OAuthRSResponse
//                        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
//                        .setRealm(Constants.RESOURCE_SERVER_NAME)
//                        .buildHeaderMessage();
//
//                HttpHeaders headers = new HttpHeaders();
//                headers.add(OAuth.HeaderType.WWW_AUTHENTICATE,
//                        oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
//                return new ResponseEntity(headers, HttpStatus.UNAUTHORIZED);
//            }
//
//            OAuthResponse oauthResponse = OAuthRSResponse
//                    .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
//                    .setRealm(Constants.RESOURCE_SERVER_NAME)
//                    .setError(e.getError())
//                    .setErrorDescription(e.getDescription())
//                    .setErrorUri(e.getUri())
//                    .buildHeaderMessage();
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add(OAuth.HeaderType.WWW_AUTHENTICATE,
//                    oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//    }
}
