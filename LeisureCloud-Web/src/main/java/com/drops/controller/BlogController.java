package com.drops.controller;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.drops.tools.SingletonPool;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BlogController {

    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private RestTemplate restTemplate;
    /**
     *
     *  博客首页
     * @return
     */
    @RequestMapping(value = "/")
    public String pic_detail(Model model,@RequestParam(value = "jsonStr", required = false) String jsonStr,@RequestParam(value="pageNum", defaultValue="1") int pageNum,
                             @RequestParam(value="pageSize", defaultValue="10") int pageSize) {
        Logger.info("博客首页");
        List<String> nodes = SingletonPool.nodePool;
        int num = (int) (Math.random() * nodes.size() + 0.0D);
        String urlAndArgs = nodes.get(num);
        String url = "http://" + urlAndArgs + "/goHtmlByJson?jsonStr={jsonStr}&tableName={tableName}&pageNum={pageNum}&pageSize={pageSize}";
        Map<String, Object> paremMap = new HashMap<>();
        paremMap.put("tableName", "crm_news");
        paremMap.put("jsonStr", jsonStr);
        paremMap.put("pageNum", Integer.valueOf(pageNum));
        paremMap.put("pageSize", Integer.valueOf(pageSize));
        Map<String, Object> staff = (Map<String, Object>) this.restTemplate.postForObject(url, null, Map.class, paremMap);
        model.addAttribute("page", staff.get("page"));
        return "webblog/index";
    }
    /**
     *
     * 关于
     * @return
     */
    @RequestMapping(value = "/blogabout")
    public String about(Model model) {
        Logger.info("关于");
        return "webblog/about";
    }
    /**
     *
     * 文章
     * @return
     */
    @RequestMapping(value = "/blogartcle")
    public String artcle(Model model,@RequestParam(value = "jsonStr", required = false) String jsonStr,@RequestParam(value="pageNum", defaultValue="1") int pageNum,
                         @RequestParam(value="pageSize", defaultValue="10") int pageSize) {
        Logger.info("文章");
        List<String> nodes = SingletonPool.nodePool;
        int num = (int) (Math.random() * nodes.size() + 0.0D);
        String urlAndArgs = nodes.get(num);
        String url = "http://" + urlAndArgs + "/goHtmlByJson?jsonStr={jsonStr}&tableName={tableName}&pageNum={pageNum}&pageSize={pageSize}";
        Map<String, Object> paremMap = new HashMap<>();
        paremMap.put("tableName", "crm_news");
        paremMap.put("jsonStr", jsonStr);
        paremMap.put("pageNum", Integer.valueOf(pageNum));
        paremMap.put("pageSize", Integer.valueOf(pageSize));
        Map<String, Object> staff = (Map<String, Object>) this.restTemplate.postForObject(url, null, Map.class, paremMap);
        model.addAttribute("page", staff.get("page"));
        return "webblog/article";
    }
    /**
     *
     * 详情
     * @return
     */
    @RequestMapping(value = "/article_detail")
    public String detail(Model model,int id) {
        Logger.info("文章detail");
        List<String> nodes = SingletonPool.nodePool;
        int num = (int) (Math.random() * nodes.size() + 0.0D);
        String urlAndArgs = nodes.get(num);
        String url = "http://" + urlAndArgs + "/goHtmlByJson?jsonStr={jsonStr}&tableName={tableName}&pageNum={pageNum}&pageSize={pageSize}";
        Map<String, Object> paremMap = new HashMap<>();
        paremMap.put("tableName", "crm_news");
        JSONObject object = new JSONObject();
        object.put("id",id);
        paremMap.put("jsonStr", object.toJSONString());
        paremMap.put("pageNum", Integer.valueOf(0));
        paremMap.put("pageSize", Integer.valueOf(Integer.MAX_VALUE));
        Map<String, Object> staff = (Map<String, Object>) this.restTemplate.postForObject(url, null, Map.class, paremMap);
        String jsonString = JSON.toJSONString(staff.get("page"));
        PageInfo page = (PageInfo) JSON.parseObject(jsonString, PageInfo.class);
        model.addAttribute("page", page.getList().get(0));
        return "webblog/article_detail";
    }
    /**
     *
     * 留言
     * @return
     */
    @RequestMapping(value = "/blogcomment")
    public String comment(Model model) {
        Logger.info("留言");
        return "webblog/comment";
    }
    /**
     *
     * 成长
     * @return
     */
    @RequestMapping(value = "/blogmoodList")
    public String moodList(Model model,@RequestParam(value = "jsonStr", required = false) String jsonStr,@RequestParam(value="pageNum", defaultValue="1") int pageNum,
                           @RequestParam(value="pageSize", defaultValue="10") int pageSize) {
        Logger.info("成长");
        List<String> nodes = SingletonPool.nodePool;
        int num = (int) (Math.random() * nodes.size() + 0.0D);
        String urlAndArgs = nodes.get(num);
        String url = "http://" + urlAndArgs + "/goHtmlByJson?jsonStr={jsonStr}&tableName={tableName}&pageNum={pageNum}&pageSize={pageSize}";
        Map<String, Object> paremMap = new HashMap<>();
        paremMap.put("tableName", "crm_news");
        paremMap.put("jsonStr", jsonStr);
        paremMap.put("pageNum", Integer.valueOf(pageNum));
        paremMap.put("pageSize", Integer.valueOf(pageSize));
        Map<String, Object> staff = (Map<String, Object>) this.restTemplate.postForObject(url, null, Map.class, paremMap);
        model.addAttribute("page", staff.get("page"));
        return "webblog/moodList";
    }
}
