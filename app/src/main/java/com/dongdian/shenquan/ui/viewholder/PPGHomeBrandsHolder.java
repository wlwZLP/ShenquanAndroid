package com.dongdian.shenquan.ui.viewholder;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.ppg.PPGCategoriesBean;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class PPGHomeBrandsHolder extends BaseViewHolder<PPGCategoriesBean.BrandsBean.ContentsBeanX> {
    private ImageView imageView;
    private TextView name,hint;
    private LinearLayout linearLayout;
    public PPGHomeBrandsHolder(ViewGroup parent) {
        super(parent, R.layout.ppg_home_brands_item);
        linearLayout=(LinearLayout)$(R.id.ppg_home_brands_item_layout) ;
        imageView=(ImageView)$(R.id.ppg_home_brands_item_image);
        name=(TextView)$(R.id.ppg_home_brands_item_name);
        hint=(TextView)$(R.id.ppg_home_brands_item_hine);
        int screenWidth = Utils.getScreenWidth(getContext());
        int shiji= screenWidth/5;
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(shiji, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void setData(PPGCategoriesBean.BrandsBean.ContentsBeanX data) {
        super.setData(data);
        if(data.getName().equals("更多权益")){
            ShowImageUtils.showImageViewToCircle(getContext(),100,R.mipmap.ic_more,imageView);
        }else{
            ShowImageUtils.showImageViewToCircle(getContext(),100,data.getLogo(),imageView);
        }

        name.setText(data.getName());
        hint.setText(data.getBrand_desc());
    }
}
