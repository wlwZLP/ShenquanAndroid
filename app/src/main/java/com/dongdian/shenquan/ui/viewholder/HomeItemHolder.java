package com.dongdian.shenquan.ui.viewholder;

import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class HomeItemHolder extends BaseViewHolder<GoodsList.ItemsBean> {
    RelativeLayout shangpin,zhuanji;
    ImageView zhuanjiiv;
    TextView shangpinTitle,shangpinShopName,shangpinQianHouJia,shangpinYuanJia,shangpinQuan,shangpinZhuan,shangpinXiao;
    ImageView shangpinImage,shangpinIcon,shangpinYuShou;

    TextView zhuanjiFirstTitle,zhuanjiFirstZhuan,zhuanjiFirstQuanHouJia,zhuanjiSecondTitle,zhuanjiSecondZhuan,zhuanjiSecondQuanHouJia,zhuanjiThirdTitle,zhuanjiThirdZhuan,zhuanjiThirdQuanHouJia;
    ImageView zhuanjiTitleImage,zhuanjiFirstIV,zhuanjiSecondIV,zhuanjiThirdIV;
    RelativeLayout firstLayout,secondLayout,thirdLayout;
    public HomeItemHolder(ViewGroup itemView) {
        super(itemView, R.layout.third_fragment_item);
        //三种类型
        shangpin = (RelativeLayout) $(R.id.first_fragment_item_shangpin_layout);
        zhuanji = (RelativeLayout) $(R.id.first_fragment_item_zhuanji_layout);
        zhuanjiiv = (ImageView) $(R.id.first_fragment_item_zhuanji_image);

        //商品类型中的组件
        shangpinTitle = (TextView) $(R.id.first_fragment_item_shangpin_title);
        shangpinShopName = (TextView) $(R.id.first_fragment_item_shop_name);
        shangpinQianHouJia = (TextView) $(R.id.first_fragment_item_quanhoujia);
        shangpinYuanJia = (TextView) $(R.id.first_fragment_item_yuanjia);
        shangpinQuan = (TextView) $(R.id.first_fragment_item_quan);
        shangpinZhuan = (TextView) $(R.id.first_fragment_item_zhuan);
        shangpinXiao = (TextView) $(R.id.first_fragment_item_xiaoliang);
        shangpinImage = (ImageView) $(R.id.first_fragment_item_image);
        shangpinIcon = (ImageView) $(R.id.first_fragment_item_icon);
        shangpinYuShou = (ImageView)$(R.id.first_fragment_item_image_yushou);

        //专辑组件
        zhuanjiFirstTitle = (TextView) $(R.id.first_fragment_item_zhuanji_first_shangpin_title);
        zhuanjiFirstZhuan = (TextView) $(R.id.first_fragment_item_zhuanji_first_shangpin_jiang);
        zhuanjiFirstQuanHouJia = (TextView) $(R.id.first_fragment_item_zhuanji_first_shangpin_quanhoujia);
        zhuanjiSecondTitle = (TextView) $(R.id.first_fragment_item_zhuanji_second_shangpin_title);
        zhuanjiSecondZhuan = (TextView) $(R.id.first_fragment_item_zhuanji_second_shangpin_jiang);
        zhuanjiSecondQuanHouJia = (TextView) $(R.id.first_fragment_item_zhuanji_second_shangpin_quanhoujia);
        zhuanjiThirdTitle = (TextView) $(R.id.first_fragment_item_zhuanji_third_shangpin_title);
        zhuanjiThirdZhuan = (TextView) $(R.id.first_fragment_item_zhuanji_third_shangpin_jiang);
        zhuanjiThirdQuanHouJia = (TextView) $(R.id.first_fragment_item_zhuanji_third_shangpin_quanhoujia);
        zhuanjiTitleImage = (ImageView) $(R.id.first_fragment_item_zhuanji_top_image);
        zhuanjiFirstIV = (ImageView) $(R.id.first_fragment_item_zhuanji_first_shangpin_image);
        zhuanjiSecondIV = (ImageView) $(R.id.first_fragment_item_zhuanji_second_shangpin_image);
        zhuanjiThirdIV = (ImageView) $(R.id.first_fragment_item_zhuanji_third_shangpin_image);
        firstLayout = (RelativeLayout) $(R.id.first_fragment_item_zhuanji_first_layout);
        secondLayout = (RelativeLayout) $(R.id.first_fragment_item_zhuanji_second_layout);
        thirdLayout = (RelativeLayout) $(R.id.first_fragment_item_zhuanji_third_layout);
    }

    @Override
    public void setData(GoodsList.ItemsBean data) {
        super.setData(data);
        int type = data.getType();
        if(type==0||type==1){
            shangpin.setVisibility(View.VISIBLE);
            zhuanji.setVisibility(View.GONE);
            zhuanjiiv.setVisibility(View.GONE);
//            if(TextUtils.isEmpty(data.getPresale_deposit())){
//                shangpinYuShou.setVisibility(View.GONE);
//            }else{
//                shangpinYuShou.setVisibility(View.VISIBLE);
//               // ImageLoader.getInstance().displayImage(data.getDouble_eleven_presell_icon(),shangpinYuShou);
//            }
            ShowImageUtils.showImageViewToCircle(getContext(),Utils.dp2px(getContext(),2),data.getCover_image(),shangpinImage);

            shangpinTitle.setText(data.getTitle());
            shangpinTitle.getPaint().setFakeBoldText(true);
            ShowImageUtils.showImageView(getContext(),data.getMall_icon(),shangpinIcon);

            if(TextUtils.isEmpty(data.getShop_name())){
                shangpinShopName.setVisibility(View.GONE);
            }else{
                shangpinShopName.setVisibility(View.VISIBLE);
                shangpinShopName.setText(data.getShop_name());
            }
            shangpinQianHouJia.setText(data.getDiscount_price());
            shangpinYuanJia.setText("￥"+data.getPrice());
            shangpinYuanJia.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );//设置中间一条横线

            if(TextUtils.isEmpty( data.getCoupon_money())||data.getCoupon_money().equals("0")){
                shangpinQuan.setVisibility(View.INVISIBLE);
            }else{
                shangpinQuan.setVisibility(View.VISIBLE);
                shangpinQuan.setText(data.getCoupon_money()+"元券");
            }
            if(data.getMonth_sales().equals("0")){
                shangpinXiao.setVisibility(View.GONE);
            }else{
                shangpinXiao.setVisibility(View.VISIBLE);
                shangpinXiao.setText("已售"+data.getMonth_sales()+"件");
            }

            if(TextUtils.isEmpty(data.getFl_commission())){
                shangpinZhuan.setVisibility(View.GONE);
            }else{
                shangpinZhuan.setVisibility(View.VISIBLE);
                shangpinZhuan.setText(data.getFl_commission());
            }

        }else if(type==2){
            shangpin.setVisibility(View.GONE);
            zhuanji.setVisibility(View.GONE);
            zhuanjiiv.setVisibility(View.VISIBLE);
            ShowImageUtils.showImageViewToCircle(getContext(),Utils.dp2px(getContext(),8),data.getCover_image(),zhuanjiiv);
        }
    }
}
