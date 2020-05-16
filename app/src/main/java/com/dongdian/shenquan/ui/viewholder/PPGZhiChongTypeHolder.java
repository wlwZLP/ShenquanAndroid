package com.dongdian.shenquan.ui.viewholder;

import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.ppg.PPGZhiChongTitleBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class PPGZhiChongTypeHolder extends BaseViewHolder<PPGZhiChongTitleBean> {
    private TextView textView;
    public PPGZhiChongTypeHolder(ViewGroup parent) {
        super(parent, R.layout.ppg_zhichong_title_item);
        textView = (TextView)$(R.id.ppg_zhichong_title_item_text);
    }

    @Override
    public void setData(PPGZhiChongTitleBean data) {
        super.setData(data);
        if(data.isCheck()){
            textView.setBackgroundResource(R.drawable.ppg_zhichong_select);
            textView.setElevation(8f);
        }else{
            textView.setBackgroundResource(R.drawable.ppg_zhichong_iss);
            textView.setElevation(0f);
        }
        textView.setText(data.getName());
    }
}
