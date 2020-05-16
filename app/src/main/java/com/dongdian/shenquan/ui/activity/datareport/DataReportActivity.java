package com.dongdian.shenquan.ui.activity.datareport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.bean.DataReportBean;
import com.dongdian.shenquan.databinding.ActivityDataReportBinding;
import com.dongdian.shenquan.ui.viewholder.DataReportHolder;
import com.dongdian.shenquan.utils.Utils;
import com.dongdian.shenquan.view.ClearDialog;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

public class DataReportActivity extends MyBaseActivity<ActivityDataReportBinding,DataReportViewModel> {

    public RecyclerArrayAdapter dataAdapter = new RecyclerArrayAdapter(this) {
        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new DataReportHolder(parent);
        }
    };
    public RecyclerArrayAdapter plusAdapter = new RecyclerArrayAdapter(this) {
        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new DataReportHolder(parent);
        }
    };

    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_data_report;
    }

    @Override
    public int initVariableId() {
        return BR.datareportviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        Utils.setGildLayoutNotScroll(this,2,binding.dataReportOrderDataRecycler,null,dataAdapter);

        Utils.setGildLayoutNotScroll(this,2,binding.dataReportPlusDataRecycler,null,plusAdapter);
    viewModel.getData();
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<DataReportBean>() {
            @Override
            public void onChanged(DataReportBean dataReportBean) {
                dataAdapter.addAll(dataReportBean.getOrder_data());
                dataAdapter.notifyDataSetChanged();

                plusAdapter.addAll(dataReportBean.getPlus_data());
                plusAdapter.notifyDataSetChanged();
            }
        });
        viewModel.uc.yongjinhint.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                ClearDialog clearDialog = new ClearDialog(DataReportActivity.this, "根据官方联盟规则,每月28日结算上月预估佣金。即每月28号结算上个月确认收货的订单，结算完成后\"可提现佣金\"才会同步更新金额。\n" +
                        "因结算订单量大,结算时间会较长,结算会在28号晚上完成,建议您29号进行提现。\n" +
                        "如：10月份确认收货的订单, 11月28号才会进行结算，以此类推。", "温馨提示", new ClearDialog.IsConfirm() {
                    @Override
                    public void isConfirm(boolean isConfirm) {

                    }
                });
                clearDialog.hiddenCancle();
                clearDialog.showAtLocation(binding.dataReportHeader, Gravity.CENTER,0,0);
            }
        });
        viewModel.uc.huiyuanhint.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                ClearDialog clearDialog = new ClearDialog(DataReportActivity.this, "用户购买会员权益七天后，如无申请退款，“可提现会员费”才会同步更新金额。", "温馨提示", new ClearDialog.IsConfirm() {
                    @Override
                    public void isConfirm(boolean isConfirm) {

                    }
                });
                clearDialog.hiddenCancle();
                clearDialog.showAtLocation(binding.dataReportHeader, Gravity.CENTER,0,0);
            }
        });
    }
}
