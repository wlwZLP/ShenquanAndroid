package com.dongdian.shenquan.ui.viewholder;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.PPGMoreRightsBean;
import com.dongdian.shenquan.ui.activity.ppgdetail.PPGGoodsDetailActivity;
import com.dongdian.shenquan.ui.activity.ppgdetail.PPGGoodsDetailZhiChongActivity;
import com.dongdian.shenquan.ui.activity.ppggoodslist.PPGGoodsListActivity;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class PPGMoreRightsHolder extends BaseViewHolder<PPGMoreRightsBean.CouponBean> {
    private RecyclerArrayAdapter adapter = new RecyclerArrayAdapter(getContext()) {
        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new PPGMoreRightsItemHolder(parent);
        }
    };
    private TextView title;
    private EasyRecyclerView recyclerView;
    public PPGMoreRightsHolder(ViewGroup parent) {
        super(parent, R.layout.ppg_more_rights_item);
        title=(TextView)$(R.id.ppg_more_rights_item_top_title);
        recyclerView=(EasyRecyclerView)$(R.id.ppg_more_rights_item_recycler);
    }

    @Override
    public void setData(PPGMoreRightsBean.CouponBean data) {
        super.setData(data);
        title.setText(data.getName());
        Utils.setGildLayoutNotScroll(getContext(),5,recyclerView,null,adapter);
        adapter.addAll(data.getBrands());
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                PPGMoreRightsBean.BrandsBean brandsBean = data.getBrands().get(position);
                int brand_type = brandsBean.getBrand_type();
                if(brand_type==1){
                    Bundle bundle = new Bundle();
                    bundle.putString("id", brandsBean.getId() + "");
                    bundle.putString("mall_id",  "");
                    Intent intent = new Intent(getContext(),PPGGoodsDetailZhiChongActivity.class);
                    intent.putExtras(bundle);
                    getContext().startActivity(intent);
                }else{
                    Bundle bundle = new Bundle();
                    bundle.putString("id", brandsBean.getId() + "");
                    bundle.putString("mall_id",  "");
                    Intent intent = new Intent(getContext(), PPGGoodsListActivity.class);
                    intent.putExtras(bundle);
                    getContext().startActivity(intent);
                }

            }
        });
    }
}
