package com.dongdian.shenquan.ui.activity.modulelist;

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

public class ModuleListViewModel extends MyBaseViewModel {
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
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<Integer> classificationvisiab = new ObservableField<>(View.GONE);
    public ObservableField<Integer> sortvisiab = new ObservableField<>(View.GONE);
    public ObservableField<String> mall_id = new ObservableField<>();
    public ObservableField<String> sort = new ObservableField<>();
    public ObservableField<String> activity_type = new ObservableField<>();
    public ObservableField<String> targType = new ObservableField<>();
    public ObservableField<String> channel_type = new ObservableField<>();
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        public SingleLiveEvent<GoodsList> getData = new SingleLiveEvent<GoodsList>();

        public UIChangeObservable() {
        }
    }
    public ModuleListViewModel(@NonNull Application application) {
        super(application);
    }

    public void getList(int page){
        HashMap<String, Object> query = new HashMap<>();
        query.put("mall_id", mall_id.get());
        query.put("page", page);
        query.put("sort",sort.get());
        if(targType.get().equals("3")||targType.get().equals("4")){
        query.put("activity_type",activity_type.get());
        }else if(targType.get().equals("5")){
            query.put("activity_type",activity_type.get());
        }else if(targType.get().equals("6")){
            query.put("channel_type",channel_type.get());
        }
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).items(Utils.getHeader(getApplication()), query)
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
