package com.common.base.handler;

import com.common.base.constant.ReturnBean;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.Enumeration;
import java.util.Set;

/**
 * 全局异常处理类
 *
 * @author xujiping
 * @date 2018/6/14 16:41
 */
@ControllerAdvice
@ResponseBody
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    /**
     * 声明要捕获的异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String defultExcepitonHandler(HttpServletRequest request, Exception e) {
        ReturnBean returnBean = new ReturnBean();
        if (e instanceof BusinessException) {
            log("业务异常", e, request);
            BusinessException businessException = (BusinessException) e;
            returnBean.setCode(businessException.getCode());
            returnBean.setMsg(businessException.getMessage());
            returnBean.setData(businessException.getData());
        } else if (e instanceof ConstraintViolationException) {
            //参数校验异常
            returnBean.setCode(ReturnCode.PARAMS_ERROR.code());
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                returnBean.setMsg(item.getMessage());
                Path propertyPath = item.getPropertyPath();
                String propertyStr = propertyPath.toString();
                if (propertyStr.contains("arg0")) {
                    returnBean.setData("phone");
                } else if (propertyStr.contains("arg1")) {
                    returnBean.setData("validateCode");
                } else if (propertyStr.contains("arg2")) {
                    returnBean.setData("password");
                } else if (propertyStr.contains("arg3")) {
                    returnBean.setData("repeatPassword");
                }
            }
            logger.debug("参数校验异常[code = " + returnBean.getCode() + ", message = " + returnBean.getMsg() + "]");
        } else {
            //未知错误
            log("未知异常", e, request);
            String message = e.getCause().getMessage();
            if (StringUtils.isNotEmpty(message)){
                returnBean.setCodeMsg(ReturnCode.FAIL.code(), message);
            }else {
                returnBean.setReturnCode(ReturnCode.FAIL, null);
            }
        }
        return returnBean.toJsonString();
    }

    private void log(String title, Exception e, HttpServletRequest request) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(">>>" + title);
        buffer.append("->请求地址[" + request.getRequestURL().toString() + "]");
        Enumeration<String> parameterNames = request.getParameterNames();
        buffer.append("->请求参数[");
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement().toString();
            buffer.append("        " + name + ":" + request.getParameter(name) + ",");
        }
        buffer.append("]");
        logger.error(buffer.toString(), e);
    }
}
