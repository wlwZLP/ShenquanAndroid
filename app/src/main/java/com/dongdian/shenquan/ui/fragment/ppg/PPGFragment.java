package com.dongdian.shenquan.ui.fragment.ppg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseFragment;
import com.dongdian.shenquan.bean.ppg.PPGCategoriesBean;
import com.dongdian.shenquan.databinding.PpgFragmentBinding;
import com.dongdian.shenquan.ui.adapter.HomeAdapter;
import com.dongdian.shenquan.ui.fragment.ppgitem.PPGHomeFragment;
import com.dongdian.shenquan.ui.fragment.ppgitem.PPGItemFragment;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class PPGFragment extends MyBaseFragment<PpgFragmentBinding,PPGFragmentViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.ppg_fragment;
    }

    @Override
    public int initVariableId() {
        return BR.ppgviewmodel;
    }
    @Override
    public void initData() {
        super.initData();
        viewModel.getCa();
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<PPGCategoriesBean>() {
            @Override
            public void onChanged(PPGCategoriesBean ppgCategoriesBean) {
                if(ppgCategoriesBean==null){
                    ToastUtils.showShort("未获取到数据");
                    return;
                }
                ArrayList<Fragment> list = new ArrayList<>();
                String[] titles = new String[ppgCategoriesBean.getCategories().getContents().size()+1];
                list.add(new PPGHomeFragment());
                titles[0]="全部";




                for (int i = 0; i < ppgCategoriesBean.getCategories().getContents().size(); i++) {
                    PPGCategoriesBean.CategoriesBean.ContentsBean dataBean = ppgCategoriesBean.getCategories().getContents().get(i);
                    titles[i+1] = dataBean.getName();
                    PPGItemFragment homeItemFragment = new PPGItemFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("id", dataBean.getId() + "");
                    homeItemFragment.setArguments(bundle);
                    list.add(homeItemFragment);
                }

                HomeAdapter adapter=new HomeAdapter(getChildFragmentManager(),list,titles);
                binding.ppgViewpager.setAdapter(adapter);
                binding.ppgMytab.setViewPager(binding.ppgViewpager);

            }
        });
    }
}
