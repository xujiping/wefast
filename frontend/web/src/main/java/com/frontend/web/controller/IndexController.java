package com.frontend.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xujiping
 * @date 2018/7/4 18:01
 */
@Controller
public class IndexController {

    @GetMapping({"", "/index"})
    public String index(){
        return "index";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
