package com.dongdian.shenquan.ui.activity.modulelist;

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
import com.dongdian.shenquan.bean.CategoriesBean;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.databinding.ActivityModuleListBinding;
import com.dongdian.shenquan.inteface.PullListener;
import com.dongdian.shenquan.ui.activity.goodsdetail.GoodsDetailActivity;
import com.dongdian.shenquan.ui.viewholder.HomeItemHolder;
import com.dongdian.shenquan.utils.MyHandler;
import com.dongdian.shenquan.utils.Utils;

import com.google.android.material.tabs.TabLayout;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.ArrayList;

public class ModuleListActivity extends MyBaseActivity<ActivityModuleListBinding, ModuleListViewModel> {

    private ArrayList<CategoriesBean> jdcate = new ArrayList<>();
    private ArrayList<CategoriesBean> pddcate = new ArrayList<>();

    private int page=1;
    private String sort = "";
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
        return R.layout.activity_module_list;
    }

    @Override
    public int initVariableId() {
        return BR.moduleviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        Bundle extras = getIntent().getExtras();
        int targType = extras.getInt("targType");
        String title = extras.getString("title");
        viewModel.title.set(title);
        viewModel.sort.set(sort);
        viewModel.targType.set(targType+"");
        if (targType == 3 || targType == 4) {
            viewModel.classificationvisiab.set(View.GONE);
            viewModel.sortvisiab.set(View.VISIBLE);
            viewModel.mall_id.set("1");
            viewModel.activity_type.set(targType+"");
        } else if (targType == 5) {
            viewModel.classificationvisiab.set(View.VISIBLE);
            viewModel.sortvisiab.set(View.GONE);
            CategoriesBean categoriesBean1 = new CategoriesBean();
            categoriesBean1.setId(1);
            categoriesBean1.setName("推荐");
            pddcate.add(categoriesBean1);
            CategoriesBean categoriesBean2 = new CategoriesBean();
            categoriesBean2.setId(31);
            categoriesBean2.setName("今日爆款");
            pddcate.add(categoriesBean2);
            CategoriesBean categoriesBean3 = new CategoriesBean();
            categoriesBean3.setId(30);
            categoriesBean3.setName("1.9包邮");
            pddcate.add(categoriesBean3);
            CategoriesBean categoriesBean4 = new CategoriesBean();
            categoriesBean4.setId(32);
            categoriesBean4.setName("品牌清仓");
            pddcate.add(categoriesBean4);
            viewModel.mall_id.set("3");
            viewModel.activity_type.set("1");
        } else {
            viewModel.classificationvisiab.set(View.VISIBLE);
            viewModel.sortvisiab.set(View.VISIBLE);
            jdcate.clear();
            CategoriesBean categoriesBean1 = new CategoriesBean();
            categoriesBean1.setId(1);
            categoriesBean1.setName("推荐");
            jdcate.add(categoriesBean1);
            CategoriesBean categoriesBean2 = new CategoriesBean();
            categoriesBean2.setId(25);
            categoriesBean2.setName("京东超市");
            jdcate.add(categoriesBean2);
            CategoriesBean categoriesBean3 = new CategoriesBean();
            categoriesBean3.setId(10);
            categoriesBean3.setName("9.9专区");
            jdcate.add(categoriesBean3);
            CategoriesBean categoriesBean4 = new CategoriesBean();
            categoriesBean4.setId(34);
            categoriesBean4.setName("秒杀");
            jdcate.add(categoriesBean4);
            CategoriesBean categoriesBean5 = new CategoriesBean();
            categoriesBean5.setId(110);
            categoriesBean5.setName("京东自营");
            jdcate.add(categoriesBean5);
            CategoriesBean categoriesBean6 = new CategoriesBean();
            categoriesBean6.setId(24);
            categoriesBean6.setName("数码家电");
            jdcate.add(categoriesBean6);
            CategoriesBean categoriesBean7 = new CategoriesBean();
            categoriesBean7.setId(27);
            categoriesBean7.setName("家居日用");
            jdcate.add(categoriesBean7);
            CategoriesBean categoriesBean8 = new CategoriesBean();
            categoriesBean8.setId(26);
            categoriesBean8.setName("母婴玩具");
            jdcate.add(categoriesBean8);
            CategoriesBean categoriesBean9 = new CategoriesBean();
            categoriesBean9.setId(28);
            categoriesBean9.setName("美妆穿搭");
            jdcate.add(categoriesBean9);
            CategoriesBean categoriesBean10 = new CategoriesBean();
            categoriesBean10.setId(29);
            categoriesBean10.setName("医药保健");
            jdcate.add(categoriesBean10);
            viewModel.mall_id.set("4");
            viewModel.channel_type.set("1");
        }

        if (targType == 6) {
            binding.moduleListCommontab.setTabMode(TabLayout.MODE_SCROLLABLE);
            for (int i = 0; i < jdcate.size(); i++) {
                TabLayout.Tab tab1 = binding.moduleListCommontab.newTab();
                tab1.setText(jdcate.get(i).getName());
                binding.moduleListCommontab.addTab(tab1);
            }
        }else if(targType == 5){
            binding.moduleListCommontab.setTabMode(TabLayout.MODE_FIXED);
            for (int i = 0; i < pddcate.size(); i++) {
                TabLayout.Tab tab1 = binding.moduleListCommontab.newTab();
                tab1.setText(pddcate.get(i).getName());
                binding.moduleListCommontab.addTab(tab1);
            }
        }

        binding.moduleListCommontab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (targType == 6){
                    page=1;
                    viewModel.channel_type.set(jdcate.get(position).getId()+"");
                    viewModel.getList(page);
                }
                if (targType == 5){
                    page=1;
                    viewModel.activity_type.set(pddcate.get(position).getId()+"");
                    viewModel.getList(page);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        initProduct();

    }
    //列表初始化
    private void initProduct() {

        Utils.initListView(this, binding.moduleListRecycler, new DividerDecoration(Color.parseColor("#dddddd"), 0), adapter, new PullListener() {
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
                        binding.moduleListRecycler.setRefreshing(false);
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
        binding.moduleListTitleZongheLayou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.moduleListTitleZonghe.setTextColor(0xffFFD409);
                binding.moduleListTitleXiaoliangTitle.setTextColor(0xff666666);
                binding.moduleListTitleJiageTitle.setTextColor(0xff666666);
                binding.moduleListTitleXiaoliangShang.setImageResource(R.mipmap.ic_shang_iss);
                binding.moduleListTitleXiaoliangXia.setImageResource(R.mipmap.ic_xia_iss);
                binding.moduleListTitleJiageShang.setImageResource(R.mipmap.ic_shang_iss);
                binding.moduleListTitleJiageXia.setImageResource(R.mipmap.ic_xia_iss);
                page=1;
                sort="";
                xialiang=0;
                jiage=0;
                viewModel.sort.set(sort);
                viewModel.getList(page);

            }
        });

        binding.moduleListTitleXiaoliangLayou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.moduleListTitleZonghe.setTextColor(0xff666666);
                binding.moduleListTitleXiaoliangTitle.setTextColor(0xffFFD409);
                binding.moduleListTitleJiageTitle.setTextColor(0xff666666);
                if(xialiang==0||xialiang==2){
                    binding.moduleListTitleXiaoliangShang.setImageResource(R.mipmap.ic_shang_iss);
                    binding.moduleListTitleXiaoliangXia.setImageResource(R.mipmap.ic_xia_s);
                    sort="month_sales_des";
                    xialiang=1;
                }else{
                    binding.moduleListTitleXiaoliangShang.setImageResource(R.mipmap.ic_shang_s);
                    binding.moduleListTitleXiaoliangXia.setImageResource(R.mipmap.ic_xia_iss);
                    sort="month_sales_asc";
                    xialiang=2;
                }

                binding.moduleListTitleJiageShang.setImageResource(R.mipmap.ic_shang_iss);
                binding.moduleListTitleJiageXia.setImageResource(R.mipmap.ic_xia_iss);
                page=1;
                jiage=0;
                viewModel.sort.set(sort);
                viewModel.getList(page);
            }
        });
        binding.moduleListTitleJiageLayou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.moduleListTitleZonghe.setTextColor(0xff666666);
                binding.moduleListTitleXiaoliangTitle.setTextColor(0xff666666);
                binding.moduleListTitleJiageTitle.setTextColor(0xffFFD409);
                if(jiage==0||jiage==2){
                    binding.moduleListTitleJiageShang.setImageResource(R.mipmap.ic_shang_iss);
                    binding.moduleListTitleJiageXia.setImageResource(R.mipmap.ic_xia_s);
                    sort="discount_price_des";
                    jiage=1;
                }else{
                    binding.moduleListTitleJiageShang.setImageResource(R.mipmap.ic_shang_s);
                    binding.moduleListTitleJiageXia.setImageResource(R.mipmap.ic_xia_iss);
                    sort="discount_price_asc";
                    jiage=2;
                }
                binding.moduleListTitleXiaoliangShang.setImageResource(R.mipmap.ic_shang_iss);
                binding.moduleListTitleXiaoliangXia.setImageResource(R.mipmap.ic_xia_iss);
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
