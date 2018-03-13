package com.xuefuwang.webofstudy.other.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xuefuwang.webofstudy.R;
import com.xuefuwang.webofstudy.course_table.ui.CourseTableFragment;
import com.xuefuwang.webofstudy.found.FoundFragment;
import com.xuefuwang.webofstudy.home.citylist.CityListActivity;
import com.xuefuwang.webofstudy.home.ui.HomeFragment;
import com.xuefuwang.webofstudy.my.ui.MyFragment;
import com.xuefuwang.webofstudy.other.utils.Constants;


public class HomeActivity extends FragmentActivity {

    private RadioButton radioButtonHome;
    private Fragment[] fragments;
    private RadioGroup radioGroup;
    private int lastId;
    private int lastIndex;
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        radioGroup = (RadioGroup) findViewById(R.id.home_radio_group);

        //默认选中第一个RadioButton
        radioButtonHome = (RadioButton) findViewById(R.id.home_home);
        radioButtonHome.performClick();

        //下方四个按钮的点击事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                lastId = checkedId;

                int currIndex = 0;
                switch (checkedId) {
                    case R.id.home_home:
                        currIndex = 0;
                        break;

                    case R.id.home_course_table:
                        currIndex = 1;
                        break;

                    case R.id.home_found:
                        currIndex = 2;
                        break;

                    case R.id.home_my:
                        currIndex = 3;
                        break;
                    default:
                        currIndex = 0;
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                transaction.hide(fragments[lastIndex]);
                transaction.show(fragments[currIndex]);
                transaction.commit();
                lastIndex = currIndex;
            }
        });


        homeFragment = new HomeFragment();
        CourseTableFragment courseTableFragment = new CourseTableFragment();
        FoundFragment foundFragment = new FoundFragment();
        MyFragment myFragment = new MyFragment();

        //创建Fragment界面
        fragments = new Fragment[]{
                homeFragment,
                courseTableFragment,
                foundFragment,
                myFragment
        };
        //获取fragmentManger
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.length; i++) {
            Fragment fragment = fragments[i];
            transaction.add(R.id.home_content, fragment);
            transaction.hide(fragment);
        }

        //默认显示第一个fragment
        transaction.show(fragments[0]);
        transaction.commit();
    }

    //首页添加的跳转到城市列表页的方法
    public void intentToCityList(int requstCode) {

        Intent intent = new Intent(this, CityListActivity.class);
        startActivityForResult(intent, requstCode);
    }


    //接收从城市列表页返回的城市信息
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (Constants.CHOOSE_CITY_REQUEST_CODE == requestCode) {
            if (Constants.CHOOSE_CITY_RESULT_CODE == resultCode) {
                String cityname = intent.getStringExtra(Constants.CITY_NAME);
                homeFragment.setCityName(cityname);
            }
        }

        super.onActivityResult(requestCode, resultCode, intent);
    }

}
