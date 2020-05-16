package com.dongdian.shenquan.ui.activity.changeuser;

import android.app.Application;
import android.text.TextUtils;

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

public class ChangeAlipyViewModel extends MyBaseViewModel {
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public BindingCommand commit = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
                if(TextUtils.isEmpty(real_name.get())){
                    ToastUtils.showShort("姓名不能为空");
                    return;
                }
            if(TextUtils.isEmpty(alipy_account.get())){
                ToastUtils.showShort("支付宝账号不能为空");
                return;
            }
                updata();

        }
    });
    public ObservableField<String> real_name = new ObservableField<>();

    public ObservableField<String> alipy_account = new ObservableField<>();

    public ChangeAlipyViewModel(@NonNull Application application) {
        super(application);
    }
    public void updata() {

        HashMap<String, Object> fileMap = new HashMap<>();
        fileMap.put("ali_account", alipy_account.get());
        fileMap.put("real_name", real_name.get());
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).update_info(Utils.getHeader(getApplication()), fileMap)
                , this, new HttpInterFace() {
                    @Override
                    public void success(BaseBean loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        ToastUtils.showShort(loginBeanBaseBean.getMsg());
                        finish();
                    }
                });


    }
}
