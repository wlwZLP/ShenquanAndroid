package com.dongdian.shenquan.ui.activity.changeuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.databinding.ActivityChangeUserBinding;

public class ChangeUserActivity extends MyBaseActivity<ActivityChangeUserBinding,ChangeUserViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_change_user;
    }

    @Override
    public int initVariableId() {
        return BR.changeuserviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        Bundle extras = getIntent().getExtras();
        String title = extras.getString("title");
        viewModel.title.set(title);
    }
}
