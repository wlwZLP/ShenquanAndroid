package com.dongdian.shenquan.ui.activity.mycoupon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.databinding.ActivityMyCouponBinding;
import com.dongdian.shenquan.ui.adapter.MyCounponAdapter;
import com.dongdian.shenquan.ui.fragment.mycoupon.MyCouponFragment;

import java.util.ArrayList;

public class MyCouponActivity extends MyBaseActivity<ActivityMyCouponBinding,MyCouponViewModel> {



    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_my_coupon;
    }

    @Override
    public int initVariableId() {
        return BR.mycouponviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        ArrayList<Fragment> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MyCouponFragment myCouponFragment = new MyCouponFragment();
            Bundle bundle = new Bundle();
            bundle.putString("status", i+ "");
            myCouponFragment.setArguments(bundle);
            list.add(myCouponFragment);
        }
        String[] titles = new String[]{"全部","待支付","发货中","已成功","已失效"};
        MyCounponAdapter adapter=new MyCounponAdapter(getSupportFragmentManager(),list,titles);
        binding.myCouponViewpager.setAdapter(adapter);
        binding.myCouponMytab.setViewPager(binding.myCouponViewpager);


    }
}
