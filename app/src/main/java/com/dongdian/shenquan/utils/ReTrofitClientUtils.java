package com.dongdian.shenquan.utils;

import android.content.Context;
import android.content.Intent;

import android.text.TextUtils;

import com.dongdian.shenquan.base.BaseBean;
import com.dongdian.shenquan.ui.activity.login.LoginActivity;


import java.lang.reflect.Field;

import androidx.fragment.app.Fragment;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.http.ResponseThrowable;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.SPUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * Created by Administrator on 2019/3/21.
 */

public class ReTrofitClientUtils {

    public static <T> void get(Observable baseBeanObservable, final BaseViewModel viewModel, final HttpInterFaceClass httpInterFace) {

        baseBeanObservable
                .compose(RxUtils.bindToLifecycle(viewModel.getLifecycleProvider())) //请求与View周期同步
                .compose(RxUtils.schedulersTransformer()) //线程调度
                .compose(RxUtils.exceptionTransformer()) // 网络错误的异常转换, 这里可以换成自己的ExceptionHandle
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        httpInterFace.startDialog();
                    }
                })
                .subscribe(new Consumer<BaseBean<T>>() {
                    @Override
                    public void accept(BaseBean<T> loginBeanBaseBean) throws Exception {
                        httpInterFace.dismissloading();
                        if (loginBeanBaseBean == null || loginBeanBaseBean.getCode() == null) {
                            httpInterFace.error(null);
                            return;
                        }
                        if (loginBeanBaseBean.getCode().equals("0")) {
                            httpInterFace.success(loginBeanBaseBean);
                        } else if (loginBeanBaseBean.getCode().equals("1001") || loginBeanBaseBean.getCode().equals("1003")|| loginBeanBaseBean.getCode().equals("1013")) {
                            Intent intent = new Intent(viewModel.getApplication().getBaseContext(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                            viewModel.getApplication().getBaseContext().startActivity(intent);
                        } else if (loginBeanBaseBean.getCode().equals("1688")) {
//                                T result = loginBeanBaseBean.getResult();
//                                Field sysids = result.getClass().getDeclaredField("tb_oauth_url");
//                                sysids.setAccessible(true);
//                                String o1 = (String) sysids.get(sysids);
//                                Intent intent = new Intent(viewModel.getApplication().getBaseContext(), ShouQuanActivity.class);
//                                intent.putExtra("tb_oauth_url",o1);
//                                viewModel.getApplication().getBaseContext().startActivity(intent);
                        } else if (loginBeanBaseBean.getCode().equals("1002")) {
                            ToastUtils.showShort("登录已过期，请重新登录");
                            Intent intent = new Intent(viewModel.getApplication().getBaseContext(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                            viewModel.getApplication().getBaseContext().startActivity(intent);
                        } else {
                            httpInterFace.error(null);
                            ToastUtils.showShort(loginBeanBaseBean.getMsg());
                        }

                    }
                }, new Consumer<ResponseThrowable>() {
                    @Override
                    public void accept(ResponseThrowable throwable) throws Exception {
                        ToastUtils.showShort(throwable.message);
                        httpInterFace.dismissloading();
                        httpInterFace.error(throwable);
                        throwable.printStackTrace();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        httpInterFace.dismissloading();
                        //请求刷新完成收回
                    }
                });
    }

    public static <T> void get(Observable baseBeanObservable, final Fragment lifecycle, final HttpInterFaceClass httpInterFace) {

        baseBeanObservable
                .compose(RxUtils.bindToLifecycle(lifecycle)) //请求与View周期同步
                .compose(RxUtils.schedulersTransformer()) //线程调度
                .compose(RxUtils.exceptionTransformer()) // 网络错误的异常转换, 这里可以换成自己的ExceptionHandle
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        httpInterFace.startDialog();
                    }
                })
                .subscribe(new Consumer<BaseBean<T>>() {
                    @Override
                    public void accept(BaseBean<T> loginBeanBaseBean) throws Exception {
                        httpInterFace.dismissloading();
                        if (loginBeanBaseBean == null || loginBeanBaseBean.getCode() == null) {
                            httpInterFace.error(null);
                            return;
                        }
                        if (loginBeanBaseBean.getCode().equals("0")) {
                            httpInterFace.success(loginBeanBaseBean);
                        } else if (loginBeanBaseBean.getCode().equals("1001") || loginBeanBaseBean.getCode().equals("1003")|| loginBeanBaseBean.getCode().equals("1013")) {
                            Intent intent = new Intent(lifecycle.getActivity(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                            lifecycle.getActivity().startActivity(intent);
                        } else if (loginBeanBaseBean.getCode().equals("1688")) {
//                                T result = loginBeanBaseBean.getResult();
//                                Field sysids = result.getClass().getDeclaredField("tb_oauth_url");
//                                sysids.setAccessible(true);
//                                String o1 = (String) sysids.get(sysids);
//                                Intent intent = new Intent(viewModel.getApplication().getBaseContext(), ShouQuanActivity.class);
//                                intent.putExtra("tb_oauth_url",o1);
//                                viewModel.getApplication().getBaseContext().startActivity(intent);
                        } else if (loginBeanBaseBean.getCode().equals("1002")) {
                            ToastUtils.showShort("登录已过期，请重新登录");
                            Intent intent = new Intent(lifecycle.getActivity(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                            lifecycle.getActivity().startActivity(intent);
                        } else {
                            httpInterFace.error(null);
                            ToastUtils.showShort(loginBeanBaseBean.getMsg());
                        }

                    }
                }, new Consumer<ResponseThrowable>() {
                    @Override
                    public void accept(ResponseThrowable throwable) throws Exception {
                        ToastUtils.showShort(throwable.message);
                        httpInterFace.dismissloading();
                        httpInterFace.error(throwable);
                        throwable.printStackTrace();

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        httpInterFace.dismissloading();
                        //请求刷新完成收回
                    }
                });
    }

    public static <T> void get(Observable baseBeanObservable, final Context lifecycle, final HttpInterFaceClass httpInterFace) {

        baseBeanObservable
                .compose(RxUtils.bindToLifecycle(lifecycle)) //请求与View周期同步
                .compose(RxUtils.schedulersTransformer()) //线程调度
                .compose(RxUtils.exceptionTransformer()) // 网络错误的异常转换, 这里可以换成自己的ExceptionHandle
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        httpInterFace.startDialog();
                    }
                })
                .subscribe(new Consumer<BaseBean<T>>() {
                    @Override
                    public void accept(BaseBean<T> loginBeanBaseBean) throws Exception {
                        httpInterFace.dismissloading();
                        if (loginBeanBaseBean == null || loginBeanBaseBean.getCode() == null) {
                            httpInterFace.error(null);
                            return;
                        }
                        if (loginBeanBaseBean.getCode().equals("0")) {
                            httpInterFace.success(loginBeanBaseBean);
                        } else if (loginBeanBaseBean.getCode().equals("1001") || loginBeanBaseBean.getCode().equals("1003")|| loginBeanBaseBean.getCode().equals("1013")) {
                            Intent intent = new Intent(lifecycle, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                            lifecycle.startActivity(intent);
                        } else if (loginBeanBaseBean.getCode().equals("1688")) {
//                                T result = loginBeanBaseBean.getResult();
//                                Field sysids = result.getClass().getDeclaredField("tb_oauth_url");
//                                sysids.setAccessible(true);
//                                String o1 = (String) sysids.get(sysids);
//                                Intent intent = new Intent(viewModel.getApplication().getBaseContext(), ShouQuanActivity.class);
//                                intent.putExtra("tb_oauth_url",o1);
//                                viewModel.getApplication().getBaseContext().startActivity(intent);
                        } else if (loginBeanBaseBean.getCode().equals("1002")) {
                            ToastUtils.showShort("登录已过期，请重新登录");
                            Intent intent = new Intent(lifecycle, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                            lifecycle.startActivity(intent);
                        } else {
                            httpInterFace.error(null);
                            ToastUtils.showShort(loginBeanBaseBean.getMsg());
                        }

                    }
                }, new Consumer<ResponseThrowable>() {
                    @Override
                    public void accept(ResponseThrowable throwable) throws Exception {
                        httpInterFace.dismissloading();
                        ToastUtils.showShort(throwable.message);
                        httpInterFace.error(throwable);
                        throwable.printStackTrace();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        httpInterFace.dismissloading();
                        //请求刷新完成收回
                    }
                });
    }
}
