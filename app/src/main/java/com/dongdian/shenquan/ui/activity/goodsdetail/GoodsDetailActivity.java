package com.dongdian.shenquan.ui.activity.goodsdetail;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import me.goldze.mvvmhabit.utils.SPUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;
import com.alibaba.baichuan.trade.biz.core.taoke.AlibcTaokeParams;
import com.alibaba.baichuan.trade.biz.login.AlibcLogin;
import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;
import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.bean.goods.GoodsDetailBean;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.databinding.ActivityGoodsDetailBinding;
import com.dongdian.shenquan.ui.activity.main.MainActivity;
import com.dongdian.shenquan.ui.activity.search.SearchActivity;
import com.dongdian.shenquan.ui.viewholder.SimilarHolder;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.Utils;
import com.dongdian.shenquan.view.EvenItemDecoration;
import com.dongdian.shenquan.view.GridItemDecoration;
import com.dongdian.shenquan.view.PopWindow;
import com.dongdian.shenquan.view.TKLPopupWindow;
import com.dongdian.shenquan.view.circle.ADInfo;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alibaba.baichuan.trade.biz.applink.adapter.AlibcFailModeType.AlibcNativeFailModeJumpH5;

public class GoodsDetailActivity extends MyBaseActivity<ActivityGoodsDetailBinding, GoodsDetailViewModel> {


