package com.frontend.store.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xujiping
 * @date 2018/6/23 16:25
 */
@Controller
public class IndexController {

    @GetMapping("")
    public String index(){
        return "index";
    }

    @GetMapping("/goods/detail/{id}")
    public String detail(@PathVariable(value = "id") String id){
        return "goodsDetail";
    }
}
