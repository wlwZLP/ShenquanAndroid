package com.dongdian.shenquan.ui.activity.addfriend;

import android.app.Application;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.InviteBean;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.Utils;

import java.util.HashMap;

import androidx.annotation.NonNull;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.SPUtils;

public class AddFriendViewModel extends MyBaseViewModel {
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public AddFriendViewModel(@NonNull Application application) {
        super(application);
    }
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        public SingleLiveEvent<InviteBean> getData = new SingleLiveEvent<InviteBean>();
        public UIChangeObservable() {
        }
    }
    public void getData(){
        openloading();

        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).invite_poster(Utils.getHeader(getApplication()),new HashMap<String, Object>())
        ,this,new HttpInterFace<InviteBean>(){
                    @Override
                    public void success(BaseBean<InviteBean> loginBeanBaseBean) {
                        closeloading();
                        super.success(loginBeanBaseBean);
                        uc.getData.setValue(loginBeanBaseBean.getData());
                    }

                    @Override
                    public void error(Exception e) {
                        closeloading();
                        super.error(e);
                    }
                });
    }

}
