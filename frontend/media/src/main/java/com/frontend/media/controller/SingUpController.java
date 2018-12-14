package com.frontend.media.controller;

import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.frontend.media.service.ClientListenService;
import com.frontend.media.service.RedisService;
import com.frontend.media.service.SmsService;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * 注册
 *
 * @author xujiping
 * @date 2018/8/8 18:30
 */
@Controller
@RequestMapping("signUp")
@Validated
@Transactional(rollbackFor = Exception.class)
public class SingUpController {

    @Autowired
    private ClientListenService clientListenService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private SecurityManager securityManager;

    @GetMapping("type")
    public String selectType(){
        return "selectType";
    }

    @GetMapping("person")
    public String index() {
        return "signUp";
    }

    @GetMapping("send/code")
    @ResponseBody
    public String sendCode(@Pattern(regexp = "0?(13|14|15|17|18|19)[0-9]{9}", message = "请填写正确的手机号")
                           @RequestParam String phone) {
        ReturnBean returnBean = new ReturnBean();
        boolean signedUp = clientListenService.checkSignedUp(phone);
        if (signedUp) {
            throw new BusinessException(ReturnCode.LISTEN_USER_EXISTS, "phone");
        }
        smsService.sendCode(phone);
        return returnBean.toJsonString();
    }

    @PostMapping("")
    @ResponseBody
    public String signUp(@Pattern(regexp = "0?(13|14|15|17|18|19)[0-9]{9}", message = "请填写正确的手机号")
                         @RequestParam String phone,
                         @NotBlank(message = "请输入验证码")
                         @RequestParam String validateCode,
                         @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "请填写正确的密码")
                         @RequestParam String password,
                         @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "请填写正确的密码")
                         @RequestParam String repeatPassword) {
        ReturnBean returnBean = new ReturnBean();
        boolean signedUp = clientListenService.checkSignedUp(phone);
        if (signedUp) {
            throw new BusinessException(ReturnCode.LISTEN_USER_EXISTS, "phone");
        }
        Integer code = (Integer) redisService.get(phone);
        if (code == null || !String.valueOf(code).equals(validateCode)) {
            throw new BusinessException(ReturnCode.VALIDATE_CODE_ERROR, "validateCode");
        }
        if (!password.equals(repeatPassword)) {
            throw new BusinessException(ReturnCode.REPEAT_PASSWORD_ERROR, "repeatPassword");
        }
        boolean signUp = clientListenService.signUpPersonal(phone, password);
        if (!signUp) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        //使用用户登录信息创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken(phone, password);
        //执行登陆动作
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        //登录成功后重定向到客户端
        returnBean.setReturnCode(ReturnCode.SUCCESS, null);
        return returnBean.toJsonString();
    }

}
