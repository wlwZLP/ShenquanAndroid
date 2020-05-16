package com.dongdian.shenquan.bean;

import java.util.List;

public class HotWordsBean {

    private List<String> hot_words;
    private List<HotSearchWordsBean> hot_search_words;

    public List<String> getHot_words() {
        return hot_words;
    }

    public void setHot_words(List<String> hot_words) {
        this.hot_words = hot_words;
    }

    public List<HotSearchWordsBean> getHot_search_words() {
        return hot_search_words;
    }

    public void setHot_search_words(List<HotSearchWordsBean> hot_search_words) {
        this.hot_search_words = hot_search_words;
    }

    public static class HotSearchWordsBean {
        /**
         * id : 28
         * type : 7
         * title : 休闲零食组合套装
         * cover : http://img.daff9.cn/biyingniao/images/other/1911/5dca517a37732.png
         * aspect_ratio :
         * bg_color :
         * need_login : 0
         * target_type : 2
         * target : {"type":1,"item_id":"aa","activity_id":""}
         * title_color : #FF0000
         * is_advertising : 0
         */

        private int id;
        private int type;
        private String title;
        private String cover;
        private String aspect_ratio;
        private String bg_color;
        private int need_login;
        private int target_type;
        private TargetBean target;
        private String title_color;
        private int is_advertising;

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

        public TargetBean getTarget() {
            return target;
        }

        public void setTarget(TargetBean target) {
            this.target = target;
        }

        public String getTitle_color() {
            return title_color;
        }

        public void setTitle_color(String title_color) {
            this.title_color = title_color;
        }

        public int getIs_advertising() {
            return is_advertising;
        }

        public void setIs_advertising(int is_advertising) {
            this.is_advertising = is_advertising;
        }

        public static class TargetBean {
            /**
             * type : 1
             * item_id : aa
             * activity_id :
             */

            private int type;
            private String item_id;
            private String activity_id;



            private String url;
            private String url_type;
            private String title;
            private boolean scheme_able;
            private boolean open_share;
            private boolean need_oauth;
            private String oauth_url;

            public String getOauth_url() {
                return oauth_url;
            }

            public void setOauth_url(String oauth_url) {
                this.oauth_url = oauth_url;
            }



            private String id;
            private String path;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUrl_type() {
                return url_type;
            }

            public void setUrl_type(String url_type) {
                this.url_type = url_type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public boolean isScheme_able() {
                return scheme_able;
            }

            public void setScheme_able(boolean scheme_able) {
                this.scheme_able = scheme_able;
            }

            public boolean isOpen_share() {
                return open_share;
            }

            public void setOpen_share(boolean open_share) {
                this.open_share = open_share;
            }

            public boolean isNeed_oauth() {
                return need_oauth;
            }

            public void setNeed_oauth(boolean need_oauth) {
                this.need_oauth = need_oauth;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(String activity_id) {
                this.activity_id = activity_id;
            }
        }
    }
}
