package com.dongdian.shenquan.ui.activity.search;

import android.app.Application;
import android.text.TextUtils;
import android.view.View;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.HotWordsBean;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.ui.activity.couple.CoupleActivity;
import com.dongdian.shenquan.ui.activity.login.LoginActivity;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.Utils;
import com.dongdian.shenquan.view.SearchPreference;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.SPUtils;

public class SearchViewModel extends MyBaseViewModel {
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public BindingCommand clean = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            SearchPreference.getInstance(getApplication()).clean();
            searchLiShiVisiable.set(View.GONE);
        }
    });
    public BindingCommand couple = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
                startActivity(LoginActivity.class);
                return;
            }

            startActivity(CoupleActivity.class);
        }
    });
    public UIChangeObservable uc = new UIChangeObservable();
    public class UIChangeObservable {
        public SingleLiveEvent<HotWordsBean> getData = new SingleLiveEvent<HotWordsBean>();
        public SingleLiveEvent<GoodsList.ItemsBean> openTKL = new SingleLiveEvent<>();
        public UIChangeObservable() {
        }
    }
    public ObservableField<String> searchKey = new ObservableField<>();
    public ObservableField<Integer> searchLiShiVisiable = new ObservableField<>(View.VISIBLE);
    public SearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void getData(){
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).hot_words(Utils.getHeader(getApplication()),new HashMap<String, Object>())
        ,this,new HttpInterFace<HotWordsBean>(){
                    @Override
                    public void success(BaseBean<HotWordsBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        uc.getData.setValue(loginBeanBaseBean.getData());
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
