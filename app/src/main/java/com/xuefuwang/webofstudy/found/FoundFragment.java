package com.xuefuwang.webofstudy.found;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.xuefuwang.webofstudy.R;
import com.xuefuwang.webofstudy.found.adapter.NearTeacherAdapter;
import com.xuefuwang.webofstudy.found.bean.NearTeacher;
import com.xuefuwang.webofstudy.found.utils.FoundHttpUtils;
import com.xuefuwang.webofstudy.home.utils.AssistantTask;
import com.xuefuwang.webofstudy.home.utils.LoadMoreListView;
import com.xuefuwang.webofstudy.home.utils.LogUtil;
import com.xuefuwang.webofstudy.other.ui.BaseFragment;
import com.xuefuwang.webofstudy.other.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FoundFragment extends BaseFragment {

    private ImageView jump_iv;    //地图跳转icon
    private LoadMoreListView listView; //加载更多
    private SwipeRefreshLayout refreshLayout; //刷新

    //定位坐标
    private double latitude;
    private double longitude;
    private List<NearTeacher> list;//bean类
    private int page = 1;


    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private TextView location_tv;

    private NearTeacherAdapter adapter;//适配器
    private TextView textView;


    @Override
    protected int getLayout() {
        return R.layout.fragment_found;
    }

    @Override
    protected void initViews() {
        textView = new TextView(getActivity());
        textView.setText("正在加载.....");
        jump_iv = (ImageView) root.findViewById(R.id.found_jump_to_map_iv);
        listView = (LoadMoreListView) root.findViewById(R.id.found_teacher_lv);
        refreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.found_srfl);
        location_tv = (TextView) root.findViewById(R.id.found_tv);

        mLocationClient = new LocationClient(getContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数

        mLocationClient.start();


//        location_tv.setText();
    }


    @Override
    protected void initEvents() {

        //跳转地图
        jump_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BDMapActivity.class);
                startActivity(intent);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                page = 1;
                //下拉刷新先清空数据
                list.clear();

                //再请求网络
                requestList(list);


            }
        });


        listView.setLoadMoreListener(new LoadMoreListView.ILoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                listView.addFooterView(textView);
                requestList(list);

            }
        });


    }

    @Override
    protected void initData() {

        list = new ArrayList<>();
        adapter = new NearTeacherAdapter(getActivity(), list);
        listView.setAdapter(adapter);

        requestList(list);
    }


    //Volley请求
    private void requestList(final List<NearTeacher> List) {
        FoundHttpUtils.requestNearTeacherListByVolley(getContext(), page, new AssistantTask.IRequestCallback() {
            @Override
            public void onSuccess(Object obj) {
                try {


                    JSONObject root = new JSONObject(obj.toString());

                    JSONArray info = root.getJSONArray(Constants.RESPONSE_RESULT_VALUE);

                    List<NearTeacher> train = NearTeacher.arrayNearTeacherFromData(info.toString());

                    List.addAll(train);

                    adapter.notifyDataSetChanged();

                    //隐藏正在刷新的效果
                    refreshLayout.setRefreshing(false);

                    listView.removeFooterView(textView);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String msg) {


                //隐藏正在刷新的效果
                refreshLayout.setRefreshing(false);
                listView.removeFooterView(textView);
            }
        });

    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }


    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            latitude = bdLocation.getLatitude();//x坐标
            longitude = bdLocation.getLongitude();//y坐标
            LogUtil.w("--", latitude + "----" + longitude);
            LatLng pt5 = new LatLng(latitude, longitude);
            reverseGeoCode(pt5);
        }
    }

    /**
     * 反地理编码得到地址信息
     */
    private void reverseGeoCode(LatLng latLng) {
        // 创建地理编码检索实例
        GeoCoder geoCoder = GeoCoder.newInstance();
        //
        OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
            // 反地理编码查询结果回调函数
            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    // 没有检测到结果

                    LogUtil.w("----", "抱歉，未能找到结果");
                }
                location_tv.setText(result.getAddress().toString());
                LogUtil.w("----", "位置" + result.getAddress());
            }

            // 地理编码查询结果回调函数
            @Override
            public void onGetGeoCodeResult(GeoCodeResult result) {
                if (result == null
                        || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    // 没有检测到结果
                }
            }
        };
        // 设置地理编码检索监听者
        geoCoder.setOnGetGeoCodeResultListener(listener);
        //
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
        // 释放地理编码检索实例
        // geoCoder.destroy();
    }
}
