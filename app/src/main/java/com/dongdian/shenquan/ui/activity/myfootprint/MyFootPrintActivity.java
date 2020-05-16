package com.dongdian.shenquan.ui.activity.myfootprint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import me.goldze.mvvmhabit.utils.ToastUtils;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.bean.MyCollectListBean;
import com.dongdian.shenquan.databinding.ActivityMyFootPrintBinding;
import com.dongdian.shenquan.inteface.PullListener;
import com.dongdian.shenquan.ui.activity.goodsdetail.GoodsDetailActivity;
import com.dongdian.shenquan.ui.viewholder.MyCollectHolder;
import com.dongdian.shenquan.ui.viewholder.MyFootPrintHolder;
import com.dongdian.shenquan.utils.MyHandler;
import com.dongdian.shenquan.utils.Utils;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class MyFootPrintActivity extends MyBaseActivity<ActivityMyFootPrintBinding,MyFootPrintViewModel> {

    private int page=1;
    private MyHandler handler = new MyHandler();
    RecyclerArrayAdapter adapter = new RecyclerArrayAdapter(this) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyFootPrintHolder(parent);
        }
    };


    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_my_foot_print;
    }

    @Override
    public int initVariableId() {
        return BR.myfootprintviewmodel;
    }
    @Override
    public void initData() {
        super.initData();
        binding.myFootprintRecycler.setEmptyView(R.layout.empty);
        binding.myFootprintRecycler.setAdapter(adapter);
        initProduct();
    }

    private void initProduct() {
        Utils.initGridView(this,binding.myFootprintRecycler,3,new DividerDecoration(Color.parseColor("#f6f6f6"), 30),adapter,new PullListener(){

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.myFootprintRecycler.setRefreshing(false);
                        if (page == 1) {
                            return;
                        }
                        viewModel.getList(page);
                    }
                }, 100);
            }

            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //刷新
                        page=1;
                        viewModel.getList(page);
                    }
                }, 100);



            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
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
        viewModel.uc.getData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String myCollectListBean) {
                if(page==1){
                    adapter.clear();
                }
                Log.e("====","牛逼不"+myCollectListBean);
                try {
                    JSONObject jsonObject = new JSONObject(myCollectListBean);

                    JSONObject data = jsonObject.getJSONObject("data");
                    if(data==null){
                        return;
                    }
                    int total_page = jsonObject.optInt("total_page");


                    Iterator<String> keys = data.keys();
                    while (keys.hasNext()){
                        String next = keys.next();
                        JSONArray jsonArray = data.optJSONArray(next);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            String s = jsonArray.optString(i);
                            Gson gson = new Gson();
                            MyCollectListBean.DataBean dataBean = gson.fromJson(s, MyCollectListBean.DataBean.class);
                            adapter.add(dataBean);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    if(total_page==page){
                        ToastUtils.showShort("====");
                        adapter.stopMore();
                        return;
                    }
                    page++;

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }
}
