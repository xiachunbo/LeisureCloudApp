package com.drops.controller;

import com.alibaba.fastjson.JSON;
import com.drops.domain.SysMenu;
import com.drops.domain.UserInfo;
import com.drops.service.*;
import com.drops.tools.AjaxResult;
import com.drops.tools.ListUtils;
import com.drops.tools.ListUtilsHook;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class MenuController {
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private GenericService genericService;
    public Logger logger = Logger.getLogger(MenuController.class);


    @RequestMapping({"/login"})
    public String homeHtml(HashMap<String, Object> map) {
        map.put("home", "");
        return "plateform/page_login";
    }

    @RequestMapping({"/html/welcome"})
    public String welcome(Model model, HashMap<String, Object> map) {
        map.put("index", "");
        return "plateform/html/welcome";
    }


    @RequestMapping({"/admin-add"})
    public String adminadd(Model model, HashMap<String, Object> map) {
        map.put("index", "");
        return "plateform/html/admin-add";
    }


    @RequestMapping({"/role-add"})
    public String roleadd(Model model, HashMap<String, Object> map) {
        map.put("index", "");
        return "plateform/html/role-add";
    }

    @RequestMapping({"/lyear_pages_edit_pwd"})
    public String memberpassword(Model model, HashMap<String, Object> map) {
        map.put("index", "");
        return "plateform/html/member-password";
    }


    @RequestMapping({"/admin-edit"})
    public String adminedit(Model model, HashMap<String, Object> map) {
        map.put("index", "");
        return "plateform/html/admin-edit";
    }
    @ResponseBody
    @RequestMapping(value = {"/loginAjax"}, produces = {"application/json; charset=utf-8"}, method = {RequestMethod.POST})
    public AjaxResult login(String username, String password) {
        Map<String, Object> parems = new HashMap<>();
        parems.put("username", username);
        parems.put("password", password);
        List<LinkedHashMap<String, Object>> list = this.genericService.selectByParem("crm_sys_user", "*", parems);
        if (list.size() > 0) {
            Map<String, Object> userMap = list.get(0);
            parems = new HashMap<>();
            parems.put("userId", userMap.get("id"));
            List<LinkedHashMap<String, Object>> rules = this.genericService.selectByParem("crm_sys_user_role", "*", parems);
            if (rules.size() > 0) {
                Map<String, Object> ruleMap = rules.get(0);
                long roleId = Long.valueOf(ruleMap.get("roleId").toString()).longValue();
                long userId = Long.valueOf(userMap.get("id").toString()).longValue();
                UserInfo userInfo = new UserInfo(userId, (String)userMap.get("username"), roleId);
                HttpSession session = getRequest().getSession();
                session.setAttribute("user_info_in_the_session", JSON.toJSONString(userInfo));
                return AjaxResult.successData(200, JSON.toJSONString(userInfo));
            }
            return AjaxResult.error("登陆失败");
        }

        return AjaxResult.error("登陆失败");
    }
    private HttpServletRequest getRequest() { return ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest(); }

    @RequestMapping(value = {"/loginOut"}, produces = {"application/json; charset=utf-8"}, method = {RequestMethod.GET})
    public String loginOut() {
        HttpSession session = getRequest().getSession();
        session.removeAttribute("user_info_in_the_session");
        return "plateform/page_login";
    }
    @ResponseBody
    @RequestMapping(value = {"/indexForJson"}, produces = {"application/json; charset=utf-8"}, method = {RequestMethod.POST, RequestMethod.GET})
    public String indexForJson(@RequestParam(value = "ids", required = false) String ids, @RequestParam(value = "tableName", required = false) String tableName, @RequestParam(value = "fields", defaultValue = "*") String fields, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "2147483647") int pageSize, @RequestParam(value = "parem", required = false) String parem, Model model, HttpServletRequest request, HttpServletResponse response, HashMap<String, Object> map) {
        PageHelper.startPage(pageNum, pageSize);
        UserInfo userInfo = new UserInfo(1L, "admin", 1L);
        List<SysMenu> list = this.sysMenuService.listByUserId(Long.valueOf(userInfo.getRole()));
        final List<SysMenu> menus  = ListUtils.filter(list, new ListUtilsHook<SysMenu>(){
            @Override
            public boolean test(SysMenu l) {
                return l.getType().equals(1);
            }
        });

        //支持多级菜单
        List<SysMenu> firstLevel  = ListUtils.filter(menus, new ListUtilsHook<SysMenu>(){
            @Override
            public boolean test(SysMenu p) {
                return p.getParentId().equals(0);
            }
        });
        for (SysMenu m : firstLevel) {
            setChild(m, menus);
        }
        List<LinkedHashMap<String, Object>> menuList = ListUtils.EntityConvertMap(firstLevel);
        PageInfo<LinkedHashMap<String, Object>> page = new PageInfo(menuList);
        String jsonString = JSON.toJSONString(page);
        PageInfo staff = (PageInfo)JSON.parseObject(jsonString, PageInfo.class);
        model.addAttribute("page", staff);
        JSONObject jsonObj = new JSONObject(staff);
        return jsonObj.toString();
    }
    @RequestMapping("/index")
    public String helloHtml(@RequestParam(value="ids",required = false) String ids,
                            @RequestParam(value="tableName",required = false) String tableName,
                            @RequestParam(value="fields",defaultValue="*") String fields,
                            @RequestParam(value="pageNum", defaultValue="0") int pageNum,
                            @RequestParam(value="pageSize", defaultValue=Integer.MAX_VALUE+"") int pageSize ,
                            @RequestParam(value="parem",required = false) String parem,Model model,HashMap<String, Object> map) {

        //LoginUser loginUser = UserUtil.getLoginUser();
        //Long id = Long.valueOf(String.valueOf(loginUser.getId()));
        PageHelper.startPage(pageNum, pageSize);
        List<SysMenu> list = sysMenuService.listByUserId(1l);
        final List<SysMenu> menus  = ListUtils.filter(list, new ListUtilsHook<SysMenu>(){
            @Override
            public boolean test(SysMenu l) {
                return l.getType().equals(1);
            }
        });

        //支持多级菜单
        List<SysMenu> firstLevel  = ListUtils.filter(menus, new ListUtilsHook<SysMenu>(){
            @Override
            public boolean test(SysMenu p) {
                return p.getParentId().equals(0);
            }
        });

        for(SysMenu m :firstLevel){
            setChild(m, menus);
        }
        List<LinkedHashMap<String, Object>> menuList = ListUtils.EntityConvertMap(firstLevel);
        PageInfo<LinkedHashMap<String, Object>> page = new PageInfo<LinkedHashMap<String, Object>>(menuList);
        String jsonString = JSON.toJSONString(page);
        PageInfo staff = JSON.parseObject(jsonString, PageInfo.class);
        model.addAttribute("page",staff);
        return "plateform/html/index1";
    }
    /**
     * 设置子元素
     * @param m
     * @param menus
     */
    private void setChild(final SysMenu m, List<SysMenu> menus) {
        List<SysMenu> child =  ListUtils.filter(menus, new ListUtilsHook<SysMenu>(){
            @Override
            public boolean test(SysMenu a) {
                return a.getParentId().equals(m.getMenu_id());
            }
        });
        m.setChild(child);
        if (!CollectionUtils.isEmpty(child)) {
            for(SysMenu menu :child){
                setChild(menu, menus);
            }
        }
    }


}
