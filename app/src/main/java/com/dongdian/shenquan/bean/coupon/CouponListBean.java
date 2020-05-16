package com.dongdian.shenquan.bean.coupon;

import java.util.List;

public class CouponListBean {

    /**
     * data : [{"brand_cover":"","count":1,"created_at":"2020-03-21 14:47:49","face_price":"10.00","goods_cover":"","goods_name":"爱奇艺黄金会员周卡","goods_type":2,"help":"1.本产品可在PC端、Pad端、手机端享受会员服务，TV端不可用。","notice_phone":"","order_no":"20200321144749358761","order_price":"7.00","sale_price":"7.00","status":2,"straight_params":[],"straight_type":0,"info":{"coupons":[{"goods_type":"PASSWORD","goods_link":"","goods_number":"","goods_password":"737f6a0d-b85f-431f-a7cb-033a38564ea6","effective_time":"2020-09-30 00:00:00"}],"straights":[]}}]
     * has_more : false
     * total : 80
     */

    private boolean has_more;
    private int total;
    private List<DataBean> data;

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * brand_cover :
         * count : 1
         * created_at : 2020-03-21 14:47:49
         * face_price : 10.00
         * goods_cover :
         * goods_name : 爱奇艺黄金会员周卡
         * goods_type : 2
         * help : 1.本产品可在PC端、Pad端、手机端享受会员服务，TV端不可用。
         * notice_phone :
         * order_no : 20200321144749358761
         * order_price : 7.00
         * sale_price : 7.00
         * status : 2
         * straight_params : []
         * straight_type : 0
         * info : {"coupons":[{"goods_type":"PASSWORD","goods_link":"","goods_number":"","goods_password":"737f6a0d-b85f-431f-a7cb-033a38564ea6","effective_time":"2020-09-30 00:00:00"}],"straights":[]}
         */

        private String brand_cover;
        private int count;
        private String created_at;
        private String face_price;
        private String goods_cover;
        private String goods_name;
        private int goods_type;
        private String help;
        private String notice_phone;
        private String order_no;
        private String order_price;
        private String sale_price;
        private int status;
        private int straight_type;
        private InfoBean info;
        private StraightParamsBean straight_params;

        public StraightParamsBean getStraight_params() {
            return straight_params;
        }

        public void setStraight_params(StraightParamsBean straight_params) {
            this.straight_params = straight_params;
        }

        public String getBrand_cover() {
            return brand_cover;
        }

        public void setBrand_cover(String brand_cover) {
            this.brand_cover = brand_cover;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getFace_price() {
            return face_price;
        }

        public void setFace_price(String face_price) {
            this.face_price = face_price;
        }

        public String getGoods_cover() {
            return goods_cover;
        }

        public void setGoods_cover(String goods_cover) {
            this.goods_cover = goods_cover;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public int getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(int goods_type) {
            this.goods_type = goods_type;
        }

        public String getHelp() {
            return help;
        }

        public void setHelp(String help) {
            this.help = help;
        }

        public String getNotice_phone() {
            return notice_phone;
        }

        public void setNotice_phone(String notice_phone) {
            this.notice_phone = notice_phone;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
        }

        public String getSale_price() {
            return sale_price;
        }

        public void setSale_price(String sale_price) {
            this.sale_price = sale_price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStraight_type() {
            return straight_type;
        }

        public void setStraight_type(int straight_type) {
            this.straight_type = straight_type;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class StraightParamsBean{
            private String recharge_number;

            public String getRecharge_number() {
                return recharge_number;
            }

            public void setRecharge_number(String recharge_number) {
                this.recharge_number = recharge_number;
            }
        }

        public static class InfoBean {
            private List<CouponsBean> coupons;
            private List<?> straights;

            public List<CouponsBean> getCoupons() {
                return coupons;
            }

            public void setCoupons(List<CouponsBean> coupons) {
                this.coupons = coupons;
            }

            public List<?> getStraights() {
                return straights;
            }

            public void setStraights(List<?> straights) {
                this.straights = straights;
            }

            public static class CouponsBean {
                /**
                 * goods_type : PASSWORD
                 * goods_link :
                 * goods_number :
                 * goods_password : 737f6a0d-b85f-431f-a7cb-033a38564ea6
                 * effective_time : 2020-09-30 00:00:00
                 */

                private String goods_type;
                private String goods_link;
                private String goods_number;
                private String goods_password;
                private String effective_time;

                public String getGoods_type() {
                    return goods_type;
                }

                public void setGoods_type(String goods_type) {
                    this.goods_type = goods_type;
                }

                public String getGoods_link() {
                    return goods_link;
                }

                public void setGoods_link(String goods_link) {
                    this.goods_link = goods_link;
                }

                public String getGoods_number() {
                    return goods_number;
                }

                public void setGoods_number(String goods_number) {
                    this.goods_number = goods_number;
                }

                public String getGoods_password() {
                    return goods_password;
                }

                public void setGoods_password(String goods_password) {
                    this.goods_password = goods_password;
                }

                public String getEffective_time() {
                    return effective_time;
                }

                public void setEffective_time(String effective_time) {
                    this.effective_time = effective_time;
                }
            }
        }
    }
}
