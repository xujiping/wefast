package com.common.base.exception;

import com.common.base.constant.ReturnCode;

/**
 * 业务异常
 *
 * @author xujiping
 * @date 2018/6/14 16:38
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -6118010137349240796L;

    /**
     * 错误码
     */
    private Integer code;

    private Object data;

    public BusinessException() {
    }

    public BusinessException(ReturnCode returnCode) {
        super(returnCode.msg());
        this.code = returnCode.code();
    }

    public BusinessException(ReturnCode returnCode, Object data) {
        super(returnCode.msg());
        this.code = returnCode.code();
        this.data = data;
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public BusinessException(ReturnCode returnCode, Exception e) {
        super(returnCode.msg(), e.getCause());
        this.code = returnCode.code();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
