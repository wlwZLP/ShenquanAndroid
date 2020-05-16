package com.dongdian.shenquan.ui.activity.membercenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import me.crosswall.lib.coverflow.CoverFlow;
import me.goldze.mvvmhabit.utils.SPUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alipay.sdk.app.PayTask;
import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.bean.CommonBean;
import com.dongdian.shenquan.bean.InviteBean;
import com.dongdian.shenquan.databinding.ActivityMembercenterBinding;
import com.dongdian.shenquan.ui.activity.addfriend.AddFriendActivity;
import com.dongdian.shenquan.ui.activity.ppgdetail.PPGGoodsDetailActivity;
import com.dongdian.shenquan.utils.ShowImageUtils;

import java.util.List;
import java.util.Map;

public class MembercenterActivity extends MyBaseActivity<ActivityMembercenterBinding,MembercenterViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_membercenter;
    }

    @Override
    public int initVariableId() {
        return BR.membercenterviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.getcommon();
        viewModel.memberpage();
    }

    @Override
    protected void onResume() {
        super.onResume();
       viewModel.getdata();

    }
    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<CommonBean>() {
            @Override
            public void onChanged(CommonBean ommonBean) {

                ViewPager pager = binding.memberCenterPagerContainer.getViewPager();
                pager.setAdapter(new MyPagerAdapter(ommonBean.getPlus_benefit_poster()));
                pager.setClipChildren(false);
                pager.setOffscreenPageLimit(15);
                new CoverFlow.Builder().with(pager).scale(0.3f).spaceSize(0f).build();

            }
        });  viewModel.uc.pay.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                final Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(MembercenterActivity.this);
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

    private class MyPagerAdapter extends PagerAdapter {

        List<String> list;

        public MyPagerAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view = LayoutInflater.from(ctx).inflate(R.layout.item_cover, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image_cover);
            ShowImageUtils.showImageView(ctx,list.get(position),imageView);

            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
    }

}
