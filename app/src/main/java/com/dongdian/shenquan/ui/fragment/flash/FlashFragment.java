package com.dongdian.shenquan.ui.fragment.flash;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseFragment;
import com.dongdian.shenquan.bean.FlashFragmentBean;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.databinding.FlashFragmentBinding;
import com.dongdian.shenquan.inteface.PullListener;
import com.dongdian.shenquan.ui.activity.goodsdetail.GoodsDetailActivity;
import com.dongdian.shenquan.ui.viewholder.FlashFragmentHolder;
import com.dongdian.shenquan.ui.viewholder.HomeItemHolder;
import com.dongdian.shenquan.utils.MyHandler;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

public class FlashFragment extends MyBaseFragment<FlashFragmentBinding,FlashFragmentViewModel> {
    private String time;
    private int page=1;
    private MyHandler handler = new MyHandler();
    RecyclerArrayAdapter adapter = new RecyclerArrayAdapter(getContext()) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new FlashFragmentHolder(parent);
        }
    };
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.flash_fragment;
    }

    @Override
    public int initVariableId() {
        return BR.flashfragmentviewmodel;
    }


    @Override
    public void initData() {
        super.initData();
        Bundle arguments = getArguments();
        time = arguments.getString("time");
        binding.flashFramentRecycler.setEmptyView(R.layout.empty);
        binding.flashFramentRecycler.setAdapter(adapter);
        viewModel.time.set(time);
        initProduct();
    }
    //列表初始化
    private void initProduct() {

        Utils.initListView(getActivity(), binding.flashFramentRecycler, new DividerDecoration(Color.parseColor("#dddddd"), 0), adapter, new PullListener() {
            @Override
            public void onRefresh() {
                page = 1;
                viewModel.getList(page);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.flashFramentRecycler.setRefreshing(false);
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
                FlashFragmentBean.ItemsBean item = (FlashFragmentBean.ItemsBean) adapter.getItem(position);
                int status = item.getStatus();
                if(status==2){
                    return;
                }
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
        viewModel.uc.getData.observe(this, new Observer<FlashFragmentBean>() {
            @Override
            public void onChanged(FlashFragmentBean goodsList) {
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
