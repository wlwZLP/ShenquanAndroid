package com.dongdian.shenquan.ui.activity.myorder;

import android.app.Application;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.MyOrderListBean;
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

public class MyOrderListViewModel extends MyBaseViewModel {
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public ObservableField<String> title = new ObservableField<>();
    public MyOrderListViewModel(@NonNull Application application) {
        super(application);
    }
    public ObservableField<String> type = new ObservableField<>();
    public ObservableField<String> mode = new ObservableField<>();
    public ObservableField<String> status = new ObservableField<>();
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        public SingleLiveEvent<MyOrderListBean> getData = new SingleLiveEvent<MyOrderListBean>();
        public UIChangeObservable() {
        }
    }
    public void getData(String page){
        openloading();
        HashMap<String,Object> query = new HashMap<>();
        query.put("type",type.get()+"");
        query.put("mode",mode.get()+"");
        query.put("status",status.get()+"");
        query.put("page",page);
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).orders(Utils.getHeader(getApplication()),query)
        ,this,new HttpInterFace<MyOrderListBean>(){
                    @Override
                    public void success(BaseBean<MyOrderListBean> loginBeanBaseBean) {
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
