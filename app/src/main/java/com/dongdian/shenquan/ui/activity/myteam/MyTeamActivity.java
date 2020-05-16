package com.dongdian.shenquan.ui.activity.myteam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.bean.TeamListBean;
import com.dongdian.shenquan.bean.coupon.CouponListBean;
import com.dongdian.shenquan.databinding.ActivityMyTeamBinding;
import com.dongdian.shenquan.inteface.PullListener;
import com.dongdian.shenquan.ui.activity.mycoupon.CouponDetailActivity;
import com.dongdian.shenquan.ui.viewholder.MyTeamHodler;
import com.dongdian.shenquan.utils.MyHandler;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

public class MyTeamActivity extends MyBaseActivity<ActivityMyTeamBinding,MyTeamViewModel> {
    private String level="1";
    private int page=1;
    private String sort="team_num_des";
    private boolean guimo = true;
    private boolean registerTime = false;
    private MyHandler handler = new MyHandler();
    RecyclerArrayAdapter adapter = new RecyclerArrayAdapter(this) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyTeamHodler(parent);
        }
    };
    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_my_team;
    }

    @Override
    public int initVariableId() {
        return BR.myteamviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        Bundle extras = getIntent().getExtras();
        String parent = extras.getString("parent");
        binding.myTeamRecycler.setEmptyView(R.layout.empty);
        binding.myTeamRecycler.setAdapter(adapter);
        viewModel.leval.set(Integer.valueOf(level));
        if(!TextUtils.isEmpty(parent)){
            viewModel.parentName.set("我的邀请人"+parent);
        }

        initProduct();
        viewModel.getList(parent+"",level,sort);
    }
    private void initProduct() {

        Utils.initListView(this, binding.myTeamRecycler, new DividerDecoration(Color.parseColor("#dddddd"), 0), adapter, new PullListener() {
            @Override
            public void onRefresh() {
                binding.myTeamRecycler.setRefreshing(false);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.myTeamRecycler.setRefreshing(false);
                        if (page == 1) {
                            return;
                        }
                        viewModel.getList(page+"",level,sort);
                    }
                }, 500);
            }
        }, new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

        binding.myTeamGuimoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(guimo){
                    binding.myTeamGuimoShang.setImageResource(R.mipmap.ic_shang_s);
                    binding.myTeamGuimoXia.setImageResource(R.mipmap.ic_xia_iss);
                    binding.myTeamTimeShang.setImageResource(R.mipmap.ic_shang_iss);
                    binding.myTeamTimeXia.setImageResource(R.mipmap.ic_xia_iss);
                    sort="team_num_asc";
                    page=1;
                    viewModel.getList(page+"",level,sort);
                    guimo=false;
                }else{
                    guimo=true;
                    binding.myTeamGuimoShang.setImageResource(R.mipmap.ic_shang_iss);
                    binding.myTeamGuimoXia.setImageResource(R.mipmap.ic_xia_s);
                    binding.myTeamTimeShang.setImageResource(R.mipmap.ic_shang_iss);
                    binding.myTeamTimeXia.setImageResource(R.mipmap.ic_xia_iss);
                    sort="team_num_des";
                    page=1;
                    viewModel.getList(page+"",level,sort);
                }
            }
        });

        binding.myTeamTimeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(registerTime){
                    binding.myTeamGuimoShang.setImageResource(R.mipmap.ic_shang_iss);
                    binding.myTeamGuimoXia.setImageResource(R.mipmap.ic_xia_iss);
                    binding.myTeamTimeShang.setImageResource(R.mipmap.ic_shang_s);
                    binding.myTeamTimeXia.setImageResource(R.mipmap.ic_xia_iss);
                    sort="register_time_asc";
                    page=1;
                    viewModel.getList(page+"",level,sort);
                    registerTime=false;
                }else{
                    binding.myTeamGuimoShang.setImageResource(R.mipmap.ic_shang_iss);
                    binding.myTeamGuimoXia.setImageResource(R.mipmap.ic_xia_iss);
                    binding.myTeamTimeShang.setImageResource(R.mipmap.ic_shang_iss);
                    binding.myTeamTimeXia.setImageResource(R.mipmap.ic_xia_s);
                    sort="register_time_des";
                    page=1;
                    viewModel.getList(page+"",level,sort);
                    registerTime=true;
                }
            }
        });
        binding.myTeamTitleZhiyao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(level.equals("1")){
                    return;
                }
                viewModel.hint.set("特指直接通过您分享的链接或二维码注册成功的人");
                binding.myTeamTitleZhiyao.setTextColor(0xffFFD409);
                binding.myTeamTitleQita.setTextColor(0xff222222);
                page=1;
                level="1";
                viewModel.leval.set(Integer.valueOf(level));
                viewModel.getList(page+"",level,sort);
            }
        });
        binding.myTeamTitleQita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(level.equals("2")){
                    return;
                }
                viewModel.hint.set("泛指通过您的直邀下级分享的链接或二维码注册成功的人");
                binding.myTeamTitleZhiyao.setTextColor(0xff222222);
                binding.myTeamTitleQita.setTextColor(0xffFFD409);
                page=1;
                level="2";
                viewModel.leval.set(Integer.valueOf(level));
                viewModel.getList(page+"",level,sort);
            }
        });

    }


    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<TeamListBean>() {
            @Override
            public void onChanged(TeamListBean teamListBean) {
                if(page==1){
                    adapter.clear();
                }
                if(teamListBean==null||teamListBean.getData()==null||teamListBean.getData().size()==0){
                    adapter.stopMore();
                }else{
                    adapter.addAll(teamListBean.getData());
                    adapter.notifyDataSetChanged();
                    if(teamListBean.getTotal_page()==page){
                        adapter.stopMore();
                    }else{
                        page++;
                    }

                }

            }
        });
    }
}
