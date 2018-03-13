package com.xuefuwang.webofstudy.my.ui;

import android.os.Bundle;
import android.view.View;

import com.xuefuwang.webofstudy.R;
import com.xuefuwang.webofstudy.my.utils.SlideBackActivity;
import com.xuefuwang.webofstudy.other.utils.TitleTextView;


public class RechargeFragment extends SlideBackActivity {

    private TitleTextView titleTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_wallet_recharge);

        titleTextView= (TitleTextView) findViewById(R.id.ttview);

        initView();
    }
    private void initView(){

        //点击返回
    titleTextView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });

    }


}
