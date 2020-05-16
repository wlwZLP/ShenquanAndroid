package com.dongdian.shenquan.bean;

import java.util.List;

public class TeamListBean  {

    /**
     * total_count : 12
     * total_page : 1
     * page : 1
     * data : [{"id":"1","out_uid":"2321","area_code":"86","phone":"12154545","name":"吴系挂","avatar":"http://test.com/test.png","register_time":"2019-07-29 14:30:57","last_login_time":"2019-07-30 10:46:23","recommend_code":"2ERX3D","team_num":1,"sum_pre_amount":"0.00"}]
     */

    private int total_count;
    private int total_page;
    private int page;
    private List<DataBean> data;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * out_uid : 2321
         * area_code : 86
         * phone : 12154545
         * name : 吴系挂
         * avatar : http://test.com/test.png
         * register_time : 2019-07-29 14:30:57
         * last_login_time : 2019-07-30 10:46:23
         * recommend_code : 2ERX3D
         * team_num : 1
         * sum_pre_amount : 0.00
         */

        private String id;
        private String out_uid;
        private String area_code;
        private String phone;
        private String name;
        private String avatar;
        private String register_time;
        private String last_login_time;
        private String recommend_code;
        private int team_num;
        private String sum_pre_amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOut_uid() {
            return out_uid;
        }

        public void setOut_uid(String out_uid) {
            this.out_uid = out_uid;
        }

        public String getArea_code() {
            return area_code;
        }

        public void setArea_code(String area_code) {
            this.area_code = area_code;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getRegister_time() {
            return register_time;
        }

        public void setRegister_time(String register_time) {
            this.register_time = register_time;
        }

        public String getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(String last_login_time) {
            this.last_login_time = last_login_time;
        }

        public String getRecommend_code() {
            return recommend_code;
        }

        public void setRecommend_code(String recommend_code) {
            this.recommend_code = recommend_code;
        }

        public int getTeam_num() {
            return team_num;
        }

        public void setTeam_num(int team_num) {
            this.team_num = team_num;
        }

        public String getSum_pre_amount() {
            return sum_pre_amount;
        }

        public void setSum_pre_amount(String sum_pre_amount) {
            this.sum_pre_amount = sum_pre_amount;
        }
    }
}
