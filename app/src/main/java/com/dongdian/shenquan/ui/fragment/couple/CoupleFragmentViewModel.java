package com.dongdian.shenquan.ui.fragment.couple;

import android.app.Application;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.CoupleetailDBean;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.ui.viewholder.CoupleDetailHolder;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.Utils;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.SPUtils;

public class CoupleFragmentViewModel extends MyBaseViewModel {
    public CoupleFragmentViewModel(@NonNull Application application) {
        super(application);
    }
    public ObservableField<String> categoryId = new ObservableField<>();
    public UIChangeObservable uc = new UIChangeObservable();
    public class UIChangeObservable {
        public SingleLiveEvent<CoupleetailDBean> getData = new SingleLiveEvent<CoupleetailDBean>();

        public UIChangeObservable() {
        }
    }
    public void getList(int page) {
        HashMap<String, Object> query = new HashMap<>();

        query.put("page", page);
        query.put("cid", categoryId.get());

        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).articles(Utils.getHeader(getApplication()), query)
                , this, new HttpInterFace<CoupleetailDBean>() {
                    @Override
                    public void success(BaseBean<CoupleetailDBean> loginBeanBaseBean) {
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

}
