package com.dongdian.shenquan.ui.activity.couple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.bean.CoupleBean;
import com.dongdian.shenquan.databinding.ActivityCoupleBinding;
import com.dongdian.shenquan.ui.adapter.HomeAdapter;
import com.dongdian.shenquan.ui.fragment.couple.CoupleFragment;

import java.util.ArrayList;
import java.util.List;

public class CoupleActivity extends MyBaseActivity<ActivityCoupleBinding,CoupleViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_couple;
    }

    @Override
    public int initVariableId() {
        return BR.coupleviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.getdata();
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<List<CoupleBean>>() {
            @Override
            public void onChanged(List<CoupleBean> coupleBeans) {
                ArrayList<Fragment> list = new ArrayList<>();
                String[] titles = new String[coupleBeans.size()];

                for (int i = 0; i < coupleBeans.size(); i++) {
                    CoupleBean category = coupleBeans.get(i);

                    titles[i] = category.getName();
                    CoupleFragment homeItemFragment = new CoupleFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("id", category.getId() + "");
                    homeItemFragment.setArguments(bundle);
                    list.add(homeItemFragment);
                }
                if(coupleBeans.size()==1){
                    binding.coupleMytab.setVisibility(View.GONE);
                }else{
                    binding.coupleMytab.setVisibility(View.VISIBLE);
                }
                HomeAdapter adapter=new HomeAdapter(getSupportFragmentManager(),list,titles);
                binding.coupleViewpager.setAdapter(adapter);
                binding.coupleMytab.setViewPager(binding.coupleViewpager);
            }
        });
    }
}
