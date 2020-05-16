package com.dongdian.shenquan.ui.adapter;

import com.dongdian.shenquan.bean.TimeList;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {

    List<TimeList> timeLists;
    private List<Fragment> fragmentList;
    public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, List<TimeList> timeLists) {
        super(fm);
        this.fragmentList = fragmentList;
        this.timeLists=timeLists;
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }
    @Override
    public int getCount() {
        return fragmentList.size();
    }


}