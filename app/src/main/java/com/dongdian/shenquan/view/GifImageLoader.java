package com.dongdian.shenquan.view;

import android.os.AsyncTask;
import android.os.Build;

import com.dongdian.shenquan.utils.GifUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.annotation.RequiresApi;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class GifImageLoader extends AsyncTask<String, Void, GifDrawable> {
    private GifImageView gifImageView;
    private String url;

    public GifImageLoader(GifImageView gifImageView) {
        this.gifImageView = gifImageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        GifImageLoader loader = (GifImageLoader) gifImageView.getTag();
        if (loader != null) {
            loader.cancel(false);
        }
        gifImageView.setTag(this);
    }

    @Override
    protected GifDrawable doInBackground(String... params) {
        url = params[0];
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            int code = connection.getResponseCode();
            if (code == 200) {
                InputStream inputStream = connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] bytes = new byte[10240];
                int len = 0;
                while ((len = inputStream.read(bytes)) != -1) {
                    baos.write(bytes, 0, len);
                }
                byte[] data = baos.toByteArray();
                GifDrawable gifDrawable = new GifDrawable(data);
                return gifDrawable;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(GifDrawable gifDrawable) {
        super.onPostExecute(gifDrawable);
        if (gifDrawable != null) {
            try {
                gifImageView.setImageDrawable(gifDrawable);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

        }
        gifImageView.setTag(null);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    protected void onCancelled(GifDrawable gifFrom) {
        if (gifFrom != null) {
            GifUtil.cache.put(url, gifFrom);
        }
    }

}
