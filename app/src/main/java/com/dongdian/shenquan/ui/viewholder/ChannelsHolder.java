package com.dongdian.shenquan.ui.viewholder;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.GoodsHomeBean;
import com.dongdian.shenquan.utils.GifUtil;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import pl.droidsonroids.gif.GifImageView;

public class ChannelsHolder extends BaseViewHolder<GoodsHomeBean.BannersBean> {
    GifImageView imageView;
    TextView title;
    LinearLayout linearLayout;
    public ChannelsHolder(ViewGroup parent) {
        super(parent, R.layout.j_x_channels_item);
        linearLayout = (LinearLayout)$(R.id.j_x_channels_item_layout);
        imageView = (GifImageView) $(R.id.j_x_channels_item_image);
        title = (TextView) $(R.id.j_x_channels_item_text);
        int screenWidth = Utils.getScreenWidth(getContext());
        int shiji= screenWidth/5;
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(shiji, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void setData(GoodsHomeBean.BannersBean data) {
        super.setData(data);
        if(data.getCover().endsWith(".gif")){
            GifUtil.loadImage(imageView,data.getCover());
        }else{
            ShowImageUtils.showImageView(getContext(),data.getCover(),imageView);
        }
        title.setText(data.getTitle());
    }
}
