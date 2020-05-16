package com.dongdian.shenquan.base;


/*
 * 项目名:    BaseLib
 * 包名       com.zhon.baselib.mvpbase.baseImpl
 * 文件名:    BaseBean
 * 创建者:    ZJB
 * 创建时间:  2017/9/7 on 14:17
 * 描述:      请求结果基础bean；仅用于判断操作是否成功
 */
public class BaseBean<T> {

    private String code;
    private String msg;

    private T data;
    private boolean success;
    private float runtime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public float getRuntime() {
        return runtime;
    }

    public void setRuntime(float runtime) {
        this.runtime = runtime;
    }
}
