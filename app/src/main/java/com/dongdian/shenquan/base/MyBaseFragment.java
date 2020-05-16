package com.dongdian.shenquan.base;

import android.content.Context;

import android.os.Bundle;


import com.dongdian.shenquan.view.MyProgressDialog;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * Created by Administrator on 2019/3/25.
 */

public abstract  class MyBaseFragment<V extends ViewDataBinding, VM extends MyBaseViewModel> extends BaseFragment<V,VM> {
    protected Context ctx;
    private MyProgressDialog progressDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx=getContext();
    }
    @Override
    public void showDialog(String msg) {
        if (msg == null) {
            msg = "加载中";
        }

            if (progressDialog == null) {
                progressDialog = new MyProgressDialog(getActivity());
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
