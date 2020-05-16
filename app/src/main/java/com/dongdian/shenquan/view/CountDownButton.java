package com.dongdian.shenquan.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

public class CountDownButton extends TextView {
    private static final int START_DOWN = 0;
    private int countDownMax = 60;
    private int countDown = countDownMax;
    private String normalText = "获取验证码";
    private boolean isUsing;



    public CountDownButton(Context context) {
        super(context);
        setTextColor(0xffFFD409);
    }

    public CountDownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTextColor(0xffFFD409);
    }

    public String getNormalText() {
        return normalText;
    }

    public void setNormalText(String normalText) {
        this.normalText = normalText;
    }

    public int getCountDownMax() {
        return countDownMax;
    }

    public void setCountDownMax(int countDownMax) {
        this.countDownMax = countDownMax;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case START_DOWN:
                    countDown = countDown - 1;
                    if (countDown > 0) {
                        setText(countDown + "秒");
                        mHandler.sendEmptyMessageDelayed(START_DOWN, 1000);
                    } else {
                        isUsing= false;
                        setText(normalText);
                        setTextColor(0xffFFD409);
                    }
                    break;

            }
        }
    };

    public void startCountDown() {
        if(isUsing)return;
        isUsing = true;
        countDown = countDownMax;
        mHandler.removeMessages(START_DOWN);
        setText(countDown + "秒");
        setTextColor(0xffFFD409);
        mHandler.sendEmptyMessageDelayed(START_DOWN, 1000);
    }

    public void stopCountDown() {
        isUsing = false;
        mHandler.removeMessages(START_DOWN);
        setText(normalText);
        countDown = countDownMax;
        setTextColor(0xff000000);
    }

    public void releaseCountDown() {
        countDown = countDownMax;
        mHandler.removeMessages(START_DOWN);
    }
}
