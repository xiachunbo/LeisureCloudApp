package com.drops.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class DruidController {

    @Autowired
    JdbcTemplate template;

    @GetMapping("/query")
    @ResponseBody
    public List<Map<String, Object>> map(){
        List<Map<String, Object>> list = template.queryForList("select * from crm_news");
        return list;
    }

}