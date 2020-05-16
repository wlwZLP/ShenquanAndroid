package com.dongdian.shenquan.ui.activity.addfriend;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.PagerContainer;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.bean.InviteBean;
import com.dongdian.shenquan.databinding.ActivityAddFriendBinding;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.dongdian.shenquan.utils.Utils;

import java.util.List;
import java.util.UUID;

public class AddFriendActivity extends MyBaseActivity<ActivityAddFriendBinding,AddFriendViewModel> {



    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_add_friend;
    }

    @Override
    public int initVariableId() {
        return BR.addfriendviewmodel;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.getData();
        binding.addFriendSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bean!=null&&bean.getPosters()!=null&&bean.getPosters().size()>0){
                    Glide.with(AddFriendActivity.this)
                            .asBitmap()
                            .load(bean.getPosters().get(index))
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                    Utils.saveBmp2Gallery(AddFriendActivity.this,resource, UUID.randomUUID().toString() + ".jpg");
                                }
                            });
                }
            }
        });
    }
    int index = 0;
    InviteBean bean;
    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<InviteBean>() {
            @Override
            public void onChanged(InviteBean inviteBean) {
                bean=inviteBean;
                ViewPager pager = binding.addFriendPagerContainer.getViewPager();
                pager.setAdapter(new MyPagerAdapter(inviteBean.getPosters()));
                pager.setClipChildren(false);
                pager.setOffscreenPageLimit(15);
                new CoverFlow.Builder().with(pager).scale(0.3f).spaceSize(0f).build();
                pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        index = position;
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
            }
        });
    }
    private class MyPagerAdapter extends PagerAdapter {

        List<String> list;

        public MyPagerAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view = LayoutInflater.from(ctx).inflate(R.layout.item_cover, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image_cover);
            ShowImageUtils.showImageView(AddFriendActivity.this,list.get(position),imageView);

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
