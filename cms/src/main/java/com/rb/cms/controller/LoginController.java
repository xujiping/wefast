package com.rb.cms.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xujiping
 * @date 2018/6/8 13:50
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private SecurityManager securityManager;

    @GetMapping("")
    public String index() {
        return "login";
    }

    @PostMapping("")
    public String login(String username, String password, String callback) {
        try {
            //使用用户登录信息创建令牌
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            //执行登陆动作
            SecurityUtils.setSecurityManager(securityManager);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            //登录成功后重定向到客户端
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "login";
    }
}
