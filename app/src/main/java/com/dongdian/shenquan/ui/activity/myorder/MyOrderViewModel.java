package com.dongdian.shenquan.ui.activity.myorder;

import android.app.Application;
import android.os.Bundle;

import com.dongdian.shenquan.base.MyBaseViewModel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class MyOrderViewModel extends MyBaseViewModel {
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public BindingCommand tb = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("mode",mode.get());
            bundle.putString("type","1");
           startActivity(MyOrderListActivity.class,bundle);
        }
    });
    public BindingCommand jd = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("mode",mode.get());
            bundle.putString("type","3");
            startActivity(MyOrderListActivity.class,bundle);
        }
    });
    public BindingCommand pdd = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("mode",mode.get());
            bundle.putString("type","2");
            startActivity(MyOrderListActivity.class,bundle);
        }
    });
    public BindingCommand dazhong = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("mode",mode.get());
            bundle.putString("type","5");
            startActivity(MyOrderListActivity.class,bundle);
        }
    });
    public BindingCommand mtwaimai = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("暂不支持该类型订单");
        }
    });
    public BindingCommand mt = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("mode",mode.get());
            bundle.putString("type","6");
            startActivity(MyOrderListActivity.class,bundle);
        }
    });
    public BindingCommand jiudian = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("mode",mode.get());
            bundle.putString("type","4");
            startActivity(MyOrderListActivity.class,bundle);
        }
    });

    public ObservableField<String> mode = new ObservableField<>();



    public MyOrderViewModel(@NonNull Application application) {
        super(application);
    }
}
