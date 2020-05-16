package com.dongdian.shenquan.bean;

import java.util.List;

public class CommonBean {

    /**
     * float_window : null
     * plus_benefit_poster : ["https://img.daff9.cn/biyingniao/images/other/2003/5e66067c3dbab.jpg","https://img.daff9.cn/biyingniao/images/other/2003/5e66067ec10e6.jpg","https://img.daff9.cn/biyingniao/images/other/2003/5e660680d0cdc.png","https://img.daff9.cn/biyingniao/images/other/2003/5e660682d0435.png","https://img.daff9.cn/biyingniao/images/other/2003/5e6606849de62.png","https://img.daff9.cn/biyingniao/images/other/2003/5e6606869ef10.png","https://img.daff9.cn/biyingniao/images/other/2003/5e660688be3c4.png"]
     * invite_rank : https://img.daff9.cn/biyingniao/images/other/2003/5e6f1caa62ef8.png
     * income_rank : https://img.daff9.cn/biyingniao/images/other/2003/5e6f1cafb1b1c.png
     * vip_popup_text : 恭喜你
     成为会员啦！
     * vip_popup_remark : 一定要添加客服微信
     领取会员专属福利哦
     */

    private Object float_window;
    private String invite_rank;
    private String income_rank;
    private String vip_popup_text;
    private String vip_popup_remark;
    private List<String> plus_benefit_poster;

    public Object getFloat_window() {
        return float_window;
    }

    public void setFloat_window(Object float_window) {
        this.float_window = float_window;
    }

    public String getInvite_rank() {
        return invite_rank;
    }

    public void setInvite_rank(String invite_rank) {
        this.invite_rank = invite_rank;
    }

    public String getIncome_rank() {
        return income_rank;
    }

    public void setIncome_rank(String income_rank) {
        this.income_rank = income_rank;
    }

    public String getVip_popup_text() {
        return vip_popup_text;
    }

    public void setVip_popup_text(String vip_popup_text) {
        this.vip_popup_text = vip_popup_text;
    }

    public String getVip_popup_remark() {
        return vip_popup_remark;
    }

    public void setVip_popup_remark(String vip_popup_remark) {
        this.vip_popup_remark = vip_popup_remark;
    }

    public List<String> getPlus_benefit_poster() {
        return plus_benefit_poster;
    }

    public void setPlus_benefit_poster(List<String> plus_benefit_poster) {
        this.plus_benefit_poster = plus_benefit_poster;
    }
}
