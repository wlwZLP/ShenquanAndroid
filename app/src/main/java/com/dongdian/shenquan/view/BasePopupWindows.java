package com.dongdian.shenquan.view;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

public abstract class BasePopupWindows extends PopupWindow {

    protected ClearDialog.IsConfirm isConfirm;
    protected int position = -1;
    protected String title;
    protected Context ctx;

    private Window window;

    protected abstract void init();

    protected Object data;

    public BasePopupWindows(Context ctx) {
        super(ctx);
        this.ctx = ctx;
        window = ((Activity) ctx).getWindow();
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setInputMethodMode(PopupWindow.INPUT_METHOD_FROM_FOCUSABLE);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//设置背景
        //setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//透明背景
        init();
    }

    public BasePopupWindows(Context ctx, Object t) {
        super(ctx);
        this.ctx = ctx;
        this.data = t;
        window = ((Activity) ctx).getWindow();
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setInputMethodMode(PopupWindow.INPUT_METHOD_FROM_FOCUSABLE);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//设置背景
        //setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//透明背景
        init();
    }

    public BasePopupWindows(Context ctx, Object t, String title, ClearDialog.IsConfirm isConfirm) {
        super(ctx);
        this.ctx = ctx;
        this.data = t;
        this.title = title;//对话框标题
        this.isConfirm = isConfirm;
        window = ((Activity) ctx).getWindow();
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setInputMethodMode(PopupWindow.INPUT_METHOD_FROM_FOCUSABLE);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//设置背景

        //setBackgroundDrawable(new ColorDrawable(ctx.getResources().getColor(R.color.transparent)));
        init();
    }


    public BasePopupWindows(Context ctx, Object t, String title, int position, ClearDialog.IsConfirm isConfirm) {
        super(ctx);
        this.ctx = ctx;
        this.data = t;
        this.title = title;//对话框标题
        this.position = position;//点击的position
        this.isConfirm = isConfirm;
        window = ((Activity) ctx).getWindow();
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setInputMethodMode(PopupWindow.INPUT_METHOD_FROM_FOCUSABLE);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//设置背景
        //setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//透明背景
        init();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 1.0f;
        window.setAttributes(lp);
    }

    @Override
    public void showAsDropDown(View anchor) {
        this.showAsDropDown(anchor, 0, 0);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        super.showAsDropDown(anchor, xoff, yoff);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.7f;
        window.setAttributes(lp);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.7f;
        window.setAttributes(lp);
    }

    private class T {
    }
}
