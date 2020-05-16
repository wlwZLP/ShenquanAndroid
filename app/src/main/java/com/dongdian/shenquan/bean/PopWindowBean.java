package com.dongdian.shenquan.bean;

public class PopWindowBean {

    /**
     * id : 51
     * title : test
     * cover : http://img.daff9.cn/biyingniao/images/other/1912/5dedb50db8083.png
     * aspect_ratio : 1:1.2
     * bg_color : null
     * need_login : 0
     * target_type : 1
     */

    private int id;
    private String title;
    private String cover;
    private String aspect_ratio;
    private Object bg_color;
    private int need_login;
    private int target_type;
    private HotWordsBean.HotSearchWordsBean.TargetBean target;

    public HotWordsBean.HotSearchWordsBean.TargetBean getTarget() {
        return target;
    }

    public void setTarget(HotWordsBean.HotSearchWordsBean.TargetBean target) {
        this.target = target;
    }

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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getAspect_ratio() {
        return aspect_ratio;
    }

    public void setAspect_ratio(String aspect_ratio) {
        this.aspect_ratio = aspect_ratio;
    }

    public Object getBg_color() {
        return bg_color;
    }

    public void setBg_color(Object bg_color) {
        this.bg_color = bg_color;
    }

    public int getNeed_login() {
        return need_login;
    }

    public void setNeed_login(int need_login) {
        this.need_login = need_login;
    }

    public int getTarget_type() {
        return target_type;
    }

    public void setTarget_type(int target_type) {
        this.target_type = target_type;
    }
}
