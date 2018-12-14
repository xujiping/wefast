package com.common.base.util;

import java.util.UUID;

/**
 * @author xujiping
 * @date 2018/6/15 14:38
 */
public class UuidUtil {

    public static String uuid32(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
