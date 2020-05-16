package com.dongdian.shenquan.ui.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.MyOrderListBean;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class MyOrderHolder extends BaseViewHolder<MyOrderListBean.DataBean> {
    private ImageView imageView;
    private TextView title,copy,no,status,xiadan,shouhuo,jiang,price;
    public MyOrderHolder(ViewGroup parent) {
        super(parent, R.layout.my_order_list_item);
        imageView=(ImageView)$(R.id.myorder_list_item_icon);
        title=(TextView)$(R.id.myorder_list_item_goods_data_title);
        copy=(TextView)$(R.id.myorder_list_item_copy);
        no=(TextView)$(R.id.myorder_list_item_no);
        status=(TextView)$(R.id.myorder_list_item_status);
        xiadan=(TextView)$(R.id.myorder_list_item_goods_xiadan_time);
        shouhuo=(TextView)$(R.id.myorder_list_item_goods_shouhuo_time);
        jiang=(TextView)$(R.id.myorder_list_item_jiang);
        price=(TextView)$(R.id.myorder_list_item_goods_data_price);
    }

    @Override
    public void setData(MyOrderListBean.DataBean data) {
        super.setData(data);
        ShowImageUtils.showImageView(getContext(),data.getItem_image(),imageView);

        title.setText(data.getItem_title());
        no.setText("订单编号："+data.getOrder_no());
        status.setText(data.getStatus_txt());
        if(TextUtils.isEmpty(data.getCreate_time())){
            xiadan.setVisibility(View.INVISIBLE);
        }else{
            xiadan.setVisibility(View.VISIBLE);
            xiadan.setText("下单时间："+data.getCreate_time());
        }
        if(TextUtils.isEmpty(data.getEarning_time())){
            shouhuo.setVisibility(View.INVISIBLE);
        }else{
            shouhuo.setVisibility(View.VISIBLE);
            shouhuo.setText("收货时间："+data.getEarning_time());
        }
        jiang.setText("奖¥"+data.getPre_amount()+"元");
        price.setText("¥"+data.getPay_amount());
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.copy(getContext(),data.getOrder_no());
            }
        });

    }
}
