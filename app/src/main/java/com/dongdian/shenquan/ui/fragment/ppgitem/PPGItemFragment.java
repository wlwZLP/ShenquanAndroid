package com.dongdian.shenquan.ui.fragment.ppgitem;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseFragment;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.bean.ppg.PPGItemGoodsListBean;
import com.dongdian.shenquan.databinding.PpgItemFragmentBinding;
import com.dongdian.shenquan.inteface.PullListener;
import com.dongdian.shenquan.ui.activity.goodsdetail.GoodsDetailActivity;
import com.dongdian.shenquan.ui.activity.ppgdetail.PPGGoodsDetailActivity;
import com.dongdian.shenquan.ui.activity.ppgdetail.PPGGoodsDetailZhiChongActivity;
import com.dongdian.shenquan.ui.viewholder.PPGItemHolder;
import com.dongdian.shenquan.utils.MyHandler;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class PPGItemFragment extends MyBaseFragment<PpgItemFragmentBinding, PPGItemFragmentViewModel> {
    private String category_id;
    private int page = 1;
    private MyHandler handler = new MyHandler();
    RecyclerArrayAdapter adapter = new RecyclerArrayAdapter(getContext()) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new PPGItemHolder(parent);
        }
    };

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.ppg_item_fragment;
    }

    @Override
    public int initVariableId() {
        return BR.ppgitemviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        Bundle arguments = getArguments();
        category_id = arguments.getString("id");
        binding.ppgItemRecycler.setEmptyView(R.layout.empty);
        binding.ppgItemRecycler.setAdapter(adapter);
        viewModel.categoryId.set(category_id);
        initProduct();
    }

    //列表初始化
    private void initProduct() {

        Utils.initListView(getActivity(), binding.ppgItemRecycler, new DividerDecoration(Color.parseColor("#f6f6f6"), 0), adapter, new PullListener() {
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
                        binding.ppgItemRecycler.setRefreshing(false);
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
                PPGItemGoodsListBean.DataBean item = (PPGItemGoodsListBean.DataBean) adapter.getItem(position);

                if (item.getCoupon_type() == 1 || item.getCoupon_type() == 3) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", item.getId() + "");
                    bundle.putString("mall_id", item.getMall_id() + "");
                    startActivity(PPGGoodsDetailZhiChongActivity.class, bundle);
                } else if (item.getCoupon_type() == 2 || item.getCoupon_type() == 4) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", item.getId() + "");
                    bundle.putString("mall_id", item.getMall_id() + "");
                    startActivity(PPGGoodsDetailActivity.class, bundle);
                } else {
                    ToastUtils.showShort("非法跳转类型");
                }


            }
        });
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<PPGItemGoodsListBean>() {
            @Override
            public void onChanged(PPGItemGoodsListBean goodsList) {
                if (page == 1) {
                    adapter.clear();
                }
                if (goodsList == null || goodsList.getData() == null || goodsList.getData().size() == 0) {
                    adapter.stopMore();
                } else {
                    adapter.addAll(goodsList.getData());
                    adapter.notifyDataSetChanged();
                    if (!goodsList.isHas_more()) {
                        adapter.stopMore();
                    } else {
                        page++;
                    }

                }


            }
        });
    }
}
