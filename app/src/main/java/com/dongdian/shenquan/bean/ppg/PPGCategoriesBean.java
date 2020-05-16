package com.dongdian.shenquan.bean.ppg;

import java.util.List;

public class PPGCategoriesBean {


    /**
     * categories : {"label":"分类","icon":"","contents":[{"id":2,"type":1,"name":"视频音频","logo":""},{"id":3,"type":1,"name":"外卖送餐","logo":""},{"id":4,"type":1,"name":"商超购物","logo":""},{"id":7,"type":1,"name":"生活服务","logo":""},{"id":1,"type":1,"name":"美食饮品","logo":""},{"id":5,"type":1,"name":"旅游出行","logo":""},{"id":6,"type":1,"name":"电影演出","logo":""},{"id":8,"type":2,"name":"会员福利","logo":""},{"id":9,"type":1,"name":"0元福利","logo":""},{"id":10,"type":1,"name":"其他","logo":""}]}
     * brands : {"label":"品牌墙","icon":"","contents":[{"id":15,"name":"爱奇艺","goods_type_id":15,"type":1,"sort":"","logo":"http://oss.365taoquan.cn/commodityTypeLogo/1562144637861logoFilepic#deVersion=2019032902","category_id":2,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":1,"brand_type":2,"brand_desc":"6.2折直充"},{"id":33,"name":"饿了么","goods_type_id":103,"type":1,"sort":100,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1561964445091logoFilepic#deVersion=2019032902","category_id":3,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:57","is_hot":1,"brand_type":2,"brand_desc":"天天领红包"},{"id":4,"name":"盒马生鲜","goods_type_id":57,"type":1,"sort":99,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1558086023982logoFilepic#deVersion=2019032902","category_id":4,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":1,"brand_type":2,"brand_desc":"特惠7折起"},{"id":3,"name":"必胜客","goods_type_id":5,"type":1,"sort":98,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1561020509235logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:57","is_hot":0,"brand_type":2,"brand_desc":"7.5折扣特惠"},{"id":10,"name":"星巴克","goods_type_id":1,"type":1,"sort":98,"logo":"https://biyingniao.oss-cn-shanghai.aliyuncs.com/coupon/%E6%98%9F%E5%B7%B4%E5%85%8B.png","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:56","is_hot":1,"brand_type":2,"brand_desc":"尊享7.8折"},{"id":14,"name":"肯德基","goods_type_id":6,"type":1,"sort":97,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1553647518704logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:57","is_hot":1,"brand_type":2,"brand_desc":"立享8.5折"},{"id":31,"name":"麦当劳","goods_type_id":46,"type":1,"sort":97,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1553648943791logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:57","is_hot":0,"brand_type":2,"brand_desc":"7.2折特惠"},{"id":50,"name":"哈根达斯","goods_type_id":8,"type":1,"sort":96,"logo":"https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/officialAccount/79/materials/5e1edff544874.jpg","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-14 17:29:04","is_hot":0,"brand_type":2,"brand_desc":"5折起特惠"},{"id":34,"name":"面包新语","goods_type_id":155,"type":1,"sort":94,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1562130122358logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":0,"brand_type":2,"brand_desc":"7折特惠"},{"id":39,"name":"真功夫","goods_type_id":169,"type":1,"sort":93,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1564651866537logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":0,"brand_type":2,"brand_desc":"尊享3折"},{"id":38,"name":"瑞幸咖啡","goods_type_id":168,"type":1,"sort":92,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1566470544664logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":0,"brand_type":2,"brand_desc":"尊享5.3折"},{"id":25,"name":"DQ冰淇淋","goods_type_id":11,"type":1,"sort":91,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1558086804891logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:56","is_hot":0,"brand_type":2,"brand_desc":"尊享5.1折"},{"id":11,"name":"百果园","goods_type_id":10,"type":1,"sort":90,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1558086687094logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:57","is_hot":0,"brand_type":2,"brand_desc":"7折起特惠"},{"id":18,"name":"来伊份","goods_type_id":69,"type":1,"sort":89,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1558086919859logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:57","is_hot":0,"brand_type":2,"brand_desc":"特惠9.5折起"},{"id":41,"name":"21Cake蛋糕","goods_type_id":172,"type":1,"sort":88,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1565144495500logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":0,"brand_type":2,"brand_desc":"特惠9.5折起"},{"id":37,"name":"幸福西饼","goods_type_id":166,"type":1,"sort":85,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1564537382620logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":0,"brand_type":2,"brand_desc":"劲爆1折"},{"id":42,"name":"良品铺子","goods_type_id":177,"type":1,"sort":84,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1566369434058logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":0,"brand_type":2,"brand_desc":"劲爆1折"},{"id":1,"name":"85°C","goods_type_id":58,"type":1,"sort":83,"logo":"https://biyingniao.oss-cn-shanghai.aliyuncs.com/coupon/85%E5%BA%A6c.png","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":0,"brand_type":2,"brand_desc":"7折起特惠"},{"id":9,"name":"腾讯会员","goods_type_id":14,"type":1,"sort":81,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1558085554418logoFilepic#deVersion=2019032902","category_id":2,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:57","is_hot":0,"brand_type":2,"brand_desc":"7折起特惠"},{"id":7,"name":"优酷会员","goods_type_id":16,"type":1,"sort":80,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1562745870777logoFilepic#deVersion=2019032902","category_id":2,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:57","is_hot":0,"brand_type":2,"brand_desc":"6.5折起特惠"},{"id":12,"name":"芒果TV","goods_type_id":17,"type":1,"sort":79,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1558086485087logoFilepic#deVersion=2019032902","category_id":2,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:56","is_hot":0,"brand_type":2,"brand_desc":"5折起特惠"},{"id":5,"name":"QQ音乐包","goods_type_id":21,"type":1,"sort":78,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1558085706071logoFilepic#deVersion=2019032902","category_id":2,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:57","is_hot":0,"brand_type":2,"brand_desc":"9折起特惠"},{"id":36,"name":"今今乐道","goods_type_id":160,"type":1,"sort":76,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1563759191958logoFilepic#deVersion=2019032902","category_id":2,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":0,"brand_type":2,"brand_desc":"1折包月听书"},{"id":48,"name":"当当云阅读","goods_type_id":193,"type":1,"sort":75,"logo":"https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/officialAccount/79/materials/5e1ed7b94f9f6.png","category_id":2,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":0,"brand_type":2,"brand_desc":"1折读正版书"},{"id":49,"name":"潇湘书院","goods_type_id":194,"type":1,"sort":74,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1572595614598logoFilepic#deVersion=2019032902","category_id":2,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":0,"brand_type":2,"brand_desc":"1折精品网文"},{"id":52,"name":"咪咕阅读","goods_type_id":204,"type":1,"sort":73,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1572595570736logoFilepic#deVersion=2019032902","category_id":2,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":0,"brand_type":2,"brand_desc":"1折包月阅读"},{"id":65,"name":"百度网盘","goods_type_id":23,"type":1,"sort":69,"logo":"https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/officialAccount/79/materials/5e1ed7ba17681.jpg","category_id":2,"created_at":"2020-01-13 23:42:57","updated_at":"2020-01-13 23:42:57","is_hot":0,"brand_type":2,"brand_desc":"超会8折起"}]}
     * broadcasts : {"label":"播报","icon":"","contents":[{"redirect_type":1,"redirect":{"path":"/pages/recharges/recharges"},"content":"各大品牌最低5折起充值"},{"redirect_type":1,"redirect":{"path":"/pages/details/brand/brand?id=1106&mall_id=5"},"content":"glee买了一杯星巴克，省了10元"},{"redirect_type":1,"redirect":{"path":"/pages/details/brand/brand?id=1134&mall_id=5"},"content":"小虎买了爱奇艺黄金会员周卡，省了5元"}]}
     * banners : {"label":"横幅","icon":"","contents":[{"id":38,"title":"广告位-首页-精品-外部","image":"https://img.daff9.cn/biyingniao/images/adzone/5e7abd861a6fa.gif","redirect_type":2,"need_login":false,"bg_color":"","redirect":{"path":"/pages/sharePid/shareWebView/index?pid=mm_121524567_23466229_136776001:1111111111_111_1111111111&rid=2330894015&withLoginInfo=1&extraInfo=1","app_id":"wxece3a9a4c82f58c9"}},{"id":39,"title":"中部广告位","image":"https://img.daff9.cn/biyingniao/images/adzone/5e7abdb7bcf23.png","redirect_type":4,"need_login":false,"bg_color":"","redirect":{"path":"/pages/recharges/recharges"}},{"id":46,"title":"标题","image":"https://img.daff9.cn/biyingniao/images/other/1910/5dad795f31051.png","redirect_type":4,"need_login":false,"bg_color":"","redirect":{"path":"/pages/brands/clist/clist?bid=5&bt=2"}}]}
     * zones : {"label":"精品专区","icon":"https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/MiniProgram/qhb/star.png","contents":[{"id":37,"title":"精选1","image":"https://img.daff9.cn/biyingniao/images/adzone/5e71bf6e48561.png","redirect_type":4,"need_login":false,"bg_color":"","redirect":{"path":"/pages/details/tjp/tjp?id=91938235866&mall_id=3"}},{"id":40,"title":"精选2","image":"https://img.daff9.cn/biyingniao/images/adzone/5e71ba4cb8ff6.png","redirect_type":4,"need_login":false,"bg_color":"","redirect":{"path":"/pages/brands/gather/gather?cid=8&title=会员福利"}},{"id":41,"title":"精选3","image":"https://img.daff9.cn/biyingniao/images/adzone/5e71ba3f0f193.png","redirect_type":4,"need_login":false,"bg_color":"","redirect":{"path":"/pages/details/recharge/recharge?bid=83&id="}},{"id":42,"title":"精选4","image":"https://img.daff9.cn/biyingniao/images/adzone/5e71ba35c45ef.png","redirect_type":4,"need_login":false,"bg_color":"","redirect":{"path":"/pages/details/tjp/tjp?id=96633572616&mall_id=3"}},{"id":43,"title":"精选5","image":"https://img.daff9.cn/biyingniao/images/adzone/5e71b9bd02c8e.png","redirect_type":4,"need_login":false,"bg_color":"","redirect":{"path":"/pages/details/tjp/tjp?id=2611290&mall_id=4"}}]}
     * recommend : {"label":"热门推荐","icon":"https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/MiniProgram/qhb/hot.png","contents":[{"id":1269,"face_price":"10.00","coupon_cover":"http://oss.365taoquan.cn/commodityFace/1559525677594faceFilepic","sale_price":"7.50","coupon_name":"爱奇艺黄金会员周卡","coupon_type":1,"mall_id":6,"member_price":"6.00","brand_cover":"https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/MiniProgram/qhb/brands/iqiy.png","expire":"约30天","type":2,"short_title":"","fl_commission":"","coupon_money_text":"","sales":"","need_login":false,"rest_num":99},{"id":1326,"face_price":"33.00","coupon_cover":"http://rebate-robot.oss-cn-hangzhou.aliyuncs.com/uploads/image/2020/2/664d566084dacc09a91ba8ad581ee7f0.jpg","sale_price":"30.00","coupon_name":"中杯全国伙伴券","coupon_type":2,"mall_id":5,"member_price":"26.00","brand_cover":"https://biyingniao.oss-cn-shanghai.aliyuncs.com/coupon/%E6%98%9F%E5%B7%B4%E5%85%8B.png","expire":"约30天","type":2,"short_title":"","fl_commission":"","coupon_money_text":"","sales":9,"need_login":false,"rest_num":86},{"id":1131,"face_price":"50.00","coupon_cover":"http://oss.365taoquan.cn/commodityFace/1553048522610faceFilepic","sale_price":"49.00","coupon_name":"肯德基50元代金券链接","coupon_type":2,"mall_id":5,"member_price":"42.00","brand_cover":"http://oss.365taoquan.cn/commodityTypeLogo/1553647518704logoFilepic#deVersion=2019032902","expire":"约30天","type":2,"short_title":"","fl_commission":"","coupon_money_text":"","sales":0,"need_login":false,"rest_num":99},{"id":1136,"face_price":"58.00","coupon_cover":"http://oss.365taoquan.cn/commodityFace/1548725647435faceFilepic","sale_price":"44.50","coupon_name":"爱奇艺黄金会员季卡","coupon_type":2,"mall_id":5,"member_price":"39.50","brand_cover":"http://oss.365taoquan.cn/commodityTypeLogo/1562144637861logoFilepic#deVersion=2019032902","expire":"约30天","type":2,"short_title":"","fl_commission":"","coupon_money_text":"","sales":0,"need_login":false,"rest_num":99}]}
     * goods_recommend : {"label":"好货推荐","icon":"","contents":[]}
     */

