package com.drops.domain;

import com.drops.domain.SysMenu;

import java.util.List;

public class SysMenu {
    private int menu_id;
    private String menu_name;
    private Integer parentid;
    private String parentname;
    private String menu_url;
    private String icon;
    private Integer type;
    private List<SysMenu> child;

    public int getMenu_id() {
        return this.menu_id;
    }


    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }


    public String getMenu_name() {
        return this.menu_name;
    }


    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }


    public String getMenu_url() {
        return this.menu_url;
    }


    public void setMenu_url(String menu_url) {
        this.menu_url = menu_url;
    }


    public Integer getParentId() {
        return this.parentid;
    }

    public void setParentId(Integer parentId) {
        this.parentid = parentId;
    }


    public String getParentname() {
        return this.parentname;
    }


    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getIcon() {
        return this.icon;
    }


    public void setIcon(String icon) {
        this.icon = icon;
    }


    public Integer getType() {
        return this.type;
    }


    public void setType(Integer type) {
        this.type = type;
    }


    public List<SysMenu> getChild() {
        return this.child;
    }


    public void setChild(List<SysMenu> child) {
        this.child = child;
    }
}