package com.dongdian.shenquan.bean;

import java.util.List;

public class MyOrderListBean {

    /**
     * total_count : 36
     * total_page : 2
     * page : 1
     * data : [{"order_no":"405112547719837370","item_title":"雪玲妃去角质面部女男去死皮全身体磨砂膏搓泥宝深层清洁毛孔脸部","item_image":"https://img.alicdn.com/bao/uploaded/i3/1588446985/O1CN01nTaLvk21TB37iOIfW_!!0-item_pic.jpg","create_time":"2019-04-10 13:40:18","earning_time":"2019-04-13 20:43:08","pay_amount":"5.10","pre_amount":"0.12","status":1,"status_txt":"已结算"}]
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
         * order_no : 405112547719837370
         * item_title : 雪玲妃去角质面部女男去死皮全身体磨砂膏搓泥宝深层清洁毛孔脸部
         * item_image : https://img.alicdn.com/bao/uploaded/i3/1588446985/O1CN01nTaLvk21TB37iOIfW_!!0-item_pic.jpg
         * create_time : 2019-04-10 13:40:18
         * earning_time : 2019-04-13 20:43:08
         * pay_amount : 5.10
         * pre_amount : 0.12
         * status : 1
         * status_txt : 已结算
         */

        private String order_no;
        private String item_title;
        private String item_image;
        private String create_time;
        private String earning_time;
        private String pay_amount;
        private String pre_amount;
        private int status;
        private String status_txt;

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getItem_title() {
            return item_title;
        }

        public void setItem_title(String item_title) {
            this.item_title = item_title;
        }

        public String getItem_image() {
            return item_image;
        }

        public void setItem_image(String item_image) {
            this.item_image = item_image;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getEarning_time() {
            return earning_time;
        }

        public void setEarning_time(String earning_time) {
            this.earning_time = earning_time;
        }

        public String getPay_amount() {
            return pay_amount;
        }

        public void setPay_amount(String pay_amount) {
            this.pay_amount = pay_amount;
        }

        public String getPre_amount() {
            return pre_amount;
        }

        public void setPre_amount(String pre_amount) {
            this.pre_amount = pre_amount;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatus_txt() {
            return status_txt;
        }

        public void setStatus_txt(String status_txt) {
            this.status_txt = status_txt;
        }
    }
}
