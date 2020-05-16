package com.dongdian.shenquan.ui.activity.ppgdetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.ViewGroup;

import com.alipay.sdk.app.PayTask;
import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.bean.ppg.PPGZhiChongBean;
import com.dongdian.shenquan.bean.ppg.PPGZhiChongTitleBean;
import com.dongdian.shenquan.databinding.ActivityPpggoodsDetailZhiChongBinding;
import com.dongdian.shenquan.ui.activity.membercenter.MembercenterActivity;
import com.dongdian.shenquan.ui.viewholder.PPGZhiChongChirdHolder;
import com.dongdian.shenquan.ui.viewholder.PPGZhiChongTypeHolder;
import com.dongdian.shenquan.utils.Utils;
import com.dongdian.shenquan.view.BuyVipPopup;
import com.dongdian.shenquan.view.EvenItemDecoration;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PPGGoodsDetailZhiChongActivity extends MyBaseActivity<ActivityPpggoodsDetailZhiChongBinding, PPGGoodsDetailZhiChongViewModel> {

    private RecyclerArrayAdapter parentAdapter = new RecyclerArrayAdapter(this) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new PPGZhiChongTypeHolder(parent);
        }
    };
    private RecyclerArrayAdapter childAdapter = new RecyclerArrayAdapter(this) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new PPGZhiChongChirdHolder(parent);
        }
    };
    private ArrayList<ArrayList<PPGZhiChongBean>> dataBean = new ArrayList<>();

    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_ppggoods_detail_zhi_chong;
    }

    @Override
    public int initVariableId() {
        return BR.ppggoodsdetailzhichongviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("id");
        String mall_id = extras.getString("mall_id");
        viewModel.id.set(id);
        viewModel.mall_id.set(mall_id);
        viewModel.getData();

        Utils.setGildLayoutNotScroll(this, 3, binding.ppggoodsDetailZhichongRecyclerType
                , new EvenItemDecoration(Utils.dp2px(this, 4), 3), parentAdapter);
        Utils.setGildLayoutNotScroll(this,3,binding.ppggoodsDetailZhichongRecyclerData
        ,new EvenItemDecoration(Utils.dp2px(this,4),3),childAdapter);
        parentAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ArrayList<PPGZhiChongTitleBean> allData = (ArrayList<PPGZhiChongTitleBean>) parentAdapter.getAllData();
                if(allData.get(position).isCheck()){
                   return;
               }
                for (int i = 0; i < allData.size(); i++) {
                    if(i==position){
                        allData.get(i).setCheck(true);
                    }else{
                        allData.get(i).setCheck(false);
                    }
                }
                parentAdapter.clear();
                parentAdapter.addAll(allData);
                parentAdapter.notifyDataSetChanged();

                ArrayList<PPGZhiChongBean> zhiChongBeans = dataBean.get(position);

                childAdapter.clear();
                childAdapter.addAll(zhiChongBeans);
                childAdapter.clear();

                for (int i = 0; i < zhiChongBeans.size(); i++) {
                    if(zhiChongBeans.get(i).isCheck()){
                        initViewModelData(zhiChongBeans.get(i));
                    }
                }
            }
        });
        childAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ArrayList<PPGZhiChongBean> allData = (ArrayList<PPGZhiChongBean>) childAdapter.getAllData();
                if(allData.get(position).isCheck()){
                    return;
                }
                for (int i = 0; i < allData.size(); i++) {
                    if(i==position){
                        allData.get(i).setCheck(true);
                        initViewModelData(allData.get(i));
                        viewModel.payId.set(allData.get(i).getId()+"");
                    }else{
                        allData.get(i).setCheck(false);
                    }
                }

                childAdapter.clear();
                childAdapter.addAll(allData);
                childAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<HashMap<PPGZhiChongTitleBean, ArrayList<PPGZhiChongBean>>>() {
            @Override
            public void onChanged(HashMap<PPGZhiChongTitleBean, ArrayList<PPGZhiChongBean>> ppgZhiChongTitleBeanArrayListHashMap) {
                ArrayList<PPGZhiChongTitleBean> arrayList = new ArrayList<>();

                for (Map.Entry<PPGZhiChongTitleBean, ArrayList<PPGZhiChongBean>> entry : ppgZhiChongTitleBeanArrayListHashMap.entrySet()) {
                    PPGZhiChongTitleBean key = entry.getKey();
                    ArrayList<PPGZhiChongBean> value = entry.getValue();
                    arrayList.add(key);
                    dataBean.add(value);

                }
                parentAdapter.addAll(arrayList);
                parentAdapter.notifyDataSetChanged();
                childAdapter.addAll(dataBean.get(0));
                viewModel.payId.set(dataBean.get(0).get(0).getId()+"");
                childAdapter.notifyDataSetChanged();
                initViewModelData(dataBean.get(0).get(0));
            }
        });
        viewModel.uc.openPopup.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                BuyVipPopup buyVipPopup = new BuyVipPopup(PPGGoodsDetailZhiChongActivity.this);
                buyVipPopup.setConfirmCallBack(new BuyVipPopup.ConfirmCallBack() {
                    @Override
                    public void confirm() {
                        startActivity(MembercenterActivity.class);
                        finish();
                    }
                });
                buyVipPopup.showAtLocation(binding.ppggoodsDetailZhichongHeader, Gravity.CENTER,0,0);
            }
        });
        viewModel.uc.pay.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                final Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(PPGGoodsDetailZhiChongActivity.this);
                        Map<String, String> result = alipay.payV2(s, true);
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

    public void initViewModelData(PPGZhiChongBean bean) {
        viewModel.help.set(bean.getHelp());
        viewModel.iconUrl.set(bean.getBrand_cover());
        viewModel.name.set(bean.getCoupon_name());
        viewModel.shuming.set(bean.getValidity());
    }

    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    //这里接收支付宝的回调信息
                    //需要注意的是，支付结果一定要调用自己的服务端来确定，不能通过支付宝的回调结果来判断
                    break;
                }
                default:
                    break;
            }
        };
    };
}
