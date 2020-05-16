package com.dongdian.shenquan.ui.viewholder;

import android.graphics.Paint;
import android.media.Image;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.ppg.PPGItemGoodsListBean;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.text.DecimalFormat;

public class PPGItemHolder extends BaseViewHolder<PPGItemGoodsListBean.DataBean> {
    private ImageView icon, mall_icon;
    private TextView sheng, price, title, old_price;
    private LinearLayout zhichong,kaquan;
    private ImageView zhichongicon;
    private TextView zhichongtitle,zhichongrearks;
    public PPGItemHolder(ViewGroup itemView) {
        super(itemView, R.layout.ppg_item_goods_list_item);

        zhichong=(LinearLayout)$(R.id.ppg_item_goods_item_zhichong_layout);
        kaquan=(LinearLayout)$(R.id.ppg_item_goods_item_kaquan_layout);

        zhichongicon=(ImageView)$(R.id.ppg_item_goods_item_zhichong_image);
        zhichongtitle=(TextView)$(R.id.ppg_item_goods_item_zhichong_title);
        zhichongrearks=(TextView)$(R.id.ppg_item_goods_item_zhichong_remark);

        icon = (ImageView) $(R.id.ppg_item_goods_item_image);
        mall_icon = (ImageView) $(R.id.ppg_item_goods_item_mall_icon);
        sheng = (TextView) $(R.id.ppg_item_goods_item_sheng);
        price = (TextView) $(R.id.ppg_item_goods_item_price);
        title = (TextView) $(R.id.ppg_item_goods_item_title);
        old_price = (TextView) $(R.id.ppg_item_goods_item_old_price);

    }

    @Override
    public void setData(PPGItemGoodsListBean.DataBean data) {
        super.setData(data);
        ShowImageUtils.showImageViewToCircle(getContext(), Utils.dp2px(getContext(), 8), data.getCoupon_cover(), icon);

        ShowImageUtils.showImageView(getContext(), data.getBrand_cover(), mall_icon);

        String sale_count = data.getMember_price();
        String face_price = data.getFace_price();
        if(!TextUtils.isEmpty(sale_count)&&!TextUtils.isEmpty(face_price)){
            float v = Float.valueOf(face_price) - Float.valueOf(sale_count);
            if (v > 0) {
                sheng.setText("省" + new DecimalFormat(".00").format(v) + "元");
                sheng.setVisibility(View.VISIBLE);
            } else {
                sheng.setVisibility(View.GONE);
            }
        }else{
            sheng.setVisibility(View.GONE);
        }
        price.setText("¥" + sale_count);
        old_price.setText("官网价" + face_price);
        old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        title.setText(data.getCoupon_name());
        int coupon_type = data.getCoupon_type();
        if(coupon_type==1||coupon_type==3){
            zhichong.setVisibility(View.VISIBLE);
            kaquan.setVisibility(View.GONE);
            ShowImageUtils.showImageViewToCircle(getContext(), Utils.dp2px(getContext(), 8), data.getCoupon_cover(), zhichongicon);

            zhichongtitle.setText(data.getCoupon_name());
            zhichongrearks.setText(data.getBrand_desc());


        }else if (coupon_type == 2 || coupon_type == 4){
            zhichong.setVisibility(View.GONE);
            kaquan.setVisibility(View.VISIBLE);

        }



    }
}
