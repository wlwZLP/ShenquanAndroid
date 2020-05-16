package com.dongdian.shenquan.ui.fragment.home;

import android.app.Application;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.CategoriesBean;
import com.dongdian.shenquan.bean.GoodsHomeBean;
import com.dongdian.shenquan.bean.PopWindowBean;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.ui.activity.main.MainViewModel;
import com.dongdian.shenquan.ui.activity.search.SearchActivity;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.SPUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class HomeFragmentViewModel extends MyBaseViewModel {
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        public SingleLiveEvent<List<CategoriesBean>> getData = new SingleLiveEvent<List<CategoriesBean>>();
        public SingleLiveEvent<PopWindowBean> popup = new SingleLiveEvent<PopWindowBean>();
        public SingleLiveEvent<GoodsHomeBean.BannersBean> suspend = new SingleLiveEvent<GoodsHomeBean.BannersBean>();
        public UIChangeObservable() {
        }
    }
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


    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public void getCa(){
        HashMap<String,Object> query = new HashMap<>();
        query.put("mall_id",1);
        // query.put("mode",1);

        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).categories(Utils.getHeader(getApplication()),query),this,new HttpInterFace<List<CategoriesBean>>(){
            @Override
            public void success(BaseBean<List<CategoriesBean>> loginBeanBaseBean) {
                super.success(loginBeanBaseBean);
                uc.getData.setValue(loginBeanBaseBean.getData());
            }

            @Override
            public void error(Exception e) {
                super.error(e);
                initView();
            }
        });
    }

    public void popup(){
        HashMap<String,Object> query = new HashMap<>();
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).pop_window(Utils.getHeader(getApplication()),query)
        ,this,new HttpInterFace<PopWindowBean>(){
                    @Override
                    public void success(BaseBean<PopWindowBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        if(loginBeanBaseBean.getData()!=null){
                            uc.popup.setValue(loginBeanBaseBean.getData());
                        }
                    }
                });
    }

    public void suspend(){
        HashMap<String,Object> query = new HashMap<>();
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).suspend(Utils.getHeader(getApplication()),query)
                ,this,new HttpInterFace<GoodsHomeBean.BannersBean>(){
                    @Override
                    public void success(BaseBean<GoodsHomeBean.BannersBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        if(loginBeanBaseBean.getData()!=null){
                            uc.suspend.setValue(loginBeanBaseBean.getData());
                        }
                    }
                });
    }



    public void initView(){
        List<CategoriesBean> categories = new ArrayList<>();
        CategoriesBean categories1 = new CategoriesBean();
        categories1.setId(0);
        categories1.setName("流行");
        categories.add(categories1);
        CategoriesBean categories2 = new CategoriesBean();
        categories2.setId(999);
        categories2.setName("精选");
        categories.add(categories2);
        CategoriesBean categories3 = new CategoriesBean();
        categories3.setId(1);
        categories3.setName("女装");
        categories.add(categories3);
        CategoriesBean categories4 = new CategoriesBean();
        categories4.setId(2);
        categories4.setName("男装");
        categories.add(categories4);
        CategoriesBean categories5 = new CategoriesBean();
        categories5.setId(3);
        categories5.setName("内衣");
        categories.add(categories5);
        uc.getData.setValue(categories);
    }

}
