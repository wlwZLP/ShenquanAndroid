package com.dongdian.shenquan.base;

import android.content.Context;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;

import com.dongdian.shenquan.restart.AppStatus;
import com.dongdian.shenquan.restart.AppStatusManager;
import com.dongdian.shenquan.ui.activity.mipush.MipushActivity;
import com.dongdian.shenquan.utils.StatusBarUtil;
import com.dongdian.shenquan.view.MyProgressDialog;



import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * Created by Administrator on 2019/3/25.
 */

public abstract class MyBaseActivity<V extends ViewDataBinding, VM extends MyBaseViewModel> extends BaseActivity<V,VM> {
    protected Context ctx;
    private MyProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
//        //设置状态栏颜色
        if (AppStatusManager.getInstance().getAppStatus() == AppStatus.STATUS_RECYCLE){
            //被回收，跳转到启动页面
            Intent intent = new Intent(this, MipushActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

    }

    public void changebarColor(int color){
        StatusBarUtil.setStatusBarColor(this, color);
    }
    public void statusBarLightMode(){
        StatusBarUtil.statusBarLightMode(this);
    }
    //需要先调用透明，在调用改变字体颜色，才能保证及透明又改变颜色
    public void setbarfull(){
        StatusBarUtil.setStatusBarFullTransparent(this);
    }

    @Override
    public void showDialog(String msg) {

        if (msg == null) {
            msg = "加载中";
        }
            if(this==null){
                return;
            }
            if (progressDialog == null) {
                progressDialog = new MyProgressDialog(this);
            }
            progressDialog.show();
            progressDialog.setMessage(msg);

    }

    public void dismissDialog() {
        if ( progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.ucBase.openLoading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                showDialog(null);
            }
        });

        viewModel.ucBase.closeLoading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                dismissDialog();
            }
        });


    }
}
