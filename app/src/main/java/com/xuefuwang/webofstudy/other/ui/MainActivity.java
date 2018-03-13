package com.xuefuwang.webofstudy.other.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.baidu.mapapi.SDKInitializer;
import com.xuefuwang.webofstudy.R;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SDKInitializer.initialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        final Animation rotate= AnimationUtils.loadAnimation(this, R.anim.anim_rotate);	//获取“旋转”动画资源
//        final Animation translate= AnimationUtils.loadAnimation(this, R.anim.anim_translate);	//获取“平移”动画资源
        final Animation scale=AnimationUtils.loadAnimation(this, R.anim.anim_scale);	//获取“缩放”动画资源
//        final Animation alpha=AnimationUtils.loadAnimation(this, R.anim.anim_alpha);	//获取“透明度变化”动画资源
        ImageView iv=(ImageView)findViewById(R.id.imageView1);	//获取要应用动画效果的ImageView



//        iv.startAnimation(rotate);		//播放“旋转”动画
//        iv.startAnimation(translate);	//播放“平移”动画
        iv.startAnimation(scale);	//播放“缩放”动画
//        iv.startAnimation(alpha);	//播放“透明度渐变”动画



        //设置缩放动画监听器
        scale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                //跳转后结束
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

}