    private CategoriesBean categories;
    private BrandsBean brands;
    private BroadcastsBean broadcasts;
    private BannersBean banners;
    private ZonesBean zones;
    private RecommendBean recommend;
    private GoodsRecommendBean goods_recommend;

    public CategoriesBean getCategories() {
        return categories;
    }

    public void setCategories(CategoriesBean categories) {
        this.categories = categories;
    }

    public BrandsBean getBrands() {
        return brands;
    }

    public void setBrands(BrandsBean brands) {
        this.brands = brands;
    }

    public BroadcastsBean getBroadcasts() {
        return broadcasts;
    }

    public void setBroadcasts(BroadcastsBean broadcasts) {
        this.broadcasts = broadcasts;
    }

    public BannersBean getBanners() {
        return banners;
    }

    public void setBanners(BannersBean banners) {
        this.banners = banners;
    }

    public ZonesBean getZones() {
        return zones;
    }

    public void setZones(ZonesBean zones) {
        this.zones = zones;
    }

    public RecommendBean getRecommend() {
        return recommend;
    }

    public void setRecommend(RecommendBean recommend) {
        this.recommend = recommend;
    }

    public GoodsRecommendBean getGoods_recommend() {
        return goods_recommend;
    }

    public void setGoods_recommend(GoodsRecommendBean goods_recommend) {
        this.goods_recommend = goods_recommend;
    }

