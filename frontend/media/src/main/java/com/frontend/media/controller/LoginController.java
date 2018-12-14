package com.frontend.media.controller;

import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author xujiping
 * @date 2018/7/13 17:27
 */
@Controller
@RequestMapping("/login")
@Validated
public class LoginController {

    @Autowired
    private SecurityManager securityManager;

    @GetMapping("")
    public String login() {
        return "login";
    }

    @PostMapping("/login.do")
    @ResponseBody
    public String doLogin(@Length(min = 6, max = 20, message = "用户名必须6-20位") @RequestParam String username,
                          @Length(min = 6, max = 20, message = "密码必须6-20位") @RequestParam String password) {
        ReturnBean returnBean = new ReturnBean();
        returnBean.setReturnCode(ReturnCode.LOGIN_FAIL, null);
        //使用用户登录信息创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //执行登陆动作
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        //登录成功后重定向到客户端
        returnBean.setReturnCode(ReturnCode.SUCCESS, null);
        return returnBean.toJsonString();
    }
}
