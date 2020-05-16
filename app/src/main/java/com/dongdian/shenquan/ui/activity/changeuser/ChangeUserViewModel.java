package com.dongdian.shenquan.ui.activity.changeuser;

import android.app.Application;

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

public class ChangeUserViewModel extends MyBaseViewModel {
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public BindingCommand clean = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            newData.set("");
        }
    });
    public BindingCommand commit = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (title.get().equals("用户名")) {
                updata("name", newData.get());
            } else if (title.get().equals("微信号")) {
                updata("wx_account", newData.get());
            } else {
                updata("phone", newData.get());
            }
        }
    });
    public ObservableField<String> title = new ObservableField<>();

    public ObservableField<String> newData = new ObservableField<>();

    public ChangeUserViewModel(@NonNull Application application) {
        super(application);
    }

    public void updata(String key, String value) {

        HashMap<String, Object> fileMap = new HashMap<>();
        fileMap.put(key, value);
        fileMap.put("area_code","+86");
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
