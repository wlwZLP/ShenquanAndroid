package com.dongdian.shenquan.ui.activity.album;

import android.app.Application;
import android.view.View;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.ui.activity.search.SearchActivity;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.Utils;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.SPUtils;

public class AlbumViewModel extends MyBaseViewModel {
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public BindingCommand search = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(SearchActivity.class);
        }
    });
    public ObservableField<String> id = new ObservableField<>();

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> sort = new ObservableField<>();
    public UIChangeObservable uc = new UIChangeObservable();
    public class UIChangeObservable {
        public SingleLiveEvent<GoodsList> getData = new SingleLiveEvent<GoodsList>();

        public UIChangeObservable() {
        }
    }
    public AlbumViewModel(@NonNull Application application) {
        super(application);
    }
    public void getList(int page){


        HashMap<String, Object> query = new HashMap<>();
        query.put("page", page);
        query.put("album_sort",sort.get());

        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).album(id.get(),Utils.getHeader(getApplication()), query)
                , this, new HttpInterFace<GoodsList>() {
                    @Override
                    public void success(BaseBean<GoodsList> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        if (loginBeanBaseBean.getData() != null) {
                            uc.getData.setValue(loginBeanBaseBean.getData());
                        }
                    }

                    @Override
                    public void startDialog() {
                        openloading();
                    }

                    @Override
                    public void dismissloading() {
                        closeloading();
                    }
                });
    }

}
