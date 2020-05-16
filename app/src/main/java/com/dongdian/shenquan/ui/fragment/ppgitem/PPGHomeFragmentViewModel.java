package com.dongdian.shenquan.ui.fragment.ppgitem;

import android.app.Application;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.ppg.PPGCategoriesBean;
import com.dongdian.shenquan.bean.ppg.PPGHomeBean;
import com.dongdian.shenquan.bean.ppg.PPGItemGoodsListBean;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.Utils;

import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.SPUtils;

public class PPGHomeFragmentViewModel extends MyBaseViewModel {
    public PPGHomeFragmentViewModel(@NonNull Application application) {
        super(application);
    }
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        public SingleLiveEvent<List<PPGHomeBean>> getData = new SingleLiveEvent<List<PPGHomeBean>>();
        public SingleLiveEvent<PPGCategoriesBean> getBrands = new SingleLiveEvent<PPGCategoriesBean>();
        public UIChangeObservable() {
        }
    }

    public void getList(int page) {
        HashMap<String, Object> query = new HashMap<>();
        query.put("page",page);
        query.put("is_coupon","1");
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).recommends(Utils.getHeader(getApplication()), query)
                , this, new HttpInterFace<List<PPGHomeBean>>() {
                    @Override
                    public void success(BaseBean<List<PPGHomeBean>> loginBeanBaseBean) {
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
    public void getCa(){
        openloading();
        HashMap<String,Object> query = new HashMap<>();
        query.put("app_key",Contants.APPKEY);
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).o_categories(Utils.getHeader(getApplication()),query),this,new HttpInterFace<PPGCategoriesBean>(){
            @Override
            public void success(BaseBean<PPGCategoriesBean> loginBeanBaseBean) {
                super.success(loginBeanBaseBean);
                closeloading();
                uc.getBrands.setValue(loginBeanBaseBean.getData());
            }

            @Override
            public void error(Exception e) {
                super.error(e);
                closeloading();
            }
        });

    }


}
