package com.dongdian.shenquan.ui.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.DataReportDetailBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class DataReportDetailHolder extends BaseViewHolder<DataReportDetailBean.DataBean> {
    private TextView title,time,price;
    public DataReportDetailHolder(ViewGroup parent) {
        super(parent, R.layout.data_report_detail_item);
        title=(TextView)$(R.id.data_report_detail_item_title);
        time=(TextView)$(R.id.data_report_detail_item_time);
        price=(TextView)$(R.id.data_report_detail_item_price);
    }

    @Override
    public void setData(DataReportDetailBean.DataBean data) {
        super.setData(data);
        title.setText(data.getRemark());
        time.setText(data.getCreated_at());
        price.setText(data.getChg_amount());
        Double aDouble = Double.valueOf(data.getChg_amount());
        if(aDouble<0){
            price.setTextColor(0xff222222);
        }else{
            price.setTextColor(0xffFB5434);
        }
    }
}
