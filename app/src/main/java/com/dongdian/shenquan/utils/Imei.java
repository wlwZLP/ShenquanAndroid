package com.dongdian.shenquan.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;

import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.app.ActivityCompat;


/**
 * Created by mac on 2019/1/8.
 */

public class Imei {

    public static String getimei(Context ctx) {
        try {
            TelephonyManager tm = (TelephonyManager)ctx.getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                String imei = tm.getDeviceId();
                if (!TextUtils.isEmpty(imei)) {
                    return imei;
                }else{
                    return Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID);
                }
            }
        } catch (Exception e) {
            Log.e("API", e.getMessage());
        }
        return null;
    }
}
