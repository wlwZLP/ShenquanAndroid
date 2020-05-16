package com.dongdian.shenquan.ui.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.GoodsHomeBean;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class ZonesHolder extends BaseViewHolder<GoodsHomeBean.BannersBean> {
    ImageView imageView;

    public ZonesHolder(ViewGroup parent) {
        super(parent, R.layout.zone_item);
        imageView = (ImageView) $(R.id.zone_item_image);

    }

    @Override
    public void setData(GoodsHomeBean.BannersBean data) {
        super.setData(data);
        ShowImageUtils.showImageView(getContext(),data.getCover(),imageView);
    }
}

