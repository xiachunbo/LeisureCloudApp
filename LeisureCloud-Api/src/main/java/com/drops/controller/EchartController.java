package com.drops.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class EchartController {
    @RequestMapping({"/echarts1"})
    public String echarts1(Model model, HashMap<String, Object> map) {
        map.put("index", "欢迎进入HTML页面");
        return "plateform/html/echarts1";
    }

    @RequestMapping({"/echarts2"})
    public String echarts2(Model model, HashMap<String, Object> map) {
        map.put("index", "欢迎进入HTML页面");
        return "plateform/html/echarts2";
    }

    @RequestMapping({"/echarts3"})
    public String echarts3(Model model, HashMap<String, Object> map) {
        map.put("index", "欢迎进入HTML页面");
        return "plateform/html/echarts3";
    }

    @RequestMapping({"/echarts4"})
    public String echarts4(Model model, HashMap<String, Object> map) {
        map.put("index", "欢迎进入HTML页面");
        return "plateform/html/echarts4";
    }

    @RequestMapping({"/echarts5"})
    public String echarts5(Model model, HashMap<String, Object> map) {
        map.put("index", "欢迎进入HTML页面");
        return "plateform/html/echarts5";
    }

    @RequestMapping({"/echarts6"})
    public String echarts6(Model model, HashMap<String, Object> map) {
        map.put("index", "欢迎进入HTML页面");
        return "plateform/html/echarts6";
    }

    @RequestMapping({"/echarts7"})
    public String echarts7(Model model, HashMap<String, Object> map) {
        map.put("index", "欢迎进入HTML页面");
        return "plateform/html/echarts7";
    }

    @RequestMapping({"/echarts8"})
    public String echarts8(Model model, HashMap<String, Object> map) {
        map.put("index", "欢迎进入HTML页面");
        return "plateform/html/echarts8";
    }
}