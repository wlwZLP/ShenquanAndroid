package com.dongdian.shenquan.ui.activity.ppgmorerights;

import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.PPGMoreRightsBean;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.ui.activity.search.PPGSearchActivity;
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
import me.goldze.mvvmhabit.utils.ToastUtils;

public class PPGMoreRightsViewModel extends MyBaseViewModel {
    public ObservableField<String> keyword= new ObservableField<>();
    public BindingCommand search = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(TextUtils.isEmpty(keyword.get())){
                ToastUtils.showShort("搜索词不能为空");
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("keyword",keyword.get());
            startActivity(PPGSearchActivity.class,bundle);
        }
    });
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
          finish();
        }
    });
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        public SingleLiveEvent<PPGMoreRightsBean> getData = new SingleLiveEvent<PPGMoreRightsBean>();

        public UIChangeObservable() {
        }
    }

    public PPGMoreRightsViewModel(@NonNull Application application) {
        super(application);
    }


    public void getdata(){
        openloading();
        HashMap<String, Object> query = new HashMap<>();
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).life_main(Utils.getHeader(getApplication()),query)
        ,this,new HttpInterFace<PPGMoreRightsBean>(){
                    @Override
                    public void success(BaseBean<PPGMoreRightsBean> loginBeanBaseBean) {
                        closeloading();
                        super.success(loginBeanBaseBean);
                        uc.getData.setValue(loginBeanBaseBean.getData());
                    }

                    @Override
                    public void error(Exception e) {
                        super.error(e);
                        closeloading();
                    }
                });
    }
}
