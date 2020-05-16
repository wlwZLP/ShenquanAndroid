package com.dongdian.shenquan.ui.fragment.recommended;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseFragment;
import com.dongdian.shenquan.bean.GoodsHomeBean;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.databinding.RecommendedFragmentBinding;
import com.dongdian.shenquan.databinding.RecommendedFragmentHeaderBinding;
import com.dongdian.shenquan.inteface.PullListener;
import com.dongdian.shenquan.ui.activity.flash.FlashActivity;
import com.dongdian.shenquan.ui.activity.goodsdetail.GoodsDetailActivity;
import com.dongdian.shenquan.ui.activity.web.WebViewActivity;
import com.dongdian.shenquan.ui.viewholder.ChannelsHolder;
import com.dongdian.shenquan.ui.viewholder.FlashSaleHolder;
import com.dongdian.shenquan.ui.viewholder.HomeItemHolder;
import com.dongdian.shenquan.ui.viewholder.MiddlesHolder;
import com.dongdian.shenquan.ui.viewholder.ZonesHolder;
import com.dongdian.shenquan.utils.GridLayoutDivider;
import com.dongdian.shenquan.utils.MyHandler;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.dongdian.shenquan.utils.Utils;
import com.dongdian.shenquan.view.MyGridLayoutManager;
import com.dongdian.shenquan.view.MyLinearLayoutManager;
import com.dongdian.shenquan.view.circle.ADInfo;
import com.dongdian.shenquan.view.circle.ImageCycleView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class RecommendedFragment extends MyBaseFragment<RecommendedFragmentBinding,RecommendedFragmentViewModel> {

    private RecommendedFragmentHeaderBinding headerBinding;
    private View header;
    private int page=1;
    private MyHandler handler = new MyHandler();
    RecyclerArrayAdapter adapter = new RecyclerArrayAdapter(getContext()) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new HomeItemHolder(parent);
        }
    };
    //限时抢购适配器
    public RecyclerArrayAdapter flashAdapter = new RecyclerArrayAdapter(getContext()) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new FlashSaleHolder(parent);
        }
    };
    //middle适配器
    public RecyclerArrayAdapter middleAdapter = new RecyclerArrayAdapter(getContext()) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new MiddlesHolder(parent);
        }
    };
    //channel适配器
    public RecyclerArrayAdapter channelAdapter = new RecyclerArrayAdapter(getContext()) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new ChannelsHolder(parent);
        }
    };
    //zones适配器
    public RecyclerArrayAdapter zonesAdapter = new RecyclerArrayAdapter(getContext()) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new ZonesHolder(parent);
        }
    };
    //顶部banner
    private ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {

        @Override
        public void onImageClick(ADInfo info, int position, View imageView) {
            if (info.getBannerBean() != null) {

                Utils.bannerJump(RecommendedFragment.this,info.getBannerBean().getTarget_type(),info.getBannerBean());
            }
        }

        @Override
        public void displayImage(String imageURL, ImageView imageView) {
            // 使用Glide对图片进行加装！
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ShowImageUtils.showImageViewToCircle(getContext(),Utils.dp2px(getContext(),8),imageURL,imageView);
        }
    };
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        header = getActivity().getLayoutInflater().inflate(R.layout.recommended_fragment_header, null);
        headerBinding = DataBindingUtil.bind(header);
        return R.layout.recommended_fragment;
    }

    @Override
    public int initVariableId() {
        return BR.recommendviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        headerBinding.recommendedFragmentHeaderFlashSalesRecycler.setAdapter(flashAdapter);
        MyGridLayoutManager flashgridLayoutManager = new MyGridLayoutManager(getContext(), 1);
        flashgridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        flashgridLayoutManager.setSpanSizeLookup(flashAdapter.obtainGridSpanSizeLookUp(1));
        headerBinding.recommendedFragmentHeaderFlashSalesRecycler.setLayoutManager(flashgridLayoutManager);
        flashAdapter.setNotifyOnChange(false);//不加这句总是不显示，具体原因不太清楚（可能是不会主动调用notifyDataSetChanged，需要手动调用一下）
        flashAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                GoodsHomeBean.FlashsaleBean.DataBean item = (GoodsHomeBean.FlashsaleBean.DataBean) flashAdapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("item_id", item.getItem_id());
                bundle.putString("mall_id", item.getMall_id() + "");
                if (!TextUtils.isEmpty(item.getActivity_id())) {
                    bundle.putString("activity_id", item.getActivity_id());
                }
                startActivity(GoodsDetailActivity.class, bundle);
            }
        });
        Utils.setGildLayoutNotScroll(getContext(),2, headerBinding.recommendedFragmentHeaderZonesRecycler,null,zonesAdapter);
