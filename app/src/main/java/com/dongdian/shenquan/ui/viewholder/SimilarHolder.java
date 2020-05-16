package com.dongdian.shenquan.ui.viewholder;

import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.utils.CornerTransform;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2019/12/16.
 */

public class SimilarHolder extends BaseViewHolder<GoodsList.ItemsBean> {
    private ImageView goodsImage,mall_icon;
    private TextView titleTv, quan, zhuan, discountTv,price,sales;

    public SimilarHolder(ViewGroup itemView) {
        super(itemView, R.layout.simargoods_layout);
        goodsImage = (ImageView) $(R.id.simargoods_item_image);
        mall_icon = (ImageView) $(R.id.simargoods_item_mall_icon);
        titleTv = (TextView) $(R.id.simargoods_item_title);
        price = (TextView) $(R.id.simargoods_item_price);
        quan = (TextView) $(R.id.quan);
        sales = (TextView) $(R.id.simargoods_item_sale);
        zhuan = (TextView) $(R.id.zhuan);
        discountTv = (TextView) $(R.id.simargoods_item_coupon_after_price_tv);
    }

    @Override
    public void setData(GoodsList.ItemsBean data) {
        super.setData(data);
        CornerTransform cornerTransform = new CornerTransform(getContext(), Utils.dp2px(getContext(), 8));
        cornerTransform.setExceptCorner(false,false,true,true);
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)//图片加载出来前，显示的图片
                .fallback(R.mipmap.ic_launcher) //url为空的时候,显示的图片
                .error(R.mipmap.ic_launcher)
                .transform(cornerTransform);//

        Glide.with(getContext()).load(data.getCover_image())// 加载图片
                .apply(options)
                .into(goodsImage);

        ShowImageUtils.showImageView(getContext(),data.getMall_icon(),mall_icon);
        titleTv.setText(data.getTitle());
        if(TextUtils.isEmpty(data.getFl_commission())){
            zhuan.setVisibility(View.GONE);
        }else{
            zhuan.setVisibility(View.VISIBLE);
            zhuan.setText(data.getFl_commission());
        }
        if(TextUtils.isEmpty(data.getCoupon_money())){
            quan.setVisibility(View.GONE);
        }else{
            quan.setVisibility(View.VISIBLE);
            quan.setText(data.getCoupon_money()+"元券");
        }
        discountTv.setText(data.getDiscount_price());
        price.setText("¥"+data.getPrice());
        price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
        sales.setText("已售"+data.getMonth_sales()+"件");






    }
}
