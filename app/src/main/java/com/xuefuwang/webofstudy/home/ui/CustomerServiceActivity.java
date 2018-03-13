package com.xuefuwang.webofstudy.home.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuefuwang.webofstudy.R;

/**
 * Created by Administrator on 2016/4/4.
 */
public class CustomerServiceActivity extends Activity {


    private ImageView iv;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.actiivty_service);



        initview();
        initEvents();

    }

    private void initEvents() {


        //返回
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        //点击客服聊天
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerServiceActivity.this,ChatActivity.class);

                startActivity(intent);
            }
        });





    }

    private void initview() {

        iv = (ImageView) findViewById(R.id.service_bk);

        tv= (TextView) findViewById(R.id.service_lt);

    }
}
