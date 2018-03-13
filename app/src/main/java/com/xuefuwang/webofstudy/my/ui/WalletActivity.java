package com.xuefuwang.webofstudy.my.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xuefuwang.webofstudy.R;


public class WalletActivity extends Activity {

    private TextView tvRecharge,tvWithdraw,tvBankcard,tvBill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        tvRecharge= (TextView) findViewById(R.id.wallet_tv_recharge);
        tvWithdraw= (TextView) findViewById(R.id.wallet_tv_Withdraw);
        tvBankcard= (TextView) findViewById(R.id.wallet_tv_bankcard);
        tvBill= (TextView) findViewById(R.id.wallet_tv_bill);

        initView();
    }

    private void initView(){

        //充值的点击监听
        tvRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WalletActivity.this,RechargeFragment.class);
                startActivity(intent);
            }
        });
        tvWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(WalletActivity.this,WithdrawActivity.class);
                startActivity(intent);
            }
        });


    }
}
