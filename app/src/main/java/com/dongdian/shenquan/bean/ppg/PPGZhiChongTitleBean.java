package com.dongdian.shenquan.bean.ppg;

public class PPGZhiChongTitleBean {
    private String name;
    private boolean isCheck;

    public PPGZhiChongTitleBean(String name, boolean isCheck) {
        this.name = name;
        this.isCheck = isCheck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
