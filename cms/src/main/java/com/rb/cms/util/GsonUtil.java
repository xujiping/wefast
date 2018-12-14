package com.rb.cms.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * gson 工具类
 * com.voice.util
 *
 * @author MENGJUN
 * on 2017/4/6.
 */
public class GsonUtil {

    public static Map<String, Object> toMap(String jsonStr) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        return gson.fromJson(jsonStr, type);
    }

    public static List<Object> toList(String jsonStr) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Object>>() {
        }.getType();
        return gson.fromJson(jsonStr, type);
    }

    public static String toStr(Object bean) {
        return new Gson().toJson(bean);
    }

    public static Object toObject(String json, Class beanClass) {
        Gson gson = new Gson();
        Object res = gson.fromJson(json, beanClass);
        return res;
    }
}
