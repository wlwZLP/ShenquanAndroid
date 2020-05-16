package com.dongdian.shenquan.ui.fragment.mycoupon;

import android.app.Application;
import android.text.TextUtils;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.coupon.CouponCancelBean;
import com.dongdian.shenquan.bean.coupon.CouponListBean;
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

public class MyCouponFragmentViewModel extends MyBaseViewModel {
    public ObservableField<String> status = new ObservableField<>();

    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        public SingleLiveEvent<CouponListBean> getData = new SingleLiveEvent<CouponListBean>();
        public SingleLiveEvent<Boolean> isSuccess = new SingleLiveEvent<Boolean>();
        public UIChangeObservable() {
        }
    }
    public MyCouponFragmentViewModel(@NonNull Application application) {
        super(application);
    }
    public void getList(int page){
        HashMap<String,Object> query = new HashMap<>();
        query.put("app_key",Contants.APPKEY);
        query.put("page",page);
        if(!TextUtils.isEmpty(status.get())&status.get()!="-1"){
            query.put("status", status.get());
        }
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).o_orders(Utils.getHeader(getApplication()),query)
                ,this,new HttpInterFace<CouponListBean>(){
                    @Override
                    public void success(BaseBean<CouponListBean> loginBeanBaseBean) {
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
    public void cancel(String order_no){
        openloading();
        HashMap<String,Object> query = new HashMap<>();
        query.put("order_no",order_no);
        query.put("app_key",Contants.APPKEY);
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).o_orders_cancel(Utils.getHeader(getApplication()),query)
        ,this,new HttpInterFace<CouponCancelBean>(){
                    @Override
                    public void success(BaseBean<CouponCancelBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        closeloading();
                        uc.isSuccess.setValue(loginBeanBaseBean.getData().isOperate());
                    }

                    @Override
                    public void error(Exception e) {
                        super.error(e);
                        closeloading();
                    }
                });



    }

}
