package com.dongdian.shenquan.ui.activity.mycoupon;

import android.app.Application;
import android.view.View;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.coupon.CouponDetailBean;
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
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.SPUtils;

public class CouponDetailViewModel extends MyBaseViewModel {
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public BindingCommand copy = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
           Utils.copy(getApplication(),kami.get());
        }
    });
    public ObservableField<String> url = new ObservableField<>();
    public ObservableField<String> goodName = new ObservableField<>();
    public ObservableField<String> goodEndTime = new ObservableField<>();
    public ObservableField<String> guize = new ObservableField<>();

    public ObservableField<Integer> kaquanfirst = new ObservableField<>(View.GONE);
    public ObservableField<Integer> kaquansecond = new ObservableField<>(View.GONE);
    public ObservableField<Integer> kaquanthird = new ObservableField<>(View.GONE);


    public ObservableField<String> kahao = new ObservableField<>();
    public ObservableField<String> kami = new ObservableField<>();
    public UIChangeObservable uc = new UIChangeObservable();
    public class UIChangeObservable {
        public SingleLiveEvent<String> qrcode = new SingleLiveEvent<String>();
        public UIChangeObservable() {
        }
    }

   ;


    public CouponDetailViewModel(@NonNull Application application) {
        super(application);
    }

    public void getDetail(final String order_no){

        HashMap<String,Object> query = new HashMap<>();
        query.put("order_no",order_no);
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).o_orders_detail(Utils.getHeader(getApplication()),query)
        ,this,new HttpInterFace<CouponDetailBean>(){
                    @Override
                    public void success(BaseBean<CouponDetailBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        CouponDetailBean.OrderDetailBean order_detail = loginBeanBaseBean.getData().getOrder_detail();

                        String goods_type = order_detail.getInfo().getCoupons().get(0).getGoods_type();
                        if(goods_type.equals("NUMBER_PASSWORD")){
                            kaquanfirst.set(View.GONE);
                            kaquansecond.set(View.VISIBLE);
                            kaquanthird.set(View.GONE);
                        }else if(goods_type.equals("LINK")||goods_type.equals("PICTURE")){
                            kaquanfirst.set(View.VISIBLE);
                            kaquansecond.set(View.GONE);
                            kaquanthird.set(View.GONE);
                            uc.qrcode.setValue(order_detail.getInfo().getCoupons().get(0).getGoods_link());
                        }else{
                            kaquanfirst.set(View.GONE);
                            kaquansecond.set(View.GONE);
                            kaquanthird.set(View.VISIBLE);
                        }
                        kahao.set(order_detail.getInfo().getCoupons().get(0).getGoods_number());
                        kami.set(order_detail.getInfo().getCoupons().get(0).getGoods_password());
                        url.set(order_detail.getGoods_cover());

                        goodName.set(order_detail.getGoods_name());
                        goodEndTime.set(order_detail.getInfo().getCoupons().get(0).getEffective_time());
                        guize.set(order_detail.getHelp());

                    }
                });
    }




}
