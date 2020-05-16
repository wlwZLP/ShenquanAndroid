package com.dongdian.shenquan.ui.adapter;

import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class HomeAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> mFagments;
    String[] mTitles;
    public HomeAdapter(@NonNull FragmentManager fm,ArrayList<Fragment> mFagments, String[] titles) {
        super(fm);
        this.mFagments=mFagments;
        this.mTitles = titles;
    }




    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }

    @Override
    public int getCount() {
        return mFagments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFagments.get(position);
    }
}
