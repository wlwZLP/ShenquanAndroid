package com.dongdian.shenquan.bean;

import java.util.List;

public class MyCollectListBean  {

    /**
     * total_count : 1
     * total_page : 1
     * page : 1
     * data : [{"id":1,"item_type":1,"item_id":"589923736330","item_info":{"price":"138","title":"石榴石手链","cover_image":""},"created_at":"2019-08-13 17:41:37"}]
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
         * item_type : 1
         * item_id : 589923736330
         * item_info : {"price":"138","title":"石榴石手链","cover_image":""}
         * created_at : 2019-08-13 17:41:37
         */

        private int id;
        private int item_type;
        private String item_id;
        private ItemInfoBean item_info;
        private String created_at;
        private String mall_icon;
        private String mall_id;

        public String getMall_id() {
            return mall_id;
        }

        public void setMall_id(String mall_id) {
            this.mall_id = mall_id;
        }

        public String getMall_icon() {
            return mall_icon;
        }

        public void setMall_icon(String mall_icon) {
            this.mall_icon = mall_icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getItem_type() {
            return item_type;
        }

        public void setItem_type(int item_type) {
            this.item_type = item_type;
        }

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public ItemInfoBean getItem_info() {
            return item_info;
        }

        public void setItem_info(ItemInfoBean item_info) {
            this.item_info = item_info;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public static class ItemInfoBean {
            /**
             * price : 138
             * title : 石榴石手链
             * cover_image :
             */

            private String price;
            private String title;
            private String cover_image;

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCover_image() {
                return cover_image;
            }

            public void setCover_image(String cover_image) {
                this.cover_image = cover_image;
            }
        }
    }
}
