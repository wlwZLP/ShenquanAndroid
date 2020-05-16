package com.dongdian.shenquan.ui.activity.customerservice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.bean.KeFuBean;
import com.dongdian.shenquan.databinding.ActivityCustomerServiceBinding;
import com.dongdian.shenquan.ui.activity.addfriend.AddFriendActivity;
import com.dongdian.shenquan.utils.QRCodeUtil;
import com.dongdian.shenquan.utils.Utils;

import java.util.UUID;

public class CustomerServiceActivity extends MyBaseActivity<ActivityCustomerServiceBinding,CustomerServiceViewModel> {



    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_customer_service;
    }

    @Override
    public int initVariableId() {
        return BR.customerserviceviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.getData();
//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        String customer = extras.getString("customer");
//        viewModel.wechat.set(customer);
//        if(!TextUtils.isEmpty(customer)){
//            binding.customerServiceQrcode.setImageBitmap(  QRCodeUtil.createQRCode(customer));
//        }
        binding.customerServiceQrcode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(kefuBean!=null){
                    Glide.with(CustomerServiceActivity.this)
                            .asBitmap()
                            .load(kefuBean.getQrcode())
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                    Utils.saveBmp2Gallery(CustomerServiceActivity.this,resource, UUID.randomUUID().toString() + ".jpg");
                                }
                            });
                }
                return true;
            }
        });
    }
    private KeFuBean kefuBean;
    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<KeFuBean>() {
            @Override
            public void onChanged(KeFuBean keFuBean) {
                kefuBean=keFuBean;

            }
        });
    }
}
