package com.dongdian.shenquan.bean.tixian;

import java.util.List;

public class WithDrawalReportBean {

    /**
     * total_count : 12
     * total_page : 1
     * page : 1
     * data : [{"amount":"150.00","status":1,"status_text":"提现成功","deal_at":"2019-07-29 14:30:57","remark":"","payment_method":"支付宝","account":"15868177549","account_name":"张三"}]
     */

    private int total_count;
    private int total_page;
    private int page;
    private List<DataBean> data;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * amount : 150.00
         * status : 1
         * status_text : 提现成功
         * deal_at : 2019-07-29 14:30:57
         * remark :
         * payment_method : 支付宝
         * account : 15868177549
         * account_name : 张三
         */

        private String amount;
        private int status;
        private String status_text;
        private String deal_at;
        private String remark;
        private String payment_method;
        private String account;
        private String account_name;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatus_text() {
            return status_text;
        }

        public void setStatus_text(String status_text) {
            this.status_text = status_text;
        }

        public String getDeal_at() {
            return deal_at;
        }

        public void setDeal_at(String deal_at) {
            this.deal_at = deal_at;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getPayment_method() {
            return payment_method;
        }

        public void setPayment_method(String payment_method) {
            this.payment_method = payment_method;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getAccount_name() {
            return account_name;
        }

        public void setAccount_name(String account_name) {
            this.account_name = account_name;
        }
    }
}
