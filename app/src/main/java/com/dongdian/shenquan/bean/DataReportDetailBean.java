package com.dongdian.shenquan.bean;

import java.util.List;

public class DataReportDetailBean {

    /**
     * total_count : 0
     * total_page : 1
     * page : 1
     * data : [{"chg_amount":"145.00","now_amount":"145.00","busi_code":"1001","remark":"订单返利","created_at":"2020-02-10 14:23:45"},{"chg_amount":"-145.00","now_amount":"0","busi_code":"1003","remark":"提现","created_at":"2020-02-10 14:23:45"}]
     */

    private int total_count;
    private int total_page;
    private int page;
    private List<DataBean> data;
    private boolean has_more;

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

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
         * chg_amount : 145.00
         * now_amount : 145.00
         * busi_code : 1001
         * remark : 订单返利
         * created_at : 2020-02-10 14:23:45
         */

        private String chg_amount;
        private String now_amount;
        private String busi_code;
        private String remark;
        private String created_at;

        public String getChg_amount() {
            return chg_amount;
        }

        public void setChg_amount(String chg_amount) {
            this.chg_amount = chg_amount;
        }

        public String getNow_amount() {
            return now_amount;
        }

        public void setNow_amount(String now_amount) {
            this.now_amount = now_amount;
        }

        public String getBusi_code() {
            return busi_code;
        }

        public void setBusi_code(String busi_code) {
            this.busi_code = busi_code;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