    public static class CategoriesBean {
        /**
         * label : 分类
         * icon :
         * contents : [{"id":2,"type":1,"name":"视频音频","logo":""},{"id":3,"type":1,"name":"外卖送餐","logo":""},{"id":4,"type":1,"name":"商超购物","logo":""},{"id":7,"type":1,"name":"生活服务","logo":""},{"id":1,"type":1,"name":"美食饮品","logo":""},{"id":5,"type":1,"name":"旅游出行","logo":""},{"id":6,"type":1,"name":"电影演出","logo":""},{"id":8,"type":2,"name":"会员福利","logo":""},{"id":9,"type":1,"name":"0元福利","logo":""},{"id":10,"type":1,"name":"其他","logo":""}]
         */

        private String label;
        private String icon;
        private List<ContentsBean> contents;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public List<ContentsBean> getContents() {
            return contents;
        }

        public void setContents(List<ContentsBean> contents) {
            this.contents = contents;
        }

        public static class ContentsBean {
            /**
             * id : 2
             * type : 1
             * name : 视频音频
             * logo :
             */

            private int id;
            private int type;
            private String name;
            private String logo;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }
        }
    }

    public static class BrandsBean {
        /**
         * label : 品牌墙
         * icon :
         * contents : [{"id":15,"name":"爱奇艺","goods_type_id":15,"type":1,"sort":"","logo":"http://oss.365taoquan.cn/commodityTypeLogo/1562144637861logoFilepic#deVersion=2019032902","category_id":2,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":1,"brand_type":2,"brand_desc":"6.2折直充"},{"id":33,"name":"饿了么","goods_type_id":103,"type":1,"sort":100,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1561964445091logoFilepic#deVersion=2019032902","category_id":3,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:57","is_hot":1,"brand_type":2,"brand_desc":"天天领红包"},{"id":4,"name":"盒马生鲜","goods_type_id":57,"type":1,"sort":99,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1558086023982logoFilepic#deVersion=2019032902","category_id":4,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":1,"brand_type":2,"brand_desc":"特惠7折起"},{"id":3,"name":"必胜客","goods_type_id":5,"type":1,"sort":98,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1561020509235logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:57","is_hot":0,"brand_type":2,"brand_desc":"7.5折扣特惠"},{"id":10,"name":"星巴克","goods_type_id":1,"type":1,"sort":98,"logo":"https://biyingniao.oss-cn-shanghai.aliyuncs.com/coupon/%E6%98%9F%E5%B7%B4%E5%85%8B.png","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:56","is_hot":1,"brand_type":2,"brand_desc":"尊享7.8折"},{"id":14,"name":"肯德基","goods_type_id":6,"type":1,"sort":97,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1553647518704logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:57","is_hot":1,"brand_type":2,"brand_desc":"立享8.5折"},{"id":31,"name":"麦当劳","goods_type_id":46,"type":1,"sort":97,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1553648943791logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:57","is_hot":0,"brand_type":2,"brand_desc":"7.2折特惠"},{"id":50,"name":"哈根达斯","goods_type_id":8,"type":1,"sort":96,"logo":"https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/officialAccount/79/materials/5e1edff544874.jpg","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-14 17:29:04","is_hot":0,"brand_type":2,"brand_desc":"5折起特惠"},{"id":34,"name":"面包新语","goods_type_id":155,"type":1,"sort":94,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1562130122358logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":0,"brand_type":2,"brand_desc":"7折特惠"},{"id":39,"name":"真功夫","goods_type_id":169,"type":1,"sort":93,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1564651866537logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":0,"brand_type":2,"brand_desc":"尊享3折"},{"id":38,"name":"瑞幸咖啡","goods_type_id":168,"type":1,"sort":92,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1566470544664logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":0,"brand_type":2,"brand_desc":"尊享5.3折"},{"id":25,"name":"DQ冰淇淋","goods_type_id":11,"type":1,"sort":91,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1558086804891logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:56","is_hot":0,"brand_type":2,"brand_desc":"尊享5.1折"},{"id":11,"name":"百果园","goods_type_id":10,"type":1,"sort":90,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1558086687094logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:57","is_hot":0,"brand_type":2,"brand_desc":"7折起特惠"},{"id":18,"name":"来伊份","goods_type_id":69,"type":1,"sort":89,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1558086919859logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:57","is_hot":0,"brand_type":2,"brand_desc":"特惠9.5折起"},{"id":41,"name":"21Cake蛋糕","goods_type_id":172,"type":1,"sort":88,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1565144495500logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":0,"brand_type":2,"brand_desc":"特惠9.5折起"},{"id":37,"name":"幸福西饼","goods_type_id":166,"type":1,"sort":85,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1564537382620logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":0,"brand_type":2,"brand_desc":"劲爆1折"},{"id":42,"name":"良品铺子","goods_type_id":177,"type":1,"sort":84,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1566369434058logoFilepic#deVersion=2019032902","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":0,"brand_type":2,"brand_desc":"劲爆1折"},{"id":1,"name":"85°C","goods_type_id":58,"type":1,"sort":83,"logo":"https://biyingniao.oss-cn-shanghai.aliyuncs.com/coupon/85%E5%BA%A6c.png","category_id":1,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":0,"brand_type":2,"brand_desc":"7折起特惠"},{"id":9,"name":"腾讯会员","goods_type_id":14,"type":1,"sort":81,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1558085554418logoFilepic#deVersion=2019032902","category_id":2,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:57","is_hot":0,"brand_type":2,"brand_desc":"7折起特惠"},{"id":7,"name":"优酷会员","goods_type_id":16,"type":1,"sort":80,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1562745870777logoFilepic#deVersion=2019032902","category_id":2,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:57","is_hot":0,"brand_type":2,"brand_desc":"6.5折起特惠"},{"id":12,"name":"芒果TV","goods_type_id":17,"type":1,"sort":79,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1558086485087logoFilepic#deVersion=2019032902","category_id":2,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:56","is_hot":0,"brand_type":2,"brand_desc":"5折起特惠"},{"id":5,"name":"QQ音乐包","goods_type_id":21,"type":1,"sort":78,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1558085706071logoFilepic#deVersion=2019032902","category_id":2,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:42:57","is_hot":0,"brand_type":2,"brand_desc":"9折起特惠"},{"id":36,"name":"今今乐道","goods_type_id":160,"type":1,"sort":76,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1563759191958logoFilepic#deVersion=2019032902","category_id":2,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":0,"brand_type":2,"brand_desc":"1折包月听书"},{"id":48,"name":"当当云阅读","goods_type_id":193,"type":1,"sort":75,"logo":"https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/officialAccount/79/materials/5e1ed7b94f9f6.png","category_id":2,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":0,"brand_type":2,"brand_desc":"1折读正版书"},{"id":49,"name":"潇湘书院","goods_type_id":194,"type":1,"sort":74,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1572595614598logoFilepic#deVersion=2019032902","category_id":2,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":0,"brand_type":2,"brand_desc":"1折精品网文"},{"id":52,"name":"咪咕阅读","goods_type_id":204,"type":1,"sort":73,"logo":"http://oss.365taoquan.cn/commodityTypeLogo/1572595570736logoFilepic#deVersion=2019032902","category_id":2,"created_at":"2020-01-09 12:02:12","updated_at":"2020-01-13 23:56:05","is_hot":0,"brand_type":2,"brand_desc":"1折包月阅读"},{"id":65,"name":"百度网盘","goods_type_id":23,"type":1,"sort":69,"logo":"https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/officialAccount/79/materials/5e1ed7ba17681.jpg","category_id":2,"created_at":"2020-01-13 23:42:57","updated_at":"2020-01-13 23:42:57","is_hot":0,"brand_type":2,"brand_desc":"超会8折起"}]
         */

        private String label;
        private String icon;
        private List<ContentsBeanX> contents;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public List<ContentsBeanX> getContents() {
            return contents;
        }

        public void setContents(List<ContentsBeanX> contents) {
            this.contents = contents;
        }

        public static class ContentsBeanX {
            /**
             * id : 15
             * name : 爱奇艺
             * goods_type_id : 15
             * type : 1
             * sort :
             * logo : http://oss.365taoquan.cn/commodityTypeLogo/1562144637861logoFilepic#deVersion=2019032902
             * category_id : 2
             * created_at : 2020-01-09 12:02:12
             * updated_at : 2020-01-13 23:56:05
             * is_hot : 1
             * brand_type : 2
             * brand_desc : 6.2折直充
             */

            private int id;
            private String name;
            private int goods_type_id;
            private int type;
            private String sort;
            private String logo;
            private int category_id;
            private String created_at;
            private String updated_at;
            private int is_hot;
            private int brand_type;
            private String brand_desc;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getGoods_type_id() {
                return goods_type_id;
            }

            public void setGoods_type_id(int goods_type_id) {
                this.goods_type_id = goods_type_id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public int getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(int is_hot) {
                this.is_hot = is_hot;
            }

            public int getBrand_type() {
                return brand_type;
            }

            public void setBrand_type(int brand_type) {
                this.brand_type = brand_type;
            }

            public String getBrand_desc() {
                return brand_desc;
            }

            public void setBrand_desc(String brand_desc) {
                this.brand_desc = brand_desc;
            }
        }
    }

    public static class BroadcastsBean {
        /**
         * label : 播报
         * icon :
         * contents : [{"redirect_type":1,"redirect":{"path":"/pages/recharges/recharges"},"content":"各大品牌最低5折起充值"},{"redirect_type":1,"redirect":{"path":"/pages/details/brand/brand?id=1106&mall_id=5"},"content":"glee买了一杯星巴克，省了10元"},{"redirect_type":1,"redirect":{"path":"/pages/details/brand/brand?id=1134&mall_id=5"},"content":"小虎买了爱奇艺黄金会员周卡，省了5元"}]
         */

        private String label;
        private String icon;
        private List<ContentsBeanXX> contents;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public List<ContentsBeanXX> getContents() {
            return contents;
        }

        public void setContents(List<ContentsBeanXX> contents) {
            this.contents = contents;
        }

        public static class ContentsBeanXX {
            /**
             * redirect_type : 1
             * redirect : {"path":"/pages/recharges/recharges"}
             * content : 各大品牌最低5折起充值
             */

            private int redirect_type;
            private RedirectBean redirect;
            private String content;

            public int getRedirect_type() {
                return redirect_type;
            }

            public void setRedirect_type(int redirect_type) {
                this.redirect_type = redirect_type;
            }

            public RedirectBean getRedirect() {
                return redirect;
            }

            public void setRedirect(RedirectBean redirect) {
                this.redirect = redirect;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public static class RedirectBean {
                /**
                 * path : /pages/recharges/recharges
                 */

                private String path;

                public String getPath() {
                    return path;
                }

                public void setPath(String path) {
                    this.path = path;
                }
            }
        }
    }

    public static class BannersBean {
        /**
         * label : 横幅
         * icon :
         * contents : [{"id":38,"title":"广告位-首页-精品-外部","image":"https://img.daff9.cn/biyingniao/images/adzone/5e7abd861a6fa.gif","redirect_type":2,"need_login":false,"bg_color":"","redirect":{"path":"/pages/sharePid/shareWebView/index?pid=mm_121524567_23466229_136776001:1111111111_111_1111111111&rid=2330894015&withLoginInfo=1&extraInfo=1","app_id":"wxece3a9a4c82f58c9"}},{"id":39,"title":"中部广告位","image":"https://img.daff9.cn/biyingniao/images/adzone/5e7abdb7bcf23.png","redirect_type":4,"need_login":false,"bg_color":"","redirect":{"path":"/pages/recharges/recharges"}},{"id":46,"title":"标题","image":"https://img.daff9.cn/biyingniao/images/other/1910/5dad795f31051.png","redirect_type":4,"need_login":false,"bg_color":"","redirect":{"path":"/pages/brands/clist/clist?bid=5&bt=2"}}]
         */

        private String label;
        private String icon;
        private List<ContentsBeanXXX> contents;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public List<ContentsBeanXXX> getContents() {
            return contents;
        }

        public void setContents(List<ContentsBeanXXX> contents) {
            this.contents = contents;
        }

        public static class ContentsBeanXXX {
            /**
             * id : 38
             * title : 广告位-首页-精品-外部
             * image : https://img.daff9.cn/biyingniao/images/adzone/5e7abd861a6fa.gif
             * redirect_type : 2
             * need_login : false
             * bg_color :
             * redirect : {"path":"/pages/sharePid/shareWebView/index?pid=mm_121524567_23466229_136776001:1111111111_111_1111111111&rid=2330894015&withLoginInfo=1&extraInfo=1","app_id":"wxece3a9a4c82f58c9"}
             */

            private int id;
            private String title;
            private String image;
            private int redirect_type;
            private boolean need_login;
            private String bg_color;
            private RedirectBeanX redirect;

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

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getRedirect_type() {
                return redirect_type;
            }

            public void setRedirect_type(int redirect_type) {
                this.redirect_type = redirect_type;
            }

            public boolean isNeed_login() {
                return need_login;
            }

            public void setNeed_login(boolean need_login) {
                this.need_login = need_login;
            }

            public String getBg_color() {
                return bg_color;
            }

            public void setBg_color(String bg_color) {
                this.bg_color = bg_color;
            }

            public RedirectBeanX getRedirect() {
                return redirect;
            }

            public void setRedirect(RedirectBeanX redirect) {
                this.redirect = redirect;
            }

            public static class RedirectBeanX {
                /**
                 * path : /pages/sharePid/shareWebView/index?pid=mm_121524567_23466229_136776001:1111111111_111_1111111111&rid=2330894015&withLoginInfo=1&extraInfo=1
                 * app_id : wxece3a9a4c82f58c9
                 */

                private String path;
                private String app_id;

                public String getPath() {
                    return path;
                }

                public void setPath(String path) {
                    this.path = path;
                }

                public String getApp_id() {
                    return app_id;
                }

                public void setApp_id(String app_id) {
                    this.app_id = app_id;
                }
            }
        }
    }

    public static class ZonesBean {
        /**
         * label : 精品专区
         * icon : https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/MiniProgram/qhb/star.png
         * contents : [{"id":37,"title":"精选1","image":"https://img.daff9.cn/biyingniao/images/adzone/5e71bf6e48561.png","redirect_type":4,"need_login":false,"bg_color":"","redirect":{"path":"/pages/details/tjp/tjp?id=91938235866&mall_id=3"}},{"id":40,"title":"精选2","image":"https://img.daff9.cn/biyingniao/images/adzone/5e71ba4cb8ff6.png","redirect_type":4,"need_login":false,"bg_color":"","redirect":{"path":"/pages/brands/gather/gather?cid=8&title=会员福利"}},{"id":41,"title":"精选3","image":"https://img.daff9.cn/biyingniao/images/adzone/5e71ba3f0f193.png","redirect_type":4,"need_login":false,"bg_color":"","redirect":{"path":"/pages/details/recharge/recharge?bid=83&id="}},{"id":42,"title":"精选4","image":"https://img.daff9.cn/biyingniao/images/adzone/5e71ba35c45ef.png","redirect_type":4,"need_login":false,"bg_color":"","redirect":{"path":"/pages/details/tjp/tjp?id=96633572616&mall_id=3"}},{"id":43,"title":"精选5","image":"https://img.daff9.cn/biyingniao/images/adzone/5e71b9bd02c8e.png","redirect_type":4,"need_login":false,"bg_color":"","redirect":{"path":"/pages/details/tjp/tjp?id=2611290&mall_id=4"}}]
         */

        private String label;
        private String icon;
        private List<ContentsBeanXXXX> contents;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public List<ContentsBeanXXXX> getContents() {
            return contents;
        }

        public void setContents(List<ContentsBeanXXXX> contents) {
            this.contents = contents;
        }

        public static class ContentsBeanXXXX {
            /**
             * id : 37
             * title : 精选1
             * image : https://img.daff9.cn/biyingniao/images/adzone/5e71bf6e48561.png
             * redirect_type : 4
             * need_login : false
             * bg_color :
             * redirect : {"path":"/pages/details/tjp/tjp?id=91938235866&mall_id=3"}
             */

            private int id;
            private String title;
            private String image;
            private int redirect_type;
            private boolean need_login;
            private String bg_color;
            private RedirectBeanXX redirect;

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

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getRedirect_type() {
                return redirect_type;
            }

            public void setRedirect_type(int redirect_type) {
                this.redirect_type = redirect_type;
            }

            public boolean isNeed_login() {
                return need_login;
            }

            public void setNeed_login(boolean need_login) {
                this.need_login = need_login;
            }

            public String getBg_color() {
                return bg_color;
            }

            public void setBg_color(String bg_color) {
                this.bg_color = bg_color;
            }

            public RedirectBeanXX getRedirect() {
                return redirect;
            }

            public void setRedirect(RedirectBeanXX redirect) {
                this.redirect = redirect;
            }

            public static class RedirectBeanXX {
                /**
                 * path : /pages/details/tjp/tjp?id=91938235866&mall_id=3
                 */

                private String path;

                public String getPath() {
                    return path;
                }

                public void setPath(String path) {
                    this.path = path;
                }
            }
        }
    }

    public static class RecommendBean {
        /**
         * label : 热门推荐
         * icon : https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/MiniProgram/qhb/hot.png
         * contents : [{"id":1269,"face_price":"10.00","coupon_cover":"http://oss.365taoquan.cn/commodityFace/1559525677594faceFilepic","sale_price":"7.50","coupon_name":"爱奇艺黄金会员周卡","coupon_type":1,"mall_id":6,"member_price":"6.00","brand_cover":"https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/MiniProgram/qhb/brands/iqiy.png","expire":"约30天","type":2,"short_title":"","fl_commission":"","coupon_money_text":"","sales":"","need_login":false,"rest_num":99},{"id":1326,"face_price":"33.00","coupon_cover":"http://rebate-robot.oss-cn-hangzhou.aliyuncs.com/uploads/image/2020/2/664d566084dacc09a91ba8ad581ee7f0.jpg","sale_price":"30.00","coupon_name":"中杯全国伙伴券","coupon_type":2,"mall_id":5,"member_price":"26.00","brand_cover":"https://biyingniao.oss-cn-shanghai.aliyuncs.com/coupon/%E6%98%9F%E5%B7%B4%E5%85%8B.png","expire":"约30天","type":2,"short_title":"","fl_commission":"","coupon_money_text":"","sales":9,"need_login":false,"rest_num":86},{"id":1131,"face_price":"50.00","coupon_cover":"http://oss.365taoquan.cn/commodityFace/1553048522610faceFilepic","sale_price":"49.00","coupon_name":"肯德基50元代金券链接","coupon_type":2,"mall_id":5,"member_price":"42.00","brand_cover":"http://oss.365taoquan.cn/commodityTypeLogo/1553647518704logoFilepic#deVersion=2019032902","expire":"约30天","type":2,"short_title":"","fl_commission":"","coupon_money_text":"","sales":0,"need_login":false,"rest_num":99},{"id":1136,"face_price":"58.00","coupon_cover":"http://oss.365taoquan.cn/commodityFace/1548725647435faceFilepic","sale_price":"44.50","coupon_name":"爱奇艺黄金会员季卡","coupon_type":2,"mall_id":5,"member_price":"39.50","brand_cover":"http://oss.365taoquan.cn/commodityTypeLogo/1562144637861logoFilepic#deVersion=2019032902","expire":"约30天","type":2,"short_title":"","fl_commission":"","coupon_money_text":"","sales":0,"need_login":false,"rest_num":99}]
         */

        private String label;
        private String icon;
        private List<ContentsBeanXXXXX> contents;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public List<ContentsBeanXXXXX> getContents() {
            return contents;
        }

        public void setContents(List<ContentsBeanXXXXX> contents) {
            this.contents = contents;
        }

        public static class ContentsBeanXXXXX {
            /**
             * id : 1269
             * face_price : 10.00
             * coupon_cover : http://oss.365taoquan.cn/commodityFace/1559525677594faceFilepic
             * sale_price : 7.50
             * coupon_name : 爱奇艺黄金会员周卡
             * coupon_type : 1
             * mall_id : 6
             * member_price : 6.00
             * brand_cover : https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/MiniProgram/qhb/brands/iqiy.png
             * expire : 约30天
             * type : 2
             * short_title :
             * fl_commission :
             * coupon_money_text :
             * sales :
             * need_login : false
             * rest_num : 99
             */

            private int id;
            private String face_price;
            private String coupon_cover;
            private String sale_price;
            private String coupon_name;
            private int coupon_type;
            private int mall_id;
            private String member_price;
            private String brand_cover;
            private String expire;
            private int type;
            private String short_title;
            private String fl_commission;
            private String coupon_money_text;
            private String sales;
            private boolean need_login;
            private int rest_num;

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

            public int getMall_id() {
                return mall_id;
            }

            public void setMall_id(int mall_id) {
                this.mall_id = mall_id;
            }

            public String getMember_price() {
                return member_price;
            }

            public void setMember_price(String member_price) {
                this.member_price = member_price;
            }

            public String getBrand_cover() {
                return brand_cover;
            }

            public void setBrand_cover(String brand_cover) {
                this.brand_cover = brand_cover;
            }

            public String getExpire() {
                return expire;
            }

            public void setExpire(String expire) {
                this.expire = expire;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getShort_title() {
                return short_title;
            }

            public void setShort_title(String short_title) {
                this.short_title = short_title;
            }

            public String getFl_commission() {
                return fl_commission;
            }

            public void setFl_commission(String fl_commission) {
                this.fl_commission = fl_commission;
            }

            public String getCoupon_money_text() {
                return coupon_money_text;
            }

            public void setCoupon_money_text(String coupon_money_text) {
                this.coupon_money_text = coupon_money_text;
            }

            public String getSales() {
                return sales;
            }

            public void setSales(String sales) {
                this.sales = sales;
            }

            public boolean isNeed_login() {
                return need_login;
            }

            public void setNeed_login(boolean need_login) {
                this.need_login = need_login;
            }

            public int getRest_num() {
                return rest_num;
            }

            public void setRest_num(int rest_num) {
                this.rest_num = rest_num;
            }
        }
    }

    public static class GoodsRecommendBean {
        /**
         * label : 好货推荐
         * icon :
         * contents : []
         */

        private String label;
        private String icon;
        private List<?> contents;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public List<?> getContents() {
            return contents;
        }

        public void setContents(List<?> contents) {
            this.contents = contents;
        }
    }
}
