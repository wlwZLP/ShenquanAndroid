package com.dongdian.shenquan.ui.fragment.guesslike;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.base.MyBaseFragment;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.databinding.GLFragmentBinding;
import com.dongdian.shenquan.inteface.PullListener;
import com.dongdian.shenquan.ui.activity.goodsdetail.GoodsDetailActivity;
import com.dongdian.shenquan.ui.viewholder.HomeItemHolder;
import com.dongdian.shenquan.utils.MyHandler;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;


public class GuessLikeFragment extends MyBaseFragment<GLFragmentBinding,GuessLikeFragmentViewModel> {
    private int page=1;
    private MyHandler handler = new MyHandler();
    RecyclerArrayAdapter adapter = new RecyclerArrayAdapter(getContext()) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new HomeItemHolder(parent);
        }
    };
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.g_l_fragment;
    }

    @Override
    public int initVariableId() {
        return BR.glviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        initProduct();
    }
    //列表初始化
    private void initProduct() {

        Utils.initListView(getActivity(), binding.gLRecycler, new DividerDecoration(Color.parseColor("#dddddd"), 0), adapter, new PullListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //刷新
                        page = 1;
                        viewModel.getList(page);
                    }
                }, 500);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.gLRecycler.setRefreshing(false);
                        if (page == 1) {
                            return;
                        }
                        viewModel.getList(page);
                    }
                }, 500);
            }
        }, new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                GoodsList.ItemsBean item = (GoodsList.ItemsBean) adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("item_id",item.getItem_id());
                bundle.putString("mall_id",item.getMall_id()+"");
                if(!TextUtils.isEmpty(item.getActivity_id())){
                    bundle.putString("activity_id",item.getActivity_id());
                }
                startActivity(GoodsDetailActivity.class,bundle);

            }
        });
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<GoodsList>() {
            @Override
            public void onChanged(GoodsList goodsList) {
                if(page==1){
                    adapter.clear();
                }
                if(goodsList==null||goodsList.getItems()==null||goodsList.getItems().size()==0){
                    adapter.stopMore();
                }else{
                    adapter.addAll(goodsList.getItems());
                    adapter.notifyDataSetChanged();
                    if(!goodsList.isHas_next()){
                        adapter.stopMore();
                    }else{
                        page++;
                    }

                }

            }
        });
    }
}
