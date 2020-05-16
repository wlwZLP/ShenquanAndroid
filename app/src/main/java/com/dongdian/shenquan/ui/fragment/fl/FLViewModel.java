package com.dongdian.shenquan.ui.fragment.fl;

import android.app.Application;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.CategoriesBean;
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

public class FLViewModel extends MyBaseViewModel {
    public BindingCommand search = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(SearchActivity.class);
        }
    });
    public BindingCommand message = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

        }
    });
    public UIChangeObservable uc = new UIChangeObservable();
    public class UIChangeObservable {
        public SingleLiveEvent<List<CategoriesBean>> getData = new SingleLiveEvent<List<CategoriesBean>>();
        public UIChangeObservable() {
        }
    }
    public FLViewModel(@NonNull Application application) {
        super(application);
    }
    public void getCa(){
        HashMap<String,Object> query = new HashMap<>();
        query.put("mall_id",1);
         query.put("mode",2);

        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).categories(Utils.getHeader(getApplication()),query),this,new HttpInterFace<List<CategoriesBean>>(){
            @Override
            public void success(BaseBean<List<CategoriesBean>> loginBeanBaseBean) {
                super.success(loginBeanBaseBean);
                uc.getData.setValue(loginBeanBaseBean.getData());
            }

            @Override
            public void error(Exception e) {
                super.error(e);

            }
        });





    }

}
