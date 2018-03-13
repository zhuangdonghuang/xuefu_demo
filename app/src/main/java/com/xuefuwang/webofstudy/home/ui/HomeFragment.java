package com.xuefuwang.webofstudy.home.ui;

import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xuefuwang.webofstudy.R;
import com.xuefuwang.webofstudy.home.bean.HomeAdvertisementImage;
import com.xuefuwang.webofstudy.home.utils.AssistantTask;
import com.xuefuwang.webofstudy.home.utils.BannerLayout;
import com.xuefuwang.webofstudy.home.utils.RippleLayout;
import com.xuefuwang.webofstudy.home.utils.VolleyUtils;
import com.xuefuwang.webofstudy.other.ui.BaseFragment;
import com.xuefuwang.webofstudy.other.ui.HomeActivity;
import com.xuefuwang.webofstudy.other.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * 主页的fragment
 * Created by pengh on 2016/4/1.
 */
public class HomeFragment extends BaseFragment {

    //请求获取四张广告图片的连接
    private static final String urlString = "http://112.74.128.53:9528/APP_Action.ashx?VerSafe=26D3C2B92BE93727851FC108194D73C9&action=GetIndexPics";

    //陪学
    private RelativeLayout rlpx;
    //培优
    private RelativeLayout rlpy;
    //陪特
    private RelativeLayout rlpt;

    //图片轮播控件
    private BannerLayout bannerLayout;

    private EditText edss;
    private ImageView kf;

    //左上角选择地理位置的按钮
    private TextView cityText;

    //波纹
    private RippleLayout rippleLayout;
    private Animation picAnimation;


    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {

        //注册EventBus
        EventBus.getDefault().register(this);

        //左上角选择地理位置的按钮
        cityText = (TextView) root.findViewById(R.id.fragment_search_break);

        //波纹
        rippleLayout = (RippleLayout) root.findViewById(R.id.ripplelayout);

        //图片轮播类
        bannerLayout = (BannerLayout) root.findViewById(R.id.banner);


        //搜索框
        edss = (EditText) root.findViewById(R.id.fragment_et_search);

        //客服
        kf = (ImageView) root.findViewById(R.id.fragment_search_qd);

        //陪学
        rlpx = (RelativeLayout) root.findViewById(R.id.home_px);

        //培优
        rlpy = (RelativeLayout) root.findViewById(R.id.home_py);

        rlpt = (RelativeLayout) root.findViewById(R.id.home_pt);
    }

    @Override
    protected void initEvents() {

        //跳转到城市列表选择页面
        cityText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity homeActivity = (HomeActivity) getActivity();
                homeActivity.intentToCityList(Constants.CHOOSE_CITY_REQUEST_CODE);
            }
        });

        edss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });


        kf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CustomerServiceActivity.class);
                startActivity(intent);

            }
        });


        //轮播图片添加监听事件
        bannerLayout.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), ShareActivity.class);
                startActivity(intent);

            }
        });

        //必需在水纹波动监听方法里面，设置Activity跳转
        rippleLayout.setOnRippleCompleteListener(new RippleLayout.OnRippleCompleteListener() {
            @Override
            public void onComplete(int id) {

                //培学
                if (id == R.id.iv_iv) {
                    Intent intent = new Intent(getActivity(), TrainingActvity.class);
                    startActivity(intent);
                }
                //培优
                else if (id == R.id.iv_ivv) {
                    Intent intent = new Intent(getActivity(), ExcellentActivity.class);
                    startActivity(intent);
                }
                //培特
                else if (id == R.id.iv_pt) {
                    Intent intent = new Intent(getActivity(), PetterActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void initData() {
        ArrayList<Integer> list = new ArrayList<>();


        //给广告栏设置默认的图片
        list.add(R.drawable.bannerer);
        bannerLayout.setViewRes(list);

        final List<String> imageUrls = new ArrayList<>();
        VolleyUtils.doGet(getContext(), urlString, new AssistantTask.IRequestCallback() {
            @Override
            public void onSuccess(Object obj) {
                try {
                    JSONObject root = new JSONObject(obj.toString());
                    JSONArray rootJSONArray = root.getJSONArray(Constants.RESPONSE_RESULT_VALUE);
                    List<HomeAdvertisementImage> homeAdvertisementImages = HomeAdvertisementImage.arrayHomeAdvertisementImageFromData(rootJSONArray.toString());
                    for (int i = 0; i < homeAdvertisementImages.size(); i++) {
                        imageUrls.add("http://112.74.128.53:9997/" + homeAdvertisementImages.get(i).getImageLinkAPP());
                    }
                    EventBus.getDefault().post(imageUrls);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String msg) {
                Toast.makeText(getContext(),"您没有网络或者网络不稳定哦！",Toast.LENGTH_LONG).show();
            }
        });


        //陪学等图片的动画
        picAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.home_fragment_pic);

        //给对用的图片设置动画
        rlpx.setAnimation(picAnimation);
        rlpy.setAnimation(picAnimation);
        rlpt.setAnimation(picAnimation);

        //开启动画
        picAnimation.start();
    }

    //通过城市列表页的选择设置当前位置
    public void setCityName(String cityName) {
        cityText.setText(cityName);
    }

    @Subscribe
    public void onEvent(List<String> list){
        bannerLayout.setViewUrls(list);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //在onDestroy方法中解除注册
        EventBus.getDefault().unregister(this);
    }
}
