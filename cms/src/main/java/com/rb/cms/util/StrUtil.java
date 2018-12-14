package com.rb.cms.util;

/**
 * 字符串工具类
 *
 * @author xujiping
 * @date 2018/11/17 10:16
 */
public class StrUtil {

    /**
     * 包裹% str %
     * @param str
     * @return
     */
    public static String wrapLike(String str) {
        String s = "%";
        return s + str + s;
    }
}
