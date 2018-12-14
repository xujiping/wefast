package com.common.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.List;

/**
 * @author xujiping
 * @date 2018/6/14 14:30
 */
public class JsonUtil {

    /**
     * List 转 json 字符串
     * @param list list
     * @return json 字符串
     */
    public static String listToJson(List list) {
        if (list == null || list.size() <= 0) {
            return "";
        }
        try {
            JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(list));
            return jsonArray.toJSONString();
        } catch (Exception e) {
            return "";
        }
    }
}
