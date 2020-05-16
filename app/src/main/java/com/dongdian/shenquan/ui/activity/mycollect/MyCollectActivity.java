package com.dongdian.shenquan.ui.activity.mycollect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.bean.MyCollectListBean;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.databinding.ActivityMyCollectBinding;
import com.dongdian.shenquan.inteface.PullListener;
import com.dongdian.shenquan.ui.activity.goodsdetail.GoodsDetailActivity;
import com.dongdian.shenquan.ui.viewholder.HomeItemHolder;
import com.dongdian.shenquan.ui.viewholder.MyCollectHolder;
import com.dongdian.shenquan.utils.MyHandler;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

public class MyCollectActivity extends MyBaseActivity<ActivityMyCollectBinding,MyCollectViewModel> {
    private int page=1;
    private MyHandler handler = new MyHandler();
    RecyclerArrayAdapter adapter = new RecyclerArrayAdapter(this) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyCollectHolder(parent);
        }
    };

    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_my_collect;
    }

    @Override
    public int initVariableId() {
        return BR.mycollectviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        binding.myCollectRecycler.setEmptyView(R.layout.empty);
        binding.myCollectRecycler.setAdapter(adapter);
        initProduct();
    }
    //列表初始化
    private void initProduct() {

        Utils.initListView(this, binding.myCollectRecycler, new DividerDecoration(Color.parseColor("#dddddd"), 0), adapter, new PullListener() {
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
                        binding.myCollectRecycler.setRefreshing(false);
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
                MyCollectListBean.DataBean dataBean = (MyCollectListBean.DataBean) adapter.getAllData().get(position);
                Bundle bundle = new Bundle();
                bundle.putString("item_id",dataBean.getItem_id());
                bundle.putString("mall_id",dataBean.getMall_id()+"");
                startActivity(GoodsDetailActivity.class,bundle);
            }
        });
    }
    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<MyCollectListBean>() {
            @Override
            public void onChanged(MyCollectListBean myCollectListBean) {
                if(page==1){
                    adapter.clear();
                }
                if(myCollectListBean==null||myCollectListBean.getData()==null||myCollectListBean.getData().size()==0){
                    adapter.stopMore();
                }else{
                    adapter.addAll(myCollectListBean.getData());
                    adapter.notifyDataSetChanged();
                    if(page==myCollectListBean.getTotal_page()){
                        adapter.stopMore();
                    }else{
                        page++;
                    }

                }

            }
        });
    }
}
