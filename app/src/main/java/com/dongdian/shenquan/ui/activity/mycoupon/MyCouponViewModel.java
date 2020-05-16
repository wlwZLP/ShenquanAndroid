package com.dongdian.shenquan.ui.activity.mycoupon;

import android.app.Application;

import com.dongdian.shenquan.base.MyBaseViewModel;

import androidx.annotation.NonNull;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class MyCouponViewModel extends MyBaseViewModel {
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public MyCouponViewModel(@NonNull Application application) {
        super(application);
    }
}
