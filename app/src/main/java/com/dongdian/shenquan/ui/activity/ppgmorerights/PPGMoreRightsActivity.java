package com.dongdian.shenquan.ui.activity.ppgmorerights;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.bean.PPGMoreRightsBean;
import com.dongdian.shenquan.databinding.ActivityPpgmoreRightsBinding;
import com.dongdian.shenquan.ui.activity.ppgdetail.PPGGoodsDetailZhiChongActivity;
import com.dongdian.shenquan.ui.activity.ppggoodslist.PPGGoodsListActivity;
import com.dongdian.shenquan.ui.viewholder.PPGMoreRightsHolder;
import com.dongdian.shenquan.ui.viewholder.PPGMoreRightsItemHolder;
import com.dongdian.shenquan.utils.Utils;
import com.dongdian.shenquan.view.MyLinearLayoutManager;
import com.google.android.material.tabs.TabLayout;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class PPGMoreRightsActivity extends MyBaseActivity<ActivityPpgmoreRightsBinding,PPGMoreRightsViewModel> {

    RecyclerArrayAdapter adapter = new RecyclerArrayAdapter(this) {
        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new PPGMoreRightsHolder(parent);
        }


    };
    RecyclerArrayAdapter hotAdapter = new RecyclerArrayAdapter(this) {
        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new PPGMoreRightsItemHolder(parent);
        }


    };
    //判读是否是recyclerView主动引起的滑动，true- 是，false- 否，由tablayout引起的
    private boolean isRecyclerScroll;
    //记录上一次位置，防止在同一内容块里滑动 重复定位到tablayout
    private int lastPos;
    //用于recyclerView滑动到指定的位置
    private boolean canScroll;
    private int scrollToPosition;
    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_ppgmore_rights;
    }

    @Override
    public int initVariableId() {
        return BR.ppgmorerightsviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        Utils.setGildLayoutNotScroll(this,5,binding.ppgMoreRightsTopHotRecycler,null,hotAdapter);
        binding.ppgMoreRightsRecycler.setEmptyView(R.layout.empty);
        binding.ppgMoreRightsRecycler.setAdapter(adapter);
        MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(this);
        binding.ppgMoreRightsRecycler.setLayoutManager(myLinearLayoutManager);
        adapter.setNotifyOnChange(false);
        viewModel.getdata();
        binding.ppgMoreRightsRecycler.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //当滑动由recyclerView触发时，isRecyclerScroll 置true
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    isRecyclerScroll = true;
                }
                return false;
            }
        });
        binding.ppgMoreRightsRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isRecyclerScroll) {
                    //第一个可见的view的位置，即tablayou需定位的位置
                    int position = myLinearLayoutManager.findFirstVisibleItemPosition();
                    if (lastPos != position) {
                        binding.ppgMoreRightsCommontab.setScrollPosition(position, 0, true);
                    }
                    lastPos = position;
                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (canScroll) {
                    canScroll = false;
                    moveToPosition(myLinearLayoutManager, recyclerView, scrollToPosition);
                }
            }
        });
        binding.ppgMoreRightsCommontab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //点击标签，使recyclerView滑动，isRecyclerScroll置false
                int pos = tab.getPosition();
                isRecyclerScroll = false;
                moveToPosition(myLinearLayoutManager, binding.ppgMoreRightsRecycler.getRecyclerView(), pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        hotAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                List<PPGMoreRightsBean.BrandsBean> allData = hotAdapter.getAllData();
                PPGMoreRightsBean.BrandsBean brandsBean = allData.get(position);
                int brand_type = brandsBean.getBrand_type();
                if(brand_type==1){
                    Bundle bundle = new Bundle();
                    bundle.putString("id", brandsBean.getId() + "");
                    bundle.putString("mall_id",  "");
                    startActivity(PPGGoodsDetailZhiChongActivity.class,bundle);
                }else{
                    Bundle bundle = new Bundle();
                    bundle.putString("id", brandsBean.getId() + "");
                    bundle.putString("mall_id",  "");
                    startActivity(PPGGoodsListActivity.class,bundle);
                }

            }
        });


    }
    public void moveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int position) {
        // 第一个可见的view的位置
        int firstItem = manager.findFirstVisibleItemPosition();
        // 最后一个可见的view的位置
        int lastItem = manager.findLastVisibleItemPosition();
        if (position <= firstItem) {
            // 如果跳转位置firstItem 之前(滑出屏幕的情况)，就smoothScrollToPosition可以直接跳转，

            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            // 跳转位置在firstItem 之后，lastItem 之间（显示在当前屏幕），smoothScrollBy来滑动到指定位置
            int top = mRecyclerView.getChildAt(position - firstItem).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        } else {
            // 如果要跳转的位置在lastItem 之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用当前moveToPosition方法，执行上一个判断中的方法
            mRecyclerView.smoothScrollToPosition(position);
            scrollToPosition = position;
            canScroll = true;
        }
    }
    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<PPGMoreRightsBean>() {
            @Override
            public void onChanged(PPGMoreRightsBean ppgMoreRightsBean) {
                List<PPGMoreRightsBean.CouponBean> straight = ppgMoreRightsBean.getStraight();
                List<PPGMoreRightsBean.CouponBean> coupon = ppgMoreRightsBean.getCoupon();
                List<PPGMoreRightsBean.HotBean> hot = ppgMoreRightsBean.getHot();
                hotAdapter.addAll(hot.get(0).getBrands());
                hotAdapter.notifyDataSetChanged();

                adapter.addAll(straight);
                adapter.addAll(coupon);
                adapter.notifyDataSetChanged();
                List<PPGMoreRightsBean.CouponBean> allData = adapter.getAllData();
                binding.ppgMoreRightsCommontab.setTabMode(TabLayout.MODE_SCROLLABLE);
                for (int i = 0; i < allData.size(); i++) {
                    TabLayout.Tab tab1 = binding.ppgMoreRightsCommontab.newTab();
                    tab1.setText(allData.get(i).getName());
                    binding.ppgMoreRightsCommontab.addTab(tab1);
                }
            }
        });
    }
}
