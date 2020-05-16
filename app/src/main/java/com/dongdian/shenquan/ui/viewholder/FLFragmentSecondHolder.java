package com.dongdian.shenquan.ui.viewholder;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.CategoriesBean;
import com.dongdian.shenquan.ui.activity.flgoodslist.FLGoodsListActivity;
import com.dongdian.shenquan.utils.Utils;
import com.dongdian.shenquan.view.EvenItemDecoration;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class FLFragmentSecondHolder extends BaseViewHolder<CategoriesBean.ChildrenBeanX> {
    public RecyclerArrayAdapter thirdAdapter = new RecyclerArrayAdapter<CategoriesBean>(getContext()) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new FLFragmentThirdHolder(parent);
        }
    };
    private EasyRecyclerView easyRecyclerView;
    private TextView textView;
    public FLFragmentSecondHolder(ViewGroup parent) {
        super(parent, R.layout.f_l_fragment_second_item);
        textView = (TextView)$(R.id.f_l_fragment_second_item_title);
        easyRecyclerView = (EasyRecyclerView)$(R.id.f_l_fragment_second_item_recycler);
    }

    @Override
    public void setData(CategoriesBean.ChildrenBeanX data) {
        super.setData(data);
        thirdAdapter.clear();
        Utils.setGildLayoutNotScroll(getContext(),3,easyRecyclerView,null,thirdAdapter);
        thirdAdapter.addAll(data.getChildren());
        thirdAdapter.notifyDataSetChanged();
        textView.setText(data.getName());
        thirdAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                CategoriesBean.ChildrenBeanX.ChildrenBean item = (CategoriesBean.ChildrenBeanX.ChildrenBean)thirdAdapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("category_id",item.getId()+"");
                bundle.putString("title",item.getName());
                Intent intent = new Intent(getContext(), FLGoodsListActivity.class);
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });
    }
}
