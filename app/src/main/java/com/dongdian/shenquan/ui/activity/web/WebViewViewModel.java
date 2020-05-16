package com.dongdian.shenquan.ui.activity.web;

import android.app.Application;

import com.dongdian.shenquan.base.MyBaseViewModel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class WebViewViewModel extends MyBaseViewModel {
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public ObservableField<String> title = new ObservableField<>();
    public WebViewViewModel(@NonNull Application application) {
        super(application);
    }
}
