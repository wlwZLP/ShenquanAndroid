package com.dongdian.shenquan.ui.activity.myfootprint;

import android.app.Application;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.MyCollectListBean;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.Utils;
import com.google.gson.Gson;

import java.util.HashMap;

import androidx.annotation.NonNull;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.SPUtils;

public class MyFootPrintViewModel extends MyBaseViewModel {
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        public SingleLiveEvent<String> getData = new SingleLiveEvent<String>();
        public UIChangeObservable() {
        }
    }
    public MyFootPrintViewModel(@NonNull Application application) {
        super(application);
    }
    public void getList(int page){
        HashMap<String,Object> query = new HashMap<>();

        query.put("page",page);


        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).browsers(Utils.getHeader(getApplication()),query)
                ,this,new HttpInterFace<MyCollectListBean>(){
                    @Override
                    public void success(BaseBean loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        if(loginBeanBaseBean.getData()!=null){
                            Gson gson = new Gson();
                            String s = gson.toJson(loginBeanBaseBean.getData());

                            uc.getData.setValue(s);
                        }
                    }

                    @Override
                    public void startDialog() {
                        openloading();
                    }

                    @Override
                    public void dismissloading() {
                        closeloading();
                    }
                });
    }
}
