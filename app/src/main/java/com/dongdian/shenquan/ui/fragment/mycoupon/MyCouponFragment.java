package com.dongdian.shenquan.ui.fragment.mycoupon;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alipay.sdk.app.PayTask;
import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.bean.PayBean;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseFragment;
import com.dongdian.shenquan.bean.coupon.CouponListBean;
import com.dongdian.shenquan.databinding.MyCouponFragmentBinding;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.inteface.PullListener;
import com.dongdian.shenquan.ui.activity.mycoupon.CouponDetailActivity;
import com.dongdian.shenquan.ui.viewholder.MyCouponItemHolder;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.MyHandler;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import me.goldze.mvvmhabit.utils.SPUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class MyCouponFragment extends MyBaseFragment<MyCouponFragmentBinding,MyCouponFragmentViewModel> {
    private String status;
    private int page=1;
    private MyHandler handler = new MyHandler();
    RecyclerArrayAdapter adapter = new RecyclerArrayAdapter(getContext()) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyCouponItemHolder(parent, new MyCouponItemHolder.CallBack() {
                @Override
                public void quxiao(String order_no) {
                   viewModel.cancel(order_no);
                }

                @Override
                public void look(String order_no) {
                    Bundle bundle = new Bundle();
                    bundle.putString("order_no",order_no);
                    startActivity(CouponDetailActivity.class,bundle);
                }

                @Override
                public void pay(String order_no) {
                    product_order_second(order_no);

                }
            });
        }
    };
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.my_coupon_fragment;
    }

    @Override
    public int initVariableId() {
        return BR.mycouponfragmentviewmodel;
    }
    @Override
    public void initData() {
        super.initData();
        Bundle arguments = getArguments();
        status = arguments.getString("status");
        binding.myCouponRecycler.setEmptyView(R.layout.empty);
        binding.myCouponRecycler.setAdapter(adapter);
        if(status.equals("0")){
            viewModel.status.set("");
        }else if(status.equals("1")){
            viewModel.status.set("0");
        }else if(status.equals("2")){
            viewModel.status.set("3");
        }else if(status.equals("3")){
            viewModel.status.set("2");
        }else{
            viewModel.status.set("-1");
        }
        initProduct();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initProduct() {

        Utils.initListView(getActivity(), binding.myCouponRecycler, new DividerDecoration(Color.parseColor("#dddddd"), 0), adapter, new PullListener() {
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
                        binding.myCouponRecycler.setRefreshing(false);
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
//                CouponListBean.DataBean item = (CouponListBean.DataBean) adapter.getItem(position);
//                if(item.getStatus()!=2){
//                    return;
//                }
//                String order_no = item.getOrder_no();
//                Bundle bundle = new Bundle();
//                bundle.putString("order_no",order_no);
//                startActivity(CouponDetailActivity.class,bundle);

            }
        });
    }
    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<CouponListBean>() {
            @Override
            public void onChanged(CouponListBean couponListBean) {
                if(page==1){
                    adapter.clear();
                }
                if(couponListBean==null||couponListBean.getData()==null||couponListBean.getData().size()==0){
                    adapter.stopMore();
                }else{
                    adapter.addAll(couponListBean.getData());
                    adapter.notifyDataSetChanged();
                    if(!couponListBean.isHas_more()){
                        adapter.stopMore();
                    }else{
                        page++;
                    }

                }

            }
        });
        viewModel.uc.isSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    ToastUtils.showShort("订单取消成功");
                    page=1;
                    viewModel.getList(page);
                }else{
                    ToastUtils.showShort("订单取消失败");
                }
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            page=1;
            viewModel.getList(page);
        }
    }

    private void product_order_second(String order_no){
        HashMap<String,Object> fileMap = new HashMap<>();
        fileMap.put("order_no",order_no);
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).product_order_second(Utils.getHeader(getContext()),fileMap)
                ,this,new HttpInterFace<PayBean>(){
                    @Override
                    public void success(BaseBean<PayBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        //uc.pay.setValue("");
                        final Runnable payRunnable = new Runnable() {

                            @Override
                            public void run() {
                                PayTask alipay = new PayTask(getActivity());
                                Map<String, String> result = alipay.payV2(loginBeanBaseBean.getData().getPay_params(), true);
                                Message msg = new Message();
                                msg.what = 1;
                                msg.obj = result;
                                mHandler.sendMessage(msg);
                            }
                        };
                        // 必须异步调用
                        Thread payThread = new Thread(payRunnable);
                        payThread.start();

                    }
                });
    }




    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {

                    page=1;
                    viewModel.getList(page);
                    break;
                }
                default:
                    break;
            }
        };
    };

}
