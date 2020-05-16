package com.dongdian.shenquan.ui.viewholder;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.ppg.PPGZhiChongBean;
import com.dongdian.shenquan.bean.ppg.PPGZhiChongTitleBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class PPGZhiChongChirdHolder extends BaseViewHolder<PPGZhiChongBean> {
    private TextView shichang,price,old_price;
    private LinearLayout linearLayout;
    public PPGZhiChongChirdHolder(ViewGroup parent) {
        super(parent, R.layout.ppg_zhichong_data_item);
        linearLayout = (LinearLayout)$(R.id.ppg_zhichong_data_item_layout);
        shichang = (TextView)$(R.id.ppg_zhichong_data_item_shichang);
        price = (TextView)$(R.id.ppg_zhichong_data_item_price);
        old_price = (TextView)$(R.id.ppg_zhichong_data_item_old_price);
    }

    @Override
    public void setData(PPGZhiChongBean data) {
        super.setData(data);
        if(data.isCheck()){
            linearLayout.setBackgroundResource(R.drawable.ppg_zhichong_select);
            linearLayout.setElevation(8f);
        }else{
            linearLayout.setBackgroundResource(R.drawable.ppg_zhichong_iss);
            linearLayout.setElevation(0f);
        }
        shichang.setText(data.getValidity());
        price.setText("¥"+data.getMember_price());
        old_price.setText("官网价"+data.getFace_price());
    }
}
