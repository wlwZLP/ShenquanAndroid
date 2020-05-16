package com.dongdian.shenquan.ui.activity.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.databinding.ActivityLoginBinding;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends MyBaseActivity<ActivityLoginBinding,LoginViewModel> {



    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.loginviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        binding.loginCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                viewModel.checkbox.set(b);
            }
        });
        binding.loginInviteCodeEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length()>=6){
                    viewModel.get_recommendor();
                }
            }
        });
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.wechatlogin.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                UMShareAPI.get(getApplication()).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN
                        , new UMAuthListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                                if (share_media == SHARE_MEDIA.WEIXIN) {
                                    HashMap<String, Object> rmap = new HashMap<String, Object>();
                                    rmap.put("openid", map.get("openid"));
                                    rmap.put("nickname", map.get("name"));
                                    rmap.put("gender", map.get("gender"));
                                    rmap.put("city", map.get("city"));
                                    rmap.put("province", map.get("province"));
                                    rmap.put("country", map.get("country"));
                                    rmap.put("avatar_url", map.get("iconurl"));
                                    rmap.put("union_id", map.get("unionid"));
                                    rmap.put("area_code","+86");
                                    viewModel.wechatparma.set(rmap);
                                   viewModel.exist("2","openid",map.get("openid"));
                                }
                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media, int i) {

                            }
                        });
            }
        });
        viewModel.uc.openvalidate.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.loginCountDownButton.startCountDown();
                viewModel.uc.isClick.setValue(true);
            }
        });
        viewModel.uc.isClick.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    binding.loginLoginButton.setBackgroundResource(R.drawable.home_top_search_text);
                }else{
                    binding.loginLoginButton.setBackgroundResource(R.drawable.bg_login_button_isclick);
                }
            }
        });
    }
}
