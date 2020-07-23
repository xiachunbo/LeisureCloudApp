package com.drops.common.base;

import com.drops.common.base.BaseController;
import com.drops.tools.AjaxResult;
import com.drops.tools.StringUtils;

import java.beans.PropertyEditor;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Controller
public class BaseController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, (PropertyEditor) new CustomDateEditor(dateFormat, true));
    }

    protected AjaxResult toAjax(int rows) {
        return (rows > 0) ? success() : error();
    }

    public AjaxResult success() {
        return AjaxResult.success();
    }

    public AjaxResult error() {
        return AjaxResult.error();
    }

    public AjaxResult success(String message) {
        return AjaxResult.success(message);
    }

    public AjaxResult error(String message) {
        return AjaxResult.error(message);
    }

    public AjaxResult error(int code, String message) {
        return AjaxResult.error(code, message);
    }

    public AjaxResult retobject(int code, Object data) {
        return AjaxResult.successData(code, data);
    }

    public String redirect(String url) {
        return StringUtils.format("redirect:{}", new Object[]{url});
    }
}
