package com.dongdian.shenquan.ui.viewholder;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.PPGMoreRightsBean;
import com.dongdian.shenquan.utils.GifUtil;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import pl.droidsonroids.gif.GifImageView;

public class PPGMoreRightsItemHolder extends BaseViewHolder<PPGMoreRightsBean.BrandsBean> {
    GifImageView imageView;
    TextView title;
    LinearLayout linearLayout;
    public PPGMoreRightsItemHolder(ViewGroup parent) {
        super(parent, R.layout.ppg_more_rights_item_item);
        linearLayout = (LinearLayout)$(R.id.ppg_more_rights_item_item_layout);
        imageView = (GifImageView) $(R.id.ppg_more_rights_item_item_image);
        title = (TextView) $(R.id.ppg_more_rights_item_item_text);
        int screenWidth = Utils.getScreenWidth(getContext());
        int shiji= screenWidth/5;
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(shiji, ViewGroup.LayoutParams.WRAP_CONTENT));
    }


    @Override
    public void setData(PPGMoreRightsBean.BrandsBean data) {
        super.setData(data);
        if(data.getLogo().endsWith(".gif")){
            GifUtil.loadImage(imageView,data.getLogo());
        }else{
            ShowImageUtils.showImageView(getContext(),data.getLogo(),imageView);
        }
        title.setText(data.getName());
    }
}
