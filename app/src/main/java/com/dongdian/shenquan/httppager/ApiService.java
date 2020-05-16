package com.dongdian.shenquan.httppager;


import com.dongdian.shenquan.bean.OauthBean;
import com.dongdian.shenquan.bean.PayBean;
import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.bean.CategoriesBean;
import com.dongdian.shenquan.bean.CheckVersionBean;
import com.dongdian.shenquan.bean.CommonBean;
import com.dongdian.shenquan.bean.CoupleBean;
import com.dongdian.shenquan.bean.CoupleetailDBean;
import com.dongdian.shenquan.bean.DataReportBean;
import com.dongdian.shenquan.bean.DataReportDetailBean;
import com.dongdian.shenquan.bean.FlashFragmentBean;
import com.dongdian.shenquan.bean.GoodsHomeBean;
import com.dongdian.shenquan.bean.HotWordsBean;
import com.dongdian.shenquan.bean.InviteBean;
import com.dongdian.shenquan.bean.KeFuBean;
import com.dongdian.shenquan.bean.MemberPageBean;
import com.dongdian.shenquan.bean.MyCollectListBean;
import com.dongdian.shenquan.bean.MyOrderListBean;
import com.dongdian.shenquan.bean.PPGMoreRightsBean;
import com.dongdian.shenquan.bean.PPGSearchBean;
import com.dongdian.shenquan.bean.PopWindowBean;
import com.dongdian.shenquan.bean.PosterBean;
import com.dongdian.shenquan.bean.ProductOrderBean;
import com.dongdian.shenquan.bean.RecommendBean;
import com.dongdian.shenquan.bean.TeamListBean;
import com.dongdian.shenquan.bean.TimeList;
import com.dongdian.shenquan.bean.coupon.CouponCancelBean;
import com.dongdian.shenquan.bean.coupon.CouponDetailBean;
import com.dongdian.shenquan.bean.coupon.CouponListBean;
import com.dongdian.shenquan.bean.login.ExistBean;
import com.dongdian.shenquan.bean.goods.GoodsDetailBean;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.bean.login.LoginBean;
import com.dongdian.shenquan.bean.ppg.PPGCategoriesBean;
import com.dongdian.shenquan.bean.ppg.PPGGoodsDetailBean;
import com.dongdian.shenquan.bean.ppg.PPGHomeBean;
import com.dongdian.shenquan.bean.ppg.PPGItemGoodsListBean;
import com.dongdian.shenquan.bean.tixian.WithDrawalReportBean;
import com.dongdian.shenquan.bean.user.UserBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;


