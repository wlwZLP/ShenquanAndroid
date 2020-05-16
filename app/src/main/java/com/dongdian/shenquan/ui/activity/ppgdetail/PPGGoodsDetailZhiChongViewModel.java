package com.dongdian.shenquan.ui.activity.ppgdetail;

import android.app.Application;
import android.text.TextUtils;

import com.dongdian.shenquan.bean.PayBean;
import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.ProductOrderBean;
import com.dongdian.shenquan.bean.ppg.PPGZhiChongBean;
import com.dongdian.shenquan.bean.ppg.PPGZhiChongTitleBean;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.ui.activity.customerservice.CustomerServiceActivity;
import com.dongdian.shenquan.ui.activity.login.LoginActivity;
import com.dongdian.shenquan.ui.activity.main.MainActivity;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.Utils;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.SPUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class PPGGoodsDetailZhiChongViewModel extends MyBaseViewModel {
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    public BindingCommand go_home = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(MainActivity.class);
            finish();
        }
    });
    public BindingCommand go_custom_service = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(CustomerServiceActivity.class);
            finish();
        }
    });
    public BindingCommand buy = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(TextUtils.isEmpty(SPUtils.getInstance().getString("token"))){
                startActivity(LoginActivity.class);
                return;
            }
            int plus_level = SPUtils.getInstance().getInt("plus_level");
            if (plus_level == 0) {
                uc.openPopup.setValue(true);
            } else {
                if(TextUtils.isEmpty(phone.get())){
                    ToastUtils.showShort("请输入您要充值的手机号");
                    return;
                }
                product_order_first();
            }
        }
    });
    public ObservableField<String> id = new ObservableField<>();
    public ObservableField<String> mall_id = new ObservableField<>();
    public ObservableField<String> iconUrl = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> phone = new ObservableField<>();

    public ObservableField<String> shuming = new ObservableField<>();
    public ObservableField<String> help = new ObservableField<>();
    public ObservableField<String> payId = new ObservableField<>();
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        public SingleLiveEvent<HashMap<PPGZhiChongTitleBean, ArrayList<PPGZhiChongBean>>> getData = new SingleLiveEvent<HashMap<PPGZhiChongTitleBean, ArrayList<PPGZhiChongBean>>>();
        public SingleLiveEvent<Boolean> openPopup = new SingleLiveEvent<>();
        public SingleLiveEvent<String> pay = new SingleLiveEvent<>();
        public UIChangeObservable() {
        }
    }


    public PPGGoodsDetailZhiChongViewModel(@NonNull Application application) {
        super(application);
    }

    public void getData() {
        String s = mall_id.get();
        if(!TextUtils.isEmpty(s)&&!s.equals("0")){
            getMallIdData();
        }else{
            getNoMallIdData();
        }
    }


    public void getMallIdData(){
        HashMap<PPGZhiChongTitleBean, ArrayList<PPGZhiChongBean>> myDataBean = new HashMap<>();

        openloading();
        HashMap<String, Object> query = new HashMap<>();
        query.put("id", id.get());
        query.put("mall_id",mall_id.get());
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).o_goods_info_second(Utils.getHeader(getApplication()), query)
                , this, new HttpInterFace() {
                    @Override
                    public void success(BaseBean loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        Gson gson = new Gson();
                        String s = gson.toJson(loginBeanBaseBean);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            JSONObject data = jsonObject.getJSONObject("data");
                            Iterator<String> keys = data.keys();
                            int position = 0;
                            while (keys.hasNext()) {
                                ArrayList<PPGZhiChongBean> zhiChongBeans = new ArrayList<>();
                                String next = keys.next();
                                JSONArray jsonArray = data.optJSONArray(next);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    String zhichongbeandata = jsonArray.optString(i);
                                    Gson zhichongbeanjson = new Gson();
                                    PPGZhiChongBean ppgZhiChongBean = zhichongbeanjson.fromJson(zhichongbeandata, PPGZhiChongBean.class);
                                    if(i==0){
                                        ppgZhiChongBean.setCheck(true);
                                    }else{
                                        ppgZhiChongBean.setCheck(false);
                                    }
                                    zhiChongBeans.add(ppgZhiChongBean);
                                }
                                if(position==0){
                                    myDataBean.put(new PPGZhiChongTitleBean(next,true), zhiChongBeans);
                                }else{
                                    myDataBean.put(new PPGZhiChongTitleBean(next,false), zhiChongBeans);
                                }
                                position++;
                            }
                            uc.getData.setValue(myDataBean);
                            closeloading();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            closeloading();
                        }
                    }

                    @Override
                    public void error(Exception e) {
                        super.error(e);
                        closeloading();
                    }
                });
    }
    public void getNoMallIdData(){
        HashMap<PPGZhiChongTitleBean, ArrayList<PPGZhiChongBean>> myDataBean = new HashMap<>();
        openloading();
        HashMap<String, Object> query = new HashMap<>();
        query.put("bid", id.get());
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).brand_products_second(Utils.getHeader(getApplication()), query)
                , this, new HttpInterFace() {
                    @Override
                    public void success(BaseBean loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        Gson gson = new Gson();
                        String s = gson.toJson(loginBeanBaseBean);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            JSONObject data = jsonObject.getJSONObject("data");
                            Iterator<String> keys = data.keys();
                            int position = 0;
                            while (keys.hasNext()) {
                                ArrayList<PPGZhiChongBean> zhiChongBeans = new ArrayList<>();
                                String next = keys.next();
                                JSONArray jsonArray = data.optJSONArray(next);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    String zhichongbeandata = jsonArray.optString(i);
                                    Gson zhichongbeanjson = new Gson();
                                    PPGZhiChongBean ppgZhiChongBean = zhichongbeanjson.fromJson(zhichongbeandata, PPGZhiChongBean.class);
                                    if(i==0){
                                        ppgZhiChongBean.setCheck(true);
                                    }else{
                                        ppgZhiChongBean.setCheck(false);
                                    }
                                    zhiChongBeans.add(ppgZhiChongBean);
                                }
                                if(position==0){
                                    myDataBean.put(new PPGZhiChongTitleBean(next,true), zhiChongBeans);
                                }else{
                                    myDataBean.put(new PPGZhiChongTitleBean(next,false), zhiChongBeans);
                                }
                                position++;
                            }
                            uc.getData.setValue(myDataBean);
                            closeloading();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            closeloading();
                        }
                    }

                    @Override
                    public void error(Exception e) {
                        super.error(e);
                        closeloading();
                    }
                });
    }


    private void product_order_first(){
        HashMap<String,Object> fileMap = new HashMap<>();
        fileMap.put("id",payId.get());
        fileMap.put("count",1);
        fileMap.put("type",2);
        fileMap.put("recharge_number",phone.get());
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).product_order_first(Utils.getHeader(getApplication()),fileMap)
                ,this,new HttpInterFace<ProductOrderBean>(){
                    @Override
                    public void success(BaseBean<ProductOrderBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        product_order_second(loginBeanBaseBean.getData().getOrder_no());
                    }
                });
    }

    private void product_order_second(String order_no){
        HashMap<String,Object> fileMap = new HashMap<>();
        fileMap.put("order_no",order_no);
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).product_order_second(Utils.getHeader(getApplication()),fileMap)
                ,this,new HttpInterFace<PayBean>(){
                    @Override
                    public void success(BaseBean<PayBean> loginBeanBaseBean) {
                        super.success(loginBeanBaseBean);
                        uc.pay.setValue(loginBeanBaseBean.getData().getPay_params());
                    }
                });
    }

}
