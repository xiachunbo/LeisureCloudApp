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
    @RequestMapping({"/app/fabu.html"})
    public String fabu(Model model, HashMap<String, Object> map) {
        map.put("index", "欢迎进入HTML页面");
        return "app/wl/fabu";
    }
    @RequestMapping({"/app/duanzi.html"})
    public String duanzi(Model model, HashMap<String, Object> map) {
        map.put("index", "欢迎进入HTML页面");
        return "app/wl/duanzi";
    }
    @RequestMapping({"/app/land.html"})
    public String land(Model model, HashMap<String, Object> map) {
        map.put("index", "欢迎进入HTML页面");
        return "app/wl/land";
    }
    @RequestMapping({"/app/pl.html"})
    public String pl(Model model, HashMap<String, Object> map) {
        map.put("index", "欢迎进入HTML页面");
        return "app/wl/pl";
    }
    @RequestMapping({"/app/search.html"})
    public String search(Model model, HashMap<String, Object> map) {
        map.put("index", "欢迎进入HTML页面");
        return "app/wl/search";
    }
    @RequestMapping({"/app/wuliao.html"})
    public String wuliao(Model model, HashMap<String, Object> map) {
        map.put("index", "欢迎进入HTML页面");
        return "app/wl/wuliao";
    }
    @RequestMapping({"/app/FM.html"})
    public String FM(Model model, HashMap<String, Object> map) {
        map.put("index", "欢迎进入HTML页面");
        return "app/wl/FM";
    }
}