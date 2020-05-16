package com.dongdian.shenquan.ui.activity.goodsdetail;

import android.app.Application;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;
import com.alibaba.baichuan.trade.biz.core.taoke.AlibcTaokeParams;
import com.alibaba.baichuan.trade.biz.login.AlibcLogin;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.PosterBean;
import com.dongdian.shenquan.bean.goods.GoodsDetailBean;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.ui.activity.couple.CoupleActivity;
import com.dongdian.shenquan.ui.activity.login.LoginActivity;
import com.dongdian.shenquan.ui.activity.main.MainActivity;
import com.dongdian.shenquan.ui.activity.search.SearchActivity;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.dongdian.shenquan.utils.Utils;
import com.dongdian.shenquan.view.circle.ADInfo;
import com.dongdian.shenquan.view.circle.ImageCycleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.ImageUtils;
import me.goldze.mvvmhabit.utils.SPUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;
import retrofit2.http.HeaderMap;

public class GoodsDetailViewModel extends MyBaseViewModel {
    public GoodsDetailBean goodsDetailBean;
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public BindingCommand lingqu = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
                startActivity(LoginActivity.class);
                return;
            }
            if (goodsDetailBean.getMall_id().equals("1") || goodsDetailBean.getMall_id().equals("2")) {
                if (goodsDetailBean.isNeed_oauth()) {
                    uc.oauth.setValue(goodsDetailBean);
                } else {
                    uc.lingquan.setValue(goodsDetailBean);
                }
            } else if (goodsDetailBean.getMall_id().equals("3") || goodsDetailBean.getMall_id().equals("4")) {
                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(goodsDetailBean.getDeeplink()));
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                List<ResolveInfo> activities = getApplication().getPackageManager().queryIntentActivities(in, 0);
                boolean isValid = !activities.isEmpty();
                //   startActivity(in);
                if (isValid) {
                    getApplication().startActivity(in);
                } else
                    ToastUtils.showShort("未安装该应用");
            }
        }
    });
    public BindingCommand chakanjiaocheng = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(CoupleActivity.class);
        }
    });
    public BindingCommand open_close_xiangqing = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (!TextUtils.isEmpty(goodsDetailBean.getDetail_web_url())) {
                uc.xiangqingopen.setValue(goodsDetailBean.getDetail_web_url());
            }
        }
    });
    public BindingCommand go_home = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(MainActivity.class);
            finish();
        }
    });
    public BindingCommand collect = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
                startActivity(LoginActivity.class);
                return;
            }
            if (goodsDetailBean.getMall_id().equals("1") || goodsDetailBean.getMall_id().equals("2") || goodsDetailBean.getMall_id().equals("3") || goodsDetailBean.getMall_id().equals("4")) {
                if (uc.iscollect.getValue()) {
                    cancel_collect();
                } else {
                    collect();
                }
            } else {
                ToastUtils.showShort("暂不支持该商品收藏");
                return;
            }

        }
    });
    public BindingCommand share = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
                startActivity(LoginActivity.class);
                return;
            }
            if (goodsDetailBean == null) {
                return;
            }
            uc.share.setValue(goodsDetailBean.getMall_id());
        }
    });
    public BindingCommand buy = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
                startActivity(LoginActivity.class);
                return;
            }
            if (goodsDetailBean.getMall_id().equals("1") || goodsDetailBean.getMall_id().equals("2")) {
                if (goodsDetailBean.isNeed_oauth()) {
                    uc.oauth.setValue(goodsDetailBean);
                } else {
                    uc.lingquan.setValue(goodsDetailBean);
                }
            } else if (goodsDetailBean.getMall_id().equals("3") || goodsDetailBean.getMall_id().equals("4")) {
                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(goodsDetailBean.getDeeplink()));
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                List<ResolveInfo> activities = getApplication().getPackageManager().queryIntentActivities(in, 0);
                boolean isValid = !activities.isEmpty();
                //   startActivity(in);
                if (isValid) {
                    getApplication().startActivity(in);
                } else
                    ToastUtils.showShort("未安装该应用");
            }

        }
    });
    public BindingCommand tosearch = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

           uc.isToSearch.setValue("");
        }
    });
    public BindingCommand totop = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.toTop.setValue("");
        }
    });
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {

        public UIChangeObservable() {
        }

        public SingleLiveEvent<String> xiangqingopen = new SingleLiveEvent<>();
        public SingleLiveEvent<String> webviewchushihua = new SingleLiveEvent<>();

        public SingleLiveEvent<List<String>> imagerecycler = new SingleLiveEvent<>();
        public SingleLiveEvent<String> toTop = new SingleLiveEvent<>();

        public SingleLiveEvent<GoodsList> similar_list = new SingleLiveEvent<>();

        public SingleLiveEvent<Boolean> iscollect = new SingleLiveEvent<>();

        public SingleLiveEvent<String> share = new SingleLiveEvent<>();

        public SingleLiveEvent<GoodsDetailBean> oauth = new SingleLiveEvent<>();
        public SingleLiveEvent<GoodsDetailBean> lingquan = new SingleLiveEvent<>();
        public SingleLiveEvent<String> shareImageWechat = new SingleLiveEvent<>();
        public SingleLiveEvent<String> shareImagepengyou = new SingleLiveEvent<>();

        public SingleLiveEvent<GoodsList.ItemsBean> openTKL = new SingleLiveEvent<>();
        public SingleLiveEvent<String> isToSearch = new SingleLiveEvent<>();

    }

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> discount_price = new ObservableField<>();
    public ObservableField<String> mall_icon = new ObservableField<>();
    public ObservableField<String> coupon_money = new ObservableField<>();
    public ObservableField<String> shop_logo = new ObservableField<>();
    public ObservableField<Integer> fl_commission_button = new ObservableField<>(View.GONE);
    public ObservableField<Integer> fl_commission_visiable = new ObservableField<>(View.GONE);
    public ObservableField<Integer> youhuivisiable = new ObservableField<>(View.GONE);
    public ObservableField<Integer> dsr_info = new ObservableField<>(View.GONE);
    public ObservableField<Integer> webviewvisiable = new ObservableField<>(View.GONE);
    public ObservableField<Integer> xiangsi = new ObservableField<>(View.GONE);
    public ObservableField<String> price = new ObservableField<>();
    public ObservableField<String> month_sales = new ObservableField<>();
    public ObservableField<String> coupon_starttime_coupon_endtime = new ObservableField<>();
    public ObservableField<String> shop_name = new ObservableField<>();
    public ObservableField<String> fl_commission = new ObservableField<>();

    public ObservableField<String> descriptionMatch = new ObservableField<>();
    public ObservableField<String> serviceAttitude = new ObservableField<>();
    public ObservableField<String> deliverySpeed = new ObservableField<>();
    public ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {

        @Override
        public void onImageClick(ADInfo info, int position, View imageView) {

        }

        @Override
        public void displayImage(String imageURL, ImageView imageView) {
            // 使用Glide对图片进行加装！
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            ShowImageUtils.showImageView(getApplication(), imageURL, imageView);
        }
    };


    public GoodsDetailViewModel(@NonNull Application application) {
        super(application);
    }


    public void getDetail(HashMap<String, Object> headerMap, HashMap<String, Object> queryMap) {
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).goodsdetail(headerMap, queryMap)
                , this, new HttpInterFace<GoodsDetailBean>() {
                    @Override
                    public void success(BaseBean<GoodsDetailBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);

                        goodsDetailBean = loginBeanBaseBean.getData();
                        uc.iscollect.setValue(goodsDetailBean.isIs_collect());
                        if (!TextUtils.isEmpty(goodsDetailBean.getFl_commission())) {
                            fl_commission_visiable.set(View.VISIBLE);
                            fl_commission_button.set(View.VISIBLE);
                            fl_commission.set(goodsDetailBean.getFl_commission());
                        } else {
                            fl_commission_visiable.set(View.INVISIBLE);
                            fl_commission_button.set(View.GONE);
                        }

                        if (!TextUtils.isEmpty(goodsDetailBean.getCoupon_money()) && Float.valueOf(goodsDetailBean.getCoupon_money()) > 0) {
                            youhuivisiable.set(View.VISIBLE);
                            coupon_starttime_coupon_endtime.set(goodsDetailBean.getCoupon_starttime() + "~" + goodsDetailBean.getCoupon_endtime());
                        } else {
                            youhuivisiable.set(View.GONE);
                        }

                        if (goodsDetailBean.getDsr_info() != null && goodsDetailBean.getDsr_info().getDeliverySpeed() != null) {
                            dsr_info.set(View.VISIBLE);
                            descriptionMatch.set("宝贝描述：" + goodsDetailBean.getDsr_info().getDescriptionMatch());
                            serviceAttitude.set("卖家服务：" + goodsDetailBean.getDsr_info().getServiceAttitude());
                            deliverySpeed.set("处理速度：" + goodsDetailBean.getDsr_info().getDeliverySpeed());
                        } else {
                            dsr_info.set(View.GONE);
                        }
                        if (!TextUtils.isEmpty(goodsDetailBean.getMall_id()) && (goodsDetailBean.getMall_id().equals("3") || goodsDetailBean.getMall_id().equals("4"))) {
                            xiangsi.set(View.GONE);
                        } else {
                            xiangsi.set(View.VISIBLE);
                        }

                        price.set("原价¥" + goodsDetailBean.getPrice());
                        month_sales.set("已售" + goodsDetailBean.getMonth_sales() + "件");
                        shop_name.set("【" + goodsDetailBean.getShop_name() + "】");


                        title.set(goodsDetailBean.getTitle());
                        discount_price.set(goodsDetailBean.getDiscount_price());
                        mall_icon.set(goodsDetailBean.getMall_icon());
                        coupon_money.set(goodsDetailBean.getCoupon_money());
                        shop_logo.set(goodsDetailBean.getShop_logo());
                        if (!TextUtils.isEmpty(goodsDetailBean.getDetail_web_url())) {
                            uc.webviewchushihua.setValue(goodsDetailBean.getDetail_web_url());
                        }


                        List<String> images = goodsDetailBean.getImages();
                        if (images != null && images.size() > 0) {
                            uc.imagerecycler.setValue(images);
                        } else {
                            List<String> image = new ArrayList<>();
                            image.add(goodsDetailBean.getCover_image());
                            uc.imagerecycler.setValue(image);
                        }


                    }

                });
    }

    public void getSimilar(String item_id, int page) {
        if (page == 1) {
            openloading();
        }
        HashMap<String, Object> query = new HashMap<>();
        query.put("page", page);
        query.put("item_id", item_id);
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).similar_items(Utils.getHeader(getApplication()), query)
                , this, new HttpInterFace<GoodsList>() {
                    @Override
                    public void success(BaseBean<GoodsList> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        closeloading();
                        uc.similar_list.setValue(loginBeanBaseBean.getData());
                    }

                    @Override
                    public void error(Exception e) {
                        super.error(e);
                        closeloading();
                    }
                });


    }


    public void collect() {
        openloading();
        HashMap<String, Object> queryMap = new HashMap<>();
        queryMap.put("item_type", goodsDetailBean.getMall_id());
        queryMap.put("item_id", goodsDetailBean.getItem_id());
        queryMap.put("price", goodsDetailBean.getPrice());
        queryMap.put("title", goodsDetailBean.getTitle());
        queryMap.put("cover_image", goodsDetailBean.getCover_image());
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).collect(Utils.getHeader(getApplication()), queryMap)
                , this, new HttpInterFace() {
                    @Override
                    public void success(BaseBean loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        closeloading();
                        ToastUtils.showShort("收藏成功");
                        uc.iscollect.setValue(true);
                    }

                    @Override
                    public void error(Exception e) {
                        super.error(e);
                        closeloading();
                    }
                });

    }

    public void cancel_collect() {
        openloading();
        HashMap<String, Object> queryMap = new HashMap<>();
        queryMap.put("item_type", goodsDetailBean.getMall_id());
        queryMap.put("item_id", goodsDetailBean.getItem_id());

        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).cancel_collect(Utils.getHeader(getApplication()), queryMap)
                , this, new HttpInterFace() {
                    @Override
                    public void success(BaseBean loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        closeloading();
                        ToastUtils.showShort("取消收藏");
                        uc.iscollect.setValue(false);
                    }

                    @Override
                    public void error(Exception e) {
                        super.error(e);
                        closeloading();
                    }
                });

    }

    public void kouling() {
        Utils.copy(getApplication(), goodsDetailBean.getTkl());
    }

    public void wechat() {
        openloading();
        HashMap<String, Object> fileMap = new HashMap<>();
        fileMap.put("mall_id", goodsDetailBean.getMall_id());
        fileMap.put("item_id", goodsDetailBean.getItem_id());
        fileMap.put("item_name", goodsDetailBean.getTitle());
        fileMap.put("item_image", goodsDetailBean.getCover_image());
        fileMap.put("price", goodsDetailBean.getPrice());
        fileMap.put("discount_price", goodsDetailBean.getDiscount_price());
        fileMap.put("coupon_money", goodsDetailBean.getCoupon_money());

        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).item_poster(Utils.getHeader(getApplication()), fileMap)
                , this, new HttpInterFace<PosterBean>() {
                    @Override
                    public void success(BaseBean<PosterBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        closeloading();
                        uc.shareImageWechat.setValue(loginBeanBaseBean.getData().getPoster());
                    }

                    @Override
                    public void error(Exception e) {
                        super.error(e);
                        closeloading();
                        ToastUtils.showShort("分享失败");
                    }
                });


    }

    public void pengyou() {
        openloading();
        HashMap<String, Object> fileMap = new HashMap<>();
        fileMap.put("mall_id", goodsDetailBean.getMall_id());
        fileMap.put("item_id", goodsDetailBean.getItem_id());
        fileMap.put("item_name", goodsDetailBean.getTitle());
        fileMap.put("item_image", goodsDetailBean.getCover_image());
        fileMap.put("price", goodsDetailBean.getPrice());
        fileMap.put("discount_price", goodsDetailBean.getDiscount_price());
        fileMap.put("coupon_money", goodsDetailBean.getCoupon_money());

        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).item_poster(Utils.getHeader(getApplication()), fileMap)
                , this, new HttpInterFace<PosterBean>() {
                    @Override
                    public void success(BaseBean<PosterBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        closeloading();
                        uc.shareImagepengyou.setValue(loginBeanBaseBean.getData().getPoster());
                    }

                    @Override
                    public void error(Exception e) {
                        super.error(e);
                        closeloading();
                        ToastUtils.showShort("分享失败");
                    }
                });


    }

    public void lianjie() {

    }
    public void deep_search(String content){
        HashMap<String,Object> map = new HashMap<>();
        map.put("keyword",content);
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).deep_search(Utils.getHeader(getApplication()),map)
                ,this,new HttpInterFace<GoodsList.ItemsBean>(){
                    @Override
                    public void success(BaseBean<GoodsList.ItemsBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        uc.openTKL.setValue(loginBeanBaseBean.getData());
                    }

                    @Override
                    public void error(Exception e) {
                        super.error(e);
                        uc.openTKL.setValue(null);
                    }
                });
    }


}
