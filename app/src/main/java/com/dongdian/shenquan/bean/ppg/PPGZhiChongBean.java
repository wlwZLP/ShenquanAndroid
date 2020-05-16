package com.dongdian.shenquan.bean.ppg;

public class PPGZhiChongBean {

    /**
     * id : 2272
     * face_price : 10.00
     * coupon_cover :
     * sale_price : 10.00
     * coupon_name : 爱奇艺黄金会员周卡
     * coupon_type : 1
     * member_price : 0.00
     * brand_id : 79
     * group : 黄金
     * validity : 7天
     * help : xixi
     * brand_cover : http://oss.365taoquan.cn/commodityTypeLogo/1562144637861logoFilepic#deVersion=2019032902
     */

    private int id;
    private String face_price;
    private String coupon_cover;
    private String sale_price;
    private String coupon_name;
    private int coupon_type;
    private String member_price;
    private int brand_id;
    private String group;
    private String validity;
    private String help;
    private String brand_cover;
    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

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

    public String getMember_price() {
        return member_price;
    }

    public void setMember_price(String member_price) {
        this.member_price = member_price;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getBrand_cover() {
        return brand_cover;
    }

    public void setBrand_cover(String brand_cover) {
        this.brand_cover = brand_cover;
    }
}
