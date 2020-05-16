package com.dongdian.shenquan.ui.activity.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.databinding.ActivitySettingBinding;

public class SettingActivity extends MyBaseActivity<ActivitySettingBinding,SettingViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_setting;
    }

    @Override
    public int initVariableId() {
        return BR.settingviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        Bundle extras = getIntent().getExtras();
        String userIcon = extras.getString("userIcon", "");
        String userName = extras.getString("userName", "");
        String userId = extras.getString("userId", "");
        String userPhone = extras.getString("userPhone", "");
        String userWechat = extras.getString("userWechat", "");
        String userAlipy = extras.getString("userAlipy", "");

        viewModel.userAlipy.set(userAlipy);
        viewModel.userIcon.set(userIcon);
        viewModel.userId.set(userId);
        viewModel.userName.set(userName);
        viewModel.userPhone.set(userPhone);
        viewModel.userWechat.set(userWechat);

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getdata();
    }
}
