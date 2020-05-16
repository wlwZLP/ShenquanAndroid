package com.dongdian.shenquan.bean.ppg;

import java.util.List;

public class PPGItemGoodsListBean {


    /**
     * data : [{"id":1101,"face_price":"50.00","coupon_cover":"http://oss.365taoquan.cn/commodityFace/1538212634320faceFilepic","sale_price":"48.75","coupon_name":"必胜客50元卡链通兑券","coupon_type":2,"mall_id":5,"member_price":"45.50","brand_cover":"http://oss.365taoquan.cn/commodityTypeLogo/1561020509235logoFilepic#deVersion=2019032902","expire":"约30天","type":2,"short_title":"","fl_commission":"","coupon_money_text":"","sales":""},{"id":1103,"face_price":"10.00","coupon_cover":"https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/resource/zwt.png","sale_price":"9.75","coupon_name":"必胜客10元卡链代金券","coupon_type":2,"mall_id":5,"member_price":"9.75","brand_cover":"http://oss.365taoquan.cn/commodityTypeLogo/1561020509235logoFilepic#deVersion=2019032902","expire":"约30天","type":2,"short_title":"","fl_commission":0,"coupon_money_text":"","sales":0}]
     * has_more : true
     */

    private boolean has_more;
    private List<DataBean> data;

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1101
         * face_price : 50.00
         * coupon_cover : http://oss.365taoquan.cn/commodityFace/1538212634320faceFilepic
         * sale_price : 48.75
         * coupon_name : 必胜客50元卡链通兑券
         * coupon_type : 2
         * mall_id : 5
         * member_price : 45.50
         * brand_cover : http://oss.365taoquan.cn/commodityTypeLogo/1561020509235logoFilepic#deVersion=2019032902
         * expire : 约30天
         * type : 2
         * short_title :
         * fl_commission :
         * coupon_money_text :
         * sales :
         */

        private String id;
        private String face_price;
        private String coupon_cover;
        private String sale_price;
        private String coupon_name;
        private int coupon_type;
        private int mall_id;
        private String member_price;
        private String brand_cover;
        private String expire;
        private int type;
        private String short_title;
        private String fl_commission;
        private String coupon_money_text;
        private String sales;
        private String brand_desc;

        public String getBrand_desc() {
            return brand_desc;
        }

        public void setBrand_desc(String brand_desc) {
            this.brand_desc = brand_desc;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFace_price() {
            return face_price;
        }

        public void setFace_price(String face_price) {
            this.face_price = face_price;
        }

        public String getCoupon_cover() {
            return coupon_cover;
        }

        public void setCoupon_cover(String coupon_cover) {
            this.coupon_cover = coupon_cover;
        }

        public String getSale_price() {
            return sale_price;
        }

        public void setSale_price(String sale_price) {
            this.sale_price = sale_price;
        }

        public String getCoupon_name() {
            return coupon_name;
        }

        public void setCoupon_name(String coupon_name) {
            this.coupon_name = coupon_name;
        }

        public int getCoupon_type() {
            return coupon_type;
        }

        public void setCoupon_type(int coupon_type) {
            this.coupon_type = coupon_type;
        }

        public int getMall_id() {
            return mall_id;
        }

        public void setMall_id(int mall_id) {
            this.mall_id = mall_id;
        }

        public String getMember_price() {
            return member_price;
        }

        public void setMember_price(String member_price) {
            this.member_price = member_price;
        }

        public String getBrand_cover() {
            return brand_cover;
        }

        public void setBrand_cover(String brand_cover) {
            this.brand_cover = brand_cover;
        }

        public String getExpire() {
            return expire;
        }

        public void setExpire(String expire) {
            this.expire = expire;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getShort_title() {
            return short_title;
        }

        public void setShort_title(String short_title) {
            this.short_title = short_title;
        }

        public String getFl_commission() {
            return fl_commission;
        }

        public void setFl_commission(String fl_commission) {
            this.fl_commission = fl_commission;
        }

        public String getCoupon_money_text() {
            return coupon_money_text;
        }

        public void setCoupon_money_text(String coupon_money_text) {
            this.coupon_money_text = coupon_money_text;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }
    }
}
