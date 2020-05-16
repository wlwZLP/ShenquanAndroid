package com.dongdian.shenquan.base;

import android.content.Context;
import android.os.Bundle;

import com.dongdian.shenquan.view.MyProgressDialog;

import androidx.annotation.UiThread;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import me.goldze.mvvmhabit.base.BaseFragment;

public abstract class MyBaseLazyFragment<V extends ViewDataBinding, VM extends MyBaseViewModel> extends BaseFragment<V,VM> {
    protected boolean isViewInitiated;//是否加载完毕
    protected boolean isLazyLoaded;//是否已经懒加载过



    protected Context ctx;
    private MyProgressDialog progressDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx=getContext();
        isViewInitiated=true;
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()&&isViewInitiated&&!isLazyLoaded){
            onLazyLoad();
            isLazyLoaded=true;
        }
    }



    @UiThread
    public abstract void onLazyLoad();
}
