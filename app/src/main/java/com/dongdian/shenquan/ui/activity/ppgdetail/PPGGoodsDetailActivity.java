package com.dongdian.shenquan.ui.activity.ppgdetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;

import com.alipay.sdk.app.PayTask;
import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.databinding.ActivityPpggoodsDetailBinding;
import com.dongdian.shenquan.ui.activity.membercenter.MembercenterActivity;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.dongdian.shenquan.view.BuyVipPopup;

import java.util.Map;

public class PPGGoodsDetailActivity extends MyBaseActivity<ActivityPpggoodsDetailBinding, PPGGoodsDetailViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_ppggoods_detail;
    }

    @Override
    public int initVariableId() {
        return BR.ppggoodsdetailviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("id");
        String mall_id = extras.getString("mall_id");
        viewModel.mall_id = mall_id;
        viewModel.id = id;
        binding.ppggoodsDetailOldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewModel.getData();

    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.openPopup.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                BuyVipPopup buyVipPopup = new BuyVipPopup(PPGGoodsDetailActivity.this);
                buyVipPopup.setConfirmCallBack(new BuyVipPopup.ConfirmCallBack() {
                    @Override
                    public void confirm() {
                        startActivity(MembercenterActivity.class);
                        finish();
                    }
                });
                buyVipPopup.showAtLocation(binding.ppggoodsDetailHeader, Gravity.CENTER, 0, 0);
            }
        });
        viewModel.uc.pay.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                final Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(PPGGoodsDetailActivity.this);
                        Map<String, String> result = alipay.payV2(s, true);
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };
                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }
        });
    }


    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    //这里接收支付宝的回调信息
                    //需要注意的是，支付结果一定要调用自己的服务端来确定，不能通过支付宝的回调结果来判断
                    break;
                }
                default:
                    break;
            }
        };
    };

}
