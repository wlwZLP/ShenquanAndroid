package com.dongdian.shenquan.ui.activity.customerservice;

import android.app.Application;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.KeFuBean;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.Utils;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.SPUtils;

public class CustomerServiceViewModel extends MyBaseViewModel {
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public BindingCommand copy = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Utils.copy(getApplication(),wechat.get());
        }
    });
    public ObservableField<String> wechat= new ObservableField<>();
    public ObservableField<String> nickname= new ObservableField<>();
    public ObservableField<String> avatar= new ObservableField<>();
    public ObservableField<String> qrcode= new ObservableField<>();
    public CustomerServiceViewModel(@NonNull Application application) {
        super(application);
    }
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        public SingleLiveEvent<KeFuBean> getData = new SingleLiveEvent<KeFuBean>();
        public UIChangeObservable() {
        }
    }
    public void getData(){

        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).app_kefu(Utils.getHeader(getApplication()),new HashMap<String, Object>())
        ,this,new HttpInterFace<KeFuBean>(){
                    @Override
                    public void success(BaseBean<KeFuBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);

                        wechat.set(loginBeanBaseBean.getData().getWxid());
                        avatar.set(loginBeanBaseBean.getData().getAvatar());
                        nickname.set(loginBeanBaseBean.getData().getNickname());
                        qrcode.set(loginBeanBaseBean.getData().getQrcode());
                        uc.getData.setValue(loginBeanBaseBean.getData());
                    }
                });
    }


}
