package com.dongdian.shenquan.ui.viewholder;

import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.PPGSearchBean;
import com.dongdian.shenquan.bean.ppg.PPGHomeBean;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.text.DecimalFormat;

public class PPGSearchGoodsHolder extends BaseViewHolder<PPGSearchBean.DataBean> {
    private ImageView icon, mall_icon;
    private TextView  price, title, old_price;

    public PPGSearchGoodsHolder(ViewGroup itemView) {
        super(itemView, R.layout.ppg_search_goods_item);
        icon = (ImageView) $(R.id.ppg_item_goods_item_image);
        mall_icon = (ImageView) $(R.id.ppg_item_goods_item_mall_icon);

        price = (TextView) $(R.id.ppg_item_goods_item_price);
        title = (TextView) $(R.id.ppg_item_goods_item_title);
        old_price = (TextView) $(R.id.ppg_item_goods_item_old_price);

    }
    @Override
    public void setData(PPGSearchBean.DataBean data) {
        super.setData(data);
        ShowImageUtils.showImageViewToCircle(getContext(), Utils.dp2px(getContext(), 8), data.getCoupon_cover(), icon);
        ShowImageUtils.showImageView(getContext(), data.getBrand_cover(), mall_icon);
        String sale_count = data.getMember_price();
        String face_price = data.getFace_price();

        price.setText("¥" + sale_count);
        old_price.setText("官网价" + face_price);
        old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        title.setText(data.getCoupon_name());
    }
}
