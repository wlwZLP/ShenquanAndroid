package com.dongdian.shenquan.ui.activity.search;

import android.app.Application;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.PPGSearchBean;
import com.dongdian.shenquan.bean.ppg.PPGHomeBean;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.Utils;

import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.SPUtils;

public class PPGSearchViewModel extends MyBaseViewModel {
    public PPGSearchViewModel(@NonNull Application application) {
        super(application);
    }
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        public SingleLiveEvent<PPGSearchBean> getData = new SingleLiveEvent<PPGSearchBean>();

        public UIChangeObservable() {
        }
    }
    public BindingCommand clean = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            keyword.set("");
        }
    });
    public ObservableField<String> keyword = new ObservableField<>();
    public void getList(int page){
        HashMap<String, Object> query = new HashMap<>();
        query.put("page",page);
        query.put("keyword",keyword.get());
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).mpv_search(Utils.getHeader(getApplication()), query)
                , this, new HttpInterFace<PPGSearchBean>() {
                    @Override
                    public void success(BaseBean<PPGSearchBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        if (loginBeanBaseBean.getData() != null) {
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
