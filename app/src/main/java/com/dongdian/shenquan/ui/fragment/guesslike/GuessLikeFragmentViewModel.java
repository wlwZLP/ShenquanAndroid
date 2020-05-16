package com.dongdian.shenquan.ui.fragment.guesslike;

import android.app.Application;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.goods.GoodsList;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.Imei;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.Utils;

import java.util.HashMap;

import androidx.annotation.NonNull;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.SPUtils;

public class GuessLikeFragmentViewModel extends MyBaseViewModel {
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        public SingleLiveEvent<GoodsList> getData = new SingleLiveEvent<GoodsList>();
        public UIChangeObservable() {
        }
    }
    public GuessLikeFragmentViewModel(@NonNull Application application) {
        super(application);
    }
    public void getList(int page){
        HashMap<String,Object> query = new HashMap<>();
        query.put("page",page);
        query.put("device_type","IMEI");
        query.put("device_value", Imei.getimei(getApplication())+"");

        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).guess_like(Utils.getHeader(getApplication()),query)
                ,this,new HttpInterFace<GoodsList>(){
                    @Override
                    public void success(BaseBean<GoodsList> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        if(loginBeanBaseBean.getData()!=null){
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
