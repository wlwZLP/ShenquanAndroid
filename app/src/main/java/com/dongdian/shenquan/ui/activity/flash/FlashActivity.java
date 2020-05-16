package com.dongdian.shenquan.ui.activity.flash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.bean.TimeList;
import com.dongdian.shenquan.databinding.ActivityFlashBinding;
import com.dongdian.shenquan.ui.adapter.FragmentAdapter;
import com.dongdian.shenquan.ui.fragment.flash.FlashFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class FlashActivity extends MyBaseActivity<ActivityFlashBinding, FlashViewModel> {
    private List<Fragment> fragments = new ArrayList<>();
    private List<TimeList> timeLists = new ArrayList<>();
    private FragmentAdapter adapter;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_flash;
    }

    @Override
    public int initVariableId() {
        return BR.flashviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.getList();


    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<List<TimeList>>() {
            @Override
            public void onChanged(List<TimeList> timelists) {

                for (int i = 0; i < timelists.size(); i++) {
                    FlashFragment fragment = new FlashFragment();

                    Bundle bundle = new Bundle();
                    bundle.putString("time", timelists.get(i).getTime());

                    fragment.setArguments(bundle);

                    fragments.add(fragment);
                    timeLists.add(timelists.get(i));
                }

                adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, timeLists);
                binding.flashTablayout.setupWithViewPager(binding.flashViewpager);
                binding.flashViewpager.setAdapter(adapter);
                binding.flashViewpager.setOffscreenPageLimit(1);
                for (int i = 0; i < adapter.getCount(); i++) {
                    TabLayout.Tab tab = binding.flashTablayout.getTabAt(i);//获得每一个tab
                    tab.setCustomView(R.layout.xian_shi_top_item);//给每一个tab设置view
                    TextView textView = (TextView) tab.getCustomView().findViewById(R.id.xian_shi_top_item_time);
                    textView.setText(timeLists.get(i).getShow_time());//设置tab上的文字
                    TextView state = (TextView) tab.getCustomView().findViewById(R.id.xian_shi_top_item_state);
                    state.setText(timeLists.get(i).getDesc());//设置tab上的文字
                    LinearLayout autoLinearLayout = (LinearLayout) tab.getCustomView().findViewById(R.id.xian_shi_top_item_layout);
                    if (timeLists.get(i).getStatus() == 1) {
                        tab.select();
                        autoLinearLayout.setBackgroundResource(R.mipmap.bg_flash_tab);
                    } else {
                        autoLinearLayout.setBackground(null);
                    }
                }
                binding.flashTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        View customView = tab.getCustomView();
                        customView.setBackgroundResource(R.mipmap.bg_flash_tab);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        View customView = tab.getCustomView();
                        customView.setBackground(null);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });


            }
        });
    }
}
