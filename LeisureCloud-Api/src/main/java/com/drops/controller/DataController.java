package com.drops.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.drops.common.ratelimit.RateLimit;
import com.drops.service.GenericService;
import com.drops.service.HelloService;
import com.drops.service.TableService;
import com.drops.tools.AjaxResult;
import com.drops.tools.YmlUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DataController {
    public Logger logger = Logger.getLogger(DataController.class);


    @Autowired
    private TableService tableService;


    @Autowired
    private GenericService genericService;

    @Autowired
    private  HelloService helloService;

    @Value("${liang.aaa}")
    private String liang;

    @RateLimit(limitNum = 20.0D)
    @RequestMapping({"/goHtml"})
    public String selectByParem(@RequestParam(value = "tableName", required = false) String tableName, @RequestParam(value = "jsonStr", required = false) String jsonStr, @RequestParam(value = "pagehtml", required = false) String pagehtml, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, Model model) {
        String jsonString = "";
        Map<String, Object> parems = new HashMap<>();
        try {
            String fields = "";
            String remarks = "";
            if (jsonStr != null && !"".equals(jsonStr)) {
                parems = (Map<String, Object>) JSON.parse(jsonStr);
            }
            if (parems.keySet().size() > 0 || tableName.equals("sys_menu")) {
                pageSize = Integer.MAX_VALUE;
            }
            Map<String, Object> parems1 = new HashMap<>();
            String columnfields = "filed,remark";
            parems1.put("tablename", tableName);
            List<String> colnumLists = new ArrayList<>();
            List<String> colnumNameLists = new ArrayList<>();
            List<LinkedHashMap<String, Object>> columns = this.genericService.selectByParem("sys_table_mapper", columnfields, parems1);
            if (columns.size() > 0) {
                parems1 = columns.get(0);
                fields = parems1.get("filed").toString();
                remarks = parems1.get("remark").toString();
                colnumLists = Arrays.asList(fields.split(","));
                colnumNameLists = Arrays.asList(remarks.split(","));
            } else {
                PageHelper.startPage(0, 2147483647);
                List<Map<String, Object>> list1 = this.tableService.listTableColumn(tableName);
                PageInfo<Map<String, Object>> page1 = new PageInfo(list1);
                String _columns = JSON.toJSONString(page1.getList(), new SerializerFeature[]{SerializerFeature.WriteNullStringAsEmpty});
                JSONArray columnsArr = JSONArray.parseArray(_columns);
                for (Object aColumnsArr : columnsArr) {
                    JSONObject job = (JSONObject) aColumnsArr;
                    fields = fields + job.get("COLUMN_NAME").toString() + ",";
                }
                fields = fields.substring(0, fields.length() - 1);
                colnumLists = Arrays.asList(fields.split(","));
                if (colnumLists.size() > 5) {
                    colnumLists = colnumLists.subList(0, 6);
                }
            }

            PageHelper.startPage(pageNum, pageSize);
            List<LinkedHashMap<String, Object>> list = this.genericService.selectByParem(tableName, fields, parems);
            PageInfo<LinkedHashMap<String, Object>> page = new PageInfo(list);
            jsonString = JSON.toJSONString(page);
            PageInfo staff = (PageInfo) JSON.parseObject(jsonString, PageInfo.class);
            model.addAttribute("page", staff);
            model.addAttribute("colnumList", colnumLists);
            model.addAttribute("colnumNameLists", colnumNameLists);
            model.addAttribute("tableName", tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "plateform/datatables/" + pagehtml;
    }


    @RateLimit(limitNum = 20.0D)
    @ResponseBody
    @RequestMapping({"/goHtmlByJson"})
    public Map<String, Object> goHtmlByJson(@RequestParam(value = "tableName", required = false) String tableName, @RequestParam(value = "jsonStr", required = false) String jsonStr, @RequestParam(value = "pagehtml", required = false) String pagehtml, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, Model model) {
        String jsonString = "";
        Map<String, Object> parems = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        try {
            String fields = "";
            String remarks = "";
            if (jsonStr != null && !"".equals(jsonStr)) {
                parems = (Map<String, Object>) JSON.parse(jsonStr);
            }
            if (parems.keySet().size() > 0 || tableName.equals("sys_menu")) {
                pageSize = Integer.MAX_VALUE;
            }
            Map<String, Object> parems1 = new HashMap<>();
            String columnfields = "filed,remark";
            parems1.put("tablename", tableName);
            List<String> colnumLists = new ArrayList<>();
            List<String> colnumNameLists = new ArrayList<>();
            List<LinkedHashMap<String, Object>> columns = this.genericService.selectByParem("sys_table_mapper", columnfields, parems1);
            if (columns.size() > 0) {
                parems1 = columns.get(0);
                fields = parems1.get("filed").toString();
                remarks = parems1.get("remark").toString();
                colnumLists = Arrays.asList(fields.split(","));
                colnumNameLists = Arrays.asList(remarks.split(","));
            } else {
                PageHelper.startPage(0, 2147483647);
                List<Map<String, Object>> list1 = this.tableService.listTableColumn(tableName);
                PageInfo<Map<String, Object>> page1 = new PageInfo(list1);
                String _columns = JSON.toJSONString(page1.getList(), new SerializerFeature[]{SerializerFeature.WriteNullStringAsEmpty});
                JSONArray columnsArr = JSONArray.parseArray(_columns);
                for (Object aColumnsArr : columnsArr) {
                    JSONObject job = (JSONObject) aColumnsArr;
                    fields = fields + job.get("COLUMN_NAME").toString() + ",";
                }
                fields = fields.substring(0, fields.length() - 1);
                colnumLists = Arrays.asList(fields.split(","));
                if (colnumLists.size() > 5) {
                    colnumLists = colnumLists.subList(0, 6);
                }
            }

            PageHelper.startPage(pageNum, pageSize);
            List<LinkedHashMap<String, Object>> list = this.genericService.selectByParem(tableName, fields, parems);
            PageInfo<LinkedHashMap<String, Object>> page = new PageInfo(list);
            jsonString = JSON.toJSONString(page);
            PageInfo staff = (PageInfo) JSON.parseObject(jsonString, PageInfo.class);
            map.put("page", staff);
            map.put("colnumList", colnumLists);
            map.put("colnumNameLists", colnumNameLists);
            map.put("tableName", tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    @ResponseBody
    @RequestMapping({"/insert"})
    public AjaxResult insert(@RequestParam(value = "tableName", required = false) String tableName, @RequestParam(value = "jsonStr", required = false) String jsonStr) {
        Map<String, Object> parems = new HashMap<>();
        try {
            if (jsonStr != null && !"".equals(jsonStr)) {
                parems = (Map<String, Object>) JSON.parse(jsonStr);
            }
            this.genericService.insert(tableName, parems);
        } catch (Exception e) {
            return AjaxResult.error("失败");
        }
        return AjaxResult.success("成功");
    }


    @RequestMapping({"/updateByParem"})
    public String updateByParem(@RequestParam(value = "tableName", required = false) String tableName, @RequestParam(value = "paremStr", required = false) String paremStr, @RequestParam(value = "dataStr", required = false) String dataStr) {
        Map<String, Object> paremMap = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        try {
            if (paremStr != null && !"".equals(paremStr)) {
                paremMap = (Map<String, Object>) JSON.parse(paremStr);
            }
            if (dataStr != null && !"".equals(dataStr)) {
                dataMap = (Map<String, Object>) JSON.parse(dataStr);
            }
            this.genericService.updateByParem(tableName, paremMap, dataMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping({"/deleteByParem"})
    public String deleteByParem(@RequestParam(value = "tableName", required = false) String tableName, @RequestParam(value = "paremStr", required = false) String paremStr) {
        Map<String, Object> parems = new HashMap<>();
        try {
            if (paremStr != null && !"".equals(paremStr)) {
                parems = (Map<String, Object>) JSON.parse(paremStr);
            } else {
                return null;
            }
            this.genericService.deleteByParem(tableName, parems);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @ResponseBody
    @RequestMapping({"/tests"})
    public AjaxResult insert() {
        Map<String, Object> parems = new HashMap<>();
        this.logger.info("test!");
        return AjaxResult.success("成功");
    }

    @ResponseBody
    @RequestMapping({"/liang"})
    public AjaxResult liang() {
        YmlUtils configs = new YmlUtils();
        try {

            boolean b = configs.updateYaml("liang.aaa", "Intel Core 555555"+Math.random(), "application.yml");
            System.out.println(b);
            System.out.println(helloService.hello("1111"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,Object> map = configs.getYamlToMap("application.yml");
        System.out.println(configs.getValue("liang.aaa",map));
        return AjaxResult.success("成功");
    }
}