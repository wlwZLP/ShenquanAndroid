package com.dongdian.shenquan.ui.activity.main;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.utils.SPUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.RadioGroup;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.databinding.ActivityMainBinding;
import com.dongdian.shenquan.ui.activity.login.LoginActivity;
import com.dongdian.shenquan.ui.fragment.fl.FLFragment;
import com.dongdian.shenquan.ui.fragment.home.HomeFragment;
import com.dongdian.shenquan.ui.fragment.me.MeFragment;
import com.dongdian.shenquan.ui.fragment.ppg.PPGFragment;
import com.dongdian.shenquan.utils.StatusBarUtil;
import com.dongdian.shenquan.utils.Utils;
import com.dongdian.shenquan.view.TKLPopupWindow;
import com.dongdian.shenquan.view.UpdataPopup;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

public class MainActivity extends MyBaseActivity<ActivityMainBinding, MainViewModel> {
    private HomeFragment homeFragment;
    private MeFragment meFragment;
    private FLFragment flFragment;
    private PPGFragment ppgFragment;
    private long exitTime;
    private UpdataPopup updataPopup;
    private String copy;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.mian;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarFullTransparent(this);
        statusBarLightMode();
        selectStyle(R.id.tab_home);
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_CALENDAR,
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_PHONE_STATE
                )
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            if (permission.name.equals("android.permission.READ_PHONE_STATE")) {
                                Intent intent = new Intent();
                                intent.setAction("LOGIN_REFLASE");
                                LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(MainActivity.this);
                                lbm.sendBroadcast(intent);
                            }
                        } else if (permission.shouldShowRequestPermissionRationale) {

                        } else {

                        }
                    }
                });
        if (!TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
            viewModel.bind();
        }
        viewModel.check_version();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.RadioGBottem.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectStyle(checkedId);
                if (!TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
                    //推送设置tag
                    viewModel.bind();
                }

            }
        });

        copy = Utils.getCopy(ctx);
        if(!TextUtils.isEmpty(copy)){
            viewModel.deep_search(copy);
        }

    }
    //根据具体点击切换显示对应fragment
    private void selectStyle(int ID) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (ID) {
            case R.id.tab_home:

                update(0, ft);
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.FrameAct_FragmentGroup, homeFragment);
                } else {
                    ft.show(homeFragment);
                }
                break;
            case R.id.tab_fl:
                update(2, ft);
                if (flFragment == null) {
                    flFragment = new FLFragment();
                    ft.add(R.id.FrameAct_FragmentGroup, flFragment);
                } else {
                    ft.show(flFragment);
                }
                break;
            case R.id.tab_me:
                if (TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
                    binding.tabHome.performClick();
                    startActivity(LoginActivity.class);
                    return;
                }
                update(3, ft);
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    ft.add(R.id.FrameAct_FragmentGroup, meFragment);
                } else {
                    ft.show(meFragment);

                }
                break;
            case R.id.tab_ppg:
                update(1, ft);
                if (ppgFragment == null) {
                    ppgFragment = new PPGFragment();
                    ft.add(R.id.FrameAct_FragmentGroup, ppgFragment);
                } else {
                    ft.show(ppgFragment);

                }
                break;

        }
        //提交
        ft.commitAllowingStateLoss();
    }

    private void update(int i, FragmentTransaction ft) {

        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (flFragment != null) {
            ft.hide(flFragment);
        }
        if (meFragment != null) {
            ft.hide(meFragment);
        }
        if (ppgFragment != null) {
            ft.hide(ppgFragment);
        }
        binding.tabHome.setTextColor(0xff000000);
        binding.tabFl.setTextColor(0xff000000);
        binding.tabMe.setTextColor(0xff000000);
        binding.tabPpg.setTextColor(0xff000000);
        if(i==0){
            binding.tabHome.setTextColor(0xffFFD409);
        }else if(i==1){
            binding.tabPpg.setTextColor(0xffFFD409);
        }else if(i==2){
            binding.tabFl.setTextColor(0xffFFD409);
        }else if(i==3){
            binding.tabMe.setTextColor(0xffFFD409);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(updataPopup!=null&&updataPopup.isShowing()&&!updataPopup.isGoBack()){
                return true;
            }
            if ((System.currentTimeMillis() - exitTime) > 2000) {// 不是2s内，连续按下
                //OwnUtils.showMessage("再按一次退出程序");

                ToastUtils.showShort("再按一次退出程序");
                exitTime = System.currentTimeMillis();
                return false;
            } else {// 连续按下俩次，关闭定位退出
                //  MyApplication.getInstance().exit();
                finish();
                return true;
            }
        }
        return false;
    }



    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.openUpdataPopup.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                updataPopup = new UpdataPopup(MainActivity.this,s);
                updataPopup.showAtLocation(binding.RadioGBottem, Gravity.CENTER,0,0);
            }
        });
        viewModel.uc.openTKL.observe(this, new Observer<GoodsList.ItemsBean>() {
            @Override
            public void onChanged(GoodsList.ItemsBean itemsBean) {
                if(itemsBean==null){
                    TKLPopupWindow tklPopupWindow = new TKLPopupWindow(MainActivity.this,2,null,copy);
                    tklPopupWindow.showAtLocation(binding.RadioGBottem,Gravity.CENTER,0,0);
                }else{
                    TKLPopupWindow tklPopupWindow = new TKLPopupWindow(MainActivity.this,1,itemsBean,null);
                    tklPopupWindow.showAtLocation(binding.RadioGBottem,Gravity.CENTER,0,0);
                }
            }
        });
    }


}
