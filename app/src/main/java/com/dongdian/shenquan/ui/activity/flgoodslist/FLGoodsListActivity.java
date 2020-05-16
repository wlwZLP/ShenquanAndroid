package com.dongdian.shenquan.ui.activity.flgoodslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.databinding.ActivityFlgoodsListBinding;
import com.dongdian.shenquan.inteface.PullListener;
import com.dongdian.shenquan.ui.activity.goodsdetail.GoodsDetailActivity;
import com.dongdian.shenquan.ui.viewholder.HomeItemHolder;
import com.dongdian.shenquan.ui.viewholder.PPGItemHolder;
import com.dongdian.shenquan.utils.MyHandler;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

public class FLGoodsListActivity extends MyBaseActivity<ActivityFlgoodsListBinding,FLGoodsListViewModel> {
    private String category_id;
    private String sort = "";
    private int page=1;
    private int xialiang=0;//0 表示不选  1  向下  2 向上
    private  int jiage =0;
    private MyHandler handler = new MyHandler();
    RecyclerArrayAdapter adapter = new RecyclerArrayAdapter(this) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new HomeItemHolder(parent);
        }
    };

    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_flgoods_list;
    }

    @Override
    public int initVariableId() {
        return BR.flgoodslistviewmodel;
    }
    @Override
    public void initData() {
        super.initData();
        Bundle arguments = getIntent().getExtras();
        category_id = arguments.getString("category_id");
        String title = arguments.getString("title");
        binding.flgoodsListRecycler.setEmptyView(R.layout.empty);
        binding.flgoodsListRecycler.setAdapter(adapter);
        viewModel.categoryId.set(category_id);
        viewModel.sort.set(sort);
        viewModel.title.set(title);
        initProduct();
    }
    //列表初始化
    private void initProduct() {

        Utils.initListView(this, binding.flgoodsListRecycler, new DividerDecoration(Color.parseColor("#dddddd"), 0), adapter, new PullListener() {
            @Override
            public void onRefresh() {
                page = 1;
                viewModel.getList(page);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.flgoodsListRecycler.setRefreshing(false);
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
                GoodsList.ItemsBean item = (GoodsList.ItemsBean) adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("item_id",item.getItem_id());
                bundle.putString("mall_id",item.getMall_id()+"");
                if(!TextUtils.isEmpty(item.getActivity_id())){
                    bundle.putString("activity_id",item.getActivity_id());
                }
                startActivity(GoodsDetailActivity.class,bundle);

            }
        });
        binding.flgoodsListTitleZongheLayou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.flgoodsListTitleZonghe.setTextColor(0xffFFD409);
                binding.flgoodsListTitleXiaoliangTitle.setTextColor(0xff666666);
                binding.flgoodsListTitleJiageTitle.setTextColor(0xff666666);
                binding.flgoodsListTitleXiaoliangShang.setImageResource(R.mipmap.ic_shang_iss);
                binding.flgoodsListTitleXiaoliangXia.setImageResource(R.mipmap.ic_xia_iss);
                binding.flgoodsListTitleJiageShang.setImageResource(R.mipmap.ic_shang_iss);
                binding.flgoodsListTitleJiageXia.setImageResource(R.mipmap.ic_xia_iss);
                page=1;
                sort="";
                xialiang=0;
                jiage=0;
                viewModel.sort.set(sort);
                viewModel.getList(page);

            }
        });

        binding.flgoodsListTitleXiaoliangLayou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.flgoodsListTitleZonghe.setTextColor(0xff666666);
                binding.flgoodsListTitleXiaoliangTitle.setTextColor(0xffFFD409);
                binding.flgoodsListTitleJiageTitle.setTextColor(0xff666666);
                if(xialiang==0||xialiang==2){
                    binding.flgoodsListTitleXiaoliangShang.setImageResource(R.mipmap.ic_shang_iss);
                    binding.flgoodsListTitleXiaoliangXia.setImageResource(R.mipmap.ic_xia_s);
                    sort="month_sales_des";
                    xialiang=1;
                }else{
                    binding.flgoodsListTitleXiaoliangShang.setImageResource(R.mipmap.ic_shang_s);
                    binding.flgoodsListTitleXiaoliangXia.setImageResource(R.mipmap.ic_xia_iss);
                    sort="month_sales_asc";
                    xialiang=2;
                }

                binding.flgoodsListTitleJiageShang.setImageResource(R.mipmap.ic_shang_iss);
                binding.flgoodsListTitleJiageXia.setImageResource(R.mipmap.ic_xia_iss);
                page=1;
                jiage=0;
                viewModel.sort.set(sort);
                viewModel.getList(page);
            }
        });
        binding.flgoodsListTitleJiageLayou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.flgoodsListTitleZonghe.setTextColor(0xff666666);
                binding.flgoodsListTitleXiaoliangTitle.setTextColor(0xff666666);
                binding.flgoodsListTitleJiageTitle.setTextColor(0xffFFD409);
                if(jiage==0||jiage==2){
                    binding.flgoodsListTitleJiageShang.setImageResource(R.mipmap.ic_shang_iss);
                    binding.flgoodsListTitleJiageXia.setImageResource(R.mipmap.ic_xia_s);
                    sort="discount_price_des";
                    jiage=1;
                }else{
                    binding.flgoodsListTitleJiageShang.setImageResource(R.mipmap.ic_shang_s);
                    binding.flgoodsListTitleJiageXia.setImageResource(R.mipmap.ic_xia_iss);
                    sort="discount_price_asc";
                    jiage=2;
                }
                binding.flgoodsListTitleXiaoliangShang.setImageResource(R.mipmap.ic_shang_iss);
                binding.flgoodsListTitleXiaoliangXia.setImageResource(R.mipmap.ic_xia_iss);
                page=1;
                xialiang=0;
                viewModel.sort.set(sort);
                viewModel.getList(page);
            }
        });


    }
    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<GoodsList>() {
            @Override
            public void onChanged(GoodsList goodsList) {
                if(page==1){
                    adapter.clear();
                }
                if(goodsList==null||goodsList.getItems()==null||goodsList.getItems().size()==0){
                    adapter.stopMore();
                }else{
                    adapter.addAll(goodsList.getItems());
                    adapter.notifyDataSetChanged();
                    if(!goodsList.isHas_next()){
                        adapter.stopMore();
                    }else{
                        page++;
                    }

                }

            }
        });
    }
}
