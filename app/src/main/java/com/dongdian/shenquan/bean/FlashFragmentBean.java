package com.dongdian.shenquan.bean;

import java.util.List;

public class FlashFragmentBean {

    /**
     * items : [{"item_id":"521497533935","title":"卫龙旗舰店大面筋112g*5包","coupon_money":"10.00","coupon_money_text":"领券省10.00","activity_id":"","cover_image":"http://img4.tbcdn.cn/tfscom///gju4.alicdn.com/tps/i1/O1CN01MP66R61GtwC2SpmB8_!!0-juitemmedia.jpg_300x300.jpg","price":"22.90","discount_price":"12.90","tk_rate":20,"fl_commission":"赚3.48","fl_commission_amount":348,"mall_id":2,"mall_icon":"http://img.daff9.cn/resource/tmall.png","time":"2019-09-05 10:00","total_amount":15000,"sold_num":5653,"status":1}]
     * has_next : true
     */

    private boolean has_next;
    private List<ItemsBean> items;

    public boolean isHas_next() {
        return has_next;
    }

    public void setHas_next(boolean has_next) {
        this.has_next = has_next;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * item_id : 521497533935
         * title : 卫龙旗舰店大面筋112g*5包
         * coupon_money : 10.00
         * coupon_money_text : 领券省10.00
         * activity_id :
         * cover_image : http://img4.tbcdn.cn/tfscom///gju4.alicdn.com/tps/i1/O1CN01MP66R61GtwC2SpmB8_!!0-juitemmedia.jpg_300x300.jpg
         * price : 22.90
         * discount_price : 12.90
         * tk_rate : 20
         * fl_commission : 赚3.48
         * fl_commission_amount : 348
         * mall_id : 2
         * mall_icon : http://img.daff9.cn/resource/tmall.png
         * time : 2019-09-05 10:00
         * total_amount : 15000
         * sold_num : 5653
         * status : 1
         */

        private String item_id;
        private String title;
        private String coupon_money;
        private String coupon_money_text;
        private String activity_id;
        private String cover_image;
        private String price;
        private String discount_price;
        private String tk_rate;
        private String fl_commission;
        private String fl_commission_amount;
        private int mall_id;
        private String mall_icon;
        private String time;
        private int total_num;
        private int sold_num;
        private int status;

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCoupon_money() {
            return coupon_money;
        }

        public void setCoupon_money(String coupon_money) {
            this.coupon_money = coupon_money;
        }

        public String getCoupon_money_text() {
            return coupon_money_text;
        }

        public void setCoupon_money_text(String coupon_money_text) {
            this.coupon_money_text = coupon_money_text;
        }

        public String getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(String activity_id) {
            this.activity_id = activity_id;
        }

        public String getCover_image() {
            return cover_image;
        }

        public void setCover_image(String cover_image) {
            this.cover_image = cover_image;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDiscount_price() {
            return discount_price;
        }

        public void setDiscount_price(String discount_price) {
            this.discount_price = discount_price;
        }

        public String getTk_rate() {
            return tk_rate;
        }

        public void setTk_rate(String tk_rate) {
            this.tk_rate = tk_rate;
        }

        public String getFl_commission() {
            return fl_commission;
        }

        public void setFl_commission(String fl_commission) {
            this.fl_commission = fl_commission;
        }

        public String getFl_commission_amount() {
            return fl_commission_amount;
        }

        public void setFl_commission_amount(String fl_commission_amount) {
            this.fl_commission_amount = fl_commission_amount;
        }

        public int getMall_id() {
            return mall_id;
        }

        public void setMall_id(int mall_id) {
            this.mall_id = mall_id;
        }

        public String getMall_icon() {
            return mall_icon;
        }

        public void setMall_icon(String mall_icon) {
            this.mall_icon = mall_icon;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getTotal_amount() {
            return total_num;
        }

        public void setTotal_amount(int total_num) {
            this.total_num = total_num;
        }

        public int getSold_num() {
            return sold_num;
        }

        public void setSold_num(int sold_num) {
            this.sold_num = sold_num;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
