package com.dongdian.shenquan.ui.activity.mycoupon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import me.goldze.mvvmhabit.utils.ToastUtils;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.databinding.ActivityCouponDetailBinding;
import com.dongdian.shenquan.utils.QRCodeUtil;

public class CouponDetailActivity extends MyBaseActivity<ActivityCouponDetailBinding,CouponDetailViewModel> {



    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_coupon_detail;
    }

    @Override
    public int initVariableId() {
        return BR.coupondetailviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        Bundle extras = getIntent().getExtras();
        String order_no = extras.getString("order_no");
        if(TextUtils.isEmpty(order_no)){
            ToastUtils.showShort("订单信息为空");
            finish();
        }else{
            viewModel.getDetail(order_no);
        }
//        Bitmap hhhhhhh = QRCodeUtil.createQRCode("http://baidu.com",197);
  //      binding.couponDetailCouponUrl.setImageBitmap(hhhhhhh);


    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.qrcode.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.couponDetailCouponUrl.setImageBitmap(QRCodeUtil.createQRCode(s));
            }
        });
    }
}
