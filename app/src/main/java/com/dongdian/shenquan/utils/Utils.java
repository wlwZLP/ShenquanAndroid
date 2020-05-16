package com.dongdian.shenquan.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;
import com.alibaba.baichuan.trade.biz.core.taoke.AlibcTaokeParams;
import com.alibaba.baichuan.trade.biz.login.AlibcLogin;
import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseFragment;
import com.dongdian.shenquan.bean.GoodsHomeBean;
import com.dongdian.shenquan.bean.OauthBean;
import com.dongdian.shenquan.bean.goods.GoodsDetailBean;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.inteface.PullListener;
import com.dongdian.shenquan.ui.activity.album.AlbumActivity;
import com.dongdian.shenquan.ui.activity.goodsdetail.GoodsDetailActivity;
import com.dongdian.shenquan.ui.activity.login.LoginActivity;
import com.dongdian.shenquan.ui.activity.modulelist.ModuleListActivity;
import com.dongdian.shenquan.ui.activity.ppgdetail.PPGGoodsDetailActivity;
import com.dongdian.shenquan.ui.activity.ppgdetail.PPGGoodsDetailZhiChongActivity;
import com.dongdian.shenquan.ui.activity.ppggoodslist.PPGCategoryListActivity;
import com.dongdian.shenquan.ui.activity.ppggoodslist.PPGGoodsListActivity;
import com.dongdian.shenquan.ui.activity.web.WebViewActivity;
import com.dongdian.shenquan.view.MyGridLayoutManager;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.temporal.ValueRange;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.goldze.mvvmhabit.utils.SPUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class Utils {
    //初始化GridView
    public static void initGridView(Context ctx, EasyRecyclerView recyclerView, int spanCount, RecyclerView.ItemDecoration decoration,
                                    final RecyclerArrayAdapter adapter,
                                    PullListener listener) {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(ctx, spanCount);
        gridLayoutManager.setSpanSizeLookup(adapter.obtainGridSpanSizeLookUp(spanCount));
        recyclerView.setLayoutManager(gridLayoutManager);
        if (decoration == null) {
            decoration = new DividerDecoration(Color.parseColor("#dddddd"), 1);
        }
        if (decoration != null) {
            recyclerView.addItemDecoration(decoration);
        }

        recyclerView.setAdapterWithProgress(adapter);

        adapter.setMore(R.layout.view_more, listener);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });

        recyclerView.setRefreshListener(listener);
        listener.onRefresh();

    }

    //初始化listView
    public static void initListView(Context ctx, EasyRecyclerView recyclerView, DividerDecoration decoration,
                                    final RecyclerArrayAdapter adapter,
                                    PullListener listener, RecyclerArrayAdapter.OnItemClickListener itemClickListener) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(layoutManager);

        //DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY,Util.dip2px(this,0.5f), Util.dip2px(this,72),0);

        if (decoration == null) {
            decoration = new DividerDecoration(Color.parseColor("#dddddd"), 0);
        }

        if (decoration != null) {
            decoration.setDrawLastItem(false);
            recyclerView.addItemDecoration(decoration);
        }

        recyclerView.setAdapterWithProgress(adapter);

        adapter.setMore(R.layout.view_more, listener);//default more
        if (itemClickListener != null) {
            adapter.setOnItemClickListener(itemClickListener);
        }
        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });

        recyclerView.setRefreshListener(listener);
        listener.onRefresh();
    }

    /**
     * 不可滚动recyclerview
     * @param ctx
     * @param spanCount
     * @param recyclerView
     * @param dividerItemDecoration
     * @param adapter
     */
    public static void setGildLayoutNotScroll(Context ctx,int spanCount,EasyRecyclerView recyclerView,RecyclerView.ItemDecoration dividerItemDecoration,RecyclerArrayAdapter adapter){
        recyclerView.setAdapter(adapter);
        MyGridLayoutManager gridLayoutManager = new MyGridLayoutManager(ctx, spanCount);
        gridLayoutManager.setScrollEnabled(false);
        if(dividerItemDecoration!=null){
            recyclerView.addItemDecoration(dividerItemDecoration);
        }
      //  gridLayoutManager.setSpanSizeLookup(adapter.obtainGridSpanSizeLookUp(spanCount));
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter.setNotifyOnChange(false);//不加这句总是不显示，具体原因不太清楚（可能是不会主动调用notifyDataSetChanged，需要手动调用一下）
    }

    /**
     * md5加密
     * @param context
     * @return
     */
    public static String encrypByMd5(String context) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(context.getBytes());//update处理
            byte [] encryContext = md.digest();//调用该方法完成计算

            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < encryContext.length; offset++) {//做相应的转化（十六进制）
                i = encryContext[offset];
                if (i < 0) i += 256;
                if (i < 16) buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取版本名
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        // 获取packagemanager的实例
        String version = "1.0";
        try {
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名
            PackageInfo packInfo = null;
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            version = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }
    //获取剪切板内容
    public static String getCopy(Context ctx) {
        ClipboardManager cm = (ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        ClipData data = cm.getPrimaryClip();
        if (data != null) {
            ClipData.Item item = data.getItemAt(data.getItemCount()-1);
            CharSequence content = item.getText();
            if (!TextUtils.isEmpty(content)) {
                cm.setText("");
                return content.toString();
            }
        }
        return null;
    }
    //dp转换px
    public static int dp2px(Context context, int dp) {
        if (context == null) {
            return 0;
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }
    public static void copy(Context ctx, String content) {
        ClipboardManager cm = (ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setText(content);

        ToastUtils.showShort("复制成功");
    }
    //保存到相册
    public static void saveBmp2Gallery(Context ctx, Bitmap bmp, String picName) {

        String fileName = null;
        //系统相册目录
        String galleryPath = Environment.getExternalStorageDirectory()
                + File.separator + Environment.DIRECTORY_DCIM
                + File.separator + "Camera" + File.separator;


        // 声明文件对象
        File file = null;
        // 声明输出流
        FileOutputStream outStream = null;

        try {
            // 如果有目标文件，直接获得文件对象，否则创建一个以filename为名称的文件
            file = new File(galleryPath, picName + ".jpg");

            // 获得文件相对路径
            fileName = file.toString();
            // 获得输出流，如果文件中有内容，追加内容
            outStream = new FileOutputStream(fileName);
            if (null != outStream) {
                bmp.compress(Bitmap.CompressFormat.JPEG, 90, outStream);
            }

        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        MediaStore.Images.Media.insertImage(ctx.getContentResolver(),
//                bmp, fileName, null);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        ctx.sendBroadcast(intent);

        ToastUtils.showShort( "图片保存成功");

    }
    /**
     * 获取屏幕宽度
     * @param teamItamActivity
     * @return
     */
    public static int getScreenWidth(Context teamItamActivity) {
        Resources resources = teamItamActivity.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        return width;
    }

    /**
     * 获取屏幕高度
     * @param teamItamActivity
     * @return
     */
    public static int getScreenHeight(Context teamItamActivity) {
        Resources resources = teamItamActivity.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        return height;
    }

    /**
     * 数组分割
     * @param resList
     * @param count
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> split(List<T> resList, int count) {
        if (resList == null || count < 1) {
            return null;
        }
        List<List<T>> ret = new ArrayList<List<T>>();
        int size = resList.size();
        if (size <= count) { //数据量不足count指定的大小
            ret.add(resList);
        } else {
            int pre = size / count;
            int last = size % count;
            //前面pre个集合，每个大小都是count个元素
            for (int i = 0; i < pre; i++) {
                List<T> itemList = new ArrayList<T>();
                for (int j = 0; j < count; j++) {
                    itemList.add(resList.get(i * count + j));
                }
                ret.add(itemList);
            }
            //last的进行处理
            if (last > 0) {
                List<T> itemList = new ArrayList<T>();
                for (int i = 0; i < last; i++) {
                    itemList.add(resList.get(pre * count + i));
                }
                ret.add(itemList);
            }
        }
        return ret;
    }

    /**
     * 时间格式化
     * @param date_str
     * @return
     */
    public static long date2TimeStamp(String date_str){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return sdf.parse(date_str).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取时分秒
     * @param time
     * @param name
     * @return
     */
    public static int parseDate(long time,String name){
        Date date = new Date(time);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hours = cal.get( Calendar.HOUR_OF_DAY );
        int minute = cal.get( Calendar.MINUTE );
        int second = cal.get( Calendar.SECOND );
        if(name.equals("hours")){
            return hours;
        }else if(name.equals("minute")){
            return minute;
        }else if(name.equals("second")){
            return second;
        }else{
            return 0;
        }
    }


    /**
     * 淘京拼 列表跳转
     * @param context
     * @param targType
     * @param bannersBean
     */
    public static void bannerJump(Fragment context , int targType, GoodsHomeBean.BannersBean bannersBean){

        int need_login = bannersBean.getNeed_login();
        if(need_login==1&& TextUtils.isEmpty(SPUtils.getInstance().getString("token"))){
            context.startActivity(new Intent(context.getContext(), LoginActivity.class));
            return;
        }
        if(bannersBean.getTarget().isNeed_oauth()){
            oauth(context.getActivity(),bannersBean.getTarget().getOauth_url());
            return;
        }

        if(targType==1){
            Bundle bundle = new Bundle();
            bundle.putString("title",bannersBean.getTarget().getTitle());
            bundle.putString("url",bannersBean.getTarget().getUrl() );
            Intent intent = new Intent(context.getContext(), WebViewActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }else if(targType==2){
            Bundle bundle = new Bundle();
            bundle.putString("item_id", bannersBean.getTarget().getItem_id());
            bundle.putString("mall_id", bannersBean.getTarget().getType() + "");
            if (!TextUtils.isEmpty(bannersBean.getTarget().getActivity_id())) {
                bundle.putString("activity_id", bannersBean.getTarget().getActivity_id());
            }
            Intent intent = new Intent(context.getContext(), GoodsDetailActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }else if(targType==3||targType==4||targType==5||targType==6){
            Bundle bundle = new Bundle();
            bundle.putInt("targType",targType);
            bundle.putString("title",bannersBean.getTitle()+"");
            Intent intent = new Intent(context.getContext(), ModuleListActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }else if(targType==7){
            Bundle bundle = new Bundle();
            bundle.putInt("targType",bannersBean.getId());
            bundle.putString("title",bannersBean.getTitle()+"");
            Intent intent = new Intent(context.getContext(), AlbumActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
        else if(targType==13){
            String path = bannersBean.getTarget().getPath();
            String[] split = path.split("\\?");
            if(split[0].equals("brand-coupons")){
                String[] split1 = split[1].split("=");
                Bundle bundle = new Bundle();
                bundle.putString("id", split1[1]);
                Intent intent = new Intent(context.getContext(), PPGGoodsListActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }else if(split[0].equals("card_coupons")){
                Bundle bundle = new Bundle();
                String[] split1 = split[1].split("&");
                for (int i = 0; i < split1.length; i++) {
                    String s = split1[i];
                    String[] split2 = s.split("=");
                    if(split2[0].equals("id")){
                        bundle.putString("id",split2[1]+"");
                    }
                    if(split2[0].equals("mall_id")){
                        bundle.putString("mall_id",split2[1]+"");
                    }
                }
                Intent intent = new Intent(context.getContext(), PPGGoodsDetailActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }else if(split[0].equals("charge_coupons")){
                Bundle bundle = new Bundle();
                String[] split1 = split[1].split("&");
                for (int i = 0; i < split1.length; i++) {
                    String s = split1[i];
                    String[] split2 = s.split("=");
                    if(split2[0].equals("id")){
                        bundle.putString("id",split2[1]+"");
                    }
                    if(split2[0].equals("mall_id")){
                        bundle.putString("mall_id",split2[1]+"");
                    }
                }
                Intent intent = new Intent(context.getContext(), PPGGoodsDetailZhiChongActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }else if(split[0].equals("category-coupons")){
                String[] split1 = split[1].split("=");
                Bundle bundle = new Bundle();
                bundle.putString("id", split1[1]);
                Intent intent = new Intent(context.getContext(), PPGCategoryListActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        }


    }
    public static void oauth(Activity activity,String authurl){
            ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).tb_oauth(getHeader(activity),new HashMap<String, Object>()), activity,
                    new HttpInterFace<OauthBean>(){
                        @Override
                        public void success(BaseBean<OauthBean> loginBeanBaseBean) {
                            super.success(loginBeanBaseBean);
                            if(!TextUtils.isEmpty(loginBeanBaseBean.getData().getOauth_url())){
                                oauthTaoBao(activity, loginBeanBaseBean.getData().getOauth_url());
                            }
                        }

                        @Override
                        public void error(Exception e) {
                            super.error(e);
                        }

                    });
    }
    /**
     * 淘宝授权
     * @param activity
     * @param authurl
     */
    public static void oauthTaoBao(Activity activity,String authurl) {

        AlibcLogin alibcLogin = AlibcLogin.getInstance();
        if (alibcLogin.isLogin()) {
            AlibcShowParams showParams = new AlibcShowParams();
            AlibcTaokeParams alibcTaokeParams = new AlibcTaokeParams("", "", "");
            Map<String, String> trackParams = new HashMap<>();

            AlibcTrade.openByUrl(activity, "", authurl,
                    null, new WebViewClient(), new WebChromeClient(), showParams, alibcTaokeParams
                    , trackParams, new AlibcTradeCallback() {
                        @Override
                        public void onTradeSuccess(AlibcTradeResult alibcTradeResult) {
                            ToastUtils.showShort(alibcTradeResult.toString());
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            ToastUtils.showShort(s);
                        }
                    });
        }else{
            AlibcLogin.getInstance().showLogin(new AlibcLoginCallback() {
                @Override
                public void onSuccess(int i, String s, String s1) {
                    AlibcShowParams showParams = new AlibcShowParams();
                    AlibcTaokeParams alibcTaokeParams = new AlibcTaokeParams("", "", "");

                    AlibcTrade.openByUrl(activity, "", authurl,
                            null, new WebViewClient(), new WebChromeClient(), showParams, alibcTaokeParams
                            , null, new AlibcTradeCallback() {
                                @Override
                                public void onTradeSuccess(AlibcTradeResult alibcTradeResult) {
                                }
                                @Override
                                public void onFailure(int i, String s) {
                                }
                            });
                }
                @Override
                public void onFailure(int i, String s) {

                }
            });
        }
    }

    /**
     * 判断是否存在某个activity
     * @param context
     * @param clazz  activity 名字
     * @retu
     */
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static boolean isLaunchedActivity(Context context, Class<?> clazz) {
        try {
            Intent intent = new Intent(context, clazz);
            ComponentName cmpName = intent.resolveActivity(context.getPackageManager());
            if (cmpName != null) { // 说明系统中存在这个activity
                ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(10);
                for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                    if (taskInfo.baseActivity.equals(cmpName)) { // 说明它已经启动了
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public interface ClickListener {
        public void click(int positon);
    }

    /**
     * 设置多样字体
     * @param context
     * @param tv  textview对象
     * @param str  多样字体分类集合
     * @param color  对应颜色
     * @param clickListener
     */
    public static void setText(Context context, TextView tv,
                               ArrayList<String> str, ArrayList<Integer> color,
                               ClickListener clickListener) {
// 累加数组所有的字符串为一个字符串
        StringBuffer long_str = new StringBuffer();
        for (int i = 0; i < str.size(); i++) {
            long_str.append(str.get(i));
        }
        SpannableString builder = new SpannableString(long_str.toString());
// 设置不同字符串的点击事件
        for (int i = 0; i < str.size(); i++) {
            int p = i;
            int star = long_str.toString().indexOf(str.get(i));
            int end = star + str.get(i).length();
            builder.setSpan(new Clickable(clickListener, p), star, end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

// 设置不同字符串的颜色

        ArrayList<ForegroundColorSpan> foregroundColorSpans = new ArrayList<ForegroundColorSpan>();
        for (int i = 0; i < color.size(); i++) {
            foregroundColorSpans.add(new ForegroundColorSpan(color.get(i)));
        }
        for (int i = 0; i < str.size(); i++) {
            int star = long_str.toString().indexOf(str.get(i));
            int end = star + str.get(i).length();
            builder.setSpan(foregroundColorSpans.get(i), star, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

// 设置点击后的颜色为透明，否则会一直出现高亮
        tv.setHighlightColor(Color.TRANSPARENT);
        tv.setClickable(true);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setText(builder);
    }

    /**
     * 多样字体点击事件
     */
    static class Clickable extends ClickableSpan implements View.OnClickListener {
        private final ClickListener clickListener;
        private int position;

        public Clickable(ClickListener clickListener, int position) {
            this.clickListener = clickListener;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            clickListener.click(position);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(Color.WHITE); // 设置文件颜色
            ds.setUnderlineText(false);
        }
    }

    /**
     * 获取请求头
     * @param context
     * @return
     */
    public static HashMap<String,Object> getHeader(Context context){
        HashMap hashMap = new HashMap<String, Object>();

        long l = System.currentTimeMillis() / 1000;
        String sign = Utils.encrypByMd5(Utils.getVersionName(context) + Contants.APP_SECRET + l + Contants.APP_SECRET);
        String authorization = SPUtils.getInstance().getString("token");
        hashMap.put("t", l + "");
        hashMap.put("sign", sign + "");
        hashMap.put("authorization", authorization + "");
        return hashMap;
    }



}
