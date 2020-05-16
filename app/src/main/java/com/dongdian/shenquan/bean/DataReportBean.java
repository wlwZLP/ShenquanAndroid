package com.dongdian.shenquan.bean;

import java.util.List;

public class DataReportBean {

    /**
     * order_amount : 1.75
     * plus_amount : 349.35
     * balance_amount : 351.10
     * today_pre_amount : 0.00
     * yesterday_pre_amount : 0.00
     * total_pre_amount : 390.32
     * this_month_pre_amount : 0.00
     * last_month_pre_amount : 0.00
     * withdraw_amount : 0.00
     * this_month_settle_amount : 0.00
     * last_month_settle_amount : 0.00
     * un_settle_amount : 1.61
     * today_plus_amount : 0.00
     * yesterday_plus_amount : 0.00
     * total_plus_amount : 386.96
     * this_month_plus_amount : 0.00
     * last_month_plus_amount : 146.51
     * min_withdraw_amount : 5.00
     * withdraw_base_amount : 1.00
     * order_data : [{"title":"今日预估收益(元)","value":"0.00"},{"title":"昨日预估收益(元)","value":"0.00"},{"title":"本月预估收益(元)","value":"0.00"},{"title":"累计预估收益(元)","value":"3.36"}]
     * plus_data : [{"title":"今日预估收益(元)","value":"0.00"},{"title":"昨日预估收益(元)","value":"0.00"},{"title":"本月预估收益(元)","value":"0.00"},{"title":"累计预估收益(元)","value":"386.96"}]
     */

    private String order_amount;
    private String plus_amount;
    private String balance_amount;
    private String today_pre_amount;
    private String yesterday_pre_amount;
    private String total_pre_amount;
    private String this_month_pre_amount;
    private String last_month_pre_amount;
    private String withdraw_amount;
    private String this_month_settle_amount;
    private String last_month_settle_amount;
    private String un_settle_amount;
    private String today_plus_amount;
    private String yesterday_plus_amount;
    private String total_plus_amount;
    private String this_month_plus_amount;
    private String last_month_plus_amount;
    private String min_withdraw_amount;
    private String withdraw_base_amount;
    private List<OrderDataBean> order_data;
    private List<OrderDataBean> plus_data;

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

    public String getWithdraw_amount() {
        return withdraw_amount;
    }

    public void setWithdraw_amount(String withdraw_amount) {
        this.withdraw_amount = withdraw_amount;
    }

    public String getThis_month_settle_amount() {
        return this_month_settle_amount;
    }

    public void setThis_month_settle_amount(String this_month_settle_amount) {
        this.this_month_settle_amount = this_month_settle_amount;
    }

    public String getLast_month_settle_amount() {
        return last_month_settle_amount;
    }

    public void setLast_month_settle_amount(String last_month_settle_amount) {
        this.last_month_settle_amount = last_month_settle_amount;
    }

    public String getUn_settle_amount() {
        return un_settle_amount;
    }

    public void setUn_settle_amount(String un_settle_amount) {
        this.un_settle_amount = un_settle_amount;
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

    public List<OrderDataBean> getOrder_data() {
        return order_data;
    }

    public void setOrder_data(List<OrderDataBean> order_data) {
        this.order_data = order_data;
    }

    public List<OrderDataBean> getPlus_data() {
        return plus_data;
    }

    public void setPlus_data(List<OrderDataBean> plus_data) {
        this.plus_data = plus_data;
    }

    public static class OrderDataBean {
        /**
         * title : 今日预估收益(元)
         * value : 0.00
         */

        private String title;
        private String value;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


}
