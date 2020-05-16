package com.dongdian.shenquan.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.ui.activity.goodsdetail.GoodsDetailActivity;
import com.dongdian.shenquan.ui.activity.search.SearchActivity;
import com.dongdian.shenquan.ui.activity.search.SearchResultActivity;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.dongdian.shenquan.utils.Utils;

public class TKLPopupWindow extends BasePopupWindows {
    private int type;
    private GoodsList.ItemsBean itemsBean;
    private String content;

    /**
     * @param ctx
     * @param type      显示类型  1表示有搜索到商品   2 没有搜索到商品
     * @param itemsBean type=1时有值
     * @param content   type =2 时有值
     */
    public TKLPopupWindow(Context ctx, int type, GoodsList.ItemsBean itemsBean, String content) {
        super(ctx);
        this.type = type;
        this.itemsBean = itemsBean;
        this.content = content;
        initView();
    }


    public void initView() {
        View clearView = LayoutInflater.from(ctx).inflate(R.layout.tkl_popup, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        clearView.setLayoutParams(params);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(clearView);
        LinearLayout shangpinLayout = clearView.findViewById(R.id.tkl_popup_shangpin_layout);
        LinearLayout contentLayout = clearView.findViewById(R.id.tkl_popup_content_layout);

        //商品控件
        TextView shangpinTitle = (TextView) clearView.findViewById(R.id.tkl_popup_shangpin_title);
        TextView shangpinShopName = (TextView) clearView.findViewById(R.id.tkl_popup_shop_name);
        TextView shangpinQianHouJia = (TextView) clearView.findViewById(R.id.tkl_popup_quanhoujia);
        TextView shangpinYuanJia = (TextView) clearView.findViewById(R.id.tkl_popup_yuanjia);
        TextView shangpinQuan = (TextView) clearView.findViewById(R.id.tkl_popup_quan);
        TextView shangpinZhuan = (TextView) clearView.findViewById(R.id.tkl_popup_zhuan);
        TextView shangpinXiao = (TextView) clearView.findViewById(R.id.tkl_popup_xiaoliang);
        ImageView shangpinImage = (ImageView) clearView.findViewById(R.id.tkl_popup_image);
        ImageView shangpinIcon = (ImageView) clearView.findViewById(R.id.tkl_popup_icon);
        TextView shangpinCancle = (TextView) clearView.findViewById(R.id.tkl_popup_cancle);
        TextView shangpinCommit = (TextView) clearView.findViewById(R.id.tkl_popup_commit);

        //搜索控件

        TextView contenttv = (TextView) clearView.findViewById(R.id.tkl_popup_content);
        TextView contentCancle = (TextView) clearView.findViewById(R.id.tkl_popup_content_cancle);
        TextView contentCommit = (TextView) clearView.findViewById(R.id.tkl_popup_content_commit);


        if (type == 1) {
            shangpinLayout.setVisibility(View.VISIBLE);
            contentLayout.setVisibility(View.GONE);
            ShowImageUtils.showImageViewToCircle(ctx, Utils.dp2px(ctx,2),itemsBean.getCover_image(),shangpinImage);

            shangpinTitle.setText(itemsBean.getTitle());
            shangpinTitle.getPaint().setFakeBoldText(true);
            ShowImageUtils.showImageView(ctx,itemsBean.getMall_icon(),shangpinIcon);

            if(TextUtils.isEmpty(itemsBean.getShop_name())){
                shangpinShopName.setVisibility(View.GONE);
            }else{
                shangpinShopName.setVisibility(View.VISIBLE);
                shangpinShopName.setText(itemsBean.getShop_name());
            }
            shangpinQianHouJia.setText(itemsBean.getDiscount_price());
            shangpinYuanJia.setText("￥"+itemsBean.getPrice());
            shangpinYuanJia.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );//设置中间一条横线

            if(TextUtils.isEmpty( itemsBean.getCoupon_money())||itemsBean.getCoupon_money().equals("0")){
                shangpinQuan.setVisibility(View.INVISIBLE);
            }else{
                shangpinQuan.setVisibility(View.VISIBLE);
                shangpinQuan.setText(itemsBean.getCoupon_money()+"元券");
            }
            if(itemsBean.getMonth_sales().equals("0")){
                shangpinXiao.setVisibility(View.GONE);
            }else{
                shangpinXiao.setVisibility(View.VISIBLE);
                shangpinXiao.setText("已售"+itemsBean.getMonth_sales()+"件");
            }

//            if(TextUtils.isEmpty(itemsBean.getFl_commission())){
//                shangpinZhuan.setVisibility(View.GONE);
//            }else{
//                shangpinZhuan.setVisibility(View.VISIBLE);
//                shangpinZhuan.setText(itemsBean.getFl_commission());
//            }

            shangpinCancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
            shangpinCommit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("item_id",itemsBean.getItem_id());
                    bundle.putString("mall_id",itemsBean.getMall_id()+"");
                    if(!TextUtils.isEmpty(itemsBean.getActivity_id())){
                        bundle.putString("activity_id",itemsBean.getActivity_id());
                    }
                    ctx.startActivity(new Intent(ctx,GoodsDetailActivity.class).putExtras(bundle));
                    dismiss();
                }
            });

        } else {
            shangpinLayout.setVisibility(View.GONE);
            contentLayout.setVisibility(View.VISIBLE);
            contenttv.setText(content);
            contentCancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
            contentCommit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("key", content);
                    bundle.putString("mall_id", "1");
                    ctx.startActivity(new Intent(ctx,SearchResultActivity.class).putExtras(bundle));
                   dismiss();
                }
            });
        }


    }

    @Override
    protected void init() {

    }
}
