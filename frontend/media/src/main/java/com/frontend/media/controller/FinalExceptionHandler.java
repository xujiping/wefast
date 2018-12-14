package com.frontend.media.controller;

import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制器异常
 * 进入控制器之前，比如请求一个不存在的地址，404错误
 * @author xujiping
 * @date 2018/7/16 16:43
 */
@RestController
public class FinalExceptionHandler implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public Object error(HttpServletResponse response, HttpServletRequest request){
        ReturnBean returnBean = new ReturnBean();
        returnBean.setReturnCode(ReturnCode.REQUEST_NOT_EXISTS, null);
        return returnBean.toJsonString();
    }

}
