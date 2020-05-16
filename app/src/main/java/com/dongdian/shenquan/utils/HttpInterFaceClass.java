package com.dongdian.shenquan.utils;


import com.dongdian.shenquan.base.BaseBean;

/**
 * Created by Administrator on 2019/8/1.
 */

public interface HttpInterFaceClass<T> {
    void startDialog();

    void dismissloading();

    void noLogin();

    void success(BaseBean<T> loginBeanBaseBean);

    void error(Exception e);
}
