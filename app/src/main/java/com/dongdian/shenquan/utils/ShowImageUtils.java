package com.dongdian.shenquan.utils;

import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.dongdian.shenquan.R;

public class ShowImageUtils {
    /**
     * (1)
     * 显示图片Imageview
     *
     * @param context  上下文
     * @param url      图片链接
     * @param imgeview 组件
     */
    public static void showImageView(Context context, String url,
                                     ImageView imgeview) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_default)//图片加载出来前，显示的图片
                .fallback(R.mipmap.ic_default) //url为空的时候,显示的图片
                .error(R.mipmap.ic_default);//


        Glide.with(context).load(url)// 加载图片
                .apply(options)
                .into(imgeview);
        // Glide.with(context).load(url).thumbnail(0.1f).error(errorimg)
        // .into(imgeview);

        // Glide
        // .with(context)
        // .load(UsageExampleListViewAdapter.eatFoodyImages[0])
        // .placeholder(R.mipmap.ic_launcher) //设置占位图
        // .error(R.mipmap.future_studio_launcher) //设置错误图片
        // .crossFade() //设置淡入淡出效果，默认300ms，可以传参
        // //.dontAnimate() //不显示动画效果
        // .into(imageViewFade);

    }


    /**
     * （8）
     * 显示图片 圆角显示  ImageView
     *
     * @param context  上下文
     * @param errorimg 错误的资源图片
     * @param url      图片链接
     * @param imgeview 组件
     */
    public static void showImageViewToCircle(Context context,int radius,
                                             String url, ImageView imgeview) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_default)//图片加载出来前，显示的图片
                .fallback(R.mipmap.ic_default)

                .bitmapTransform(new RoundedCorners(radius))
                .error(R.mipmap.ic_default);//
        Glide.with(context).load(url)
                .apply(options)
                .into(imgeview);

    }
    /**
     * （8）
     * 显示图片 圆角显示  ImageView
     *
     * @param context  上下文
     * @param errorimg 错误的资源图片
     * @param url      资源图片
     * @param imgeview 组件
     */
    public static void showImageViewToCircle(Context context,int radius,
                                             int url, ImageView imgeview) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_default)//图片加载出来前，显示的图片
                .fallback(R.mipmap.ic_default)

                .bitmapTransform(new RoundedCorners(radius))
                .error(R.mipmap.ic_default);//
        Glide.with(context).load(url)
                .apply(options)
                .into(imgeview);

    }
}
