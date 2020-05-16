package com.dongdian.shenquan.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.CheckVersionBean;
import com.dongdian.shenquan.utils.Utils;
import com.google.gson.Gson;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public class UpdataPopup extends BasePopupWindows  {
    private String s;
    private CheckVersionBean checkVersionBean;
    private ImageView finish;
    private TextView version;
    private LinearLayout updata;
    private LinearLayout content;
    private boolean isGoBack;

    public boolean isGoBack() {
        return isGoBack;
    }

    public void setGoBack(boolean goBack) {
        isGoBack = goBack;
    }

    public UpdataPopup(Context ctx, String s) {
        super(ctx);
        this.s=s;
        Gson gson = new Gson();
        checkVersionBean = gson.fromJson(s, CheckVersionBean.class);
        initData();

    }


    public UpdataPopup(Context ctx, Object t) {
        super(ctx, t);
    }

    public UpdataPopup(Context ctx, Object t, String title, ClearDialog.IsConfirm isConfirm) {
        super(ctx, t, title, isConfirm);
    }

    public UpdataPopup(Context ctx, Object t, String title, int position, ClearDialog.IsConfirm isConfirm) {
        super(ctx, t, title, position, isConfirm);
    }

    @Override
    protected void init() {

        View clearView = LayoutInflater.from(ctx).inflate(R.layout.updata_dialog, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        clearView.setLayoutParams(params);
        setWidth(Utils.getScreenWidth(ctx)*6/7);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setContentView(clearView);
        finish = (ImageView) clearView.findViewById(R.id.updata_popup_finish);

        version = (TextView) clearView.findViewById(R.id.updata_popup_version_text);

        updata = (LinearLayout) clearView.findViewById(R.id.updata_popup_button);

        content = (LinearLayout) clearView.findViewById(R.id.updata_popup_content);




    }

    private void initData() {
        if(checkVersionBean.getUpdate_type()==2){
            finish.setVisibility(View.GONE);
            setGoBack(false);
        }else{
            setGoBack(true);
            finish.setVisibility(View.VISIBLE);
        }
        version.setText(checkVersionBean.getVersion_name());
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBrowser(ctx,checkVersionBean.getDownload_link());
                dismiss();
            }
        });
        List<String> features = checkVersionBean.getFeatures();

        for (int i = 0; i < features.size(); i++) {
            TextView textView = new TextView(ctx);
            textView.setText(features.get(i));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
            textView.setTextColor(0xff3c3c3c);
            LinearLayout.LayoutParams LP_WW = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LP_WW.setMargins(0,10,0,0);
            textView.setLayoutParams(LP_WW);
            content.addView(textView);
        }




    }


    public void openBrowser(Context context, String url) {
        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        // 注意此处的判断intent.resolveActivity()可以返回显示该Intent的Activity对应的组件名
        // 官方解释 : Name of the component implementing an activity that can display the intent
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            final ComponentName componentName = intent.resolveActivity(context.getPackageManager());
            context.startActivity(Intent.createChooser(intent, "请选择浏览器"));
        } else {
            Toast.makeText(context.getApplicationContext(), "请下载浏览器", Toast.LENGTH_SHORT).show();
        }
    }






}
