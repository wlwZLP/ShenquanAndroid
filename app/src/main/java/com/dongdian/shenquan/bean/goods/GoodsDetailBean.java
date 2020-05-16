package com.dongdian.shenquan.bean.goods;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GoodsDetailBean {
    private String item_id;
    private String title;
    private String recommend;
    private String price;
    private String month_sales;
    private String cover_image;
    private String mall_id;
    private String activity_type;
    private String tk_rate;
    private String shop_name;
    private List<String> images;
    private List<String> detail_images;
    private DsrInfoBean dsr_info;

    private String tkl;
    private String mall_icon;
    private String coupon_money;
    private String discount_price;
    private String fl_commission;
    private String fl_commission_amount;
    private String activity_id;
    private String coupon_explain_money;
    private String coupon_starttime;
    private String coupon_endtime;

    private String coupon_click_url;
    private String oauth_url;
    private String presale_deposit;
    private String presale_discount_fee_text;
    private String presale_end_time;
    private String presale_start_time;
    private String presale_tail_end_time;
    private String presale_tail_start_time;
    private String detail_web_url;
    private String shop_logo;
    private String deeplink;
    private boolean need_oauth;
    private WeAppInfoBean we_app_info;

    private boolean is_collect;

    public boolean isIs_collect() {
        return is_collect;
    }

    public void setIs_collect(boolean is_collect) {
        this.is_collect = is_collect;
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

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMonth_sales() {
        return month_sales;
    }

    public void setMonth_sales(String month_sales) {
        this.month_sales = month_sales;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getMall_id() {
        return mall_id;
    }

    public void setMall_id(String mall_id) {
        this.mall_id = mall_id;
    }

    public String getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(String activity_type) {
        this.activity_type = activity_type;
    }

    public String getTk_rate() {
        return tk_rate;
    }

    public void setTk_rate(String tk_rate) {
        this.tk_rate = tk_rate;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getDetail_images() {
        return detail_images;
    }

    public void setDetail_images(List<String> detail_images) {
        this.detail_images = detail_images;
    }

    public DsrInfoBean getDsr_info() {
        return dsr_info;
    }

    public void setDsr_info(DsrInfoBean dsr_info) {
        this.dsr_info = dsr_info;
    }

    public String getTkl() {
        return tkl;
    }

    public void setTkl(String tkl) {
        this.tkl = tkl;
    }

    public String getMall_icon() {
        return mall_icon;
    }

    public void setMall_icon(String mall_icon) {
        this.mall_icon = mall_icon;
    }

    public String getCoupon_money() {
        return coupon_money;
    }

    public void setCoupon_money(String coupon_money) {
        this.coupon_money = coupon_money;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
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

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getCoupon_explain_money() {
        return coupon_explain_money;
    }

    public void setCoupon_explain_money(String coupon_explain_money) {
        this.coupon_explain_money = coupon_explain_money;
    }

    public String getCoupon_starttime() {
        return coupon_starttime;
    }

    public void setCoupon_starttime(String coupon_starttime) {
        this.coupon_starttime = coupon_starttime;
    }

    public String getCoupon_endtime() {
        return coupon_endtime;
    }

    public void setCoupon_endtime(String coupon_endtime) {
        this.coupon_endtime = coupon_endtime;
    }

    public String getCoupon_click_url() {
        return coupon_click_url;
    }

    public void setCoupon_click_url(String coupon_click_url) {
        this.coupon_click_url = coupon_click_url;
    }

    public String getOauth_url() {
        return oauth_url;
    }

    public void setOauth_url(String oauth_url) {
        this.oauth_url = oauth_url;
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

    public String getDetail_web_url() {
        return detail_web_url;
    }

    public void setDetail_web_url(String detail_web_url) {
        this.detail_web_url = detail_web_url;
    }

    public String getShop_logo() {
        return shop_logo;
    }

    public void setShop_logo(String shop_logo) {
        this.shop_logo = shop_logo;
    }

    public String getDeeplink() {
        return deeplink;
    }

    public void setDeeplink(String deeplink) {
        this.deeplink = deeplink;
    }

    public boolean isNeed_oauth() {
        return need_oauth;
    }

    public void setNeed_oauth(boolean need_oauth) {
        this.need_oauth = need_oauth;
    }

    public WeAppInfoBean getWe_app_info() {
        return we_app_info;
    }

    public void setWe_app_info(WeAppInfoBean we_app_info) {
        this.we_app_info = we_app_info;
    }

    public static class WeAppInfoBean {
        private String we_app_icon_url;
        private String user_name;
        private String page_path;
        private String banner_url;
        private String source_display_name;
        @SerializedName("title")
        private String titleX;
        private String app_id;
        private String desc;

        public String getWe_app_icon_url() {
            return we_app_icon_url;
        }

        public void setWe_app_icon_url(String we_app_icon_url) {
            this.we_app_icon_url = we_app_icon_url;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getPage_path() {
            return page_path;
        }

        public void setPage_path(String page_path) {
            this.page_path = page_path;
        }

        public String getBanner_url() {
            return banner_url;
        }

        public void setBanner_url(String banner_url) {
            this.banner_url = banner_url;
        }

        public String getSource_display_name() {
            return source_display_name;
        }

        public void setSource_display_name(String source_display_name) {
            this.source_display_name = source_display_name;
        }

        public String getTitleX() {
            return titleX;
        }

        public void setTitleX(String titleX) {
            this.titleX = titleX;
        }

        public String getApp_id() {
            return app_id;
        }

        public void setApp_id(String app_id) {
            this.app_id = app_id;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


    public class DsrInfoBean{
        private String descriptionMatch;
        private String serviceAttitude;
        private String deliverySpeed;

        public String getDescriptionMatch() {
            return descriptionMatch;
        }

        public void setDescriptionMatch(String descriptionMatch) {
            this.descriptionMatch = descriptionMatch;
        }

        public String getServiceAttitude() {
            return serviceAttitude;
        }

        public void setServiceAttitude(String serviceAttitude) {
            this.serviceAttitude = serviceAttitude;
        }

        public String getDeliverySpeed() {
            return deliverySpeed;
        }

        public void setDeliverySpeed(String deliverySpeed) {
            this.deliverySpeed = deliverySpeed;
        }
    }
}
