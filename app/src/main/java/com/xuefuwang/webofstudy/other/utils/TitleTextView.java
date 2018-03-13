package com.xuefuwang.webofstudy.other.utils;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuefuwang.webofstudy.R;

public class TitleTextView extends RelativeLayout {

    private TextView tvTitle,tvMsg;

    public TitleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //把自定义的布局作此控件的显示效果
        inflate(context, R.layout.title_main,this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.wallet_title);
        String title = typedArray.getString(R.styleable.wallet_title_wallet_title_tt);
        String msg = typedArray.getString(R.styleable.wallet_title_wallet_title_msg);

        tvTitle= (TextView) findViewById(R.id.title_tv);
        tvMsg= (TextView) findViewById(R.id.msg_tv);

        tvTitle.setText(title);
        tvMsg.setText(msg);

    }
    public void setTvTitle(String title){
        tvTitle.setText(title);
    }

    public void setTvMsg(String msg){
        tvTitle.setText(msg);
    }
}
