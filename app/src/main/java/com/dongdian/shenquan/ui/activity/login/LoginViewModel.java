package com.dongdian.shenquan.ui.activity.login;

import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.RecommendBean;
import com.dongdian.shenquan.bean.login.ExistBean;
import com.dongdian.shenquan.bean.login.LoginBean;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.ui.activity.web.WebViewActivity;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.Utils;
import com.umeng.socialize.UMShareAPI;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.SPUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class LoginViewModel extends MyBaseViewModel {
    public boolean isHave = false;
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public BindingCommand service = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("title","服务协议");
            bundle.putString("url","http://www.biyingniao.com/articles/5");
            startActivity(WebViewActivity.class,bundle);
        }
    });
    public BindingCommand privacy = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("title","隐私政策");
            bundle.putString("url","http://www.biyingniao.com/articles/17");
            startActivity(WebViewActivity.class,bundle);
        }
    });
    public BindingCommand login = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(!uc.isClick.getValue()){
                return;
            }
            if(TextUtils.isEmpty(phone.get())){
                ToastUtils.showShort("手机号不能为空");
                return;
            }
            if(TextUtils.isEmpty(verification.get())){
                ToastUtils.showShort("验证码不能为空");
                return;
            }
            if(!isHave){
                if(TextUtils.isEmpty(inviteCode.get())){
                    ToastUtils.showShort("请输入邀请码");
                    return;
                }
            }

            if(!checkbox.get()){
                ToastUtils.showShort("请先同意服务协议和隐私政策");
                return;
            }
            if(isHave){
                phoneLogin();
            }else{
                HashMap<String,Object> fileMap = new HashMap<>();
                fileMap.put("area_code","+86");
                fileMap.put("phone",phone.get());
                fileMap.put("validate_code",verification.get());
                if(!TextUtils.isEmpty(inviteCode.get())){
                    fileMap.put("recommend_code",inviteCode.get());
                }
                register(fileMap);
            }
        }
    });
    public BindingCommand wechat = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
          wechatLogin();
        }
    });
    public BindingCommand countDown = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            exist("1","phone",phone.get());
        }
    });
    public ObservableField<Boolean> checkbox = new ObservableField<>(true);
    public ObservableField<String> phone = new ObservableField<>();
    public ObservableField<String> verification = new ObservableField<>();
    public ObservableField<String> inviteCode = new ObservableField<>();
    public ObservableField<String> inviteIcon = new ObservableField<>();
    public ObservableField<String> inviteName = new ObservableField<>();
    public ObservableField<Integer> inviteCodeVisiable = new ObservableField<>(View.GONE);
    public ObservableField<HashMap<String,Object>> wechatparma = new ObservableField<>();

    public UIChangeObservable uc = new UIChangeObservable();
    public class UIChangeObservable {

        public UIChangeObservable() {
        }
        public SingleLiveEvent<String> openvalidate = new SingleLiveEvent<>();

        public SingleLiveEvent<Boolean> wechatlogin = new SingleLiveEvent<>();

        public SingleLiveEvent<Boolean> isClick = new SingleLiveEvent<>();
    }

    public LoginViewModel(@NonNull Application application) {
        super(application);
        uc.isClick.setValue(false);
    }

    public void get_validate_code(String type){


        HashMap<String,Object> query = new HashMap<>();
        query.put("area_code","+86");
        query.put("phone",phone.get());
        query.put("type",type);
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).get_validate_code(Utils.getHeader(getApplication()),query)
        ,this,new HttpInterFace(){
                    @Override
                    public void success(BaseBean loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        closeloading();
                        ToastUtils.showShort(loginBeanBaseBean.getMsg());
                        uc.openvalidate.setValue("");

                    }

                    @Override
                    public void error(Exception e) {
                        super.error(e);
                        closeloading();
                    }
                });

    }







    public void exist(final String type, String key, String value){ //type 1 手机  2 微信
       openloading();

        HashMap<String,Object> query = new HashMap<>();
        query.put(key,value);

        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).exist(Utils.getHeader(getApplication()),query)
        ,this,new HttpInterFace<ExistBean>(){
                    @Override
                    public void success(BaseBean<ExistBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        isHave=loginBeanBaseBean.getData().isIs_exist();
                        if(loginBeanBaseBean.getData().isIs_exist()){
                            if(type.equals("1")){
                                get_validate_code("2");//登录获取验证码
                            }else{

                            }
                        }else{
                            if(type.equals("1")){
                                get_validate_code("1");//注册获取验证码
                                inviteCodeVisiable.set(View.VISIBLE);

                            }else{

                            }
                        }
                    }

                    @Override
                    public void error(Exception e) {
                        super.error(e);
                        closeloading();
                    }
                });


    }
    public void wechatLogin(){
        uc.wechatlogin.setValue(true);


    }
    public void phoneLogin(){

        HashMap<String,Object> headerMap = new HashMap<>();
        headerMap.put("area_code","+86");
        headerMap.put("phone",phone.get());
        headerMap.put("validate_code",verification.get());
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).login(Utils.getHeader(getApplication()),headerMap)
                ,this,new HttpInterFace<LoginBean>(){
                    @Override
                    public void success(BaseBean<LoginBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        closeloading();
                        ToastUtils.showShort("登录成功");
                        SPUtils.getInstance().put("token",loginBeanBaseBean.getData().getToken());
                        finish();
                    }

                    @Override
                    public void error(Exception e) {
                        super.error(e);
                        closeloading();
                    }
                });

    }

    public void register(HashMap<String,Object> fileMap){
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).register(Utils.getHeader(getApplication()),fileMap)
        ,this,new HttpInterFace<LoginBean>(){
                    @Override
                    public void success(BaseBean<LoginBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        closeloading();
                        ToastUtils.showShort("登录成功");
                        SPUtils.getInstance().put("token",loginBeanBaseBean.getData().getToken());
                        finish();
                    }

                    @Override
                    public void error(Exception e) {
                        super.error(e);
                        closeloading();
                    }
                });



    }




    public void get_recommendor(){
        HashMap<String, Object> query = new HashMap<>();

        query.put("recommend_code", inviteCode.get());


        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).get_recommendor(Utils.getHeader(getApplication()),query)
        ,this,new HttpInterFace<RecommendBean>(){
                    @Override
                    public void success(BaseBean<RecommendBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        inviteIcon.set(loginBeanBaseBean.getData().getAvatar());
                        inviteName.set(loginBeanBaseBean.getData().getName());
                    }
                });

    }




}
