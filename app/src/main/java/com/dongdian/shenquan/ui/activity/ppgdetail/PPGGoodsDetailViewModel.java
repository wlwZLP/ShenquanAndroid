package com.dongdian.shenquan.ui.activity.ppgdetail;

import android.app.Application;
import android.text.TextUtils;
import android.view.View;

import com.dongdian.shenquan.bean.PayBean;
import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.base.MyBaseViewModel;
import com.dongdian.shenquan.bean.ProductOrderBean;
import com.dongdian.shenquan.bean.ppg.PPGGoodsDetailBean;
import com.dongdian.shenquan.httppager.ApiService;
import com.dongdian.shenquan.httppager.RetrofitClient;
import com.dongdian.shenquan.ui.activity.customerservice.CustomerServiceActivity;
import com.dongdian.shenquan.ui.activity.login.LoginActivity;
import com.dongdian.shenquan.ui.activity.main.MainActivity;
import com.dongdian.shenquan.utils.Contants;
import com.dongdian.shenquan.utils.HttpInterFace;
import com.dongdian.shenquan.utils.ReTrofitClientUtils;
import com.dongdian.shenquan.utils.Utils;

import java.text.DecimalFormat;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.SPUtils;

public class PPGGoodsDetailViewModel extends MyBaseViewModel {
    public String id;
    public String mall_id;
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
                product_order_first();
            }
        }
    });


    public BindingCommand jian = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            String cou = count.get();
            int integer=Integer.valueOf(cou);

           if(integer==1){
               return;
           }
           integer--;
           count.set(integer+"");
        }
    });
    public BindingCommand jia = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            String cou = count.get();
            int integer=Integer.valueOf(cou);
            integer++;
            count.set(integer+"");
        }
    });
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<String> mall_icon = new ObservableField<>();
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> price = new ObservableField<>();
    public ObservableField<String> sheng = new ObservableField<>();
    public ObservableField<String> oldprice = new ObservableField<>();

    public ObservableField<String> youxiaoqi = new ObservableField<>();
    public ObservableField<String> help = new ObservableField<>();
    public ObservableField<String> count = new ObservableField<>("1");
    public ObservableField<Integer> shengVisiable = new ObservableField<>(View.GONE);

    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        public SingleLiveEvent<Boolean> openPopup = new SingleLiveEvent<>();
        public SingleLiveEvent<String> pay = new SingleLiveEvent<>();
        public UIChangeObservable() {
        }
    }




    public PPGGoodsDetailViewModel(@NonNull Application application) {
        super(application);
    }


    public void getData(){
        openloading();

        HashMap<String,Object> query = new HashMap<>();
        query.put("id",id);
        query.put("app_key",Contants.APPKEY);
        query.put("mall_id",mall_id);
        ReTrofitClientUtils.get(RetrofitClient.getInstance().create(ApiService.class).o_goods_info(Utils.getHeader(getApplication()),query)
        ,this,new HttpInterFace<PPGGoodsDetailBean>(){
                    @Override
                    public void success(BaseBean<PPGGoodsDetailBean> loginBeanBaseBean) {
                        closeloading();
                        super.success(loginBeanBaseBean);
                        mall_icon.set(loginBeanBaseBean.getData().getBrand_cover());
                        imageUrl.set(loginBeanBaseBean.getData().getCoupon_cover());
                        title.set(loginBeanBaseBean.getData().getCoupon_name());
                        String sale_count = loginBeanBaseBean.getData().getMember_price();
                        String face_price = loginBeanBaseBean.getData().getFace_price();


                        float v = Float.valueOf(face_price) - Float.valueOf(sale_count);
                        if(v>0){
                            sheng.set("省"+new DecimalFormat(".00").format(v)+"元");
                            shengVisiable.set(View.VISIBLE);
                        }else{
                            shengVisiable.set(View.GONE);
                        }

                        price.set("¥"+sale_count);
                        oldprice.set("官网价"+face_price);

                        youxiaoqi.set("有效期：" + loginBeanBaseBean.getData().getExpire());
                        help.set(loginBeanBaseBean.getData().getHelp());

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
        fileMap.put("id",id);
        fileMap.put("count",count.get());
        fileMap.put("type",2);
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
