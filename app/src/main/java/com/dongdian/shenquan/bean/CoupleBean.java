package com.dongdian.shenquan.bean;

import java.util.List;

public class CoupleBean {

    /**
     * id : 1
     * app_id : 461
     * name : 教程
     * parent_id : null
     * icon : null
     * created_at : 2019-12-03 17:36:16
     * updated_at : 2019-12-03 17:36:16
     * children : []
     */

    private int id;
    private int app_id;
    private String name;
    private Object parent_id;
    private Object icon;
    private String created_at;
    private String updated_at;
    private List<?> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getParent_id() {
        return parent_id;
    }

    public void setParent_id(Object parent_id) {
        this.parent_id = parent_id;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public List<?> getChildren() {
        return children;
    }

    public void setChildren(List<?> children) {
        this.children = children;
    }
}
