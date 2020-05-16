package com.dongdian.shenquan.ui.viewholder;

import android.app.Application;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.MyCollectListBean;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import androidx.annotation.NonNull;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class MyCollectHolder extends BaseViewHolder<MyCollectListBean.DataBean> {
    private ImageView imageView,mall_icon;
    private TextView price,title;
    public MyCollectHolder(ViewGroup parent) {
        super(parent, R.layout.my_collect_item);
        imageView=(ImageView)$(R.id.my_collect_item_good_icon);
        mall_icon=(ImageView)$(R.id.my_collect_item_mall_icon);
        title=(TextView)$(R.id.my_collect_item_good_title);
        price=(TextView)$(R.id.my_collect_item_good_price);
    }

    @Override
    public void setData(MyCollectListBean.DataBean data) {
        super.setData(data);
        ShowImageUtils.showImageView(getContext(),data.getItem_info().getCover_image(),imageView);
        ShowImageUtils.showImageView(getContext(),data.getMall_icon(),mall_icon);
        title.setText(data.getItem_info().getTitle());
        price.setText("¥"+data.getItem_info().getPrice());
    }
}
