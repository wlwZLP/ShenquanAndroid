package com.dongdian.shenquan.ui.activity.changeuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.databinding.ActivityChangeAlipyBinding;

public class ChangeAlipyActivity extends MyBaseActivity<ActivityChangeAlipyBinding,ChangeAlipyViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_change_alipy;
    }

    @Override
    public int initVariableId() {
        return BR.changealipyviewmodel;
    }
}