//        headerBinding.recommendedFragmentHeaderZonesRecycler.setAdapter(zonesAdapter);
//        MyGridLayoutManager zoneLayoutManager = new MyGridLayoutManager(getContext(), 2);
//        GridLayoutDivider dividerItemDecoration = new GridLayoutDivider(10, 0xffffffff);
//        headerBinding.recommendedFragmentHeaderZonesRecycler.addItemDecoration(dividerItemDecoration);
//        zoneLayoutManager.setSpanSizeLookup(zonesAdapter.obtainGridSpanSizeLookUp(2));
//        headerBinding.recommendedFragmentHeaderZonesRecycler.setLayoutManager(zoneLayoutManager);
//        zonesAdapter.setNotifyOnChange(false);//不加这句总是不显示，具体原因不太清楚（可能是不会主动调用notifyDataSetChanged，需要手动调用一下）
        zonesAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                GoodsHomeBean.BannersBean item = (GoodsHomeBean.BannersBean) zonesAdapter.getItem(position);
                Utils.bannerJump(RecommendedFragment.this,item.getTarget_type(),item);
            }
        });


        headerBinding.recommendedFragmentHeaderMiddlesRecycler.setAdapter(middleAdapter);
        MyLinearLayoutManager middleLayoutManager = new MyLinearLayoutManager(getContext());
        middleLayoutManager.setScrollEnabled(false);
        headerBinding.recommendedFragmentHeaderMiddlesRecycler.setLayoutManager(middleLayoutManager);
        middleAdapter.setNotifyOnChange(false);
        middleAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                GoodsHomeBean.BannersBean item = (GoodsHomeBean.BannersBean) middleAdapter.getItem(position);
                Utils.bannerJump(RecommendedFragment.this,item.getTarget_type(),item);
            }
        });




        headerBinding.recommendedFragmentHeaderChannelsRecycler.setAdapter(channelAdapter);
        MyGridLayoutManager gridLayoutManager = new MyGridLayoutManager(getContext(), 2);
        gridLayoutManager.setScrollEnabled(false);
        gridLayoutManager.setSpanSizeLookup(channelAdapter.obtainGridSpanSizeLookUp(2));
        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        headerBinding.recommendedFragmentHeaderChannelsRecycler.setLayoutManager(gridLayoutManager);
        channelAdapter.setNotifyOnChange(false);//不加这句总是不显示，具体原因不太清楚（可能是不会主动调用notifyDataSetChanged，需要手动调用一下）
        channelAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                GoodsHomeBean.BannersBean item = (GoodsHomeBean.BannersBean) channelAdapter.getItem(position);
                Utils.bannerJump(RecommendedFragment.this,item.getTarget_type(),item);
            }
        });



        binding.recommendedFragmentRecycler.setEmptyView(R.layout.empty);
        binding.recommendedFragmentRecycler.setAdapter(adapter);
        initProduct();
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return header;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });

    } //列表初始化

    @Override
    public void onResume() {
        super.onResume();
        RecyclerView.Adapter adapter = headerBinding.recommendedFragmentHeaderChannelsRecycler.getAdapter();
        if(adapter!=null){
            viewModel.getHeader();
        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        ToastUtils.showShort(hidden+"");
    }

    private void initProduct() {

        Utils.initListView(getActivity(), binding.recommendedFragmentRecycler, new DividerDecoration(Color.parseColor("#dddddd"), 0), adapter, new PullListener() {
            @Override
            public void onRefresh() {
                page = 1;
                viewModel.getList(page);
                viewModel.getHeader();
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.recommendedFragmentRecycler.setRefreshing(false);
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
                if(item.getType()==0||item.getType()==1){
                    Bundle bundle = new Bundle();
                    bundle.putString("item_id",item.getItem_id());
                    bundle.putString("mall_id",item.getMall_id()+"");
                    if(!TextUtils.isEmpty(item.getActivity_id())){
                        bundle.putString("activity_id",item.getActivity_id());
                    }
                    startActivity(GoodsDetailActivity.class,bundle);
                }else if(item.getType()==2){
                    Bundle bundle = new Bundle();
                    bundle.putString("title",item.getTarget().getTitle());
                    bundle.putString("url",item.getTarget().getUrl()+"");
                    startActivity(WebViewActivity.class,bundle);
                }
            }
        });
        headerBinding.recommendedFragmentHeaderFlashSalesTitleMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(FlashActivity.class);
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
        viewModel.uc.getheader.observe(this, new Observer<GoodsHomeBean>() {
            @Override
            public void onChanged(GoodsHomeBean goodsHomeBean) {
                List<GoodsHomeBean.FlashsaleBean> flash_sales = goodsHomeBean.getFlash_sales();
                if(flash_sales!=null&&flash_sales.size()>0){
                    headerBinding.recommendedFragmentHeaderFlashSalesLayout.setVisibility(View.VISIBLE);
                    for (int i = 0; i < flash_sales.size(); i++) {
                        if(flash_sales.get(i).getStatus()==1){
                            String time = flash_sales.get(i).getTime();
                            if(TextUtils.isEmpty(time)){
                                headerBinding.recommendedFragmentHeaderFlashSalesLayout.setVisibility(View.GONE);
                            }else{
                                headerBinding.recommendedFragmentHeaderFlashSalesLayout.setVisibility(View.VISIBLE);
                            }
                            long l = Utils.date2TimeStamp(time);
                            int hours = Utils.parseDate(l, "hours");
                            headerBinding.recommendedFragmentHeaderFlashSalesTimeFirst.setText(hours/10+"");
                            headerBinding.recommendedFragmentHeaderFlashSalesTimeSecond.setText(hours%10+"");
                            int minute = Utils.parseDate(l, "minute");
                            headerBinding.recommendedFragmentHeaderFlashSalesTimeThird.setText(minute/10+"");
                            headerBinding.recommendedFragmentHeaderFlashSalesTimeFourth.setText(minute%10+"");
                            int second = Utils.parseDate(l, "second");
                            headerBinding.recommendedFragmentHeaderFlashSalesTimeFifth.setText(second/10+"");
                            headerBinding.recommendedFragmentHeaderFlashSalesTimeSixth.setText(second%10+"");
                            flashAdapter.clear();
                            flashAdapter.addAll(flash_sales.get(i).getData());
                            flashAdapter.notifyDataSetChanged();
                            if(flash_sales.get(i).getData()==null||flash_sales.get(i).getData().size()==0){
                                headerBinding.recommendedFragmentHeaderFlashSalesLayout.setVisibility(View.GONE);
                            }
                        }
                    }

                }else{
                    headerBinding.recommendedFragmentHeaderFlashSalesLayout.setVisibility(View.GONE);
                }



                middleAdapter.clear();
                middleAdapter.addAll(goodsHomeBean.getMiddles());
                middleAdapter.notifyDataSetChanged();

                zonesAdapter.clear();
                zonesAdapter.addAll(goodsHomeBean.getZones());
                zonesAdapter.notifyDataSetChanged();



                channelAdapter.clear();
                channelAdapter.addAll(goodsHomeBean.getChannels());
                channelAdapter.notifyDataSetChanged();


                List<GoodsHomeBean.BannersBean> banners = goodsHomeBean.getBanners();

                if(banners!=null&&banners.size()>0){
                    headerBinding.recommendedFragmentHeaderImagecycle.setVisibility(View.VISIBLE);
                    ArrayList<ADInfo> infos = new ArrayList<ADInfo>();
                    for (GoodsHomeBean.BannersBean param : banners) {
                        ADInfo info = new ADInfo();
                        info.setUrl(param.getCover());
                        infos.add(info);
                        info.setBannerBean(param);
                        headerBinding.recommendedFragmentHeaderImagecycle.pushImageCycle();
                    }
                    headerBinding.recommendedFragmentHeaderImagecycle.setImageResources(infos, mAdCycleViewListener, Gravity.LEFT);
                }else{
                    headerBinding.recommendedFragmentHeaderImagecycle.setVisibility(View.GONE);
                }


            }
        });
    }
}