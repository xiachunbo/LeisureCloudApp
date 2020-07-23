package com.drops.controller.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.drops.config.datasource.DataSourceConfig;
import com.drops.config.datasource.DynamicDataSource;
import com.drops.controller.datasource.DataSourceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DataSourceController {
    @Autowired
    private DataSourceConfig dataSourceConfig;

    @ResponseBody
    @RequestMapping({"/checkDataSource"})
    public String selectByParem(String sourceName) {
        boolean result = this.dataSourceConfig.reload(sourceName).booleanValue();
        return "{'ok':'true'}";
    }
}