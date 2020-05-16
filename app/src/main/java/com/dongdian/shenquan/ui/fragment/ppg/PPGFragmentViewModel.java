package com.dongdian.shenquan.ui.fragment.ppg;

import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.ppg.PPGCategoriesBean;
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

public class PPGFragmentViewModel extends MyBaseViewModel {
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        public SingleLiveEvent<PPGCategoriesBean> getData = new SingleLiveEvent<PPGCategoriesBean>();
        public UIChangeObservable() {
        }
    }
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
    public BindingCommand message = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

        }
    });
    public ObservableField<String> keyword= new ObservableField<>();
    public PPGFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public void getCa(){
        openloading();
        HashMap<String,Object> query = new HashMap<>();
        query.put("app_key",Contants.APPKEY);
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).o_categories(Utils.getHeader(getApplication()),query),this,new HttpInterFace<PPGCategoriesBean>(){
            @Override
            public void success(BaseBean<PPGCategoriesBean> loginBeanBaseBean) {
                super.success(loginBeanBaseBean);
                closeloading();
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
