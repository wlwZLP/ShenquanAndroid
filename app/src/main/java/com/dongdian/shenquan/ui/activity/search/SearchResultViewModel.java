package com.dongdian.shenquan.ui.activity.search;

import android.app.Application;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.ui.activity.main.MainActivity;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.Utils;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;

public class SearchResultViewModel extends MyBaseViewModel {
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(MainActivity.class);
            finish();
        }
    });
    public BindingCommand goback = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

            finish();
        }
    });
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {

        public SingleLiveEvent<GoodsList.ItemsBean> openTKL = new SingleLiveEvent<>();

        public UIChangeObservable() {
        }
    }

    public ObservableField<String> searchKey = new ObservableField<>();

    public SearchResultViewModel(@NonNull Application application) {
        super(application);
    }

    public void deep_search(String content) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("keyword", content);
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).deep_search(Utils.getHeader(getApplication()), map)
                , this, new HttpInterFace<GoodsList.ItemsBean>() {
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
