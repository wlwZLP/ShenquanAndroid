package com.dongdian.shenquan.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.utils.GifUtil;
import com.dongdian.shenquan.utils.ShowImageUtils;

import pl.droidsonroids.gif.GifImageView;

public class HomePopup extends BasePopupWindows{
    private ImageCallBack imageCallBack;
    private ImageView detele;
    private GifImageView imageView;
    private String imageURL;
    public HomePopup(Context ctx,String imageURL,  ImageCallBack imageCallBack) {
        super(ctx);
        this.imageURL=imageURL;
        this.imageCallBack=imageCallBack;
        initdata();
    }
    @Override
    protected void init(){

    }

    public void initdata() {
        View clearView = LayoutInflater.from(ctx).inflate(R.layout.home_popup, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        clearView.setLayoutParams(params);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(clearView);
        imageView=(GifImageView)clearView.findViewById(R.id.activity_image);
        detele=(ImageView)clearView.findViewById(R.id.activity_dismiss);
        if(!TextUtils.isEmpty(imageURL)){
            if(imageURL.endsWith(".gif")){
                GifUtil.loadImage(imageView,imageURL);
            }else{
                ShowImageUtils.showImageView(ctx,imageURL,imageView);
            }
            detele.setImageResource(R.mipmap.ic_updata_detele);

        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageCallBack.onClick();
                dismiss();
            }
        });
        detele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
    public interface  ImageCallBack{
        void onClick();
    }

}
