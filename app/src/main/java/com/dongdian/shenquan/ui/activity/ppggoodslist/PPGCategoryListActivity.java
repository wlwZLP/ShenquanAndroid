package com.dongdian.shenquan.ui.activity.ppggoodslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import me.goldze.mvvmhabit.utils.ToastUtils;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.bean.ppg.PPGItemGoodsListBean;
import com.dongdian.shenquan.databinding.ActivityPpgcategoryListBinding;
import com.dongdian.shenquan.inteface.PullListener;
import com.dongdian.shenquan.ui.activity.ppgdetail.PPGGoodsDetailActivity;
import com.dongdian.shenquan.ui.activity.ppgdetail.PPGGoodsDetailZhiChongActivity;
import com.dongdian.shenquan.ui.viewholder.PPGItemHolder;
import com.dongdian.shenquan.utils.MyHandler;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

public class PPGCategoryListActivity extends MyBaseActivity<ActivityPpgcategoryListBinding,PPGCategoryListViewModel> {
    private String id;
    private int page = 1;
    private MyHandler handler = new MyHandler();
    RecyclerArrayAdapter adapter = new RecyclerArrayAdapter(this) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new PPGItemHolder(parent);
        }
    };


    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_ppgcategory_list;
    }

    @Override
    public int initVariableId() {
        return BR.ppgcategorylistviewmodel;
    }
    @Override
    public void initData() {
        super.initData();
        Bundle arguments = getIntent().getExtras();
        id = arguments.getString("id");
        binding.ppgcategoryListRecycler.setEmptyView(R.layout.empty);
        binding.ppgcategoryListRecycler.setAdapter(adapter);
        viewModel.id.set(id);
        initProduct();
    }
    //列表初始化
    private void initProduct() {

        Utils.initListView(this, binding.ppgcategoryListRecycler, new DividerDecoration(Color.parseColor("#f6f6f6"), 0), adapter, new PullListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //刷新
                        page = 1;
                        viewModel.getList(page);
                    }
                }, 500);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.ppgcategoryListRecycler.setRefreshing(false);
                        if (page == 1) {
                            return;
                        }
                        viewModel.getList(page);
                    }
                }, 500);
            }
        }, new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                PPGItemGoodsListBean.DataBean item = (PPGItemGoodsListBean.DataBean) adapter.getItem(position);

                if(item.getCoupon_type()==1||item.getCoupon_type()==3){
                    Bundle bundle = new Bundle();
                    bundle.putString("id",item.getId()+"");
                    bundle.putString("mall_id",item.getMall_id()+"");
                    startActivity(PPGGoodsDetailZhiChongActivity.class,bundle);
                }else  if (item.getCoupon_type()==2||item.getCoupon_type()==4){
                    Bundle bundle = new Bundle();
                    bundle.putString("id",item.getId()+"");
                    bundle.putString("mall_id",item.getMall_id()+"");

                    startActivity(PPGGoodsDetailActivity.class,bundle);
                }else
                {
                    ToastUtils.showShort("非法跳转类型");
                }




            }
        });
    }
    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<PPGItemGoodsListBean>() {
            @Override
            public void onChanged(PPGItemGoodsListBean goodsList) {
                if (page == 1) {
                    adapter.clear();
                }
                if(goodsList==null||goodsList.getData()==null||goodsList.getData().size()==0){
                    adapter.stopMore();
                }else{
                    adapter.addAll(goodsList.getData());
                    adapter.notifyDataSetChanged();
                    if(!goodsList.isHas_more()){
                        adapter.stopMore();
                    }else{
                        page++;
                    }

                }


            }
        });
    }
}
