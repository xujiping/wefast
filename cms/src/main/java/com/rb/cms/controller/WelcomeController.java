package com.rb.cms.controller;

import com.rb.cms.entity.CmsUser;
import com.rb.cms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

/**
 * 首页欢迎页面
 *
 * @author xujiping
 * @date 2018/6/11 14:49
 */
@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    @Autowired
    private CmsUserService cmsUserService;

    @Autowired
    private RedisService redisService;


    @GetMapping("")
    public String welcome(Model model) {
        // 当前用户
        CmsUser curUser = cmsUserService.getCurUser();
        model.addAttribute("username", curUser.getUsername());
        // 当前时间
        model.addAttribute("time", System.currentTimeMillis());
        // redis key数量
        Set<Object> redisKeys = redisService.keysPrefix(null);
        if (redisKeys != null) {
            model.addAttribute("keys", redisKeys.size());
        }
        return "welcome";
    }
}
