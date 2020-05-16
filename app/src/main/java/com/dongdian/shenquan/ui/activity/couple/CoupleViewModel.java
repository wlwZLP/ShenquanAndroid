package com.dongdian.shenquan.ui.activity.couple;

import android.app.Application;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.CoupleBean;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.Utils;

import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.SPUtils;

public class CoupleViewModel extends MyBaseViewModel {
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public CoupleViewModel(@NonNull Application application) {
        super(application);
    }
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        public SingleLiveEvent<List<CoupleBean>> getData = new SingleLiveEvent<List<CoupleBean>>();
        public UIChangeObservable() {
        }
    }

    public void getdata(){


        HashMap<String, Object> query = new HashMap<>();
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).article_categories(Utils.getHeader(getApplication()),query)
        ,this,new HttpInterFace<List<CoupleBean>>(){
                    @Override
                    public void success(BaseBean<List<CoupleBean>> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        uc.getData.setValue(loginBeanBaseBean.getData());
                    }
                });
    }
}
