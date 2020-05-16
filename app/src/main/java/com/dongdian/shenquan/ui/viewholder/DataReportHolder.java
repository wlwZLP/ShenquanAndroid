package com.dongdian.shenquan.ui.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.DataReportBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class DataReportHolder extends BaseViewHolder<DataReportBean.OrderDataBean> {
    private TextView title,value;
    public DataReportHolder(ViewGroup parent) {
        super(parent, R.layout.data_report_item);
        title = (TextView)$(R.id.data_report_item_title);
        value = (TextView)$(R.id.data_report_item_value);
    }

    @Override
    public void setData(DataReportBean.OrderDataBean data) {
        super.setData(data);
        title.setText(data.getTitle());
        value.setText(data.getValue());
    }
}
