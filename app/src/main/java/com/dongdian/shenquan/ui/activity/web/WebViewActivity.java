package com.dongdian.shenquan.ui.activity.web;

import androidx.appcompat.app.AppCompatActivity;
import me.goldze.mvvmhabit.utils.SPUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.bean.DaoHangBean;
import com.dongdian.shenquan.bean.LocationBean;
import com.dongdian.shenquan.databinding.ActivityWebViewBinding;
import com.dongdian.shenquan.ui.activity.goodsdetail.GoodsDetailActivity;
import com.dongdian.shenquan.utils.GPSUtil;
import com.dongdian.shenquan.utils.MapUtil;
import com.google.gson.Gson;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.List;

public class WebViewActivity extends MyBaseActivity<ActivityWebViewBinding, WebViewViewModel> {
    private String url;


    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return R.layout.activity_web_view;
    }

    @Override
    public int initVariableId() {
        return BR.webviewviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String title = extras.getString("title");
                 url = extras.getString("url");
                viewModel.title.set(title);
            }
        }


        binding.mWebvew.getSettings().setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        binding.mWebvew.getSettings().setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        binding.mWebvew.getSettings().setDisplayZoomControls(true); //隐藏原生的缩放控件
        binding.mWebvew.getSettings().setBlockNetworkImage(false);//解决图片不显示
        binding.mWebvew.getSettings().setLoadsImagesAutomatically(true); //支持自动加载图片
        binding.mWebvew.getSettings().setDefaultTextEncodingName("utf-8");//设置编码格式
        binding.mWebvew.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String redirect) {
                Log.e("打开的网页shoul", redirect);

                if (redirect.contains("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(redirect));
                    startActivity(intent);
                    return true;
                }
                if (!redirect.startsWith("http:") && !redirect.startsWith("https:")) {

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(redirect));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Uri parse = Uri.parse(redirect);
                    String host = parse.getHost();
                    String scheme = parse.getScheme();
                    //判断某个Scheme是否有效
                    List<ResolveInfo> activities = getPackageManager().queryIntentActivities(intent, 0);
                    boolean isValid = !activities.isEmpty();
                    if (isValid) {
                        startActivity(intent);
                    }
                    return true;
                }


                return super.shouldOverrideUrlLoading(webView, redirect);
            }
        });
        binding.mWebvew.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onGeolocationPermissionsHidePrompt() {
                super.onGeolocationPermissionsHidePrompt();
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String s, GeolocationPermissionsCallback geolocationPermissionsCallback) {
                geolocationPermissionsCallback.invoke(s, true, true);
                super.onGeolocationPermissionsShowPrompt(s, geolocationPermissionsCallback);
            }


        });
        binding.mWebvew.addJavascriptInterface(new JavaScriptObject(this), "Android");

        binding.mWebvew.loadUrl(url);

    }


    class JavaScriptObject {
        Context mContxt;

        public JavaScriptObject(Context mContxt) {
            this.mContxt = mContxt;
        }

        @JavascriptInterface
        public void showPic(String pics) {
        }

        //js获取token
        @JavascriptInterface
        public String getParam(String key) {
            if ("token".equals(key)) {
                return SPUtils.getInstance().getString("token");
            }
            return null;
        }

        //js跳转详情
        @JavascriptInterface
        public void showProduct(String id, String activityId) {
            Bundle bundle = new Bundle();
            bundle.putString("item_id", id);
            bundle.putString("activity_id", activityId);
            startActivity(GoodsDetailActivity.class, bundle);
        }

        @JavascriptInterface
        public void getLocation(String pa, String callback, String failB) {
            Log.e("得到数据定位", pa + "");
            LocationBean bean = GPSUtil.getLocation(WebViewActivity.this);
//            LocationBean bean = new LocationBean();
//            bean.setLng("120.1278700000");bean.setLat("30.3306600000");

            if (bean == null) {
                ToastUtils.showShort("缺少定位权限，无法获取您的位置");
                return;
            }
            if (TextUtils.isEmpty(callback)) {
                ToastUtils.showShort("回调信息不正确，请联系客服");
                return;
            }
            Gson gson = new Gson();
            String s = gson.toJson(bean);
            final String s1 = "javascript:" + callback + "(" + s + ")";
            binding.mWebvew.post(new Runnable() {
                @Override
                public void run() {
                    binding.mWebvew.loadUrl(s1);
                }
            });
        }

        @JavascriptInterface
        public void setReferer(String pa, String callback, String failB) {
            Log.e("设置请求头", callback + "");
            final String s1 = "javascript:" + callback + "(" + ")";
            binding.mWebvew.post(new Runnable() {
                @Override
                public void run() {
                    binding.mWebvew.loadUrl(s1);
                }
            });
        }

        @JavascriptInterface
        public void launchNav(String pa, String callback, String failB) {
            Log.e("得到数据导航", pa + "");
            if (pa == null) {
                ToastUtils.showShort("无法获取位置信息");
                return;
            }
            try {
                Gson gson = new Gson();
                DaoHangBean bean = gson.fromJson(pa, DaoHangBean.class);
                if (MapUtil.checkMapAppsIsExist(WebViewActivity.this, MapUtil.BAIDU_PKG)) {
                    double[] fromLocation = GPSUtil.gcj02_To_Bd09(Double.valueOf(bean.getFromLat()), Double.valueOf(bean.getFromLng()));
                    double[] toLocation = GPSUtil.gcj02_To_Bd09(Double.valueOf(bean.getToLat()), Double.valueOf(bean.getToLng()));
                    Uri uri = Uri.parse("baidumap://map/direction?destination=latlng:" + toLocation[0] + "," + toLocation[1] + "&origin=latlng:" + fromLocation[0] + "," + fromLocation[1]);
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));

                } else if (MapUtil.checkMapAppsIsExist(WebViewActivity.this, MapUtil.GAODE_PKG)) {
                    Uri uri = Uri.parse("amapuri://route/plan/?dlat=" + bean.getToLat() + "&dlon=" + bean.getToLng() + "&slat=" + bean.getFromLat() + "&slon=" + bean.getFromLng() + "&dev=0&t=0");
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                } else if (MapUtil.checkMapAppsIsExist(WebViewActivity.this, MapUtil.TENGXUN_PKG)) {
                    Uri parse = Uri.parse("qqmap://map/routeplan?type=drive&fromcoord=" + bean.getFromLat() + "," + bean.getFromLng()
                            + "&to=" + "加油站"
                            + "&tocoord=" + bean.getToLat() + "," + bean.getToLng()
                            + "&policy=1&referer=biyingniao");
                    startActivity(new Intent(Intent.ACTION_VIEW, parse));
                } else {
                    ToastUtils.showShort("未检测到您的地图软件，无法为您导航");
                }
            } catch (Exception e) {
                ToastUtils.showShort("位置信息格式不正确");
            }

        }

    }


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onResume() {
        super.onResume();
        binding.mWebvew.onResume();
        binding.mWebvew.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.mWebvew.onPause();
        binding.mWebvew.getSettings().setLightTouchEnabled(false);
    }

    @Override
    public void onDestroy() {
        if (this.binding.mWebvew != null) {
            binding.mWebvew.destroy();
        }
        super.onDestroy();
    }
}
