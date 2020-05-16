package com.dongdian.shenquan.ui.viewholder;

import android.graphics.Paint;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.FlashFragmentBean;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.dongdian.shenquan.view.MyProgress;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class FlashFragmentHolder extends BaseViewHolder<FlashFragmentBean.ItemsBean> {
    private ImageView icon , mall_icon;
    private MyProgress progress;
    private TextView title,shop_name,price,old_price,status_text;
    public FlashFragmentHolder(ViewGroup parent) {
        super(parent, R.layout.flash_fragment_item);
        icon=(ImageView)$(R.id.flash_fragment_item_image);
        mall_icon=(ImageView)$(R.id.flash_fragment_item_mall_icon);
        progress=(MyProgress)$(R.id.flash_fragment_item_progress);
        title=(TextView)$(R.id.flash_fragment_item_title);
        shop_name=(TextView)$(R.id.flash_fragment_item_shop_name);
        price=(TextView)$(R.id.flash_fragment_item_price);
        old_price=(TextView)$(R.id.flash_fragment_item_old_price);
        status_text=(TextView)$(R.id.flash_fragment_item_status_text);
    }

    @Override
    public void setData(FlashFragmentBean.ItemsBean data) {
        super.setData(data);
        int status = data.getStatus();
        if(status==0){
            status_text.setText("即将开始");
            status_text.setTextColor(0xff999999);
            status_text.setBackgroundResource(R.drawable.bg_flash_status_second);
        }else{
            status_text.setText("已开抢");
            status_text.setTextColor(0xffFB5434);
            status_text.setBackgroundResource(R.drawable.bg_flash_status_first);
        }

        int total_amount = data.getTotal_amount();
        int sold_num = data.getSold_num();
        progress.setMax(total_amount);
        progress.setProgress(sold_num);
        progress.setText("已抢购"+sold_num);
        title.setText(data.getTitle());
        price.setText("¥"+data.getDiscount_price());
        old_price.setText("¥"+data.getPrice());
        old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        ShowImageUtils.showImageView(getContext(),data.getMall_icon(),mall_icon);
        ShowImageUtils.showImageView(getContext(),data.getCover_image(),icon);

    }
}
