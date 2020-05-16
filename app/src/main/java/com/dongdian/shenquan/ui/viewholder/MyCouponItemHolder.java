package com.dongdian.shenquan.ui.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.coupon.CouponListBean;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class MyCouponItemHolder extends BaseViewHolder<CouponListBean.DataBean> {
    private LinearLayout left;
    private RelativeLayout right;
    private TextView price,zhibao,goumai,title,liushui,quxiao,zhifu,chakan,zhibaotitle;
    private ImageView icon,statusiv;
    private View meng;
    private CallBack callBack;
    public MyCouponItemHolder(ViewGroup parent,CallBack callBack) {
        super(parent, R.layout.my_coupon_item);
        this.callBack=callBack;
        icon = (ImageView) $(R.id.my_coupon_item_goods_icon);
        statusiv = (ImageView) $(R.id.my_coupon_item_goods_status);

        left = (LinearLayout) $(R.id.my_coupon_item_left);
        right = (RelativeLayout) $(R.id.my_coupon_item_right);

        price = (TextView) $(R.id.my_coupon_item_price);
        zhibao = (TextView) $(R.id.my_coupon_item_time);
        zhibaotitle = (TextView) $(R.id.my_coupon_item_time_title);
        goumai = (TextView) $(R.id.my_coupon_item_goods_goumai_time);
        title = (TextView) $(R.id.my_coupon_item_goods_title);
        liushui = (TextView) $(R.id.my_coupon_item_goods_liushui);
        quxiao = (TextView) $(R.id.my_coupon_item_goods_quxiao);
        zhifu = (TextView) $(R.id.my_coupon_item_goods_pay);
        chakan = (TextView) $(R.id.my_coupon_item_goods_look);
        meng=(View)$(R.id.my_coupon_item_goods_mengceng);
    }

    @Override
    public void setData(final CouponListBean.DataBean data) {
        super.setData(data);
        final int status = data.getStatus();
        if(status==0){
            quxiao.setVisibility(View.VISIBLE);
            zhifu.setVisibility(View.VISIBLE);
            chakan.setVisibility(View.GONE);
            meng.setVisibility(View.GONE);
            statusiv.setImageResource(R.mipmap.ic_daizhifu);
        }else if(status==1){
            quxiao.setVisibility(View.GONE);
            zhifu.setVisibility(View.GONE);
            chakan.setVisibility(View.GONE);
            meng.setVisibility(View.GONE);
            statusiv.setImageResource(R.mipmap.ic_yichenggong);
        }else if(status==2){
            quxiao.setVisibility(View.GONE);
            zhifu.setVisibility(View.GONE);
            chakan.setVisibility(View.VISIBLE);
            meng.setVisibility(View.GONE);
            statusiv.setImageResource(R.mipmap.ic_yichenggong);
        }else if(status==3){
            quxiao.setVisibility(View.GONE);
            zhifu.setVisibility(View.GONE);
            chakan.setVisibility(View.VISIBLE);
            meng.setVisibility(View.GONE);
            statusiv.setImageResource(R.mipmap.ic_fahuozhong);
        }
        else{
            quxiao.setVisibility(View.GONE);
            zhifu.setVisibility(View.GONE);
            chakan.setVisibility(View.GONE);
            meng.setVisibility(View.VISIBLE);
            statusiv.setImageResource(R.mipmap.ic_yishibai);
        }

        ShowImageUtils.showImageView(getContext(),data.getBrand_cover(),icon);
        title.setText(data.getGoods_name());
        price.setText("¥"+data.getOrder_price());
        if(data.getInfo()!=null&&data.getInfo().getCoupons()!=null&&data.getInfo().getCoupons().size()>0){
            zhibao.setText(data.getInfo().getCoupons().get(0).getEffective_time());
            zhibaotitle.setVisibility(View.VISIBLE);

        }else{
            chakan.setVisibility(View.GONE);
            zhibaotitle.setVisibility(View.GONE);
        }
        goumai.setText("购买时间："+data.getCreated_at());
        liushui.setText("流水号："+data.getOrder_no());

        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.quxiao(data.getOrder_no());
            }
        });
        chakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.look(data.getOrder_no());
            }
        });
        zhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.pay(data.getOrder_no());
            }
        });
    }

    public interface  CallBack{
        void quxiao(String order_no);
        void look(String order_no);
        void pay(String order_no);
    }


}
