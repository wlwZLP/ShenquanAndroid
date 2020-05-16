package com.dongdian.shenquan.ui.fragment.me;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseFragment;
import com.dongdian.shenquan.databinding.MeFragmentBinding;
import com.dongdian.shenquan.view.UpdataPopup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import me.goldze.mvvmhabit.utils.SPUtils;

public class MeFragment extends MyBaseFragment<MeFragmentBinding,MeViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return R.layout.me_fragment;
    }

    @Override
    public int initVariableId() {
        return BR.mefragmentviewmodel;
    }

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void onResume() {
        super.onResume();
        if(TextUtils.isEmpty(SPUtils.getInstance().getString("token"))){
            viewModel.userName.set("");
            viewModel.userIcon.set("");
            viewModel.userInviteCode.set("");
            viewModel.balance_amount.set("");
            viewModel.today_pre_amount.set("");
            viewModel.this_month_pre_amount.set("");
            viewModel.total_pre_amount.set("");
            viewModel.invite_wechat.set("");
            viewModel.plus_level.set(View.GONE);
            viewModel.plus_type.set(View.GONE);
            viewModel.isLogin.set(View.VISIBLE);
            viewModel.isCopy.set(View.GONE);
            binding.meNowLogin.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
            binding.meNowLogin.getPaint().setAntiAlias(true);//抗锯齿
        }else{
            viewModel.isLogin.set(View.GONE);
            viewModel.isCopy.set(View.VISIBLE);
            viewModel.getdata();
        }

    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.openUpdataPopup.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                UpdataPopup updataPopup = new UpdataPopup(getActivity(),s);
                updataPopup.showAtLocation(binding.meCheckVersion, Gravity.CENTER,0,0);
            }
        });
    }
}
