package com.dongdian.shenquan.bean;

import java.util.List;

public class PPGMoreRightsBean {

    private List<HotBean> hot;
    private List<CouponBean> straight;
    private List<CouponBean> coupon;

    public List<HotBean> getHot() {
        return hot;
    }

    public void setHot(List<HotBean> hot) {
        this.hot = hot;
    }

    public List<CouponBean> getStraight() {
        return straight;
    }

    public void setStraight(List<CouponBean> straight) {
        this.straight = straight;
    }

    public List<CouponBean> getCoupon() {
        return coupon;
    }

    public void setCoupon(List<CouponBean> coupon) {
        this.coupon = coupon;
    }

    public static class HotBean {
        /**
         * name : 热门推荐
         * id : -1
         * brands : [{"id":15,"name":"爱奇艺","logo":"http://oss.365taoquan.cn/commodityTypeLogo/1562144637861logoFilepic#deVersion=2019032902","category_id":2,"is_hot":1,"brand_type":2,"brand_desc":""}]
         */

        private String name;
        private int id;
        private List<BrandsBean> brands;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<BrandsBean> getBrands() {
            return brands;
        }

        public void setBrands(List<BrandsBean> brands) {
            this.brands = brands;
        }


    }

    public static class CouponBean {
        /**
         * id : 1
         * name : 美食饮品
         * brands : [{"id":10,"name":"星巴克","logo":"http://oss.365taoquan.cn/commodityTypeLogo/1559547674788logoFilepic#deVersion=2019032902","category_id":1,"is_hot":1,"brand_type":2,"brand_desc":""},{"id":14,"name":"肯德基","logo":"http://oss.365taoquan.cn/commodityTypeLogo/1553647518704logoFilepic#deVersion=2019032902","category_id":1,"is_hot":1,"brand_type":2,"brand_desc":""}]
         */

        private int id;
        private String name;
        private List<BrandsBean> brands;

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

        public List<BrandsBean> getBrands() {
            return brands;
        }

        public void setBrands(List<BrandsBean> brands) {
            this.brands = brands;
        }


    }
    public static class BrandsBean {
        /**
         * id : 15
         * name : 爱奇艺
         * logo : http://oss.365taoquan.cn/commodityTypeLogo/1562144637861logoFilepic#deVersion=2019032902
         * category_id : 2
         * is_hot : 1
         * brand_type : 2
         * brand_desc :
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
