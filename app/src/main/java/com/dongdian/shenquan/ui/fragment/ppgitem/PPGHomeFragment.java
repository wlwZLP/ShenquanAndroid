package com.dongdian.shenquan.ui.fragment.ppgitem;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseFragment;
import com.dongdian.shenquan.bean.ppg.PPGCategoriesBean;
import com.dongdian.shenquan.bean.ppg.PPGHomeBean;
import com.dongdian.shenquan.databinding.PpgHomeFragmentBinding;
import com.dongdian.shenquan.databinding.PpgHomeFragmentHeaderBinding;
import com.dongdian.shenquan.inteface.PullListener;
import com.dongdian.shenquan.ui.activity.ppgdetail.PPGGoodsDetailActivity;
import com.dongdian.shenquan.ui.activity.ppgdetail.PPGGoodsDetailZhiChongActivity;
import com.dongdian.shenquan.ui.activity.ppggoodslist.PPGGoodsListActivity;
import com.dongdian.shenquan.ui.activity.ppgmorerights.PPGMoreRightsActivity;
import com.dongdian.shenquan.ui.viewholder.PPGHomeBrandsHolder;
import com.dongdian.shenquan.ui.viewholder.PPGHomeHolder;
import com.dongdian.shenquan.utils.MyHandler;
import com.dongdian.shenquan.utils.Utils;
import com.gcssloop.widget.PagerGridLayoutManager;
import com.gcssloop.widget.PagerGridSnapHelper;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class PPGHomeFragment extends MyBaseFragment<PpgHomeFragmentBinding, PPGHomeFragmentViewModel> {
    private int page = 1;
    private MyHandler handler = new MyHandler();

    RecyclerArrayAdapter adapter = new RecyclerArrayAdapter(getContext()) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new PPGHomeHolder(parent);
        }
    };
    RecyclerArrayAdapter brandsAdapter = new RecyclerArrayAdapter(getContext()) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new PPGHomeBrandsHolder(parent);
        }
    };
    private View header;
    private PpgHomeFragmentHeaderBinding headerBinding;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        header = inflater.inflate(R.layout.ppg_home_fragment_header, null);
        headerBinding = DataBindingUtil.bind(header);
        return R.layout.ppg_home_fragment;
    }

    @Override
    public int initVariableId() {
        return BR.ppghomefragmentviewmodel;
    }

    @Override
    public void initData() {
        super.initData();

        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return header;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
        initProduct();
    }

    //列表初始化
    private void initProduct() {
        Utils.initListView(getActivity(), binding.ppgHomeGoodsRecycler, new DividerDecoration(Color.parseColor("#f6f6f6"), 0), adapter, new PullListener() {
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
                        binding.ppgHomeGoodsRecycler.setRefreshing(false);
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

                PPGHomeBean item = (PPGHomeBean) adapter.getItem(position);
                int coupon_type = item.getCoupon_type();
                if(coupon_type==1||coupon_type==3){
                    Bundle bundle = new Bundle();
                    bundle.putString("id",item.getId()+"");
                    bundle.putString("mall_id",item.getMall_id()+"");
                    startActivity(PPGGoodsDetailZhiChongActivity.class,bundle);
                }else if(coupon_type==2||coupon_type==4){
                    Bundle bundle = new Bundle();
                    bundle.putString("id", item.getId() + "");
                    bundle.putString("mall_id", item.getMall_id() + "");
                    startActivity(PPGGoodsDetailActivity.class, bundle);
                }else{
                    ToastUtils.showShort("错误的跳转类型");
                }
            }
        });


        headerBinding.ppgHomeBrands.setAdapter(brandsAdapter);
        // 1.水平分页布局管理器
        PagerGridLayoutManager layoutManager = new PagerGridLayoutManager(
                3, 5, PagerGridLayoutManager.HORIZONTAL);

        headerBinding.ppgHomeBrands.setLayoutManager(layoutManager);
        // 2.设置滚动辅助工具

        PagerGridSnapHelper pageSnapHelper = new PagerGridSnapHelper();
        pageSnapHelper.attachToRecyclerView(headerBinding.ppgHomeBrands);
        viewModel.getCa();
        brandsAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                PPGCategoriesBean.BrandsBean.ContentsBeanX item = (PPGCategoriesBean.BrandsBean.ContentsBeanX) brandsAdapter.getItem(position);
                if (item.getName().equals("更多权益")) {
                   startActivity(PPGMoreRightsActivity.class);
                    return;
                }
                int brand_type = item.getBrand_type();
                if (brand_type == 1) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id",item.getId()+"");
                    startActivity(PPGGoodsDetailZhiChongActivity.class,bundle);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", item.getId() + "");
                    startActivity(PPGGoodsListActivity.class, bundle);
                }
            }
        });
        layoutManager.setChangeSelectInScrolling(true);
        layoutManager.setPageListener(new PagerGridLayoutManager.PageListener() {
            @Override
            public void onPageSizeChanged(int pageSize) {

            }

            @Override
            public void onPageSelect(int pageIndex) {
                int childCount = headerBinding.ppgHomeRadiogroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    ImageView childAt = (ImageView) headerBinding.ppgHomeRadiogroup.getChildAt(i);
                    if (i == pageIndex) {
                        childAt.setBackgroundResource(R.mipmap.zhuanzhong);
                    } else {
                        childAt.setBackgroundResource(R.mipmap.ppg_weixuanzhong);
                    }
                }
            }
        });
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<List<PPGHomeBean>>() {
            @Override
            public void onChanged(List<PPGHomeBean> goodsList) {
                if (page == 1) {
                    adapter.clear();
                }
                if (goodsList == null || goodsList.size() == 0) {
                    adapter.stopMore();
                } else {
                    adapter.addAll(goodsList);
                    adapter.notifyDataSetChanged();
                    page++;
                }

            }
        });
        viewModel.uc.getBrands.observe(this, new Observer<PPGCategoriesBean>() {
            @Override
            public void onChanged(PPGCategoriesBean ppgCategoriesBean) {

                List<List<PPGCategoriesBean.BrandsBean.ContentsBeanX>> split = Utils.split(ppgCategoriesBean.getBrands().getContents(), 14);
                for (int i = 0; i < split.size(); i++) {
                    PPGCategoriesBean.BrandsBean.ContentsBeanX contentsBeanX = new PPGCategoriesBean.BrandsBean.ContentsBeanX();
                    contentsBeanX.setName("更多权益");
                    contentsBeanX.setBrand_desc("超过200项");
                    split.get(i).add(contentsBeanX);
                    brandsAdapter.addAll(split.get(i));


                    ImageView mImageView = new ImageView(getContext());
                    int imageParams = (int) (getContext().getResources().getDisplayMetrics().density * 15 + 0.5f);// XP与DP转换，适应不同分辨率
                    int imagePadding = (int) (getContext().getResources().getDisplayMetrics().density * 3 + 0.5f);
                    LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams((int) imageParams, (int) imagePadding);
                    layout.setMargins(15, 0, 15, 0);
                    layout.gravity = Gravity.CENTER;
                    mImageView.setLayoutParams(layout);
                    //mImageView.setPadding(imagePadding, imagePadding, imagePadding, imagePadding);

                    if (i == 0) {
                        mImageView.setBackgroundResource(R.mipmap.zhuanzhong);
                    } else {
                        mImageView.setBackgroundResource(R.mipmap.ppg_weixuanzhong);
                    }
                    headerBinding.ppgHomeRadiogroup.addView(mImageView);
                }
                brandsAdapter.notifyDataSetChanged();
            }
        });
    }
}
