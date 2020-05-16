package com.dongdian.shenquan.ui.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.GoodsHomeBean;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class FlashSaleHolder extends BaseViewHolder<GoodsHomeBean.FlashsaleBean.DataBean> {
    ImageView imageView;
    TextView title,price,xiaoliang;
    private RelativeLayout relativeLayout;
    public FlashSaleHolder(ViewGroup parent) {
        super(parent, R.layout.flash_sale_item);
        relativeLayout=(RelativeLayout)$(R.id.flash_sale_layout);
        imageView=(ImageView)$(R.id.flash_sale_cover_image);
        title=(TextView)$(R.id.flash_sale_title);
        price=(TextView)$(R.id.flash_sale_quanhoujia);
        xiaoliang=(TextView)$(R.id.flash_sale_xiaoliang);
        int screenWidth = Utils.getScreenWidth(getContext());
        int shiji= screenWidth/3;
        relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(shiji, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void setData(GoodsHomeBean.FlashsaleBean.DataBean data) {
        super.setData(data);

        ShowImageUtils.showImageView(getContext(),data.getCover_image(),imageView);
        title.setText(data.getTitle());
        price.setText("抢购价¥"+data.getDiscount_price());
        xiaoliang.setText("已售"+data.getSold_num()+"件");
    }
}
