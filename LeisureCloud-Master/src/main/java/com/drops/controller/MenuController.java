package com.drops.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.drops.domain.UserInfo;
import com.drops.tools.SingletonPool;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class MenuController {
    @Autowired
    private RestTemplate restTemplate;
    public Logger logger = Logger.getLogger(MenuController.class);


    @RequestMapping({"/login"})
    public String homeHtml(HashMap<String, Object> map) {
        map.put("home", "login");
        return "plateform/page_login";
    }

    @RequestMapping({"/html/welcome"})
    public String welcome(Model model, HashMap<String, Object> map) {
        map.put("index", "welcome");
        return "plateform/html/welcome";
    }


    @RequestMapping({"/admin-add"})
    public String adminadd(Model model, HashMap<String, Object> map) {
        map.put("index", "adminadd");
        return "plateform/html/admin-add";
    }


    @RequestMapping({"/role-add"})
    public String roleadd(Model model, HashMap<String, Object> map) {
        map.put("index", "index");
        return "plateform/html/role-add";
    }

    @RequestMapping({"/lyear_pages_edit_pwd"})
    public String memberpassword(Model model, HashMap<String, Object> map) {
        map.put("index", "index");
        return "plateform/html/member-password";
    }


    @RequestMapping({"/admin-edit"})
    public String adminedit(Model model, HashMap<String, Object> map) {
        map.put("index", "adminedit");
        return "plateform/html/admin-edit";
    }


    @ResponseBody
    @RequestMapping(value = {"/loginAjax"}, produces = {"application/json; charset=utf-8"}, method = {RequestMethod.POST})
    public String login(String username, String password) {
        List<String> nodes = SingletonPool.nodePool;
        int num = (int) (Math.random() * nodes.size() + 0.0D);
        String urlAndArgs = nodes.get(num);
        String url = "http://" + urlAndArgs + "/loginAjax?username={username}&password={password}";

        Map<String, Object> paremMap = new HashMap<>();
        paremMap.put("username", username);
        paremMap.put("password", password);
        String json = (String) this.restTemplate.postForObject(url, null, String.class, paremMap);
        JSONObject jsStr = JSONObject.parseObject(json);
        if (jsStr.getString("code").equals("200")) {
            UserInfo userInfo = (UserInfo) JSON.parseObject(JSON.parse(jsStr.getString("data")).toString(), UserInfo.class);
            HttpSession session = getRequest().getSession();
            session.setAttribute("user_info_in_the_session", JSON.toJSONString(userInfo));
        }
        return json;
    }

    @RequestMapping(value = {"/loginOut"}, produces = {"application/json; charset=utf-8"}, method = {RequestMethod.GET})
    public String loginOut() {
        HttpSession session = getRequest().getSession();
        session.removeAttribute("user_info_in_the_session");
        return "plateform/page_login";
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }


    @RequestMapping(value = {"/index"}, produces = {"text/html; charset=utf-8"}, method = {RequestMethod.GET})
    public String helloHtml(@RequestParam(value = "ids", required = false) String ids, @RequestParam(value = "tableName", required = false) String tableName, @RequestParam(value = "fields", defaultValue = "*") String fields, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "2147483647") int pageSize, @RequestParam(value = "parem", required = false) String parem, Model model, HttpServletRequest request, HttpServletResponse response, HashMap<String, Object> map) {
        HttpSession session = getRequest().getSession();
        String userJson = (String) session.getAttribute("user_info_in_the_session");

        List<String> nodes = SingletonPool.nodePool;
        int num = (int) (Math.random() * nodes.size() + 0.0D);
        String urlAndArgs = nodes.get(num);
        String url = "http://" + urlAndArgs + "/indexForJson?ids={ids}&tableName={tableName}&fields={fields}&pageNum={pageNum}&pageSize={pageSize}&parem={parem}";


        Map<String, Object> paremMap = new HashMap<>();
        paremMap.put("ids", ids);
        paremMap.put("tableName", tableName);
        paremMap.put("fields", fields);
        paremMap.put("pageNum", Integer.valueOf(pageNum));
        paremMap.put("pageSize", Integer.valueOf(pageSize));
        paremMap.put("parem", parem);
        PageInfo staff = (PageInfo) this.restTemplate.postForObject(url, null, PageInfo.class, paremMap);
        model.addAttribute("page", staff);
        return "plateform/html/index1";
    }
}
