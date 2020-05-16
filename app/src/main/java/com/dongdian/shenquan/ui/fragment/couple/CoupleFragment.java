package com.dongdian.shenquan.ui.fragment.couple;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseFragment;
import com.dongdian.shenquan.bean.CoupleetailDBean;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.databinding.CoupleFragmentBinding;
import com.dongdian.shenquan.inteface.PullListener;
import com.dongdian.shenquan.ui.activity.goodsdetail.GoodsDetailActivity;
import com.dongdian.shenquan.ui.activity.web.WebViewActivity;
import com.dongdian.shenquan.ui.viewholder.CoupleDetailHolder;
import com.dongdian.shenquan.ui.viewholder.HomeItemHolder;
import com.dongdian.shenquan.utils.MyHandler;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

public class CoupleFragment extends MyBaseFragment<CoupleFragmentBinding,CoupleFragmentViewModel> {
    private String category_id;
    private int page=1;
    private MyHandler handler = new MyHandler();
    RecyclerArrayAdapter adapter = new RecyclerArrayAdapter(getContext()) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new CoupleDetailHolder(parent);
        }
    };
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.couple_fragment;
    }

    @Override
    public int initVariableId() {
        return BR.couplefragmentviewmodel;
    }
    @Override
    public void initData() {
        super.initData();
        Bundle arguments = getArguments();
        category_id = arguments.getString("id");
        binding.coupleFragmentRecycler.setEmptyView(R.layout.empty);
        binding.coupleFragmentRecycler.setAdapter(adapter);
        viewModel.categoryId.set(category_id);
        initProduct();
    }
    //列表初始化
    private void initProduct() {

        Utils.initListView(getActivity(), binding.coupleFragmentRecycler, new DividerDecoration(Color.parseColor("#dddddd"), 0), adapter, new PullListener() {
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
                        binding.coupleFragmentRecycler.setRefreshing(false);
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
                CoupleetailDBean.DataBean item = (CoupleetailDBean.DataBean) adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("title",item.getTitle());
                bundle.putString("url",item.getPage_url()+"");

                startActivity(WebViewActivity.class,bundle);

            }
        });
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<CoupleetailDBean>() {
            @Override
            public void onChanged(CoupleetailDBean goodsList) {
                if(page==1){
                    adapter.clear();
                }
                if(goodsList==null||goodsList.getData()==null||goodsList.getData().size()==0){
                    adapter.stopMore();
                }else{
                    adapter.addAll(goodsList.getData());
                    adapter.notifyDataSetChanged();
                    if(goodsList.getTotal_page()==page){
                        adapter.stopMore();
                    }else{
                        page++;
                    }

                }

            }
        });
    }
}
