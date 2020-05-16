package com.dongdian.shenquan.ui.activity.main;

import android.app.Application;

import android.text.TextUtils;
import android.widget.RadioGroup;


import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.CheckVersionBean;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.bean.user.UserBean;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.Utils;
import com.google.gson.Gson;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.SPUtils;

/**
 * Created by Administrator on 2019/3/20.
 */

public class MainViewModel extends MyBaseViewModel {
    public UIChangeObservabl uc= new UIChangeObservabl();
    public class UIChangeObservabl{
        public SingleLiveEvent<String> openUpdataPopup = new SingleLiveEvent<>();
        public SingleLiveEvent<GoodsList.ItemsBean> openTKL = new SingleLiveEvent<>();
        public UIChangeObservabl() {
        }
    }
    public MainViewModel(@NonNull Application application) {
        super(application);


    }



    public void bind() {


        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).info(Utils.getHeader(getApplication()), new HashMap<String, Object>())
                , this, new HttpInterFace<UserBean>() {
                    @Override
                    public void startDialog() {

                    }

                    @Override
                    public void dismissloading() {

                    }

                    @Override
                    public void noLogin() {

                    }

                    @Override
                    public void success(BaseBean<UserBean> loginBeanBaseBean) {
                        //保存个人信息
                        SPUtils.getInstance().put("phone", loginBeanBaseBean.getData().getPhone());
                        SPUtils.getInstance().put("userId", loginBeanBaseBean.getData().getId());
                        SPUtils.getInstance().put("plus_level", loginBeanBaseBean.getData().getPlus_level());
                    }
                });


    }

    public void check_version(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("system",1);
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).check_version(Utils.getHeader(getApplication()),map)
                ,this,new HttpInterFace<CheckVersionBean>(){
                    @Override
                    public void success(BaseBean<CheckVersionBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        Gson gson = new Gson();
                        String s = gson.toJson(loginBeanBaseBean.getData());
                        if(loginBeanBaseBean.getData()==null){
                            return;
                        }
                        if(loginBeanBaseBean.getData().getUpdate_type()==1||loginBeanBaseBean.getData().getUpdate_type()==2){
                            uc.openUpdataPopup.setValue(s);
                        }

                    }
                });


    }

    public void deep_search(String content){
        HashMap<String,Object> map = new HashMap<>();
        map.put("keyword",content);
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).deep_search(Utils.getHeader(getApplication()),map)
        ,this,new HttpInterFace<GoodsList.ItemsBean>(){
                    @Override
                    public void success(BaseBean<GoodsList.ItemsBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        uc.openTKL.setValue(loginBeanBaseBean.getData());
                    }

                    @Override
                    public void error(Exception e) {
                        super.error(e);
                        uc.openTKL.setValue(null);
                    }
                });
    }



}
