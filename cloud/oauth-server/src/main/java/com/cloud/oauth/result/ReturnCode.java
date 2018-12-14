package com.cloud.oauth.result;

/**
 * @author xujiping
 */
public enum ReturnCode {
    /**
     * 成功
     */
    SUCCESS(0, "200"),
    FAIL(500, "服务器错误"),
    LOGIN_FAIL(3, "登录失败，账号或密码错误"),
    USER_LOCKED(5, "该账号已被锁定，请联系管理员"),
    NULL_INFO(1001, "相关信息为空"),

    NOT_ENOUGH_COIN(1101, "零钱不足"),
    NOT_ENOUGH_GOLD(1101, "金币不足"),
    NOT_ENOUGH_BEAN(1101, "福豆不足"),

    ;

    private int code;
    private String msg;

    ReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return code;
    }

    public String msg() {
        return msg;
    }
}
