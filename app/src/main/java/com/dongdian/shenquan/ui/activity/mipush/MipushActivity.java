package com.dongdian.shenquan.ui.activity.mipush;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import me.goldze.mvvmhabit.base.BaseActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.databinding.ActivityMipushBinding;
import com.dongdian.shenquan.restart.AppStatus;
import com.dongdian.shenquan.restart.AppStatusManager;
import com.dongdian.shenquan.ui.activity.main.MainActivity;
import com.dongdian.shenquan.utils.MyHandler;
import com.dongdian.shenquan.utils.Utils;
import com.dongdian.shenquan.view.StartupPopup;

public class MipushActivity extends BaseActivity<ActivityMipushBinding,MipushViewModel> {



    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_mipush;
    }

    @Override
    public int initVariableId() {
        return BR.mipushviewmodel;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onResume() {
        Log.e("====","shaqingk");
        super.onResume();
        if(Utils.isLaunchedActivity(this, MainActivity.class)){
            finish();
        }else{
            defaultSecond=2;
            handler.sendEmptyMessageDelayed(1, 1000);

        }
    }
    private  int defaultSecond = 2;  //显示默认图时间2s
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                defaultSecond--;
                if (defaultSecond == 0) {
                    AppStatusManager.getInstance().setAppStatus(AppStatus.STATUS_NORMAL);
                    SharedPreferences isFirst = getSharedPreferences("isFirst", 0);
                    boolean isDaKai = isFirst.getBoolean("isDaKai", true);
                    if(isDaKai){
                        startActivity(new Intent(MipushActivity.this, StartupPopup.class));
                    }else{
                        startActivity(new Intent(MipushActivity.this, MainActivity.class));
                    }

                    finish();

                } else {
                    handler.sendEmptyMessageDelayed(1, 1000);
                }
            }

        }
    };
}
