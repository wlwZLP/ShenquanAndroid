package com.dongdian.shenquan.bean;

import java.util.List;

public class GoodsHomeBean {
    private List<BannersBean> banners;
    private List<BannersBean> channels;
    private List<BannersBean> middles;
    private List<BannersBean> zones;
    private List<FlashsaleBean> flash_sales;

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public List<BannersBean> getChannels() {
        return channels;
    }

    public void setChannels(List<BannersBean> channels) {
        this.channels = channels;
    }

    public List<BannersBean> getMiddles() {
        return middles;
    }

    public void setMiddles(List<BannersBean> middles) {
        this.middles = middles;
    }

    public List<BannersBean> getZones() {
        return zones;
    }

    public void setZones(List<BannersBean> zones) {
        this.zones = zones;
    }

    public List<FlashsaleBean> getFlash_sales() {
        return flash_sales;
    }

    public void setFlash_sales(List<FlashsaleBean> flash_sales) {
        this.flash_sales = flash_sales;
    }
    public static class FlashsaleBean{

        /**
         * show_time : 08:00
         * time : 2019-09-06 08:00
         * desc : 已开抢
         * status : 2
         * is_active : 0
         * data : [{"item_id":"592154991491","title":"白虾鲜虾仁 翡翠生虾仁","coupon_money":"0.00","coupon_money_text":"","activity_id":"","cover_image":"http://img4.tbcdn.cn/tfscom///gju3.alicdn.com/tps/i4/O1CN01iKuGQu1RtRatqYW08_!!2-juitemmedia.png_300x300.jpg","price":"78.90","discount_price":"78.90","tk_rate":20,"fl_commission":"","mall_id":2,"mall_icon":"http://img.daff9.cn/resource/tmall.png","time":"2019-09-06 08:00","total_num":"","sold_num":"","status":1}]
         */

        private String show_time;
        private String time;
        private String desc;
        private int status;
        private int is_active;
        private List<DataBean> data;

        public String getShow_time() {
            return show_time;
        }

        public void setShow_time(String show_time) {
            this.show_time = show_time;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getIs_active() {
            return is_active;
        }

        public void setIs_active(int is_active) {
            this.is_active = is_active;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * item_id : 592154991491
             * title : 白虾鲜虾仁 翡翠生虾仁
             * coupon_money : 0.00
             * coupon_money_text :
             * activity_id :
             * cover_image : http://img4.tbcdn.cn/tfscom///gju3.alicdn.com/tps/i4/O1CN01iKuGQu1RtRatqYW08_!!2-juitemmedia.png_300x300.jpg
             * price : 78.90
             * discount_price : 78.90
             * tk_rate : 20
             * fl_commission :
             * mall_id : 2
             * mall_icon : http://img.daff9.cn/resource/tmall.png
             * time : 2019-09-06 08:00
             * total_num :
             * sold_num :
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
            private int mall_id;
            private String mall_icon;
            private String time;
            private String total_num;
            private String sold_num;
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

            public String getTotal_num() {
                return total_num;
            }

            public void setTotal_num(String total_num) {
                this.total_num = total_num;
            }

            public String getSold_num() {
                return sold_num;
            }

            public void setSold_num(String sold_num) {
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

    public static class BannersBean{

        /**
         * id : 1
         * type : 1
         * title : 1
         * cover : http://img.daff9.cn/biyingniao/images/other/1908/5d5e1c35717b6.png
         * aspect_ratio :
         * bg_color :
         * need_login : 0
         * target_type : 1
         */

        private int id;
        private int type;
        private String title;
        private String cover;
        private String aspect_ratio;
        private String bg_color;
        private int need_login;
        private int target_type;

        public HotWordsBean.HotSearchWordsBean.TargetBean getTarget() {
            return target;
        }

        public void setTarget(HotWordsBean.HotSearchWordsBean.TargetBean target) {
            this.target = target;
        }

        private HotWordsBean.HotSearchWordsBean.TargetBean target;
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getAspect_ratio() {
            return aspect_ratio;
        }

        public void setAspect_ratio(String aspect_ratio) {
            this.aspect_ratio = aspect_ratio;
        }

        public String getBg_color() {
            return bg_color;
        }

        public void setBg_color(String bg_color) {
            this.bg_color = bg_color;
        }

        public int getNeed_login() {
            return need_login;
        }

        public void setNeed_login(int need_login) {
            this.need_login = need_login;
        }

        public int getTarget_type() {
            return target_type;
        }

        public void setTarget_type(int target_type) {
            this.target_type = target_type;
        }
    }


}
