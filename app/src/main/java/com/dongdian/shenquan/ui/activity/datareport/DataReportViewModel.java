package com.dongdian.shenquan.ui.activity.datareport;

import android.app.Application;
import android.os.Bundle;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.DataReportBean;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.ui.activity.withdrawal.WithdrawalActivity;
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

public class DataReportViewModel extends MyBaseViewModel {
    DataReportBean bean;
    public DataReportViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });


    public BindingCommand yongjinhint = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.yongjinhint.setValue(true);
        }
    });
    public BindingCommand huiyuanhint = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.huiyuanhint.setValue(true);
        }
    });


    public BindingCommand mingxi = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(DataReportDetailActivity.class);
        }
    });
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        public SingleLiveEvent<DataReportBean> getData = new SingleLiveEvent<DataReportBean>();
        public SingleLiveEvent<Boolean> yongjinhint = new SingleLiveEvent<Boolean>();
        public SingleLiveEvent<Boolean> huiyuanhint = new SingleLiveEvent<Boolean>();
        public UIChangeObservable() {
        }
    }
    public BindingCommand withdrawal = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("order_amount",bean.getOrder_amount());
            bundle.putString("plus_amount",bean.getPlus_amount());
            bundle.putString("withdraw_base_amount",bean.getWithdraw_base_amount());
            startActivity(WithdrawalActivity.class,bundle);
        }
    });
    public ObservableField<String> balance_amount = new ObservableField<>();

    public ObservableField<String> total_pre_amount = new ObservableField<>();
    public ObservableField<String> withdraw_amount = new ObservableField<>();
    public ObservableField<String> un_settle_amount = new ObservableField<>();


    public void getData(){
        openloading();


        HashMap<String,Object> query = new HashMap<>();
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).report(Utils.getHeader(getApplication()),query)
        ,this,new HttpInterFace<DataReportBean>(){
                    @Override
                    public void success(BaseBean<DataReportBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        closeloading();
                        bean=loginBeanBaseBean.getData();
                        balance_amount.set("¥"+bean.getBalance_amount());
                        total_pre_amount.set(bean.getTotal_pre_amount());
                        withdraw_amount.set(bean.getWithdraw_amount());
                        un_settle_amount.set(bean.getUn_settle_amount());
                        uc.getData.setValue(bean);
                    }

                    @Override
                    public void error(Exception e) {
                        super.error(e);
                        closeloading();
                    }
                });
    }



}
