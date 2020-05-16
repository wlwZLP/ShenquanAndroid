package com.dongdian.shenquan.view;

import android.content.Context;
import android.graphics.Color;

import com.yanyusong.y_divideritemdecoration.Y_Divider;
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder;
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;

public class GridItemDecoration extends Y_DividerItemDecoration {

    public GridItemDecoration(Context context) {
        super(context);
    }

    @Override
    public Y_Divider getDivider(int itemPosition) {
        Y_Divider divider = null;
        switch (itemPosition % 2) {
            case 0:
                //每一行第一个显示 rignt 和 bottom
                divider = new Y_DividerBuilder()
                        .setRightSideLine(true, Color.parseColor("#f6f6f6"), 0, 0, 0)
                        .setBottomSideLine(true, Color.parseColor("#f6f6f6"), 5, 0, 0)
                        .create();
                break;
            case 1:
                //第二个显示 Left 和 bottom
                divider = new Y_DividerBuilder()
                        .setLeftSideLine(true, Color.parseColor("#f6f6f6"), 0, 0, 0)
                        .setBottomSideLine(true, Color.parseColor("#f6f6f6"), 5, 0, 0)
                        .create();
                break;
            default:
                break;
        }
        return divider;
    }

}