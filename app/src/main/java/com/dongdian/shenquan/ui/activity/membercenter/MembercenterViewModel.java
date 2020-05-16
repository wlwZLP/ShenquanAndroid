package com.dongdian.shenquan.ui.activity.membercenter;

import android.app.Application;
import android.text.TextUtils;
import android.view.View;

import com.dongdian.shenquan.bean.PayBean;
import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.CommonBean;
import com.dongdian.shenquan.bean.MemberPageBean;
import com.dongdian.shenquan.bean.ProductOrderBean;
import com.dongdian.shenquan.bean.user.UserBean;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.Utils;

import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.SPUtils;

public class MembercenterViewModel extends MyBaseViewModel {
    public String mp_vip2_5="https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/MiniProgram/qhb/mp-vip2-5.png";
    public String mp_vip2_1="R.mipmap.ic_dzdp";
  //  public String mp_vip2_1="https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/MiniProgram/qhb/mp-vip2-1.png";
    public String mp_vip2_2="https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/MiniProgram/qhb/mp-vip2-2.png";
    public String mp_vip2_3="https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/MiniProgram/qhb/mp-vip2-3.png";
    public String mp_vip2_4="https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/MiniProgram/qhb/mp-vip2-4.png";
    public MembercenterViewModel(@NonNull Application application) {
        super(application);
    }
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public BindingCommand buy = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(TextUtils.isEmpty(id.get())){
                return;
            }
           // uc.pay.setValue("");
            member_buy_first();
        }
    });
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        public SingleLiveEvent<CommonBean> getData = new SingleLiveEvent<CommonBean>();
        public SingleLiveEvent<String> pay = new SingleLiveEvent<>();
        public UIChangeObservable() {
        }
    }
    public ObservableField<Integer> noLogin = new ObservableField<>(View.VISIBLE);
    public ObservableField<Integer> login = new ObservableField<>(View.GONE);
    public ObservableField<String> icon = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> plus_expire = new ObservableField<>();
    public ObservableField<String> plus_text = new ObservableField<>();

    public ObservableField<String> single_price = new ObservableField<>();
    public ObservableField<String> id = new ObservableField<>();
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

                        if(loginBeanBaseBean.getData().getPlus_level()==0){
                            login.set(View.GONE);
                            noLogin.set(View.VISIBLE);
                        }else{
                            login.set(View.VISIBLE);
                            noLogin.set(View.GONE);
                        }

                        name.set(loginBeanBaseBean.getData().getName());
                        icon.set(loginBeanBaseBean.getData().getAvatar());
                        plus_expire.set("有效期："+loginBeanBaseBean.getData().getPlus_expire());
                        plus_text.set(loginBeanBaseBean.getData().getType_text());
                    }
                });


    }


    public void getcommon(){
        HashMap<String, Object> query = new HashMap<>();
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).common(Utils.getHeader(getApplication()),query)
        ,this,new HttpInterFace<CommonBean>(){
                    @Override
                    public void success(BaseBean<CommonBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        uc.getData.setValue(loginBeanBaseBean.getData());
                    }
                });
    }

    public void memberpage(){
        HashMap<String,Object> fileMap = new HashMap<>();
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).member_page(Utils.getHeader(getApplication()),fileMap)
        ,this,new HttpInterFace<MemberPageBean>(){
                    @Override
                    public void success(BaseBean<MemberPageBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);

                        if(loginBeanBaseBean.getData()!=null&&loginBeanBaseBean.getData().getLevel()!=null&&loginBeanBaseBean.getData().getLevel().size()>0){
                            List<MemberPageBean.LevelBean> level = loginBeanBaseBean.getData().getLevel();
                            MemberPageBean.LevelBean levelBean = level.get(0);
                            id.set(levelBean.getId()+"");
                            single_price.set(levelBean.getSingle_price());
                        }
                    }
                });
    }



    private void member_buy_first(){
        HashMap<String,Object> fileMap = new HashMap<>();
        fileMap.put("id",id.get());

        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).member_buy_first(Utils.getHeader(getApplication()),fileMap)
                ,this,new HttpInterFace<ProductOrderBean>(){
                    @Override
                    public void success(BaseBean<ProductOrderBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        member_buy_second(loginBeanBaseBean.getData().getOrder_no());
                    }
                });
    }

    private void member_buy_second(String order_no){
        HashMap<String,Object> fileMap = new HashMap<>();
        fileMap.put("order_no",order_no);
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).member_buy_second(Utils.getHeader(getApplication()),fileMap)
                ,this,new HttpInterFace<PayBean>(){
                    @Override
                    public void success(BaseBean<PayBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        uc.pay.setValue(loginBeanBaseBean.getData().getPay_params());
                    }
                });
    }




}
