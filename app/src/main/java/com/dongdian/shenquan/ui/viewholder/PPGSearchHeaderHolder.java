package com.dongdian.shenquan.ui.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.PPGSearchBean;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class PPGSearchHeaderHolder extends BaseViewHolder<PPGSearchBean.BrandsBean> {
    private ImageView icon;
    private TextView title,hint;
    public PPGSearchHeaderHolder(ViewGroup parent) {
        super(parent, R.layout.ppg_search_header_item);
        icon=(ImageView)$(R.id.ppg_search_header_item_icon);
        title=(TextView)$(R.id.ppg_search_header_item_title);
        hint=(TextView)$(R.id.ppg_search_header_item_hint);
    }

    @Override
    public void setData(PPGSearchBean.BrandsBean data) {
        super.setData(data);

        ShowImageUtils.showImageViewToCircle(getContext(), Utils.dp2px(getContext(),100)
        ,data.getLogo(),icon);
        title.setText(data.getName());
        hint.setText(data.getBrand_desc());


    }
}
