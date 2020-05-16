package com.dongdian.shenquan.ui.activity.flash;

import android.app.Application;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.TimeList;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.ui.activity.search.SearchActivity;
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

public class FlashViewModel extends MyBaseViewModel {
    public FlashViewModel(@NonNull Application application) {
        super(application);
    }
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public BindingCommand search = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(SearchActivity.class);
        }
    });
    public UIChangeObservable uc = new UIChangeObservable();
    public class UIChangeObservable {
        public SingleLiveEvent<List<TimeList>> getData = new SingleLiveEvent<List<TimeList>>();

        public UIChangeObservable() {
        }
    }

    public void getList(){

        HashMap<String,Object> queryMap = new HashMap<>();
        queryMap.put("mode",0);
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).flash_sale_times(Utils.getHeader(getApplication()),queryMap)
        ,this,new HttpInterFace<List<TimeList>>(){
                    @Override
                    public void success(BaseBean<List<TimeList>> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        uc.getData.setValue(loginBeanBaseBean.getData());
                    }
                });
    }


}
