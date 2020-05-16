package com.dongdian.shenquan.ui.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dongdian.shenquan.R;
import com.dongdian.shenquan.bean.CategoriesBean;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class FLFragmentThirdHolder extends BaseViewHolder<CategoriesBean.ChildrenBeanX.ChildrenBean> {
    private ImageView imageView;
    private TextView textView;
    public FLFragmentThirdHolder(ViewGroup parent) {
        super(parent, R.layout.f_l_fragment_third_item);
        imageView=(ImageView)$(R.id.f_l_fragment_third_item_icon);
        textView=(TextView)$(R.id.f_l_fragment_third_item_name);
    }

    @Override
    public void setData(CategoriesBean.ChildrenBeanX.ChildrenBean data) {
        super.setData(data);
        ShowImageUtils.showImageView(getContext(),data.getLogo(),imageView);
        textView.setText(data.getName());
    }
}
