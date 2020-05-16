package com.dongdian.shenquan.ui.activity.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import me.goldze.mvvmhabit.utils.ToastUtils;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dongdian.shenquan.BR;
import com.dongdian.shenquan.R;
import com.dongdian.shenquan.base.MyBaseActivity;
import com.dongdian.shenquan.bean.HotWordsBean;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.databinding.ActivitySearchBinding;
import com.dongdian.shenquan.ui.activity.goodsdetail.GoodsDetailActivity;
import com.dongdian.shenquan.utils.ShowImageUtils;
import com.dongdian.shenquan.utils.Utils;
import com.dongdian.shenquan.view.SearchPreference;
import com.dongdian.shenquan.view.TKLPopupWindow;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SearchActivity extends MyBaseActivity<ActivitySearchBinding, SearchViewModel> {
    public String mall_id = "1";
    public List<HotWordsBean.HotSearchWordsBean> data = new ArrayList<>();

    @Override
    public int initContentView(Bundle savedInstanceState) {
        setbarfull();
        statusBarLightMode();
        return R.layout.activity_search;
    }

    @Override
    public int initVariableId() {
        return BR.searchviewmodel;
    }

    TagAdapter adapter;

    @Override
    public void initData() {
        super.initData();
        adapter = new TagAdapter<HotWordsBean.HotSearchWordsBean>(data) {
            @Override
            public View getView(FlowLayout parent, int position, HotWordsBean.HotSearchWordsBean o) {
                View inflate = View.inflate(SearchActivity.this, R.layout.hot_word_bg, null);
                LinearLayout layout = (LinearLayout) inflate.findViewById(R.id.hot_word_bg_layout);
                ImageView imageView = (ImageView) inflate.findViewById(R.id.hot_word_bg_image);
                TextView textView = (TextView) inflate.findViewById(R.id.hot_word_bg_text);

                textView.setText(o.getTitle());
                if (!TextUtils.isEmpty(o.getCover())) {
                    imageView.setVisibility(View.VISIBLE);
                    ShowImageUtils.showImageView(SearchActivity.this, o.getCover(), imageView);
                } else {
                    imageView.setVisibility(View.GONE);
                }

                if (!TextUtils.isEmpty(o.getTitle_color())) {
                    textView.setTextColor(Color.parseColor(o.getTitle_color()));
                } else {
                    textView.setTextColor(Color.parseColor("#666666"));
                }
                GradientDrawable gd = new GradientDrawable();
                if (!TextUtils.isEmpty(o.getBg_color())) {
                    gd.setColor(Color.parseColor(o.getBg_color()));
                } else {
                    gd.setColor(Color.parseColor("#F6F6F6"));
                }

                gd.setCornerRadius(100);

                layout.setBackground(gd);

                return inflate;
            }
        };
        binding.searchLishiIdFlowlayout.setAdapter(adapter);
        binding.searchLishiIdFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {

                String s = data.get(position).getTitle();
                viewModel.searchKey.set(s);
                binding.searchTopEdit.setText(s);
                SearchPreference.getInstance(SearchActivity.this).setKey(binding.searchTopEdit.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putString("key", binding.searchTopEdit.getText().toString());
                bundle.putString("mall_id", mall_id);
                startActivity(SearchResultActivity.class, bundle);

                return true;
            }
        });
        binding.searchTopTitleTb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.searchTopTitleTb.setTextColor(0xffFFD409);
                binding.searchTopTitleTm.setTextColor(0xff666666);
                binding.searchTopTitleJd.setTextColor(0xff666666);
                binding.searchTopTitlePdd.setTextColor(0xff666666);
                mall_id = "1";
            }
        });
        binding.searchTopTitleTm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.searchTopTitleTb.setTextColor(0xff666666);
                binding.searchTopTitleTm.setTextColor(0xffFFD409);
                binding.searchTopTitleJd.setTextColor(0xff666666);
                binding.searchTopTitlePdd.setTextColor(0xff666666);
                mall_id = "2";
            }
        });
        binding.searchTopTitleJd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.searchTopTitleTb.setTextColor(0xff666666);
                binding.searchTopTitleTm.setTextColor(0xff666666);
                binding.searchTopTitleJd.setTextColor(0xffFFD409);
                binding.searchTopTitlePdd.setTextColor(0xff666666);
                mall_id = "4";
            }
        });
        binding.searchTopTitlePdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.searchTopTitleTb.setTextColor(0xff666666);
                binding.searchTopTitleTm.setTextColor(0xff666666);
                binding.searchTopTitleJd.setTextColor(0xff666666);
                binding.searchTopTitlePdd.setTextColor(0xffFFD409);
                mall_id = "3";
            }
        });
        binding.searchTopEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (null != keyEvent && KeyEvent.KEYCODE_ENTER == keyEvent.getKeyCode()) {
                    switch (keyEvent.getAction()) {
                        case KeyEvent.ACTION_UP:
                            SearchPreference.getInstance(SearchActivity.this).setKey(binding.searchTopEdit.getText().toString());
                            Bundle bundle = new Bundle();
                            bundle.putString("key", binding.searchTopEdit.getText().toString());
                            bundle.putString("mall_id", mall_id);
                            startActivity(SearchResultActivity.class, bundle);
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
    protected void onResume() {
        super.onResume();
        initText();
        viewModel.getData();
        copy = Utils.getCopy(ctx);
        if(!TextUtils.isEmpty(copy)){
            viewModel.deep_search(copy);
        }
    }
    private String copy;

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.getData.observe(this, new Observer<HotWordsBean>() {
            @Override
            public void onChanged(HotWordsBean hotWordsBean) {
                List<HotWordsBean.HotSearchWordsBean> hot_search_words = hotWordsBean.getHot_search_words();
                data.clear();
                data.addAll(hot_search_words);
                adapter.notifyDataChanged();
            }
        });
        viewModel.uc.openTKL.observe(this, new Observer<GoodsList.ItemsBean>() {
            @Override
            public void onChanged(GoodsList.ItemsBean itemsBean) {
                if(itemsBean==null){
                    TKLPopupWindow tklPopupWindow = new TKLPopupWindow(SearchActivity.this,2,null,copy);
                    tklPopupWindow.showAtLocation(binding.searchLishiFavinfoLayout, Gravity.CENTER,0,0);
                }else{
                    TKLPopupWindow tklPopupWindow = new TKLPopupWindow(SearchActivity.this,1,itemsBean,null);
                    tklPopupWindow.showAtLocation(binding.searchLishiFavinfoLayout,Gravity.CENTER,0,0);
                }
            }
        });
    }

    public void initText() {
        Set<String> set = SearchPreference.getInstance(this).getKeys();
        if (set != null && set.size() > 0) {
            viewModel.searchLiShiVisiable.set(View.VISIBLE);
            final List<String> list = new ArrayList<>(set);

            binding.searchLishiFavinfo.setAdapter(new TagAdapter<String>(list) {
                @Override
                public View getView(FlowLayout parent, int position, String o) {
                    View inflate = View.inflate(SearchActivity.this, R.layout.hot_word_bg, null);
                    LinearLayout layout = (LinearLayout) inflate.findViewById(R.id.hot_word_bg_layout);
                    ImageView imageView = (ImageView) inflate.findViewById(R.id.hot_word_bg_image);
                    TextView textView = (TextView) inflate.findViewById(R.id.hot_word_bg_text);
                    textView.setText(o);
                    textView.setTextColor(Color.parseColor("#666666"));
                    imageView.setVisibility(View.GONE);
                    GradientDrawable gd = new GradientDrawable();
                    gd.setColor(Color.parseColor("#F6F6F6"));
                    gd.setCornerRadius(100);

                    layout.setBackground(gd);
                    return inflate;
                }
            });

            binding.searchLishiFavinfo.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    String s = list.get(position);
                    viewModel.searchKey.set(s);
                    binding.searchTopEdit.setText(s);
                    binding.searchTopEdit.setSelection(s.length());
                    SearchPreference.getInstance(SearchActivity.this).setKey(binding.searchTopEdit.getText().toString());
                    Bundle bundle = new Bundle();
                    bundle.putString("key", binding.searchTopEdit.getText().toString());
                    bundle.putString("mall_id", mall_id);
                    startActivity(SearchResultActivity.class, bundle);
                    return true;
                }
            });
        } else {
            viewModel.searchLiShiVisiable.set(View.GONE);
        }
    }

}
