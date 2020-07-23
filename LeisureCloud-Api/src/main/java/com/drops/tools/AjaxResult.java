package com.drops.tools;

import com.drops.tools.AjaxResult;

import java.util.HashMap;


public class AjaxResult
        extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public static AjaxResult error() {
        return error(1, "操作失败");
    }


    public static AjaxResult error(String msg) {
        return error(500, msg);
    }


    public static AjaxResult error(int code, String msg) {

        AjaxResult json = new AjaxResult();
        json.put("code", Integer.valueOf(code));
        json.put("msg", msg);
        return json;
    }


    public static AjaxResult success(String msg) {
        AjaxResult json = new AjaxResult();
        json.put("msg", msg);
        json.put("code", Integer.valueOf(200));
        return json;
    }


    public static AjaxResult success() {
        return success("操作成功");
    }


    public static AjaxResult successData(int key, Object value) {
        AjaxResult json = new AjaxResult();
        json.put("msg", Integer.valueOf(key));
        json.put("code", Integer.valueOf(200));
        json.put("key", Integer.valueOf(key));
        json.put("data", value);
        return json;
    }


    public AjaxResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}