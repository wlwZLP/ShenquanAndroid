package com.dongdian.shenquan.ui.viewholder;

import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.tixian.WithDrawalReportBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class WithdrawalReportHolder extends BaseViewHolder<WithDrawalReportBean.DataBean> {
    private ImageView imageView;
    private TextView statustv, respon, time, jine;

    public WithdrawalReportHolder(ViewGroup parent) {
        super(parent, R.layout.withdrawal_report_item);
        imageView = (ImageView) $(R.id.withdrawal_item_status_icon);
        statustv = (TextView) $(R.id.withdrawal_item_status_text);
        respon = (TextView) $(R.id.withdrawal_item_status_respon);
        time = (TextView) $(R.id.withdrawal_item_time);
        jine = (TextView) $(R.id.withdrawal_item_jine);
    }

    @Override
    public void setData(WithDrawalReportBean.DataBean data) {
        super.setData(data);
        int status = data.getStatus();
        if (status == 0) {
            imageView.setImageResource(R.mipmap.ic_withdrawal_zero);
            respon.setVisibility(View.INVISIBLE);
            jine.setTextColor(0xff333333);
        } else if (status == 1) {
            imageView.setImageResource(R.mipmap.ic_withdrawal_first);
            respon.setVisibility(View.INVISIBLE);
            jine.setTextColor(0xff333333);
        } else if (status == 2) {
            imageView.setImageResource(R.mipmap.ic_withdrawal_second);
            jine.setTextColor(0xffFB5434);
            respon.setVisibility(View.VISIBLE);
            respon.setText(data.getRemark());
        }
        time.setText(data.getDeal_at());
        jine.setText(data.getAmount());

        statustv.setText(data.getStatus_text());




    }
}
