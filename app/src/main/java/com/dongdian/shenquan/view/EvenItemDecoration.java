package com.dongdian.shenquan.view;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Administrator on 2019/5/24.
 */

public class EvenItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int column;

    /**
     * recycler宽度平分
     * @param space   各位多少个px
     * @param column   每行item个数
     */
    public EvenItemDecoration(int space, int column) {
        this.space = space;
        this.column = column;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position  = parent.getChildAdapterPosition(view);
        int spanSpace  = space * (column + 1) / column;

        int colIndex = position % column;
        outRect.left = space * (colIndex + 1) - spanSpace * colIndex;
        outRect.right = spanSpace * (colIndex + 1) - space * (colIndex + 1);
        // 行间距
        if (position >= column) {
            outRect.top = space;
        }
    }
}
