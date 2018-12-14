package com.demo.oauth.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xujiping
 * @date 2018/6/8 16:21
 */
@Controller
public class IndexController {

    @GetMapping("")
    public String index() {
        return "index";
    }
}
