package com.rb.cms.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author xujiping
 * @date 2018/6/11 16:58
 */
public class JsonListUtil {

    /**
     * 转JSON对象
     *
     * @param code  状态码
     * @param msg   消息
     * @param count 总数量
     * @param data  数据列表
     * @return JSONObject
     */
    public static JSONObject toJson(int code, String msg, Long count, List data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rows", JSON.parseArray(JSON.toJSONString(data)));
        jsonObject.put("code", code);
        jsonObject.put("total", count);
        jsonObject.put("msg", msg);
        return jsonObject;
    }
}
