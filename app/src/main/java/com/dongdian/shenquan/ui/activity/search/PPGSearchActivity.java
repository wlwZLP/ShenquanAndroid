package com.dongdian.shenquan.ui.activity.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import me.goldze.mvvmhabit.utils.ToastUtils;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.bean.PPGSearchBean;
import com.dongdian.shenquan.bean.ppg.PPGHomeBean;
import com.dongdian.shenquan.databinding.ActivityPpgsearchBinding;
import com.dongdian.shenquan.databinding.ActivityPpgsearchHeaderBinding;
import com.dongdian.shenquan.inteface.PullListener;
import com.dongdian.shenquan.ui.activity.ppgdetail.PPGGoodsDetailActivity;
import com.dongdian.shenquan.ui.activity.ppgdetail.PPGGoodsDetailZhiChongActivity;
import com.dongdian.shenquan.ui.viewholder.PPGHomeHolder;
import com.dongdian.shenquan.ui.viewholder.PPGSearchGoodsHolder;
import com.dongdian.shenquan.ui.viewholder.PPGSearchHeaderHolder;
import com.dongdian.shenquan.utils.MyHandler;
import com.dongdian.shenquan.utils.Utils;
import com.dongdian.shenquan.view.SearchPreference;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

public class PPGSearchActivity extends MyBaseActivity<ActivityPpgsearchBinding, PPGSearchViewModel> {
    private int page = 1;
    private MyHandler handler = new MyHandler();

    RecyclerArrayAdapter adapter = new RecyclerArrayAdapter(this) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new PPGSearchGoodsHolder(parent);
        }
    };
    RecyclerArrayAdapter banneradapter = new RecyclerArrayAdapter(this) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new PPGSearchHeaderHolder(parent);
        }
    };
    private String keyword;
    private View header;
    private ActivityPpgsearchHeaderBinding headerBinding;


    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        header = getLayoutInflater().inflate(R.layout.activity_ppgsearch_header, null);
        headerBinding = DataBindingUtil.bind(header);
        return R.layout.activity_ppgsearch;
    }

    @Override
    public int initVariableId() {
        return BR.ppgsearchviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        Bundle extras = getIntent().getExtras();
        keyword = extras.getString("keyword");
        viewModel.keyword.set(keyword);
        initProduct();
        Utils.setGildLayoutNotScroll(this,2,headerBinding.ppgSearchHeaderRecycler
        ,null,banneradapter);


        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return header;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
    }

    //列表初始化
    private void initProduct() {
        Utils.initListView(this, binding.ppgSearchRecycler, new DividerDecoration(Color.parseColor("#f6f6f6"), 0), adapter, new PullListener() {
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
                        binding.ppgSearchRecycler.setRefreshing(false);
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

                PPGSearchBean.DataBean item = (PPGSearchBean.DataBean) adapter.getItem(position);
                int coupon_type = item.getCoupon_type();
                if (coupon_type == 1 || coupon_type == 3) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", item.getId() + "");
                    bundle.putString("mall_id", item.getMall_id() + "");
                    startActivity(PPGGoodsDetailZhiChongActivity.class, bundle);
                } else if (coupon_type == 2 || coupon_type == 4) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", item.getId() + "");
                    bundle.putString("mall_id", item.getMall_id() + "");
                    startActivity(PPGGoodsDetailActivity.class, bundle);
                } else {
                    ToastUtils.showShort("错误的跳转类型");
                }
            }
        });


        binding.ppgSearchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (null != keyEvent && KeyEvent.KEYCODE_ENTER == keyEvent.getKeyCode()) {
                    switch (keyEvent.getAction()) {
                        case KeyEvent.ACTION_UP:
                            page=1;
                            viewModel.getList(page);
                            return true;
                        default:
                            return true;
                    }
                }
                return false;
            }
        });

    }


    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<PPGSearchBean>() {
            @Override
            public void onChanged(PPGSearchBean ppgSearchBean) {
                if (page == 1) {
                    adapter.clear();
                }
                if (ppgSearchBean == null ||ppgSearchBean.getData()==null|| ppgSearchBean.getData().size() == 0) {
                    adapter.stopMore();
                } else {
                    adapter.addAll(ppgSearchBean.getData());
                    adapter.notifyDataSetChanged();
                    if(ppgSearchBean.isHas_more()){
                        page++;
                    }else{
                        adapter.stopMore();
                    }

                }



                banneradapter.clear();
                banneradapter.addAll(ppgSearchBean.getBrands());
                banneradapter.clear();

            }
        });
    }
}
