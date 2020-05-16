package com.dongdian.shenquan.ui.activity.myorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.databinding.ActivityMyOrderBinding;

public class MyOrderActivity extends MyBaseActivity<ActivityMyOrderBinding,MyOrderViewModel> {



    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_my_order;
    }

    @Override
    public int initVariableId() {
        return BR.myorderviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        Bundle extras = getIntent().getExtras();
        String mode = extras.getString("mode");
        viewModel.mode.set(mode);
    }
}
