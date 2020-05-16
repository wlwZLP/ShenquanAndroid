package com.dongdian.shenquan.ui.activity.ppggoodslist;

import android.app.Application;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.ppg.PPGItemGoodsListBean;
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

public class PPGCategoryListViewModel extends MyBaseViewModel {
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public ObservableField<String> id = new ObservableField<>();
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        public SingleLiveEvent<PPGItemGoodsListBean> getData = new SingleLiveEvent<PPGItemGoodsListBean>();
        public UIChangeObservable() {
        }
    }
    public PPGCategoryListViewModel(@NonNull Application application) {
        super(application);
    }

    public void getList(int page){
        HashMap<String,Object> query = new HashMap<>();
        query.put("page",page);
        query.put("cid", id.get());
        query.put("app_key",Contants.APPKEY);
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).o_goods(Utils.getHeader(getApplication()),query)
                ,this,new HttpInterFace<PPGItemGoodsListBean>(){
                    @Override
                    public void success(BaseBean<PPGItemGoodsListBean> loginBeanBaseBean) {
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
