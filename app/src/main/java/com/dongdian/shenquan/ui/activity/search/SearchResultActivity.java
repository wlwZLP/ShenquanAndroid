package com.dongdian.shenquan.ui.activity.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.databinding.ActivitySearchResultBinding;
import com.dongdian.shenquan.ui.adapter.HomeAdapter;
import com.dongdian.shenquan.ui.fragment.homeitem.HomeItemFragment;
import com.dongdian.shenquan.ui.fragment.search.SearchResultFragment;
import com.dongdian.shenquan.utils.Utils;
import com.dongdian.shenquan.view.TKLPopupWindow;

import java.util.ArrayList;

public class SearchResultActivity extends MyBaseActivity<ActivitySearchResultBinding,SearchResultViewModel> {
    String[] titles = new String[]{"淘宝","天猫","京东","拼多多"};
    ArrayList<Fragment> list = new ArrayList<>();
    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_search_result;
    }

    @Override
    public int initVariableId() {
        return BR.searchresultviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        Bundle extras = getIntent().getExtras();
        String searchKey = extras.getString("key");
        String mall_id = extras.getString("mall_id");
        viewModel.searchKey.set(searchKey);
        SearchResultFragment taobaofragment = new SearchResultFragment();
        Bundle tbbundle = new Bundle();
        tbbundle.putString("mall_id","1");
        tbbundle.putString("searchKey",searchKey);
        taobaofragment.setArguments(tbbundle);
        list.add(taobaofragment);


        SearchResultFragment tmfragment = new SearchResultFragment();
        Bundle tmbundle = new Bundle();
        tmbundle.putString("mall_id","2");
        tmbundle.putString("searchKey",searchKey);
        tmfragment.setArguments(tmbundle);
        list.add(tmfragment);

        SearchResultFragment jdfragment = new SearchResultFragment();
        Bundle jdbundle = new Bundle();
        jdbundle.putString("mall_id","4");
        jdbundle.putString("searchKey",searchKey);
        jdfragment.setArguments(jdbundle);
        list.add(jdfragment);

        SearchResultFragment pddfragment = new SearchResultFragment();
        Bundle pddbundle = new Bundle();
        pddbundle.putString("mall_id","3");
        pddbundle.putString("searchKey",searchKey);
        pddfragment.setArguments(pddbundle);
        list.add(pddfragment);





        if(mall_id.equals("1")){
            binding.searchResultViewpager.setCurrentItem(0);
        }else if(mall_id.equals("2")){
            binding.searchResultViewpager.setCurrentItem(1);
        }else if(mall_id.equals("3")){
            binding.searchResultViewpager.setCurrentItem(3);
        }else if(mall_id.equals("4")){
            binding.searchResultViewpager.setCurrentItem(2);
        }else{
            binding.searchResultViewpager.setCurrentItem(0);
        }



        HomeAdapter adapter=new HomeAdapter(getSupportFragmentManager(),list,titles);
        binding.searchResultViewpager.setAdapter(adapter);
        binding.searchResultMytab.setViewPager(binding.searchResultViewpager);
    }
    private String copy;

    @Override
    protected void onResume() {
        super.onResume();
        copy = Utils.getCopy(ctx);
        if(!TextUtils.isEmpty(copy)){
            viewModel.deep_search(copy);
        }
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.openTKL.observe(this, new Observer<GoodsList.ItemsBean>() {
            @Override
            public void onChanged(GoodsList.ItemsBean itemsBean) {
                if(itemsBean==null){
                    TKLPopupWindow tklPopupWindow = new TKLPopupWindow(SearchResultActivity.this,2,null,copy);
                    tklPopupWindow.showAtLocation(binding.searchResultMytab, Gravity.CENTER,0,0);
                }else{
                    TKLPopupWindow tklPopupWindow = new TKLPopupWindow(SearchResultActivity.this,1,itemsBean,null);
                    tklPopupWindow.showAtLocation(binding.searchResultMytab,Gravity.CENTER,0,0);
                }
            }
        });
    }
}
