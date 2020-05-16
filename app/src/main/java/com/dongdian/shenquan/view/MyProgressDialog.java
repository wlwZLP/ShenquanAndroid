package com.dongdian.shenquan.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dongdian.shenquan.R;


/**
 * Created by Administrator on 2019/3/25.
 */

public class MyProgressDialog extends Dialog {
    private Context context;

    private TextView mMessageView;

//	private ImageView mImageView;

    private CharSequence mMessage;

//	private AnimationDrawable animationDrawable;

    public MyProgressDialog(Context context) {
        this(context, 0);
        this.context = context;
    }

    public MyProgressDialog(Context context, int theme) {
        super(context, R.style.DialogTheme);
        this.context = context;
        setCanceledOnTouchOutside(false);
        setCancelable(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_myprogressbar, null);
        mMessageView = (TextView) view.findViewById(R.id.myprogressdialog_title);
//		mImageView = (ImageView) view.findViewById(R.id.myprogressdialog_image);
//		animationDrawable = (AnimationDrawable) mImageView.getBackground();
        if (mMessage != null) {
            setMessage(mMessage);
        }
        setContentView(view);
    }

//
//	@Override
//	public void show() {
//		super.show();
//		if (animationDrawable != null) {
//			animationDrawable.start();
//		}
//	}
//
//	@Override
//	public void dismiss() {
//		super.dismiss();
//		if (animationDrawable != null) {
//			animationDrawable.stop();
//		}
//	}

    public void setMessage(CharSequence message) {
        if (message != null) {
            mMessageView.setText(message);
        } else {
            mMessageView.setText("加载中");
            mMessage = message;
        }
    }
}
