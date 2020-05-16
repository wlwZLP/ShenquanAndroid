package com.dongdian.shenquan.ui.fragment.flash;

import android.app.Application;
import android.util.Log;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.FlashFragmentBean;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.Utils;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.SPUtils;

public class FlashFragmentViewModel extends MyBaseViewModel {
    public FlashFragmentViewModel(@NonNull Application application) {
        super(application);
    }
    public ObservableField<String> time = new ObservableField<>();
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        public SingleLiveEvent<FlashFragmentBean> getData = new SingleLiveEvent<FlashFragmentBean>();

        public UIChangeObservable() {
        }
    }
    public void getList(int page) {
        HashMap<String, Object> query = new HashMap<>();
        query.put("mode", 0);
        query.put("page", page);
        query.put("time", time.get());

        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).flash_sale_items(Utils.getHeader(getApplication()), query)
                , this, new HttpInterFace<FlashFragmentBean>() {
                    @Override
                    public void success(BaseBean<FlashFragmentBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        closeloading();
                        if (loginBeanBaseBean.getData() != null) {
                            uc.getData.setValue(loginBeanBaseBean.getData());
                        }
                    }

                    @Override
                    public void error(Exception e) {
                        super.error(e);
                        closeloading();
                    }
                });
    }


}
