package com.dongdian.shenquan.bean;

import java.util.List;

public class CoupleetailDBean {

    /**
     * total_count : 2
     * total_page : 1
     * page : 1
     * data : [{"id":1,"title":"三步省钱","cover":"http://rebate-robot.oss-cn-hangzhou.aliyuncs.com/uploads/image/2019/8/d49fa92c6a9208956f2a93e0443cb49f.jpg","page_url":"http://rebate.test/articles/1?only=1","created_at":"2019-12-03 17:37:01"},{"id":4,"title":"惠省钱视频教程","cover":"http://img.daff9.cn/biyingniao/images/other/2001/5e1d5c5df01c9.png","page_url":"http://rebate.test/articles/4?only=1","created_at":"2020-01-14 14:16:58"}]
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
         * title : 三步省钱
         * cover : http://rebate-robot.oss-cn-hangzhou.aliyuncs.com/uploads/image/2019/8/d49fa92c6a9208956f2a93e0443cb49f.jpg
         * page_url : http://rebate.test/articles/1?only=1
         * created_at : 2019-12-03 17:37:01
         */

        private int id;
        private String title;
        private String cover;
        private String page_url;
        private String created_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getPage_url() {
            return page_url;
        }

        public void setPage_url(String page_url) {
            this.page_url = page_url;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
