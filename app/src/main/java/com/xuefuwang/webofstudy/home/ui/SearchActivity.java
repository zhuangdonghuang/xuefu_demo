package com.xuefuwang.webofstudy.home.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xuefuwang.webofstudy.R;

/**
 * Created by Administrator on 2016/4/2.
 */
public class SearchActivity extends Activity {


    private String[] mVals = new String[]{

            "小学","初中","高中","语文","数学","英语","物理","化学","地理"
    };

    private FlowLayout mflowLayout;

    private TextView tvbreak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        mflowLayout = (FlowLayout) findViewById(R.id.flowLayout);
        tvbreak = (TextView) findViewById(R.id.home_search_break);


        initDate();

    }


    public void initDate() {


        LayoutInflater mInflater = LayoutInflater.from(this);
        for (int i = 0; i < mVals.length; i++) {

            TextView tv = (TextView) mInflater.inflate(R.layout.tv, mflowLayout, false);

            tv.setText(mVals[i]);

            mflowLayout.addView(tv);





        }



        tvbreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}
