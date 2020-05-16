package com.dongdian.shenquan.ui.activity.setting;

import android.app.Application;
import android.os.Bundle;
import android.view.View;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.user.UserBean;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.ui.activity.changeuser.ChangeAlipyActivity;
import com.dongdian.shenquan.ui.activity.changeuser.ChangeUserActivity;
import com.dongdian.shenquan.ui.activity.main.MainActivity;
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

public class SettingViewModel extends MyBaseViewModel {



    public ObservableField<String> userName = new ObservableField<>();
    public ObservableField<String> userIcon = new ObservableField<>();
    public ObservableField<String> userId = new ObservableField<>();
    public ObservableField<String> userPhone = new ObservableField<>();
    public ObservableField<String> userAlipy = new ObservableField<>();
    public ObservableField<String> userWechat = new ObservableField<>();



    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public BindingCommand changeusericon = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

        }
    });
    public BindingCommand changeusername = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("title","用户名");
            startActivity(ChangeUserActivity.class,bundle);
        }
    });
    public BindingCommand changeuserphone = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("title","手机号");
            startActivity(ChangeUserActivity.class,bundle);
        }
    });
    public BindingCommand changeuserwechat = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("title","微信号");
            startActivity(ChangeUserActivity.class,bundle);
        }
    });
    public BindingCommand changeuseralipy = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ChangeAlipyActivity.class);
        }
    });
    public BindingCommand taobao = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public BindingCommand existlogin = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            SPUtils.getInstance().remove("token");
            SPUtils.getInstance().remove("userId");
            SPUtils.getInstance().remove("phone");
            startActivity(MainActivity.class);
            finish();
        }
    });
    public SettingViewModel(@NonNull Application application) {
        super(application);
    }
    public void getdata() {
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).info(Utils.getHeader(getApplication()), new HashMap<String, Object>())
                , this, new HttpInterFace<UserBean>() {
                    @Override
                    public void startDialog() {

                    }

                    @Override
                    public void dismissloading() {

                    }

                    @Override
                    public void noLogin() {

                    }

                    @Override
                    public void success(BaseBean<UserBean> loginBeanBaseBean) {
                        //保存个人信息
                        SPUtils.getInstance().put("phone", loginBeanBaseBean.getData().getPhone());
                        SPUtils.getInstance().put("userId", loginBeanBaseBean.getData().getId());
                        SPUtils.getInstance().put("plus_level", loginBeanBaseBean.getData().getPlus_level());

                        userAlipy.set(loginBeanBaseBean.getData().getAli_account());
                        userIcon.set(loginBeanBaseBean.getData().getAvatar());
                        userId.set(loginBeanBaseBean.getData().getId()+"");
                        userName.set(loginBeanBaseBean.getData().getName());
                        userPhone.set(loginBeanBaseBean.getData().getPhone());
                        userWechat.set(loginBeanBaseBean.getData().getWx_account());


                    }
                });


    }
}
