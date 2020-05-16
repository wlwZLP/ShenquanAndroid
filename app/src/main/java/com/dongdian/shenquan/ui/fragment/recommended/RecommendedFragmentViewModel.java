package com.dongdian.shenquan.ui.fragment.recommended;

import android.app.Application;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.GoodsHomeBean;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.Utils;

import java.util.HashMap;

import androidx.annotation.NonNull;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.SPUtils;

public class RecommendedFragmentViewModel extends MyBaseViewModel {
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        public SingleLiveEvent<GoodsList> getData = new SingleLiveEvent<GoodsList>();
        public SingleLiveEvent<GoodsHomeBean> getheader = new SingleLiveEvent<GoodsHomeBean>();
        public UIChangeObservable() {
        }
    }

    public RecommendedFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public void getList(int page) {
        HashMap<String, Object> query = new HashMap<>();

        query.put("page", page);


        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).taobao_hot(Utils.getHeader(getApplication()), query)
                , this, new HttpInterFace<GoodsList>() {
                    @Override
                    public void success(BaseBean<GoodsList> loginBeanBaseBean) {
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

    public void getHeader() {
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).goodshome(Utils.getHeader(getApplication()), new HashMap<String, Object>())
                , this, new HttpInterFace<GoodsHomeBean>() {
                    @Override
                    public void success(BaseBean<GoodsHomeBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        uc.getheader.setValue(loginBeanBaseBean.getData());
                    }
                });
    }


}
