package com.dongdian.shenquan.ui.activity.myorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.bean.MyOrderListBean;
import com.dongdian.shenquan.databinding.ActivityMyOrderListBinding;
import com.dongdian.shenquan.inteface.PullListener;
import com.dongdian.shenquan.ui.viewholder.MyOrderHolder;
import com.dongdian.shenquan.ui.viewholder.PPGItemHolder;
import com.dongdian.shenquan.utils.MyHandler;
import com.dongdian.shenquan.utils.Utils;
import com.dongdian.shenquan.view.PopWindow;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

public class MyOrderListActivity extends MyBaseActivity<ActivityMyOrderListBinding, MyOrderListViewModel> {
    private int page = 1;
    private String type;
    private String mode;
    private String status = "0";
    private MyHandler handler = new MyHandler();
    RecyclerArrayAdapter adapter = new RecyclerArrayAdapter(this) {

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyOrderHolder(parent);
        }
    };


    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_my_order_list;
    }

    @Override
    public int initVariableId() {
        return BR.myorderlistviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        Bundle arguments = getIntent().getExtras();
        type = arguments.getString("type");
        mode = arguments.getString("mode");
        if (type.equals("1")) {
            viewModel.title.set("淘宝订单");
        } else if (type.equals("2")) {
            viewModel.title.set("拼多多订单");
        } else if (type.equals("3")) {
            viewModel.title.set("京东订单");
        } else if (type.equals("4")) {
            viewModel.title.set("美团酒店");
        } else if (type.equals("5")) {
            viewModel.title.set("大众点评");
        } else if (type.equals("6")) {
            viewModel.title.set("美团商家券");
        }
        binding.myOrderListRecycler.setEmptyView(R.layout.empty);
        binding.myOrderListRecycler.setAdapter(adapter);
        viewModel.type.set(type);
        viewModel.mode.set(mode);
        viewModel.status.set(status);
        initProduct();
    }

    //列表初始化
    private void initProduct() {

        Utils.initListView(this, binding.myOrderListRecycler, new DividerDecoration(Color.parseColor("#f6f6f6"), 0), adapter, new PullListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //刷新
                        page = 1;
                        viewModel.getData(page + "");
                    }
                }, 500);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.myOrderListRecycler.setRefreshing(false);
                        if (page == 1) {
                            return;
                        }
                        viewModel.getData(page + "");
                    }
                }, 500);
            }
        }, new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });

        binding.myOrderListAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.myOrderListAll.setTextColor(0xffFFD409);
                binding.myOrderListDaijiesuan.setTextColor(0xff666666);
                binding.myOrderListYijiesuan.setTextColor(0xff666666);
                binding.myOrderListYishixiao.setTextColor(0xff666666);
                page = 1;
                status = "0";
                viewModel.status.set(status);
                viewModel.getData(page + "");
            }
        });

        binding.myOrderListDaijiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.myOrderListAll.setTextColor(0xff666666);
                binding.myOrderListDaijiesuan.setTextColor(0xffFFD409);
                binding.myOrderListYijiesuan.setTextColor(0xff666666);
                binding.myOrderListYishixiao.setTextColor(0xff666666);
                page = 1;
                status = "1";
                viewModel.status.set(status);
                viewModel.getData(page + "");
            }
        });
        binding.myOrderListYijiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.myOrderListAll.setTextColor(0xff666666);
                binding.myOrderListDaijiesuan.setTextColor(0xff666666);
                binding.myOrderListYijiesuan.setTextColor(0xffFFD409);
                binding.myOrderListYishixiao.setTextColor(0xff666666);
                page = 1;
                status = "2";
                viewModel.status.set(status);
                viewModel.getData(page + "");
            }
        });
        binding.myOrderListYishixiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.myOrderListAll.setTextColor(0xff666666);
                binding.myOrderListDaijiesuan.setTextColor(0xff666666);
                binding.myOrderListYijiesuan.setTextColor(0xff666666);
                binding.myOrderListYishixiao.setTextColor(0xffFFD409);
                page = 1;
                status = "3";
                viewModel.status.set(status);
                viewModel.getData(page + "");
            }
        });
        binding.myOrderListSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showpop();
            }
        });

    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<MyOrderListBean>() {
            @Override
            public void onChanged(MyOrderListBean goodsList) {
                if (page == 1) {
                    adapter.clear();
                }
                if (goodsList == null || goodsList.getData() == null || goodsList.getData().size() == 0) {
                    adapter.stopMore();
                } else {
                    adapter.addAll(goodsList.getData());
                    adapter.notifyDataSetChanged();
                    if (page == goodsList.getTotal_page()) {
                        adapter.stopMore();
                    }else{
                        page++;
                    }

                }


            }
        });
    }


    public void showpop() {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(getApplicationContext()).inflate(
                R.layout.popup_layout, null);
        // 设置按钮的点击事件
        final PopWindow popupWindow = new PopWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        TextView taobao = (TextView) contentView.findViewById(R.id.popup_dingdan);
        TextView pinduoduo = (TextView) contentView.findViewById(R.id.popup_pinduoduo);
        TextView jingdong = (TextView) contentView.findViewById(R.id.popup_jingdong);


        TextView meituanjiudian = (TextView) contentView.findViewById(R.id.popup_meituanjiudian);
        TextView dianping = (TextView) contentView.findViewById(R.id.popup_dianping);
        TextView shangjiaquan = (TextView) contentView.findViewById(R.id.popup_shangjiaquan);

        taobao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.myOrderListAll.setTextColor(0xffFFD409);
                binding.myOrderListDaijiesuan.setTextColor(0xff666666);
                binding.myOrderListYijiesuan.setTextColor(0xff666666);
                binding.myOrderListYishixiao.setTextColor(0xff666666);
                page = 1;
                status = "0";
                type = "1";
                viewModel.title.set("淘宝订单");
                viewModel.type.set(type);
                viewModel.status.set(status);
                viewModel.getData(page + "");
                popupWindow.dismiss();
            }
        });

        pinduoduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.myOrderListAll.setTextColor(0xffFFD409);
                binding.myOrderListDaijiesuan.setTextColor(0xff666666);
                binding.myOrderListYijiesuan.setTextColor(0xff666666);
                binding.myOrderListYishixiao.setTextColor(0xff666666);
                page = 1;
                status = "0";
                type = "2";
                viewModel.title.set(pinduoduo.getText().toString());
                viewModel.type.set(type);
                viewModel.status.set(status);
                viewModel.getData(page + "");
                popupWindow.dismiss();
            }
        });
        jingdong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.myOrderListAll.setTextColor(0xffFFD409);
                binding.myOrderListDaijiesuan.setTextColor(0xff666666);
                binding.myOrderListYijiesuan.setTextColor(0xff666666);
                binding.myOrderListYishixiao.setTextColor(0xff666666);
                page = 1;
                status = "0";
                type = "3";
                viewModel.title.set(jingdong.getText().toString());
                viewModel.type.set(type);
                viewModel.status.set(status);
                viewModel.getData(page + "");
                popupWindow.dismiss();
            }
        });
        meituanjiudian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.myOrderListAll.setTextColor(0xffFFD409);
                binding.myOrderListDaijiesuan.setTextColor(0xff666666);
                binding.myOrderListYijiesuan.setTextColor(0xff666666);
                binding.myOrderListYishixiao.setTextColor(0xff666666);
                page = 1;
                status = "0";
                type = "4";
                viewModel.title.set(meituanjiudian.getText().toString());
                viewModel.type.set(type);
                viewModel.status.set(status);
                viewModel.getData(page + "");
                popupWindow.dismiss();
            }
        });

        dianping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.myOrderListAll.setTextColor(0xffFFD409);
                binding.myOrderListDaijiesuan.setTextColor(0xff666666);
                binding.myOrderListYijiesuan.setTextColor(0xff666666);
                binding.myOrderListYishixiao.setTextColor(0xff666666);
                page = 1;
                status = "0";
                type = "5";
                viewModel.title.set(dianping.getText().toString());
                viewModel.type.set(type);
                viewModel.status.set(status);
                viewModel.getData(page + "");
                popupWindow.dismiss();
            }
        });
        shangjiaquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.myOrderListAll.setTextColor(0xffFFD409);
                binding.myOrderListDaijiesuan.setTextColor(0xff666666);
                binding.myOrderListYijiesuan.setTextColor(0xff666666);
                binding.myOrderListYishixiao.setTextColor(0xff666666);
                page = 1;
                status = "0";
                type = "6";
                viewModel.title.set(shangjiaquan.getText().toString());
                viewModel.type.set(type);
                viewModel.status.set(status);
                viewModel.getData(page + "");
                popupWindow.dismiss();
            }
        });


        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(new ColorDrawable(0000000000));
        // setBackgroundAlpha(0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // setBackgroundAlpha(1.0f);
            }
        });
        // 设置好参数之后再show
        popupWindow.showAsDropDown(binding.myOrderListSelect);
    }


}