    private String item_id;
    private String mall_id;
    private String activity_id;
    private int page = 1;
    private boolean has_next = true;
    public RecyclerArrayAdapter similar = new RecyclerArrayAdapter(this) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new SimilarHolder(parent);
        }
    };

    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        Bundle extras = getIntent().getExtras();
        item_id = extras.getString("item_id");
        mall_id = extras.getString("mall_id");
        activity_id = extras.getString("activity_id", null);
        return R.layout.activity_goods_detail;
    }
    private String copy;
    @Override
    protected void onResume() {
        super.onResume();
        copy = Utils.getCopy(ctx);
        if(!TextUtils.isEmpty(copy)){
            viewModel.deep_search(copy);
        }

    }

    @Override
    public int initVariableId() {
        return BR.goodsdetailviewmodel;
    }

    @SuppressLint("JavascriptInterface")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initData() {
        super.initData();
        binding.goodsDetailPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        binding.goodsDetailScrollview.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                int height = Utils.dp2px(GoodsDetailActivity.this, 40);

                if (i1 <= 0) {
                    binding.goodsDetailTopSearch.setAlpha(0f);
                    binding.goodsDetailToTop.setAlpha(0f);
                } else if (i1 > 0 && i1 < height) {
                    float scale = (float) i1 / height;
                    float alpha = (1.0f * scale);
                    binding.goodsDetailTopSearch.setAlpha(alpha);
                    binding.goodsDetailToTop.setAlpha(alpha);
                } else {
                    binding.goodsDetailTopSearch.setAlpha(1f);
                    binding.goodsDetailToTop.setAlpha(1f);
                }
                int scrollY = binding.goodsDetailScrollview.getScrollY();
                View onlyChild = binding.goodsDetailScrollview.getChildAt(0);
                if (onlyChild.getHeight() <= scrollY + binding.goodsDetailScrollview.getHeight()) {   // 如果满足就是到底部了
//                    if (has_next) {
//                        page++;
//                        viewModel.getSimilar(item_id, page);
//                    }
                }

            }
        });

        binding.goodsDetailWeb.getSettings().setDomStorageEnabled(true);
        // 设置支持javascript
        binding.goodsDetailWeb.getSettings().setJavaScriptEnabled(true);
        // 启动缓存
        binding.goodsDetailWeb.getSettings().setAppCacheEnabled(true);
        // 设置缓存模式
        binding.goodsDetailWeb.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        binding.goodsDetailWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });


        getData();
        if (mall_id != null && !mall_id.equals("3") && !mall_id.equals("4")) {
            Utils.setGildLayoutNotScroll(this, 2, binding.goodsDetailSimilarRecycler
                    , new EvenItemDecoration(Utils.dp2px(this, 8), 2), similar);
            similar.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    GoodsList.ItemsBean item = (GoodsList.ItemsBean) similar.getItem(position);
                    Bundle bundle = new Bundle();
                    bundle.putString("item_id", item.getItem_id());
                    bundle.putString("mall_id", item.getMall_id() + "");
                    if (!TextUtils.isEmpty(item.getActivity_id())) {
                        bundle.putString("activity_id", item.getActivity_id());
                    }
                    startActivity(GoodsDetailActivity.class, bundle);
                    finish();

                }
            });
            viewModel.getSimilar(item_id, page);
        }


    }


    public void getData() {
        HashMap<String, Object> query = new HashMap<>();
        query.put("mall_id", mall_id+"");
        query.put("item_id", item_id);
        if (!TextUtils.isEmpty(activity_id)) {
            query.put("activity_id", activity_id);
        }
        viewModel.getDetail(Utils.getHeader(getApplication()), query);
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.xiangqingopen.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (binding.goodsDetailWeb.getVisibility() == View.VISIBLE) {
                    binding.goodsDetailXiangqingIcon.setImageResource(R.mipmap.ic_xiangqing_first);
                    viewModel.webviewvisiable.set(View.GONE);
                } else {
                    binding.goodsDetailXiangqingIcon.setImageResource(R.mipmap.ic_xiangqing_second);
                    viewModel.webviewvisiable.set(View.VISIBLE);
                }

            }
        });
        viewModel.uc.webviewchushihua.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
            //    binding.goodsDetailWeb.loadUrl("http://grd22.top/mall/mashup?page_id=6878&app_key=wejbae&pid=mm_204880197_1285250460_110010000065&relation_id=2381060398");
                binding.goodsDetailWeb.loadUrl(s);
            }
        });
        viewModel.uc.imagerecycler.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                ArrayList<ADInfo> infos = new ArrayList<ADInfo>();
                for (int i = 0; i < strings.size(); i++) {
                    ADInfo info = new ADInfo();
                    info.setUrl(strings.get(i));
                    infos.add(info);
                    binding.goodsDetailImagecycler.pushImageCycle();
                }
                binding.goodsDetailImagecycler.setImageResources(infos, viewModel.mAdCycleViewListener, Gravity.CENTER);
            }
        });
        viewModel.uc.toTop.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.goodsDetailScrollview.fling(0);
                binding.goodsDetailScrollview.smoothScrollTo(0, 0);
            }
        });
        viewModel.uc.similar_list.observe(this, new Observer<GoodsList>() {
            @Override
            public void onChanged(GoodsList goodsList) {
                if (page == 1 && goodsList.getItems() != null && goodsList.getItems().size() > 0) {
                    viewModel.xiangsi.set(View.VISIBLE);
                }
                if (page == 1 && (goodsList.getItems() == null || goodsList.getItems().size() == 0)) {
                    viewModel.xiangsi.set(View.GONE);
                }
                similar.addAll(goodsList.getItems());
                similar.notifyDataSetChanged();
                has_next = goodsList.isHas_next();
            }
        });
        viewModel.uc.iscollect.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    binding.goodsDetailCollectText.setText("已收藏");
                    binding.goodsDetailCollectImage.setImageResource(R.mipmap.ic_collect_is);
                } else {
                    binding.goodsDetailCollectText.setText("收藏");
                    binding.goodsDetailCollectImage.setImageResource(R.mipmap.ic_collect);
                }
            }
        });

        viewModel.uc.oauth.observe(this, new Observer<GoodsDetailBean>() {
            @Override
            public void onChanged(GoodsDetailBean goodsDetailBean) {
                Utils.oauth(GoodsDetailActivity.this,goodsDetailBean.getOauth_url());

            }
        });

        viewModel.uc.lingquan.observe(this, new Observer<GoodsDetailBean>() {
            @Override
            public void onChanged(GoodsDetailBean goodsDetailBean) {
                taobaolingqu(goodsDetailBean);
            }
        });
        viewModel.uc.share.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String aBoolean) {
                showPopup(aBoolean);
            }
        });
        viewModel.uc.shareImageWechat.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                UMImage umImage = new UMImage(GoodsDetailActivity.this, s);
                umImage.setThumb(new UMImage(GoodsDetailActivity.this, s));
                new ShareAction(GoodsDetailActivity.this)
                        .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                        .withMedia(umImage)
                        .share();


            }
        });
        viewModel.uc.shareImagepengyou.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                UMImage umImage = new UMImage(GoodsDetailActivity.this, s);
                umImage.setThumb(new UMImage(GoodsDetailActivity.this, s));
                new ShareAction(GoodsDetailActivity.this)
                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                        .withMedia(umImage)
                        .share();


            }
        });
        viewModel.uc.isToSearch.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                float alpha = binding.goodsDetailTopSearch.getAlpha();

                if(alpha>0.2){
                    startActivity(SearchActivity.class);
                }
            }
        });
        viewModel.uc.openTKL.observe(this, new Observer<GoodsList.ItemsBean>() {
            @Override
            public void onChanged(GoodsList.ItemsBean itemsBean) {
                if(itemsBean==null){
                    TKLPopupWindow tklPopupWindow = new TKLPopupWindow(GoodsDetailActivity.this,2,null,copy);
                    tklPopupWindow.showAtLocation(binding.goodsDetailCollect,Gravity.CENTER,0,0);
                }else{
                    TKLPopupWindow tklPopupWindow = new TKLPopupWindow(GoodsDetailActivity.this,1,itemsBean,null);
                    tklPopupWindow.showAtLocation(binding.goodsDetailCollect,Gravity.CENTER,0,0);
                }
            }
        });
    }

    public void showPopup(String mallId){
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.activity_share, null);
        final PopWindow popupWindow = new PopWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        LinearLayout taokoulin = contentView.findViewById(R.id.share_popup_kouling);
        LinearLayout wechat = contentView.findViewById(R.id.share_popup_wechat);
        LinearLayout pengyou = contentView.findViewById(R.id.share_popup_pengyou);
        LinearLayout lianjie = contentView.findViewById(R.id.share_popup_lianjie);
        if(mallId.equals("3")||mallId.equals("4")){
            taokoulin.setVisibility(View.GONE);
            lianjie.setVisibility(View.GONE);
        }else{
            taokoulin.setVisibility(View.VISIBLE);
            lianjie.setVisibility(View.VISIBLE);
        }



        taokoulin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.kouling();
                popupWindow.dismiss();
            }
        });
        wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.wechat();
                popupWindow.dismiss();
            }
        });
        pengyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.pengyou();
                popupWindow.dismiss();
            }
        });
        lianjie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.lianjie();
                popupWindow.dismiss();
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable(0000000000));
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f; //0.0-1.0
        getWindow().setAttributes(lp);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f; //0.0-1.0
                getWindow().setAttributes(lp);
            }
        });
        popupWindow.showAtLocation(binding.goodsDetailGoHome,Gravity.BOTTOM,0,0);







    }



    public void taobaolingqu(GoodsDetailBean goodsDetailBean) {
        if (TextUtils.isEmpty(goodsDetailBean.getCoupon_click_url())) {
            ToastUtils.showShort("链接地址不存在");
            return;
        }
        AlibcShowParams showParams = new AlibcShowParams();
        showParams.setOpenType(OpenType.Native);
        showParams.setBackUrl("alisdk://");
        showParams.setNativeOpenFailedMode(AlibcNativeFailModeJumpH5);
        //自定义参数
        Map<String, String> trackParams = new HashMap<>();
        trackParams.put("isv_code", "appisvcode");
        trackParams.put("alibaba", "阿里巴巴");//自定义参数部分，可任意增删改
        AlibcTrade.openByUrl(GoodsDetailActivity.this, "", goodsDetailBean.getCoupon_click_url(),
                null, new WebViewClient(), new WebChromeClient(), showParams, new AlibcTaokeParams("", "", "")
                , trackParams, new AlibcTradeCallback() {
                    @Override
                    public void onTradeSuccess(AlibcTradeResult alibcTradeResult) {
                        ;
                    }

                    @Override
                    public void onFailure(int i, String s) {

                    }
                });
    }

    public class MyJavaScript {


    }

}
