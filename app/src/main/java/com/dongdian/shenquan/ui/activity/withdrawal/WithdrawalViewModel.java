package com.dongdian.shenquan.ui.activity.withdrawal;

import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
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
import me.goldze.mvvmhabit.utils.SPUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class WithdrawalViewModel extends MyBaseViewModel {
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });

    public BindingCommand withdrawalreport = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("type",type.get());
            startActivity(WithdrawalReportActivity.class,bundle);
        }
    });
    public BindingCommand all = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            String s = type.get();
            if(s.equals("1")){
                jine.set(order_amount.get());
            }else{
                jine.set(plus_amount.get());
            }
        }
    });
    public BindingCommand commit = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            withdrawal();
        }
    });

    public ObservableField<String> type = new ObservableField<>();
    public ObservableField<String> jine = new ObservableField<>();
    public ObservableField<String> order_amount = new ObservableField<>();
    public ObservableField<String> plus_amount = new ObservableField<>();

    public ObservableField<String> shouxufei = new ObservableField<>();
    public ObservableField<Integer> huiyuan = new ObservableField<>(View.GONE);

    public ObservableField<Integer> yongjin = new ObservableField<>(View.GONE);

    public ObservableField<String> ketixian = new ObservableField<>();

    public ObservableField<String> alipy_account = new ObservableField<>();
    public ObservableField<String> real_name = new ObservableField<>();

    public ObservableField<String> payment = new ObservableField<>();
    public ObservableField<Integer> alipy_account_visi = new ObservableField<>(View.GONE);

    public ObservableField<Integer> real_name_visi = new ObservableField<>(View.GONE);


    public WithdrawalViewModel(@NonNull Application application) {
        super(application);
    }

    public void withdrawal(){
        if(payment.get().equals("ALIPAY")){
            if(TextUtils.isEmpty(alipy_account.get())||TextUtils.isEmpty(real_name.get())){
                ToastUtils.showShort("请输入完整的支付宝信息");
                return;
            }
        }
        if(TextUtils.isEmpty(jine.get())){
            ToastUtils.showShort("请输入您要提现的金额");
            return;
        }
        HashMap<String,Object> query = new HashMap<>();
        query.put("type",type.get());
        query.put("amount",jine.get());
        query.put("payment",payment.get());
        if (payment.get().equals("ALIPAY")){
            query.put("real_name",real_name.get());
            query.put("ali_account",alipy_account.get());
        }
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).tixian(Utils.getHeader(getApplication()),query)
        ,this,new HttpInterFace(){
                    @Override
                    public void success(BaseBean loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        ToastUtils.showShort(loginBeanBaseBean.getMsg()+"");
                        Bundle bundle = new Bundle();
                        bundle.putString("type",type.get());
                        startActivity(WithdrawalReportActivity.class,bundle);
                        finish();
                    }
                });

    }



}
