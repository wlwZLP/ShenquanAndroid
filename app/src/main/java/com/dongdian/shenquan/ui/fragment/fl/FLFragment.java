package com.dongdian.shenquan.ui.fragment.fl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseFragment;
import com.dongdian.shenquan.bean.CategoriesBean;
import com.dongdian.shenquan.databinding.FlFragmentBinding;
import com.dongdian.shenquan.ui.viewholder.FLFragmentFirstHolder;
import com.dongdian.shenquan.ui.viewholder.FLFragmentSecondHolder;
import com.dongdian.shenquan.view.MyLinearLayoutManager;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

public class FLFragment extends MyBaseFragment<FlFragmentBinding, FLViewModel> {
    //第一list
    public RecyclerArrayAdapter firstAdapter = new RecyclerArrayAdapter<CategoriesBean>(getContext()) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new FLFragmentFirstHolder(parent);
        }
    };
    //第一list
    public RecyclerArrayAdapter secondAdapter = new RecyclerArrayAdapter<CategoriesBean>(getContext()) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new FLFragmentSecondHolder(parent);
        }
    };
    private List<List<CategoriesBean.ChildrenBeanX>> secondData = new ArrayList<>();
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fl_fragment;
    }

    @Override
    public int initVariableId() {
        return BR.flviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        binding.fLFragmentFirstRecycler.setEmptyView(R.layout.empty);
        binding.fLFragmentFirstRecycler.setAdapter(firstAdapter);
        LinearLayoutManager flashlinearLayoutManager = new LinearLayoutManager(getContext());
        binding.fLFragmentFirstRecycler.setLayoutManager(flashlinearLayoutManager);
        firstAdapter.setNotifyOnChange(false);


        binding.fLFragmentSecondRecycler.setEmptyView(R.layout.empty);
        binding.fLFragmentSecondRecycler.setAdapter(secondAdapter);
        MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(getContext());
        binding.fLFragmentSecondRecycler.setLayoutManager(myLinearLayoutManager);
        secondAdapter.setNotifyOnChange(false);



        viewModel.getCa();
        firstAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                List<CategoriesBean> allData = (List<CategoriesBean>)firstAdapter.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    CategoriesBean categoriesBean = allData.get(i);
                    if(i==position){
                        categoriesBean.setCheck(true);
                    }else{
                        categoriesBean.setCheck(false);
                    }

                }
                firstAdapter.clear();
                firstAdapter.addAll(allData);
                firstAdapter.notifyDataSetChanged();

                secondAdapter.clear();
                secondAdapter.addAll(secondData.get(position));
                secondAdapter.notifyDataSetChanged();



            }
        });
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<List<CategoriesBean>>() {
            @Override
            public void onChanged(List<CategoriesBean> categoriesBeans) {
                for (int i = 0; i < categoriesBeans.size(); i++) {
                    CategoriesBean categoriesBean = categoriesBeans.get(i);

                    if(i==0){
                    categoriesBean.setCheck(true);
                    }else{
                        categoriesBean.setCheck(false);
                    }
                    firstAdapter.add(categoriesBean);
                    secondData.add(categoriesBean.getChildren());
                }
                secondAdapter.addAll(secondData.get(0));
                secondAdapter.notifyDataSetChanged();
                firstAdapter.notifyDataSetChanged();
            }
        });
    }
}
