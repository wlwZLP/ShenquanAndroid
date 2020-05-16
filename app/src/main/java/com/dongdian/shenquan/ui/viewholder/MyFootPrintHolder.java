package com.dongdian.shenquan.ui.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.MyCollectListBean;
import com.dongdian.shenquan.utils.CornerTransform;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class MyFootPrintHolder extends BaseViewHolder<MyCollectListBean.DataBean> {
    private ImageView imageView;
    private TextView title,price,oldprice;
    public MyFootPrintHolder(ViewGroup parent) {
        super(parent, R.layout.my_footprint_item);
        imageView=(ImageView)$(R.id.my_footprint_item_goods_icon);
        title=(TextView)$(R.id.my_footprint_item_goods_title);
        price=(TextView)$(R.id.my_footprint_item_goods_price);
        oldprice=(TextView)$(R.id.my_footprint_item_goods_old_price);

    }

    @Override
    public void setData(MyCollectListBean.DataBean data) {
        super.setData(data);
        CornerTransform cornerTransform = new CornerTransform(getContext(), Utils.dp2px(getContext(), 8));
        cornerTransform.setExceptCorner(false,false,true,true);
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)//图片加载出来前，显示的图片
                .fallback(R.mipmap.ic_launcher) //url为空的时候,显示的图片
                .error(R.mipmap.ic_launcher)
                .transform(cornerTransform);//

        Glide.with(getContext()).load(data.getItem_info().getCover_image())// 加载图片
                .apply(options)
                .into(imageView);

        title.setText(data.getItem_info().getTitle());
        price.setText("¥"+data.getItem_info().getPrice());

    }
}
