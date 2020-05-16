package com.dongdian.shenquan.ui.activity.withdrawal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.databinding.ActivityWithdrawalBinding;

public class WithdrawalActivity extends MyBaseActivity<ActivityWithdrawalBinding,WithdrawalViewModel> {
    public String payment = "WECHAT";
    private String order_amount;
    private String plus_amount;
    private String type="1";
    private String withdraw_base_amount;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_withdrawal;
    }

    @Override
    public int initVariableId() {
        return BR.withdrawalviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        Bundle extras = getIntent().getExtras();
        order_amount = extras.getString("order_amount");
        plus_amount = extras.getString("plus_amount");
        withdraw_base_amount = extras.getString("withdraw_base_amount");
        if(!TextUtils.isEmpty(order_amount)){
            viewModel.ketixian.set("可提现佣金¥"+order_amount+",");
            viewModel.order_amount.set(order_amount);
        }else{
            viewModel.ketixian.set("可提现佣金¥0,");
            viewModel.order_amount.set("0");
        }

        if(!TextUtils.isEmpty(plus_amount)){
            viewModel.plus_amount.set(plus_amount);
        }else{
            viewModel.plus_amount.set("0");
        }

        viewModel.shouxufei.set("¥"+withdraw_base_amount);
        viewModel.type.set(type);
        viewModel.payment.set(payment);
        viewModel.yongjin.set(View.VISIBLE);
        viewModel.huiyuan.set(View.GONE);
        binding.withdrawalTitleYongjinLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.yongjin.set(View.VISIBLE);
                viewModel.huiyuan.set(View.GONE);
                type="1";
                viewModel.type.set(type);
                if(!TextUtils.isEmpty(order_amount)){
                    viewModel.ketixian.set("可提现佣金¥"+order_amount+",");
                }else{
                    viewModel.ketixian.set("可提现佣金¥0,");
                }
                viewModel.jine.set("");
            }
        });
        binding.withdrawalTitleHuiyuanfeiLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.yongjin.set(View.GONE);
                viewModel.huiyuan.set(View.VISIBLE);
                type="2";
                viewModel.type.set(type);
                if(!TextUtils.isEmpty(plus_amount)){
                    viewModel.ketixian.set("可提现会员费¥"+plus_amount+",");
                }else{
                    viewModel.ketixian.set("可提现会员费¥0,");
                }
                viewModel.jine.set("");
            }
        });
        binding.withdrawalChannelAlipy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.withdrawalChannelAlipy.setBackgroundResource(R.drawable.withdrawal_channel_s);
                binding.withdrawalChannelWechat.setBackgroundResource(R.drawable.withdrawal_channel_iss);
                viewModel.alipy_account_visi.set(View.VISIBLE);
                viewModel.real_name_visi.set(View.VISIBLE);
                payment="ALIPAY";
                viewModel.payment.set(payment);
            }
        });
        binding.withdrawalChannelWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.withdrawalChannelAlipy.setBackgroundResource(R.drawable.withdrawal_channel_iss);
                binding.withdrawalChannelWechat.setBackgroundResource(R.drawable.withdrawal_channel_s);
                viewModel.alipy_account_visi.set(View.GONE);
                viewModel.real_name_visi.set(View.GONE);
                payment="WECHAT";
                viewModel.payment.set(payment);
            }
        });


    }
}
