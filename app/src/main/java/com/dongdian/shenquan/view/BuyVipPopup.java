package com.dongdian.shenquan.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.dongdian.shenquan.utils.Utils;

public class BuyVipPopup extends BasePopupWindows implements View.OnClickListener{
    ConfirmCallBack confirmCallBack;

    public void setConfirmCallBack(ConfirmCallBack confirmCallBack) {
        this.confirmCallBack = confirmCallBack;
    }

    public BuyVipPopup(Context ctx) {
        super(ctx);
    }

    public BuyVipPopup(Context ctx, Object t) {
        super(ctx, t);
    }

    public BuyVipPopup(Context ctx, Object t, String title, ClearDialog.IsConfirm isConfirm) {
        super(ctx, t, title, isConfirm);
    }

    public BuyVipPopup(Context ctx, Object t, String title, int position, ClearDialog.IsConfirm isConfirm) {
        super(ctx, t, title, position, isConfirm);
    }

    @Override
    protected void init() {
        View clearView = LayoutInflater.from(ctx).inflate(R.layout.buy_vip_popup, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        clearView.setLayoutParams(params);
        setWidth(Utils.getScreenWidth(ctx)*6/7);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setContentView(clearView);
        ImageView imageView = clearView.findViewById(R.id.buy_vip_popup_image);
        TextView cancle = clearView.findViewById(R.id.buy_vip_popup_cancle);
        cancle.setOnClickListener(this);
        TextView sure = clearView.findViewById(R.id.buy_vip_popup_sure);
        sure.setOnClickListener(this);
        ShowImageUtils.showImageView(ctx,"https://rebate-robot.oss-cn-hangzhou.aliyuncs.com/MiniProgram/qhb/mp-vip-popup.png",imageView);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buy_vip_popup_cancle:

                dismiss();
                break;
            case R.id.buy_vip_popup_sure:
                confirmCallBack.confirm();
                dismiss();
                break;
        }
    }
    public interface ConfirmCallBack{
        void confirm();
    }
}
