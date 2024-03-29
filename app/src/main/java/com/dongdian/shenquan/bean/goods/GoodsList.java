package com.dongdian.shenquan.bean.goods;

import com.dongdian.shenquan.bean.HotWordsBean;

import java.util.List;

public class GoodsList {

    /**
     * items : [{"item_id":"","title":"diy双面抱枕定制照片来图印两用靠垫枕头订做可爱礼物送男女生日","coupon_money":"","coupon_money_text":"领券省2","activity_id":"","cover_image":"https://t00img.yangkeduo.com/goods/images/2019-05-25/0a5e659a055aec9b01cc95d381b3531b.jpeg","month_sales":"","price":"6.90","discount_price":"4.90","tk_rate":"","fl_commission":"赚0.34","shop_name":"GUYA顾雅家纺专卖店","mall_id":3,"mall_icon":"https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/mini_program/pin.png","type":1,"presale_deposit":"","presale_discount_fee_text":"","presale_end_time":"","presale_start_time":"","presale_tail_end_time":"","presale_tail_start_time":""},{"item_id":2574921541,"title":"【7.9元限时抢，抢完恢复9.9元】【40卷24卷10卷】5.6斤40卷植护本色卫生纸巾卷纸批发家用厕纸","coupon_money":1,"coupon_money_text":"领券省1","activity_id":0,"cover_image":"https://t00img.yangkeduo.com/goods/images/2019-04-27/cfd271f31585fbfaa0e7372061d12b13.jpeg","month_sales":1315832,"price":"7.90","discount_price":"6.90","tk_rate":20,"fl_commission":"赚0.43","fl_commission_amount":"","shop_name":"素木岭家居专营店","mall_id":3,"mall_icon":"https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/mini_program/pin.png","type":1,"presale_deposit":0,"presale_discount_fee_text":"","presale_end_time":"","presale_start_time":"","presale_tail_end_time":"","presale_tail_start_time":""}]
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
         * item_id :
         * title : diy双面抱枕定制照片来图印两用靠垫枕头订做可爱礼物送男女生日
         * coupon_money :
         * coupon_money_text : 领券省2
         * activity_id :
         * cover_image : https://t00img.yangkeduo.com/goods/images/2019-05-25/0a5e659a055aec9b01cc95d381b3531b.jpeg
         * month_sales :
         * price : 6.90
         * discount_price : 4.90
         * tk_rate :
         * fl_commission : 赚0.34
         * shop_name : GUYA顾雅家纺专卖店
         * mall_id : 3
         * mall_icon : https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/mini_program/pin.png
         * type : 1
         * presale_deposit :
         * presale_discount_fee_text :
         * presale_end_time :
         * presale_start_time :
         * presale_tail_end_time :
         * presale_tail_start_time :
         * fl_commission_amount :
         */

        private String item_id;
        private String title;
        private String coupon_money;
        private String coupon_money_text;
        private String activity_id;
        private String cover_image;
        private String month_sales;
        private String price;
        private String discount_price;
        private String tk_rate;
        private String fl_commission;
        private String shop_name;
        private int mall_id;
        private String mall_icon;
        private int type;
        private String presale_deposit;
        private String presale_discount_fee_text;
        private String presale_end_time;
        private String presale_start_time;
        private String presale_tail_end_time;
        private String presale_tail_start_time;
        private String fl_commission_amount;
        private HotWordsBean.HotSearchWordsBean.TargetBean target;
        private int target_type;

        public HotWordsBean.HotSearchWordsBean.TargetBean getTarget() {
            return target;
        }

        public void setTarget(HotWordsBean.HotSearchWordsBean.TargetBean target) {
            this.target = target;
        }

        public int getTarget_type() {
            return target_type;
        }

        public void setTarget_type(int target_type) {
            this.target_type = target_type;
        }

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

        public String getMonth_sales() {
            return month_sales;
        }

        public void setMonth_sales(String month_sales) {
            this.month_sales = month_sales;
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

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getPresale_deposit() {
            return presale_deposit;
        }

        public void setPresale_deposit(String presale_deposit) {
            this.presale_deposit = presale_deposit;
        }

        public String getPresale_discount_fee_text() {
            return presale_discount_fee_text;
        }

        public void setPresale_discount_fee_text(String presale_discount_fee_text) {
            this.presale_discount_fee_text = presale_discount_fee_text;
        }

        public String getPresale_end_time() {
            return presale_end_time;
        }

        public void setPresale_end_time(String presale_end_time) {
            this.presale_end_time = presale_end_time;
        }

        public String getPresale_start_time() {
            return presale_start_time;
        }

        public void setPresale_start_time(String presale_start_time) {
            this.presale_start_time = presale_start_time;
        }

        public String getPresale_tail_end_time() {
            return presale_tail_end_time;
        }

        public void setPresale_tail_end_time(String presale_tail_end_time) {
            this.presale_tail_end_time = presale_tail_end_time;
        }

        public String getPresale_tail_start_time() {
            return presale_tail_start_time;
        }

        public void setPresale_tail_start_time(String presale_tail_start_time) {
            this.presale_tail_start_time = presale_tail_start_time;
        }

        public String getFl_commission_amount() {
            return fl_commission_amount;
        }

        public void setFl_commission_amount(String fl_commission_amount) {
            this.fl_commission_amount = fl_commission_amount;
        }
    }
}
