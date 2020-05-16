package com.dongdian.shenquan.ui.activity.myteam;

import android.app.Application;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.TeamListBean;
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

public class MyTeamViewModel extends MyBaseViewModel {
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public ObservableField<String> renshu = new ObservableField<>();
    public ObservableField<String> parentName = new ObservableField<>();
    public ObservableField<String> hint = new ObservableField<>("特指直接通过您分享的链接或二维码注册成功的人");
    public UIChangeObservable uc = new UIChangeObservable();
    public  ObservableField<Integer> leval = new ObservableField<>();
    public class UIChangeObservable {
        public SingleLiveEvent<TeamListBean> getData = new SingleLiveEvent<TeamListBean>();

        public UIChangeObservable() {
        }
    }
    public MyTeamViewModel(@NonNull Application application) {
        super(application);
    }

    public void getList(String page, final String level, String sort){
        HashMap<String,Object> query = new HashMap<>();
        query.put("page",page);
        query.put("level",level);
        query.put("sort", sort);


        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).user_team(Utils.getHeader(getApplication()),query)
        ,this,new HttpInterFace<TeamListBean>(){
                    @Override
                    public void success(BaseBean<TeamListBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        uc.getData.setValue(loginBeanBaseBean.getData());
                        if(leval.get()==1){
                            renshu.set("直邀人数"+loginBeanBaseBean.getData().getTotal_count()+"人");
                        }else{
                            renshu.set("其他人数"+loginBeanBaseBean.getData().getTotal_count()+"人");
                        }



                    }
                });
    }


}
