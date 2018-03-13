package com.xuefuwang.webofstudy.home.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuefuwang.webofstudy.R;
import com.xuefuwang.webofstudy.home.adapter.TrainingPagerAdapter;
import com.xuefuwang.webofstudy.home.bean.Trainningcomm;
import com.xuefuwang.webofstudy.home.utils.AssistantTask;
import com.xuefuwang.webofstudy.home.utils.ImageLoader;
import com.xuefuwang.webofstudy.home.utils.LogUtil;
import com.xuefuwang.webofstudy.home.utils.PxHttpUtil;
import com.xuefuwang.webofstudy.other.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 培学，老师简介页
 *
 * Created by Administrator on 2016/4/2.
 */
public class PettercommActivity extends FragmentActivity{
    private ImageView ivb,ivkf,icon;
    private String[] mTitles = new String[] { "简介","课程", "评论" };
    private SimpleViewPagerIndicator mIndicator;
    private ViewPager mViewPager;
   // private FragmentPagerAdapter mAdapter;
    private TrainingPagerAdapter trainingPagerAdapter;
   // private TabFragment[] mFragments = new TabFragment[mTitles.length];
    private TextView tvname,tvrc,tvll,tvkc,tvhp;
    private int id;
    private Trainningcomm trainningcomm;
    private Bundle bundle;
    private List<Fragment> list;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pettercomm);

        //获取id
        id =getIntent().getIntExtra("id",0);
        initViews();
        initDatas();
        initEvents();


    }


    private void initEvents() {
        //返回
        ivb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //客服
        ivkf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });






        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                mIndicator.scroll(position, positionOffset);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initDatas()
    {
        mIndicator.setTitles(mTitles);

        for (int i = 0; i < mTitles.length; i++)
        {
         //  mFragments[i] = (TabFragment) TabFragment.newInstance(mTitles[i]);


        }

        setFragment();

        LogUtil.w("id" + id);
        if (id != 0) {
            requestList();
        }
    }



    private void initViews()
    {

        imageLoader = ImageLoader.getInstance(PettercommActivity.this);
        list = new ArrayList<>();
        icon = (ImageView) findViewById(R.id.train_comm_img);
        tvname = (TextView) findViewById(R.id.train_comm_tvname);
        tvrc = (TextView) findViewById(R.id.training_comm_rc);
        tvll = (TextView) findViewById(R.id.train_comm_ll);
        tvkc = (TextView) findViewById(R.id.train_comm_kc);
        tvhp = (TextView) findViewById(R.id.train_comm_hp);
        ivb = (ImageView) findViewById(R.id.train_comm_break);
        ivkf = (ImageView) findViewById(R.id.train_comm_kf);
        mIndicator = (SimpleViewPagerIndicator) findViewById(R.id.id_stickynavlayout_indicator);
        mViewPager = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);
    }


    /**
     * 解析数据
     */


    private void requestList(){

        PxHttpUtil.requestGameListByVolleycom(this, id, new AssistantTask.IRequestCallback() {
            @Override
            public void onSuccess(Object obj) {
                JSONObject root = null;
                try {
                    root = new JSONObject(obj.toString());

                    LogUtil.w("TrainingcommActivity root = " + root.toString());

                    JSONArray info = root.getJSONArray(Constants.RESPONSE_RESULT_VALUE);
                    JSONObject infoJSONObject = info.getJSONObject(0);

                    trainningcomm = Trainningcomm.objectFromData(infoJSONObject.toString());

                    imageLoader.displayImage("http://112.74.128.53:9997/" + trainningcomm.getPhoneLink(),icon,true);

                    tvname.setText(trainningcomm.getUserName()); //名字

                    //获取教学风格
                    String teachFeature = trainningcomm.getTeachFeature();
                    //根据“，”截取数据
                    String[] split = teachFeature.split(",");
                    StringBuilder stringBuilder = new StringBuilder();

                    //将截取到的数据拼接起来，中间用空格隔开
                    for (int i = 0; i < split.length; i++) {
                        stringBuilder.append(split[i] + " ");
                    }
                    tvrc.setText(stringBuilder); //风格

                    tvll.setText("浏览数:" + trainningcomm.getLookCount()); //浏览数
                    tvkc.setText("课程" + trainningcomm.getCourseCount()); //课程
                    tvhp.setText("好评率:" + trainningcomm.getCommpercent() + "%");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String msg) {

            }
        });
    }

    private void setFragment() {

        IntroduceFragmentPt introduceFragmentPt = new IntroduceFragmentPt();
        bundle = new Bundle();
        bundle.putInt("id",id);
        introduceFragmentPt.setArguments(bundle);
        list.add(introduceFragmentPt);

        CourseFragmentPt courseFragmentPt = new CourseFragmentPt();
        list.add(courseFragmentPt);

        CommentFragmentPt commentFragmentPt = new CommentFragmentPt();
        list.add(commentFragmentPt);

        trainingPagerAdapter = new TrainingPagerAdapter(getSupportFragmentManager(),list);
        mViewPager.setAdapter(trainingPagerAdapter);
    }

}