/**
 * Created by Administrator on 2019/3/8.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("/api/user/exist")
    Observable<BaseBean<ExistBean>> exist(@HeaderMap Map<String, Object> headerMap, @FieldMap Map<String, Object> map);
    @FormUrlEncoded
    @POST("/api/user/get_validate_code")
    Observable<BaseBean> get_validate_code(@HeaderMap Map<String, Object> headerMap, @FieldMap Map<String, Object> map);
    @FormUrlEncoded
    @POST("/api/user/register")
    Observable<BaseBean<LoginBean>> register(@HeaderMap Map<String, Object> headerMap, @FieldMap Map<String, Object> map);
    @FormUrlEncoded
    @POST("/api/user/login")
    Observable<BaseBean<LoginBean>> login(@HeaderMap Map<String, Object> headerMap, @FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/api/mpv2/item_poster")
    Observable<BaseBean<PosterBean>> item_poster(@HeaderMap Map<String, Object> headerMap, @FieldMap Map<String, Object> map);

    @GET("/api/goods/check_version")
    Observable<BaseBean<CheckVersionBean>> check_version(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);


    @GET("/api/mpv2/user/info")
    Observable<BaseBean<UserBean>> info(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/api/user/update_info")
    Observable<BaseBean> update_info(@HeaderMap Map<String, Object> headerMap, @FieldMap Map<String, Object> map);

    @GET("/api/goods/categories")
    Observable<BaseBean<List<CategoriesBean>>> categories(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);

    @GET("/api/mpv2/home")
    Observable<BaseBean<PPGCategoriesBean>> o_categories(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);



    @GET("/api/goods/pop_window")
    Observable<BaseBean<PopWindowBean>> pop_window(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);

    @GET("/api/goods/deep_search")
    Observable<BaseBean<GoodsList.ItemsBean>> deep_search(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);

    @GET("/api/goods/suspend")
    Observable<BaseBean<GoodsHomeBean.BannersBean>> suspend(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);


    @GET("/api/goods/home")
    Observable<BaseBean<GoodsHomeBean>> goodshome(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);

    @GET("/api/goods/items")
    Observable<BaseBean<GoodsList>> items(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);

    @GET("/api/user/articles")
    Observable<BaseBean<CoupleetailDBean>> articles(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);

    @GET("/api/goods/taobao/hot")
    Observable<BaseBean<GoodsList>> taobao_hot(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);

    @GET("/api/mpv2/life-main")
    Observable<BaseBean<PPGMoreRightsBean>> life_main(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);


    @GET("/api/goods/search")
    Observable<BaseBean<GoodsList>> search(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);


    @GET("api/mpv2/category-products")
    Observable<BaseBean<PPGItemGoodsListBean>> o_goods(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);


    @GET("/api/mpv2/recommends")
    Observable<BaseBean<List<PPGHomeBean>>> recommends(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);


    @GET("api/mpv2/search")
    Observable<BaseBean<PPGSearchBean>> mpv_search(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);



    @GET("/api/mpv2/brand-products")
    Observable<BaseBean<PPGItemGoodsListBean>> brand_products_first(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);
    @GET("/api/mpv2/brand-products")
    Observable<BaseBean> brand_products_second(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);



    @GET("/api/mpv2/product-detail")
    Observable<BaseBean<PPGGoodsDetailBean>> o_goods_info(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);


    @GET("/api/mpv2/product-detail")
    Observable<BaseBean> o_goods_info_second(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);


    @GET("/api/goods/guess_like")
    Observable<BaseBean<GoodsList>> guess_like(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);

    @GET("/api/goods/detail")
    Observable<BaseBean<GoodsDetailBean>> goodsdetail(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);

    @GET("/api/goods/similar_items")
    Observable<BaseBean<GoodsList>> similar_items(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);

    @GET("/api/mpv2/user/tx_recs")
    Observable<BaseBean<WithDrawalReportBean>> tx_recs(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/api/mpv2/user/tixian")
    Observable<BaseBean> tixian(@HeaderMap Map<String, Object> headerMap, @FieldMap Map<String, Object> map);


    @GET("/api/mpv2/user/report")
    Observable<BaseBean<DataReportBean>> report(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/api/coupon/o-orders")
    Observable<BaseBean<CouponListBean>> o_orders(@HeaderMap Map<String, Object> headerMap, @FieldMap Map<String, Object> map);

    @GET("/api/coupon/o-order_detail")
    Observable<BaseBean<CouponDetailBean>> o_orders_detail(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);
    @FormUrlEncoded
    @POST("api/coupon/o-order_cancel")
    Observable<BaseBean<CouponCancelBean>> o_orders_cancel(@HeaderMap Map<String, Object> headerMap, @FieldMap Map<String, Object> map);


    @GET("/api/user/collects")
    Observable<BaseBean<MyCollectListBean>> collects(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);

    @GET("/api/user/team")
    Observable<BaseBean<TeamListBean>> user_team(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);
    @GET("/api/user/browsers")
    Observable<BaseBean> browsers(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);

    @GET("/api/user/invite_poster")
    Observable<BaseBean<InviteBean>> invite_poster(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);

    @GET("/api/user/orders")
    Observable<BaseBean<MyOrderListBean>> orders(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);

    @GET("/api/goods/hot_words")
    Observable<BaseBean<HotWordsBean>> hot_words(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);


    @FormUrlEncoded
    @POST("/api/user/collect")
    Observable<BaseBean> collect(@HeaderMap Map<String, Object> headerMap, @FieldMap Map<String, Object> map);


    @FormUrlEncoded
    @POST("/api/user/cancel_collect")
    Observable<BaseBean> cancel_collect(@HeaderMap Map<String, Object> headerMap, @FieldMap Map<String, Object> map);

    @GET("/api/mpv2/user/account_flows")
    Observable<BaseBean<DataReportDetailBean>> account_flows(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);


    @GET("/api/user/article_categories")
    Observable<BaseBean<List<CoupleBean>>> article_categories(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);


    @GET("/api/goods/flash_sale_times")
    Observable<BaseBean<List<TimeList>>> flash_sale_times(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);

    @GET("/api/goods/flash_sale_items")
    Observable<BaseBean<FlashFragmentBean>> flash_sale_items(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/api/user/get_recommendor")
    Observable<BaseBean<RecommendBean>> get_recommendor(@HeaderMap Map<String, Object> headerMap, @FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/api/mpv2/common")
    Observable<BaseBean<CommonBean>> common(@HeaderMap Map<String, Object> headerMap, @FieldMap Map<String, Object> map);
    @GET("/api/mpv2/app_kefu")
    Observable<BaseBean<KeFuBean>> app_kefu(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);


    @FormUrlEncoded
    @POST("/api/mpv2/product-order")
    Observable<BaseBean<ProductOrderBean>> product_order_first(@HeaderMap Map<String, Object> headerMap, @FieldMap Map<String, Object> map);

    @GET("/api/mpv2/product-order")
    Observable<BaseBean<PayBean>> product_order_second(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);

    @GET("/api/mpv2/member-page")
    Observable<BaseBean<MemberPageBean>> member_page(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/api/mpv2/member-buy")
    Observable<BaseBean<ProductOrderBean>> member_buy_first(@HeaderMap Map<String, Object> headerMap, @FieldMap Map<String, Object> map);

    @GET("/api/mpv2/member-buy")
    Observable<BaseBean<PayBean>> member_buy_second(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);



    @GET("/api/goods/album/{id}")
    Observable<BaseBean<GoodsList>> album(@Path ("id")String id, @HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);

    @GET("/api/user/tb_oauth")
    Observable<BaseBean<OauthBean>> tb_oauth(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> map);




}
