package com.dongdian.shenquan.bean;

import java.util.List;

public class CheckVersionBean {

    /**
     * id : 1
     * title : test
     * sub_title :
     * features : ["修改了bug","新增了功能"]
     * cover :
     * download_link : http://www.baidu.com
     * app_version : 12
     * update_type : 1
     * version_name : v1.12
     * apk_size : 7340032
     * apk_secret : 5865cbb0915a2aacac981520229b7407
     */

    private int id;
    private String title;
    private String sub_title;
    private String cover;
    private String download_link;
    private String app_version;
    private int update_type;
    private String version_name;
    private int apk_size;
    private String apk_secret;
    private List<String> features;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDownload_link() {
        return download_link;
    }

    public void setDownload_link(String download_link) {
        this.download_link = download_link;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public int getUpdate_type() {
        return update_type;
    }

    public void setUpdate_type(int update_type) {
        this.update_type = update_type;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public int getApk_size() {
        return apk_size;
    }

    public void setApk_size(int apk_size) {
        this.apk_size = apk_size;
    }

    public String getApk_secret() {
        return apk_secret;
    }

    public void setApk_secret(String apk_secret) {
        this.apk_secret = apk_secret;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }
}
