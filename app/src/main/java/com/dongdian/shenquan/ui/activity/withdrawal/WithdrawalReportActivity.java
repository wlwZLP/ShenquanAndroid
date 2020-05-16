package com.dongdian.shenquan.ui.activity.withdrawal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.bean.tixian.WithDrawalReportBean;
import com.dongdian.shenquan.databinding.ActivityWithdrawalReportBinding;
import com.dongdian.shenquan.inteface.PullListener;
import com.dongdian.shenquan.ui.activity.goodsdetail.GoodsDetailActivity;
import com.dongdian.shenquan.ui.viewholder.HomeItemHolder;
import com.dongdian.shenquan.ui.viewholder.WithdrawalReportHolder;
import com.dongdian.shenquan.utils.MyHandler;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

public class WithdrawalReportActivity extends MyBaseActivity<ActivityWithdrawalReportBinding,WithdrawalReportViewModel> {

    private int page=1;
    private MyHandler handler = new MyHandler();
    RecyclerArrayAdapter adapter = new RecyclerArrayAdapter(this) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new WithdrawalReportHolder(parent);
        }
    };
    private String type;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_withdrawal_report;
    }

    @Override
    public int initVariableId() {
        return BR.withdrawalreportviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        Bundle extras = getIntent().getExtras();
        type = extras.getString("type");
        viewModel.type.set(type);
        binding.withdrawalReportRecycler.setEmptyView(R.layout.empty);
        binding.withdrawalReportRecycler.setAdapter(adapter);
        initProduct();
    }
    //列表初始化
    private void initProduct() {

        Utils.initListView(this, binding.withdrawalReportRecycler, new DividerDecoration(Color.parseColor("#f6f6f6"), 10), adapter, new PullListener() {
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
                        binding.withdrawalReportRecycler.setRefreshing(false);
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

            }
        });
    }
    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<WithDrawalReportBean>() {
            @Override
            public void onChanged(WithDrawalReportBean withDrawalReportBean) {
                if(page==1){
                    adapter.clear();
                }
                if(withDrawalReportBean==null||withDrawalReportBean.getData()==null||withDrawalReportBean.getData().size()==0){
                    adapter.stopMore();
                }else{
                    adapter.addAll(withDrawalReportBean.getData());
                    adapter.notifyDataSetChanged();
                    if(page==withDrawalReportBean.getTotal_page()){
                        adapter.stopMore();
                    }else{
                        page++;
                    }

                }

            }
        });
    }
}
