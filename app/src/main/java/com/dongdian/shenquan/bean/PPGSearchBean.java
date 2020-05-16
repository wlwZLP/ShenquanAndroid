package com.dongdian.shenquan.bean;

import java.util.List;

public class PPGSearchBean {

    /**
     * data : [{"id":2158,"face_price":"10.00","coupon_cover":"","sale_price":"4.04","coupon_name":"爱奇艺黄金会员周卡","coupon_type":2,"mall_id":5,"member_price":"","brand_cover":"http://oss.365taoquan.cn/commodityTypeLogo/1553647518704logoFilepic#deVersion=2019032902","expire":""}]
     * brands : [{"id":10,"name":"星巴克","logo":"https://biyingniao.oss-cn-shanghai.aliyuncs.com/coupon/%E6%98%9F%E5%B7%B4%E5%85%8B.png","category_id":1,"is_hot":1,"brand_type":2,"brand_desc":"尊享7.4折"}]
     * has_more : false
     */

    private boolean has_more;
    private List<DataBean> data;
    private List<BrandsBean> brands;

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

    public List<BrandsBean> getBrands() {
        return brands;
    }

    public void setBrands(List<BrandsBean> brands) {
        this.brands = brands;
    }

    public static class DataBean {
        /**
         * id : 2158
         * face_price : 10.00
         * coupon_cover :
         * sale_price : 4.04
         * coupon_name : 爱奇艺黄金会员周卡
         * coupon_type : 2
         * mall_id : 5
         * member_price :
         * brand_cover : http://oss.365taoquan.cn/commodityTypeLogo/1553647518704logoFilepic#deVersion=2019032902
         * expire :
         */

        private int id;
        private String face_price;
        private String coupon_cover;
        private String sale_price;
        private String coupon_name;
        private int coupon_type;
        private int mall_id;
        private String member_price;
        private String brand_cover;
        private String expire;

        public int getId() {
            return id;
        }

        public void setId(int id) {
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
    }

    public static class BrandsBean {
        /**
         * id : 10
         * name : 星巴克
         * logo : https://biyingniao.oss-cn-shanghai.aliyuncs.com/coupon/%E6%98%9F%E5%B7%B4%E5%85%8B.png
         * category_id : 1
         * is_hot : 1
         * brand_type : 2
         * brand_desc : 尊享7.4折
         */

        private int id;
        private String name;
        private String logo;
        private int category_id;
        private int is_hot;
        private int brand_type;
        private String brand_desc;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public int getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(int is_hot) {
            this.is_hot = is_hot;
        }

        public int getBrand_type() {
            return brand_type;
        }

        public void setBrand_type(int brand_type) {
            this.brand_type = brand_type;
        }

        public String getBrand_desc() {
            return brand_desc;
        }

        public void setBrand_desc(String brand_desc) {
            this.brand_desc = brand_desc;
        }
    }
}
