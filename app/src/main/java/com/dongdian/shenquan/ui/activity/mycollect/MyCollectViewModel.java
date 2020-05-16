package com.dongdian.shenquan.ui.activity.mycollect;

import android.app.Application;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.MyCollectListBean;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.Utils;

import java.util.HashMap;

import androidx.annotation.NonNull;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.SPUtils;

public class MyCollectViewModel extends MyBaseViewModel {
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        public SingleLiveEvent<MyCollectListBean> getData = new SingleLiveEvent<MyCollectListBean>();
        public UIChangeObservable() {
        }
    }
    public MyCollectViewModel(@NonNull Application application) {
        super(application);
    }

    public void getList(int page){
        HashMap<String,Object> query = new HashMap<>();

        query.put("page",page);


        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).collects(Utils.getHeader(getApplication()),query)
                ,this,new HttpInterFace<MyCollectListBean>(){
                    @Override
                    public void success(BaseBean<MyCollectListBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        if(loginBeanBaseBean.getData()!=null){
                            uc.getData.setValue(loginBeanBaseBean.getData());
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
