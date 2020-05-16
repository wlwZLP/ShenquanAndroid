package com.dongdian.shenquan.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

public class ClearDialog extends BasePopupWindows implements View.OnClickListener {
    private RecyclerArrayAdapter adapter;//清空数据用
    private TextView tvTitle;
    private TextView tvContent;

    /**
     * @param ctx
     * @param textContent 对话空显示的内容
     * @param adapter     要清空的对象
     */
    public ClearDialog(Context ctx, Object textContent, RecyclerArrayAdapter adapter) {
        super(ctx, textContent);
        this.adapter = adapter;
    }

    /**
     * @param ctx
     * @param textContent 对话空显示的内容
     * @param adapter     要清空的对象
     * @param position    这是删除单条
     */
    public ClearDialog(Context ctx, Object textContent, String title, RecyclerArrayAdapter adapter, int position, IsConfirm isConfirm) {
        super(ctx, textContent, title, position,isConfirm);
        this.adapter = adapter;
    }

    public ClearDialog(Context ctx, Object textContent, String title, IsConfirm isConfirm) {
        super(ctx, textContent, title, isConfirm);
    }
    private TextView tvCancel;
    private TextView tvConfirm;
    @Override
    protected void init() {
        View clearView = LayoutInflater.from(ctx).inflate(R.layout.dialog_delete, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        clearView.setLayoutParams(params);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setContentView(clearView);
        tvContent = (TextView) clearView.findViewById(R.id.tv_textContent);//提示内容
        tvTitle = (TextView) clearView.findViewById(R.id.tv_title);//标题

        if (!"".equals(data)) {
            tvContent.setText((String) data);
        }

        if (!"".equals(title) && title != null) {
            tvTitle.setText(title);
        }
        tvCancel = (TextView) clearView.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(this);
        tvConfirm = (TextView) clearView.findViewById(R.id.tv_confirm);
        tvConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                if (isConfirm != null) {//点的取消
                    isConfirm.isConfirm(false);
                }
                dismiss();
                break;
            case R.id.tv_confirm:
                if (isConfirm != null) {//点的确定
                    isConfirm.isConfirm(true);
                }
                if (adapter != null && position != -1) {
                    adapter.remove(position);
                    position = -1;
                    //TODO 删除
                }
                //清空数据
                else {
                    if (adapter != null) {
                        adapter.clear();
                        adapter.notifyDataSetChanged();
                        dismiss();
                    }
                }
                dismiss();
                break;
        }
    }
    public void setTextContentColor(String color,int gravity){
        tvContent.setTextColor(Color.parseColor(color));
        tvContent.setGravity(gravity);
    }

    //回调点击的是取消还是确定
    public static interface IsConfirm {
        void isConfirm(boolean isConfirm);
    }
    public void hiddenCancle(){
        tvCancel.setVisibility(View.GONE);
    }
}