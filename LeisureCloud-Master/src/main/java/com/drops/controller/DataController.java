package com.drops.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.drops.tools.AjaxResult;
import com.drops.tools.SingletonPool;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class DataController {
    public Logger logger = Logger.getLogger(DataController.class);
    @Autowired
    private RestTemplate restTemplate;

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }


    @RequestMapping({"/goHtml"})
    public String selectByParem(@RequestParam(value = "tableName", required = false) String tableName, @RequestParam(value = "jsonStr", required = false) String jsonStr, @RequestParam(value = "pagehtml", required = false) String pagehtml, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, Model model) {
        String jsonString = "";
        Map<String, Object> parems = new HashMap<>();
        try {
            HttpSession session = getRequest().getSession();
            String userJson = (String) session.getAttribute("user_info_in_the_session");
            if (userJson == null) {
                return "plateform/page_login";
            }
            List<String> nodes = SingletonPool.nodePool;
            int num = (int) (Math.random() * nodes.size() + 0.0D);
            String urlAndArgs = nodes.get(num);
            String url = "http://" + urlAndArgs + "/goHtmlByJson?jsonStr={jsonStr}&tableName={tableName}&pageNum={pageNum}&pageSize={pageSize}";


            Map<String, Object> paremMap = new HashMap<>();
            paremMap.put("tableName", tableName);
            paremMap.put("jsonStr", jsonStr);
            paremMap.put("pageNum", Integer.valueOf(pageNum));
            paremMap.put("pageSize", Integer.valueOf(pageSize));
            Map<String, Object> staff = (Map<String, Object>) this.restTemplate.postForObject(url, null, Map.class, paremMap);
            model.addAttribute("page", staff.get("page"));
            model.addAttribute("colnumList", staff.get("colnumList"));
            model.addAttribute("colnumNameLists", staff.get("colnumNameLists"));
            model.addAttribute("tableName", staff.get("tableName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "plateform/datatables/" + pagehtml;
    }


    @ResponseBody
    @RequestMapping({"/insert"})
    public AjaxResult insert(@RequestParam(value = "tableName", required = false) String tableName, @RequestParam(value = "jsonStr", required = false) String jsonStr) {
        Map<String, Object> parems = new HashMap<>();


        return AjaxResult.success("成功");
    }


    @RequestMapping({"/updateByParem"})
    public String updateByParem(@RequestParam(value = "tableName", required = false) String tableName, @RequestParam(value = "paremStr", required = false) String paremStr, @RequestParam(value = "dataStr", required = false) String dataStr) {
        Map<String, Object> paremMap = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();


        return null;
    }


    @RequestMapping({"/deleteByParem"})
    public String deleteByParem(@RequestParam(value = "tableName", required = false) String tableName, @RequestParam(value = "paremStr", required = false) String paremStr) {
        Map<String, Object> parems = new HashMap<>();


        return null;
    }


    @ResponseBody
    @RequestMapping({"/tests"})
    public AjaxResult insert() {
        Map<String, Object> parems = new HashMap<>();
        this.logger.info("test!");
        return AjaxResult.success("成功");
    }


    @RequestMapping({"/goServices"})
    public String goServices(@RequestParam(value = "tableName", required = false) String tableName, @RequestParam(value = "jsonStr", required = false) String jsonStr, @RequestParam(value = "pagehtml", required = false) String pagehtml, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, @RequestParam(value = "type", defaultValue = "0") int type, Model model) {
        String jsonString = "";
        Map<String, Object> parems = new HashMap<>();
        try {
            HttpSession session = getRequest().getSession();
            String userJson = (String) session.getAttribute("user_info_in_the_session");
            if (userJson == null) {
                return "plateform/page_login";
            }
            List<String> nodes = SingletonPool.nodePool;
            if (type == 1) {
                nodes = SingletonPool.getwayPool;
            }
            List<LinkedHashMap<String, Object>> list = new ArrayList<>();
            for (String node : nodes) {
                LinkedHashMap<String, Object> map = new LinkedHashMap<>();
                map.put("ip", node.split(":")[0]);
                if (node.contains("-->")) {
                    node = node.split("-->")[0];
                    map.put("port", node.split(":")[1]);
                }else{
                    map.put("port", node.split(":")[1]);
                }
                list.add(map);
            }
            PageInfo<LinkedHashMap<String, Object>> page = new PageInfo(list);
            List<String> colnumList = new ArrayList<>();
            colnumList.add("ip");
            colnumList.add("port");
            List<String> colnumNameLists = new ArrayList<>();
            colnumNameLists.add("服务地址");
            colnumNameLists.add("服务端口");
            model.addAttribute("page", page);
            model.addAttribute("colnumList", colnumList);
            model.addAttribute("colnumNameLists", colnumNameLists);
            model.addAttribute("tableName", tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "plateform/datatables/" + pagehtml;
    }

    @ResponseBody
    @RequestMapping({"/goHtmlByJson"})
    public Map<String, Object> goHtmlByJson(@RequestParam(value = "tableName", required = false) String tableName, @RequestParam(value = "jsonStr", required = false) String jsonStr, @RequestParam(value = "pagehtml", required = false) String pagehtml, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, Model model) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<String> nodes = SingletonPool.nodePool;
            int num = (int) (Math.random() * nodes.size() + 0.0D);
            String urlAndArgs = nodes.get(num);
            String url = "http://" + urlAndArgs + "/goHtmlByJson?jsonStr={jsonStr}&tableName={tableName}&pageNum={pageNum}&pageSize={pageSize}";
            Map<String, Object> paremMap = new HashMap<>();
            paremMap.put("tableName", tableName);
            paremMap.put("jsonStr", jsonStr);
            paremMap.put("pageNum", Integer.valueOf(pageNum));
            paremMap.put("pageSize", Integer.valueOf(pageSize));
            Map<String, Object> staff = (Map<String, Object>) this.restTemplate.postForObject(url, null, Map.class, paremMap);
            map.put("page", staff.get("page"));
            map.put("colnumList", staff.get("colnumList"));
            map.put("colnumNameLists", staff.get("colnumNameLists"));
            map.put("tableName", staff.get("tableName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
