package com.xuefuwang.webofstudy.my.ui;

import android.os.Bundle;
import android.view.View;

import com.xuefuwang.webofstudy.R;
import com.xuefuwang.webofstudy.my.utils.SlideBackActivity;
import com.xuefuwang.webofstudy.other.utils.TitleTextView;


public class WithdrawActivity extends SlideBackActivity{
    private TitleTextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_withdraw);

        titleTextView= (TitleTextView) findViewById(R.id.withdraw_ttview);

        initView();
    }

    private void initView(){
        titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
