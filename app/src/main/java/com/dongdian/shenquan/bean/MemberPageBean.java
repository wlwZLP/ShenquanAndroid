package com.dongdian.shenquan.bean;

import java.util.List;

public class MemberPageBean {

    private List<LevelBean> level;

    public List<LevelBean> getLevel() {
        return level;
    }

    public void setLevel(List<LevelBean> level) {
        this.level = level;
    }

    public static class LevelBean {
        /**
         * id : 1
         * month : 1
         * price :
         * single_price : ¥15.5/月
         */

        private int id;
        private int month;
        private String price;
        private String single_price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSingle_price() {
            return single_price;
        }

        public void setSingle_price(String single_price) {
            this.single_price = single_price;
        }
    }
}
