package com.dongdian.shenquan.ui.fragment.me;

import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.CheckVersionBean;
import com.dongdian.shenquan.bean.user.UserBean;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.ui.activity.addfriend.AddFriendActivity;
import com.dongdian.shenquan.ui.activity.couple.CoupleActivity;
import com.dongdian.shenquan.ui.activity.customerservice.CustomerServiceActivity;
import com.dongdian.shenquan.ui.activity.datareport.DataReportActivity;
import com.dongdian.shenquan.ui.activity.login.LoginActivity;
import com.dongdian.shenquan.ui.activity.membercenter.MembercenterActivity;
import com.dongdian.shenquan.ui.activity.mycollect.MyCollectActivity;
import com.dongdian.shenquan.ui.activity.mycoupon.MyCouponActivity;
import com.dongdian.shenquan.ui.activity.myfootprint.MyFootPrintActivity;
import com.dongdian.shenquan.ui.activity.myorder.MyOrderActivity;
import com.dongdian.shenquan.ui.activity.myteam.MyTeamActivity;
import com.dongdian.shenquan.ui.activity.setting.SettingActivity;
import com.dongdian.shenquan.ui.activity.web.WebViewActivity;
import com.dongdian.shenquan.ui.activity.withdrawal.WithdrawalActivity;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.Utils;
import com.google.gson.Gson;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.SPUtils;

public class MeViewModel extends MyBaseViewModel {
    private UserBean userBean;
    public BindingCommand setting = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
                startActivity(LoginActivity.class);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("userIcon", userIcon.get());
            bundle.putString("userName", userName.get());
            bundle.putString("userId", userBean.getId() + "");
            bundle.putString("userPhone", userBean.getPhone());
            bundle.putString("userWechat", userBean.getWx_account());
            bundle.putString("userAlipy", userBean.getAli_account());
            startActivity(SettingActivity.class, bundle);
        }
    });
    public BindingCommand copy = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
                startActivity(LoginActivity.class);
                return;
            }
            Utils.copy(getApplication(), userBean.getRecommend_code());
        }
    });
    public BindingCommand withdrawal = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
                startActivity(LoginActivity.class);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("order_amount", userBean.getOrder_amount());
            bundle.putString("plus_amount", userBean.getPlus_amount());
            bundle.putString("withdraw_base_amount", userBean.getWithdraw_base_amount());
            startActivity(WithdrawalActivity.class, bundle);
        }
    });
    public BindingCommand myorder = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
                startActivity(LoginActivity.class);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("mode", "1");
            startActivity(MyOrderActivity.class, bundle);
        }
    });
    public BindingCommand datareport = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
                startActivity(LoginActivity.class);
                return;
            }
            startActivity(DataReportActivity.class);
        }
    });
    public BindingCommand teamorder = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
                startActivity(LoginActivity.class);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("mode", "2");
            startActivity(MyOrderActivity.class, bundle);
        }
    });
    public BindingCommand mycoupoun = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
                startActivity(LoginActivity.class);
                return;
            }
            startActivity(MyCouponActivity.class);
        }
    });
    public BindingCommand myteam = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
                startActivity(LoginActivity.class);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("parent", userBean.getParent_name());
            startActivity(MyTeamActivity.class, bundle);
        }
    });

    public BindingCommand mycollect = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
                startActivity(LoginActivity.class);
                return;
            }
            startActivity(MyCollectActivity.class);
        }
    });
    public BindingCommand myfootprint = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
                startActivity(LoginActivity.class);
                return;
            }
            startActivity(MyFootPrintActivity.class);
        }
    });
    public BindingCommand couple = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
                startActivity(LoginActivity.class);
                return;
            }

            startActivity(CoupleActivity.class);
        }
    });
    public BindingCommand addfriend = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
                startActivity(LoginActivity.class);
                return;
            }
            startActivity(AddFriendActivity.class);
        }
    });

    public BindingCommand membercenter = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
                startActivity(LoginActivity.class);
                return;
            }
            startActivity(MembercenterActivity.class);
        }
    });
    public BindingCommand customerservice = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
                startActivity(LoginActivity.class);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("customer", userBean.getBusiness_kefu());
            startActivity(CustomerServiceActivity.class, bundle);
        }
    });
    public BindingCommand checkversion = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
                check_version();
        }
    });
    public BindingCommand relogin = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(LoginActivity.class);
        }
    });
    public BindingCommand buyvip = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(MembercenterActivity.class);
        }
    });
    public ObservableField<String> userName = new ObservableField<>();
    public ObservableField<String> userIcon = new ObservableField<>();
    public ObservableField<String> userInviteCode = new ObservableField<>();
    public ObservableField<String> balance_amount = new ObservableField<>();
    public ObservableField<String> today_pre_amount = new ObservableField<>();
    public ObservableField<String> this_month_pre_amount = new ObservableField<>();
    public ObservableField<String> total_pre_amount = new ObservableField<>();
    public ObservableField<String> invite_wechat = new ObservableField<>();
    public ObservableField<String> type_text = new ObservableField<>();

    public ObservableField<Integer> plus_level = new ObservableField<>(View.GONE);
    public ObservableField<Integer> plus_type = new ObservableField<>(View.GONE);

    public ObservableField<Integer> isLogin = new ObservableField<>(View.GONE);
    public ObservableField<Integer> isCopy = new ObservableField<>(View.GONE);
    public MeViewModel(@NonNull Application application) {
        super(application);
    }
    public UIChangeObservabl uc= new UIChangeObservabl();
    public class UIChangeObservabl{
        public SingleLiveEvent<String> openUpdataPopup = new SingleLiveEvent<>();
        public UIChangeObservabl() {
        }
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
                        userBean = loginBeanBaseBean.getData();
                        //保存个人信息
                        SPUtils.getInstance().put("phone", loginBeanBaseBean.getData().getPhone());
                        SPUtils.getInstance().put("userId", loginBeanBaseBean.getData().getId());
                        SPUtils.getInstance().put("plus_level", loginBeanBaseBean.getData().getPlus_level());

                        if (loginBeanBaseBean.getData().getPlus_level() == 0) {
                            plus_level.set(View.VISIBLE);
                            plus_type.set(View.GONE);
                        } else {
                            plus_level.set(View.GONE);
                            plus_type.set(View.VISIBLE);
                            type_text.set(loginBeanBaseBean.getData().getType_text());
                        }

                        userName.set(loginBeanBaseBean.getData().getName());
                        userIcon.set(loginBeanBaseBean.getData().getAvatar());
                        userInviteCode.set("邀请码：" + loginBeanBaseBean.getData().getRecommend_code());
                        balance_amount.set(loginBeanBaseBean.getData().getBalance_amount());
                        today_pre_amount.set(loginBeanBaseBean.getData().getToday_pre_amount());
                        this_month_pre_amount.set(loginBeanBaseBean.getData().getThis_month_pre_amount());
                        total_pre_amount.set(loginBeanBaseBean.getData().getTotal_pre_amount());
                        invite_wechat.set(loginBeanBaseBean.getData().getParent_name());

                    }
                });


    }

    public void check_version(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("system",1);
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).check_version(Utils.getHeader(getApplication()),map)
        ,this,new HttpInterFace<CheckVersionBean>(){
                    @Override
                    public void success(BaseBean<CheckVersionBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        Gson gson = new Gson();
                        String s = gson.toJson(loginBeanBaseBean.getData());
                        if(loginBeanBaseBean.getData().getUpdate_type()==1||loginBeanBaseBean.getData().getUpdate_type()==2){
                            uc.openUpdataPopup.setValue(s);
                        }

                    }
                });


    }





}
