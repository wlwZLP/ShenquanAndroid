package com.dongdian.shenquan.bean.coupon;

import java.util.List;

public class CouponDetailBean {

    /**
     * order_detail : {"order_no":"20200109174442343598","goods_name":"天猫超市卡50元","goods_cover":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2316473176,227870339&fm=26&gp=0.jpg","sale_price":"50.50","face_price":"50.00","count":1,"status":2,"created_at":"2020-01-09 17:44:42","goods_type":2,"notice_phone":"","straight_params":{"recharge_number":"111111"},"straight_type":0,"help":"暂无","info":{"coupons":[{"goods_type":"NUMBER_PASSWORD","goods_link":"","goods_number":"15698257562514613","goods_password":"308800ee-480c-4fab-87a7-ffc004736283","effective_time":"2020-09-30 00:00:00"}],"straights":[]}}
     */

    private OrderDetailBean order_detail;

    public OrderDetailBean getOrder_detail() {
        return order_detail;
    }

    public void setOrder_detail(OrderDetailBean order_detail) {
        this.order_detail = order_detail;
    }

    public static class OrderDetailBean {
        /**
         * order_no : 20200109174442343598
         * goods_name : 天猫超市卡50元
         * goods_cover : https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2316473176,227870339&fm=26&gp=0.jpg
         * sale_price : 50.50
         * face_price : 50.00
         * count : 1
         * status : 2
         * created_at : 2020-01-09 17:44:42
         * goods_type : 2
         * notice_phone :
         * straight_params : {"recharge_number":"111111"}
         * straight_type : 0
         * help : 暂无
         * info : {"coupons":[{"goods_type":"NUMBER_PASSWORD","goods_link":"","goods_number":"15698257562514613","goods_password":"308800ee-480c-4fab-87a7-ffc004736283","effective_time":"2020-09-30 00:00:00"}],"straights":[]}
         */

        private String order_no;
        private String goods_name;
        private String goods_cover;
        private String sale_price;
        private String face_price;
        private int count;
        private int status;
        private String created_at;
        private int goods_type;
        private String notice_phone;
        private StraightParamsBean straight_params;
        private String straight_type;
        private String help;
        private InfoBean info;

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_cover() {
            return goods_cover;
        }

        public void setGoods_cover(String goods_cover) {
            this.goods_cover = goods_cover;
        }

        public String getSale_price() {
            return sale_price;
        }

        public void setSale_price(String sale_price) {
            this.sale_price = sale_price;
        }

        public String getFace_price() {
            return face_price;
        }

        public void setFace_price(String face_price) {
            this.face_price = face_price;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(int goods_type) {
            this.goods_type = goods_type;
        }

        public String getNotice_phone() {
            return notice_phone;
        }

        public void setNotice_phone(String notice_phone) {
            this.notice_phone = notice_phone;
        }

        public StraightParamsBean getStraight_params() {
            return straight_params;
        }

        public void setStraight_params(StraightParamsBean straight_params) {
            this.straight_params = straight_params;
        }

        public String getStraight_type() {
            return straight_type;
        }

        public void setStraight_type(String straight_type) {
            this.straight_type = straight_type;
        }

        public String getHelp() {
            return help;
        }

        public void setHelp(String help) {
            this.help = help;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class StraightParamsBean {
            /**
             * recharge_number : 111111
             */

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
                 * goods_type : NUMBER_PASSWORD
                 * goods_link :
                 * goods_number : 15698257562514613
                 * goods_password : 308800ee-480c-4fab-87a7-ffc004736283
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
