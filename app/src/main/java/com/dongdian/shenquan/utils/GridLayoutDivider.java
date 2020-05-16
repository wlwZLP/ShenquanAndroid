package com.dongdian.shenquan.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class GridLayoutDivider extends RecyclerView.ItemDecoration  {
    private Paint mPaint;
    private int mDividerWidth;

    public GridLayoutDivider(int height, @ColorInt int color) {
        mDividerWidth = height;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
        // mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        draw(c, parent);
    }
    private void draw(Canvas canvas, RecyclerView parent){
        int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            //画水平分隔线
            int left = child.getLeft()-layoutParams.leftMargin-mDividerWidth;
            int right = child.getRight()+mDividerWidth;
            int top = child.getBottom() + layoutParams.bottomMargin;
            int bottom = top + mDividerWidth;
            if(mPaint!=null){
                canvas.drawRect(left, top, right, bottom, mPaint);
                //画最上面的水平线
                if(isFirstRow(i,getSpanCount(parent),childSize,parent)){
                    top=child.getTop()+layoutParams.bottomMargin-mDividerWidth;
//
                    bottom = top + mDividerWidth;
                    canvas.drawRect(left, top, right, bottom, mPaint);
                }
            }

//画垂直分割线
            top = child.getTop()-layoutParams.topMargin-mDividerWidth;
            bottom = child.getBottom() + mDividerWidth+layoutParams.bottomMargin;
            left = child.getRight() + layoutParams.rightMargin;
            right = left + mDividerWidth;

            if (mPaint != null) {
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
            //画最左侧的垂直线
            if(isFirsSpan(i,getSpanCount(parent),childSize)){
                left=child.getLeft()+layoutParams.rightMargin-mDividerWidth;
                right = left + mDividerWidth;
                if (mPaint != null) {
                    canvas.drawRect(left, top, right, bottom, mPaint);
                }
            }
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        boolean isFirstRow = isFirstRow(itemPosition,spanCount,childCount,parent);
        boolean isFirstPan = isFirsSpan(itemPosition,spanCount,childCount);
        int top;
        int left;
        int right=mDividerWidth;
        int bottom = mDividerWidth;
        if(isFirstPan){
            left=mDividerWidth;
        }else{
            left=0;
        }
        if (isFirstRow) {
            top = mDividerWidth;
        } else {
            top = 0;
        }
        outRect.set(left, top, right, bottom);

    }

    private boolean isFirsSpan(int pos, int spanCount, int childCount){
        return pos%spanCount==0;
    }
    private boolean isFirstRow(int pos,int spanCount,int childCount,RecyclerView parent){
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return pos/spanCount==0;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount)
                    return true;
            } else {
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }
    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {

            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }
        return spanCount;
    }
}
