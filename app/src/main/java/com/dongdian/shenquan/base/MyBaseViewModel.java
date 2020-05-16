package com.dongdian.shenquan.base;

import android.app.Application;


import androidx.annotation.NonNull;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;

/**
 * Created by Administrator on 2019/3/25.
 */

public class MyBaseViewModel extends BaseViewModel {
    public UIBaseChangeObservable ucBase= new UIBaseChangeObservable();
    public class UIBaseChangeObservable{
        public SingleLiveEvent<Boolean> openLoading = new SingleLiveEvent<Boolean>();
        public SingleLiveEvent<Boolean> closeLoading = new SingleLiveEvent<Boolean>();
        public UIBaseChangeObservable() {
        }
    }



    public MyBaseViewModel(@NonNull Application application) {
        super(application);
    }




    public void openloading(){
        ucBase.openLoading.setValue(true);
    }
    public void closeloading(){
        ucBase.closeLoading.setValue(true);
    }
}
