package com.dongdian.shenquan.utils;

import android.util.LruCache;

import com.dongdian.shenquan.view.GifImageLoader;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class GifUtil {
    public static LruCache<String, GifDrawable> cache = new LruCache<String, GifDrawable>(5);

    public static void loadImage(GifImageView gifImageView, String url) {
        GifDrawable gifDrawable = cache.get(url);
        if (gifDrawable != null) {

            gifImageView.setImageDrawable(gifDrawable);
        } else {
            new GifImageLoader(gifImageView).execute(url);
        }
    }
}
