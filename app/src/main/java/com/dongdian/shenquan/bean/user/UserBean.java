package com.dongdian.shenquan.bean.user;

/**
 * Created by Administrator on 2019/4/1.
 */

public class UserBean {

    /**
     * id : 1
     * area_code : 86
     * phone : 17606****32
     * name : 我的卡卡
     * avatar : file:///storage/emulated/0/cutcamera.png
     * real_name :
     * ali_account :
     * wx_account :
     * recommend_code : GRVJ18
     * is_bind_wx : false
     * balance_amount : 0.00
     * today_pre_amount : 0.00
     * total_pre_amount : 0.00
     * this_month_pre_amount : 0.00
     * last_month_pre_amount : 0.00
     * this_month_settle_amount : 0.00
     * last_month_settle_amount : 0.00
     * un_settle_amount : 0.00
     */

    private int id;
    private int type;
    private String type_text;
    private String area_code;
    private String phone;
    private String name;

    private String avatar;
    private String real_name;

    private String ali_account;
    private String wx_account;
    private String parent_name;
    private String recommend_code;
    private int plus_level;
    private String plus_expire;


    private String order_amount;
    private String plus_amount;
    private String balance_amount;
    private String today_pre_amount;
    private String yesterday_pre_amount;
    private String total_pre_amount;
    private String this_month_pre_amount;
    private String last_month_pre_amount;
    private String today_plus_amount;
    private String yesterday_plus_amount;
    private String total_plus_amount;
    private String this_month_plus_amount;
    private String last_month_plus_amount;
    private String total_saving_amount;
    private String min_withdraw_amount;
    private String withdraw_base_amount;
    private String business_kefu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getType_text() {
        return type_text;
    }

    public void setType_text(String type_text) {
        this.type_text = type_text;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getAli_account() {
        return ali_account;
    }

    public void setAli_account(String ali_account) {
        this.ali_account = ali_account;
    }

    public String getWx_account() {
        return wx_account;
    }

    public void setWx_account(String wx_account) {
        this.wx_account = wx_account;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getRecommend_code() {
        return recommend_code;
    }

    public void setRecommend_code(String recommend_code) {
        this.recommend_code = recommend_code;
    }

    public int getPlus_level() {
        return plus_level;
    }

    public void setPlus_level(int plus_level) {
        this.plus_level = plus_level;
    }

    public String getPlus_expire() {
        return plus_expire;
    }

    public void setPlus_expire(String plus_expire) {
        this.plus_expire = plus_expire;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }

    public String getPlus_amount() {
        return plus_amount;
    }

    public void setPlus_amount(String plus_amount) {
        this.plus_amount = plus_amount;
    }

    public String getBalance_amount() {
        return balance_amount;
    }

    public void setBalance_amount(String balance_amount) {
        this.balance_amount = balance_amount;
    }

    public String getToday_pre_amount() {
        return today_pre_amount;
    }

    public void setToday_pre_amount(String today_pre_amount) {
        this.today_pre_amount = today_pre_amount;
    }

    public String getYesterday_pre_amount() {
        return yesterday_pre_amount;
    }

    public void setYesterday_pre_amount(String yesterday_pre_amount) {
        this.yesterday_pre_amount = yesterday_pre_amount;
    }

    public String getTotal_pre_amount() {
        return total_pre_amount;
    }

    public void setTotal_pre_amount(String total_pre_amount) {
        this.total_pre_amount = total_pre_amount;
    }

    public String getThis_month_pre_amount() {
        return this_month_pre_amount;
    }

    public void setThis_month_pre_amount(String this_month_pre_amount) {
        this.this_month_pre_amount = this_month_pre_amount;
    }

    public String getLast_month_pre_amount() {
        return last_month_pre_amount;
    }

    public void setLast_month_pre_amount(String last_month_pre_amount) {
        this.last_month_pre_amount = last_month_pre_amount;
    }

    public String getToday_plus_amount() {
        return today_plus_amount;
    }

    public void setToday_plus_amount(String today_plus_amount) {
        this.today_plus_amount = today_plus_amount;
    }

    public String getYesterday_plus_amount() {
        return yesterday_plus_amount;
    }

    public void setYesterday_plus_amount(String yesterday_plus_amount) {
        this.yesterday_plus_amount = yesterday_plus_amount;
    }

    public String getTotal_plus_amount() {
        return total_plus_amount;
    }

    public void setTotal_plus_amount(String total_plus_amount) {
        this.total_plus_amount = total_plus_amount;
    }

    public String getThis_month_plus_amount() {
        return this_month_plus_amount;
    }

    public void setThis_month_plus_amount(String this_month_plus_amount) {
        this.this_month_plus_amount = this_month_plus_amount;
    }

    public String getLast_month_plus_amount() {
        return last_month_plus_amount;
    }

    public void setLast_month_plus_amount(String last_month_plus_amount) {
        this.last_month_plus_amount = last_month_plus_amount;
    }

    public String getTotal_saving_amount() {
        return total_saving_amount;
    }

    public void setTotal_saving_amount(String total_saving_amount) {
        this.total_saving_amount = total_saving_amount;
    }

    public String getMin_withdraw_amount() {
        return min_withdraw_amount;
    }

    public void setMin_withdraw_amount(String min_withdraw_amount) {
        this.min_withdraw_amount = min_withdraw_amount;
    }

    public String getWithdraw_base_amount() {
        return withdraw_base_amount;
    }

    public void setWithdraw_base_amount(String withdraw_base_amount) {
        this.withdraw_base_amount = withdraw_base_amount;
    }

    public String getBusiness_kefu() {
        return business_kefu;
    }

    public void setBusiness_kefu(String business_kefu) {
        this.business_kefu = business_kefu;
    }
}
