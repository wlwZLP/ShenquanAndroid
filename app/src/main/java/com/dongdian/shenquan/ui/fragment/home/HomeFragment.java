package com.dongdian.shenquan.ui.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseFragment;
import com.dongdian.shenquan.bean.CategoriesBean;
import com.dongdian.shenquan.bean.GoodsHomeBean;
import com.dongdian.shenquan.bean.PopWindowBean;
import com.dongdian.shenquan.databinding.HomeFragmentBinding;
import com.dongdian.shenquan.ui.activity.album.AlbumActivity;
import com.dongdian.shenquan.ui.activity.goodsdetail.GoodsDetailActivity;
import com.dongdian.shenquan.ui.activity.login.LoginActivity;
import com.dongdian.shenquan.ui.activity.modulelist.ModuleListActivity;
import com.dongdian.shenquan.ui.activity.web.WebViewActivity;
import com.dongdian.shenquan.ui.adapter.HomeAdapter;
import com.dongdian.shenquan.ui.fragment.guesslike.GuessLikeFragment;
import com.dongdian.shenquan.ui.fragment.homeitem.HomeItemFragment;
import com.dongdian.shenquan.ui.fragment.recommended.RecommendedFragment;
import com.dongdian.shenquan.utils.GifUtil;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.dongdian.shenquan.utils.Utils;
import com.dongdian.shenquan.view.HomePopup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import me.goldze.mvvmhabit.utils.SPUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class HomeFragment extends MyBaseFragment<HomeFragmentBinding, HomeFragmentViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.home_fragment;
    }

    @Override
    public int initVariableId() {
        return BR.homefragmentviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.getCa();
        viewModel.popup();
        viewModel.suspend();
        binding.homeSuspendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoodsHomeBean.BannersBean value = viewModel.uc.suspend.getValue();
                if(value!=null){
                    Utils.bannerJump(HomeFragment.this,value.getTarget_type(),value);
                }
            }
        });
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<List<CategoriesBean>>() {
            @Override
            public void onChanged(List<CategoriesBean> categoriesBeans) {
                if(categoriesBeans==null){
                    ToastUtils.showShort("未获取到数据");
                    return;
                }
                ArrayList<Fragment> list = new ArrayList<>();
                String[] titles = new String[categoriesBeans.size()+2];
                list.add(new RecommendedFragment());
                titles[0]="推荐";
                list.add(new GuessLikeFragment());
                titles[1]="猜你喜欢";

                for (int i = 0; i < categoriesBeans.size(); i++) {
                    CategoriesBean category = categoriesBeans.get(i);

                    titles[i+2] = category.getName();
                    HomeItemFragment homeItemFragment = new HomeItemFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("id", category.getId() + "");
                    homeItemFragment.setArguments(bundle);
                    list.add(homeItemFragment);
                }
                HomeAdapter adapter=new HomeAdapter(getChildFragmentManager(),list,titles);
                binding.homeViewpager.setAdapter(adapter);
                binding.homeMytab.setViewPager(binding.homeViewpager);
                binding.homeViewpager.setOffscreenPageLimit(1);
            }
        });
        viewModel.uc.popup.observe(this, new Observer<PopWindowBean>() {
            @Override
            public void onChanged(PopWindowBean popWindowBean) {
                HomePopup homePopup = new HomePopup(getContext(), popWindowBean.getCover(), new HomePopup.ImageCallBack() {
                    @Override
                    public void onClick() {
                        int need_login = popWindowBean.getNeed_login();
                        if(need_login==1&& TextUtils.isEmpty(SPUtils.getInstance().getString("token"))){
                            startActivity(LoginActivity.class);
                            return;
                        }
                        int targType = popWindowBean.getTarget_type();
                        if(targType==1){
                            Bundle bundle = new Bundle();
                            bundle.putString("title",popWindowBean.getTarget().getTitle());
                            bundle.putString("url",popWindowBean.getTarget().getUrl() );
                            startActivity(WebViewActivity.class,bundle);

                        }else if(targType==2){
                            Bundle bundle = new Bundle();
                            bundle.putString("item_id", popWindowBean.getTarget().getItem_id());
                            bundle.putString("mall_id", popWindowBean.getTarget().getType() + "");
                            if (!TextUtils.isEmpty(popWindowBean.getTarget().getActivity_id())) {
                                bundle.putString("activity_id", popWindowBean.getTarget().getActivity_id());
                            }
                            startActivity(GoodsDetailActivity.class,bundle);

                        }else if(targType==3||targType==4||targType==5||targType==6){
                            Bundle bundle = new Bundle();
                            bundle.putInt("targType",targType);
                            bundle.putString("title",popWindowBean.getTitle()+"");
                            startActivity(ModuleListActivity.class,bundle);

                        }else if(targType==7){
                            Bundle bundle = new Bundle();
                            bundle.putInt("targType",popWindowBean.getId());
                            bundle.putString("title",popWindowBean.getTitle()+"");
                            startActivity(AlbumActivity.class,bundle);
                        }
                    }
                });
                homePopup.showAtLocation(binding.homeMytab, Gravity.CENTER,0,0);
            }
        });

        viewModel.uc.suspend.observe(this, new Observer<GoodsHomeBean.BannersBean>() {
            @Override
            public void onChanged(GoodsHomeBean.BannersBean bannersBean) {
                if(bannersBean.getCover().endsWith(".gif")){
                    GifUtil.loadImage(binding.homeSuspendImage,bannersBean.getCover());
                }else{
                    ShowImageUtils.showImageView(getContext(),bannersBean.getCover(),binding.homeSuspendImage);
                }
            }
        });


    }
}
