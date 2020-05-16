package com.dongdian.shenquan.ui.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.CoupleetailDBean;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class CoupleDetailHolder extends BaseViewHolder<CoupleetailDBean.DataBean> {
    ImageView imageView;
    public CoupleDetailHolder(ViewGroup parent) {
        super(parent, R.layout.couple_detail_item);
        imageView=(ImageView)$(R.id.couple_detail_item_image);
    }

    @Override
    public void setData(CoupleetailDBean.DataBean data) {
        super.setData(data);
        ShowImageUtils.showImageViewToCircle(getContext(), Utils.dp2px(getContext(),4)
        ,data.getCover(),imageView);
    }
}
