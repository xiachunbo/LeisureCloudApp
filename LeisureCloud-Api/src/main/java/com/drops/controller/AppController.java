package com.drops.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AppController {
    @RequestMapping({"/app/index"})
    public String echarts1(Model model, HashMap<String, Object> map) {
        map.put("index", "欢迎进入HTML页面");
        return "app/wl/index";
    }
}