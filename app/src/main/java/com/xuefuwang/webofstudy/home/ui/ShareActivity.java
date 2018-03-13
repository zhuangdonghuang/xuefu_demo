package com.xuefuwang.webofstudy.home.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuefuwang.webofstudy.R;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Administrator on 2016/4/5.
 */
public class ShareActivity extends Activity {


    private WebView webView;
    private ImageView web_iv,share;

    private TextView web_tv;
    private Button web_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_share);

        //初始化
        initview();

        initData();

    }

    private void initData() {



        webView.loadUrl("http://m.xuef.com/activity/Share.html");




        //浏览器js一些属性
        webView.setWebChromeClient(new WebChromeClient() {


            @Override
            public void onReceivedTitle(WebView view, String title) {



                super.onReceivedTitle(view, title);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }
        });



        //重写setWebClient方法
        webView.setWebViewClient(new WebViewClient() {

            //避免调用系统浏览器打开
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);

                return super.shouldOverrideUrlLoading(view, url);
            }








            //断网时 ，加载错误信息.本地的信息
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);


                //加载text
                web_tv.setText("亲，您的网络似乎不是很好哦！");

                web_bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                //webView隐藏
                webView.setVisibility(View.GONE);


            }
        });



        //返回
        web_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //分享
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               showShareweibo();


            }
        });




    }


    private void initview() {

        webView = (WebView) findViewById(R.id.id_webview);

        //使用Html网页中javascript方法
        webView.getSettings().setJavaScriptEnabled(true);

        web_tv = (TextView) findViewById(R.id.web_id);
        web_bt = (Button) findViewById(R.id.web_bt);

        web_iv = (ImageView) findViewById(R.id.share_id_bk);

        share = (ImageView) findViewById(R.id.share_id_fx);


    }



    //添加分享调用代码
    private void showShareweibo(){


        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.app_name));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //  oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }




}
