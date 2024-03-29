package com.dongdian.shenquan.view;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;


/**
 * Created by Administrator on 2019/3/28.
 * 禁止滑动
 */

public class MyLinearLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnabled = true;

    public MyLinearLayoutManager(Context context) {
        super(context);
    }


    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }
}
