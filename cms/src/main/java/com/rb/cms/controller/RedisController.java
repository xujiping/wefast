package com.rb.cms.controller;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Maps;
import com.rb.cms.entity.Redis;
import com.rb.cms.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * redis控制器
 *
 * @author xujiping
 * @date 2018/11/19 18:17
 */
@Controller
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @GetMapping("index")
    public String index() {
        return "redis/list";
    }

    /**
     * 查询列表.
     *
     * @return Map
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public Object list(@RequestParam(required = false, value = "key") String key,
                       @RequestParam(required = false, value = "type") String type) {
        Redis redis;
        long total = 0;
        List<Redis> list = new ArrayList<>();
        // 一个类别的所有集合
        Set<Object> keys;
        keys = redisService.keysPrefix(type);
        if (keys != null && keys.size() > 0) {
            for (Object object : keys) {
                redis = new Redis();
                redis.setKey(JSONArray.toJSONString(object));
                list.add(redis);
            }
        }
        Map<String, Object> result = Maps.newHashMapWithExpectedSize(2);
        result.put("rows", list);
        result.put("total", total);
        return result;
    }
}
