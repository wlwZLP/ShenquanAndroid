package com.dongdian.shenquan.ui.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.CategoriesBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import androidx.core.content.ContextCompat;

public class FLFragmentFirstHolder extends BaseViewHolder<CategoriesBean> {
    RelativeLayout autoLinearLayout;
    TextView textView;
    View view;

    public FLFragmentFirstHolder(ViewGroup parent) {
        super(parent, R.layout.f_l_fragment_first_item);
        autoLinearLayout = (RelativeLayout) $(R.id.f_l_fragment_first_item_layout);
        textView = (TextView) $(R.id.f_l_fragment_first_item_text);
        view = (View) $(R.id.f_l_fragment_first_item_view);
    }

    @Override
    public void setData(CategoriesBean data) {
        super.setData(data);
        boolean check = data.isCheck();
        if (check) {
            textView.setTextColor(0xff222222);
            autoLinearLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.fense));
            view.setVisibility(View.VISIBLE);
        } else {
            textView.setTextColor(0xff222222);
            autoLinearLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
            view.setVisibility(View.GONE);
        }
        textView.setText(data.getName());
    }
}