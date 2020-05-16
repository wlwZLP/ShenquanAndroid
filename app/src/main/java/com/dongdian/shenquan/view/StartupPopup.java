package com.dongdian.shenquan.view;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.ui.activity.main.MainActivity;
import com.dongdian.shenquan.ui.activity.web.WebViewActivity;
import com.dongdian.shenquan.utils.Utils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zyq.easypermission.EasyPermission;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class StartupPopup extends FragmentActivity implements Utils.ClickListener {
    private TextView tv,agree,unagree;
    private ArrayList<String> str_list;
    private ArrayList<Integer> color_list;
    private final String SHARE_APP_TAG = "isFirst";
    private SharedPreferences setting;
    private Boolean first;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.startup_dialog);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
//        lp.x = 0; // 新位置X坐标
//        lp.y = 0; // 新位置Y坐标
//        lp.alpha =1f; // 透明度
        lp.width= Utils.getScreenWidth(this);
        lp.height=Utils.getScreenHeight(this);
        dialogWindow.setAttributes(lp);
        dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);
        initView();
        verifyStoragePermissions();
       // checkPermission();
    }

    private int times = 0;
    //在处理权限时的回调
    private final int REQUEST_PHONE_PERMISSIONS = 0;
    //检查全新的核心方法
    private void checkPermission(){
        times++;
        final List<String> permissionsList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if ((checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE)!= PackageManager.PERMISSION_GRANTED)) permissionsList.add(Manifest.permission.ACCESS_NETWORK_STATE);
            if ((checkSelfPermission(Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED)) permissionsList.add(Manifest.permission.READ_PHONE_STATE);
            if ((checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)) permissionsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if ((checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)) permissionsList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            if ((checkSelfPermission(Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)) permissionsList.add(Manifest.permission.CAMERA);
            if ((checkSelfPermission(Manifest.permission.BLUETOOTH)!= PackageManager.PERMISSION_GRANTED)) permissionsList.add(Manifest.permission.BLUETOOTH);
            if ((checkSelfPermission(Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED)) permissionsList.add(Manifest.permission.RECORD_AUDIO);
            if (permissionsList.size() != 0){
                if(times==1){
                   ActivityCompat.requestPermissions(StartupPopup.this,permissionsList.toArray(new String[permissionsList.size()]),
                            REQUEST_PHONE_PERMISSIONS);
                }else{
                    new AlertDialog.Builder(this)
                            .setCancelable(true)
                            .setTitle("提示")
                            .setMessage("获取不到授权，APP将无法正常使用，请允许APP获取权限！")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                                                REQUEST_PHONE_PERMISSIONS);
                                    }
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            finish();//这里对该界面直接进行销毁，让用户从新进入该界面
                        }
                    }).show();
                }
            }else{
                //initData();//初始化数据
            }
        }else{
            //initData();//初始化数据
        }
    }



    public void verifyStoragePermissions() {
        // Check if we have write permission
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .requestEachCombined(Manifest.permission.ACCESS_FINE_LOCATION,
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

                            }
                        } else if (permission.shouldShowRequestPermissionRationale) {

                        } else {

                        }
                    }
                });






//        EasyPermission.build().requestPermission(this, Manifest.permission.CALL_PHONE
//                , Manifest.permission.READ_PHONE_STATE,
//                Manifest.permission.ACCESS_NETWORK_STATE,
//                Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.MODIFY_AUDIO_SETTINGS,
//                Manifest.permission.WRITE_SETTINGS,
//                Manifest.permission.CHANGE_WIFI_STATE,
//                Manifest.permission.RECORD_AUDIO,
//                Manifest.permission.CAMERA);
    }


    private void initView() {
        setting = getSharedPreferences(SHARE_APP_TAG, 0);
        first = setting.getBoolean("isDaKai", true);
        tv=(TextView)findViewById(R.id.startup_dialog_content);
        agree=(TextView)findViewById(R.id.startup_dialog_agree);
        unagree=(TextView)findViewById(R.id.startup_dialog_unagree);
        str_list = new ArrayList<String>();
        color_list = new ArrayList<Integer>();
        str_list.add("请你务必审慎阅读。充分理解“服务协议”和“隐私政策”各条款。包括但不限于：为了向你提供周边优惠，我们需要收集你的定位信息。你可以在“设置”中管理你的授权。\n你可阅读");
        str_list.add("《用户协议》");
        str_list.add("和");
        str_list.add("《隐私政策》");
        str_list.add("了解详细信息。如你同意，请点击“同意”开始接受我们的服务。");
        color_list.add(0xff333333);
        color_list.add(0xff1B91FF);
        color_list.add(0xff333333);
        color_list.add(0xff1B91FF);
        color_list.add(0xff333333);
        Utils.setText(this, tv, str_list, color_list,
                this);
        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setting.edit().putBoolean("isDaKai",false).commit();
                startActivity(new Intent(StartupPopup.this, MainActivity.class));
                finish();
            }
        });
        unagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void click(int positon) {
        if(positon==1){
            Bundle bundle = new Bundle();
            bundle.putString("title","服务协议");
            bundle.putString("url","http://www.biyingniao.com/articles/5");

            startActivity(new Intent(this, WebViewActivity.class).putExtras(bundle));

        }else if(positon==3){
            Bundle bundle = new Bundle();
            bundle.putString("title","隐私政策");
            bundle.putString("url","http://www.biyingniao.com/articles/17");

            startActivity(new Intent(this, WebViewActivity.class).putExtras(bundle));
        }
    }
}
