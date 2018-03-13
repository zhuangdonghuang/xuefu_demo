package com.xuefuwang.webofstudy.found;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ZoomControls;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.Circle;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.xuefuwang.webofstudy.R;
import com.xuefuwang.webofstudy.home.utils.LogUtil;

import static com.xuefuwang.webofstudy.R.id.map_comebake_iv;


//百度地图
public class BDMapActivity extends Activity {
    private TextView textView;

    // 定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private MyLocationConfiguration.LocationMode mCurrentMode;
    BitmapDescriptor mCurrentMarker;

    MapView mMapView = null;
    BaiduMap mBaiduMap;
    ImageView bakeMap;

    //定位坐标
    private double latitude;
    private double longitude;

    ImageView imageView_location;
    boolean isFirstLoc = true; // 是否首次定位
    private InfoWindow mInfoWindow;//弹出菜单
    private Marker mMarkerA;
    private Marker mMarkerB;
    private Marker mMarkerC;
    private Marker mMarkerD;

    private Button requestLocButton;

     // 详细信息的 布局
    private RelativeLayout mMarkerInfoLy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        SDKInitializer.initialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bdmap);
        mMarkerInfoLy = (RelativeLayout) findViewById(R.id.id_marker_info);
        imageView_location = (ImageView) findViewById(R.id.location_iv);
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        requestLocButton = (Button) findViewById(R.id.location_bt);
        bakeMap = (ImageView) findViewById(map_comebake_iv);
//定位以及切换视角的点击事件
        View.OnClickListener btnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (mCurrentMode) {
                    case NORMAL:
                        requestLocButton.setText("跟随");
                        mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;
                        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker));
                        break;
                    case COMPASS:
                        requestLocButton.setText("普通");
                        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
                        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker));
                        break;
                    case FOLLOWING:
                        requestLocButton.setText("罗盘");
                        mCurrentMode = MyLocationConfiguration.LocationMode.COMPASS;
                        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker));
                        break;
                    default:
                        break;
                }
            }
        };
        imageView_location.setOnClickListener(btnClickListener);

        // 地图初始化
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        mBaiduMap.setOnMapClickListener(mapMap);
        mMapView.showZoomControls(false);//隐藏百度缩放按钮
        mMapView.showScaleControl(false);//隐藏百度比例
        int count = mMapView.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = mMapView.getChildAt(i);
            if (child instanceof ZoomControls || child instanceof ImageView) {
                child.setVisibility(View.INVISIBLE);
            }
        }

        //初始化定位信息TextView
        textView = (TextView) findViewById(R.id.location_tv);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();

        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }



    //标注下落动画
    public void initOverlay() {


        BitmapDescriptor bdA = BitmapDescriptorFactory
                .fromResource(R.drawable.ic_location);
        BitmapDescriptor bdB = BitmapDescriptorFactory
                .fromResource(R.drawable.ic_location);
        BitmapDescriptor bdC = BitmapDescriptorFactory
                .fromResource(R.drawable.ic_location);
        BitmapDescriptor bdD = BitmapDescriptorFactory
                .fromResource(R.drawable.ic_location);



        // add marker overlay
        LatLng llA = new LatLng(latitude+0.00123, longitude+0.00254);
        LatLng llB = new LatLng(latitude+0.00894, longitude+0.00654);
        LatLng llC = new LatLng(latitude+0.00423, longitude+0.00555);
        LatLng llD = new LatLng(latitude+0.00323, longitude+0.00666);

        MarkerOptions ooA = new MarkerOptions().position(llA).icon(bdA)
                .zIndex(9).draggable(true);
        if (isFirstLoc == true) {
            // 掉下动画
            ooA.animateType(MarkerOptions.MarkerAnimateType.drop);
        }
        mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));


        MarkerOptions ooB = new MarkerOptions().position(llB).icon(bdB)
                .zIndex(9).draggable(true);
        if (isFirstLoc == true) {
            // 掉下动画
            ooB.animateType(MarkerOptions.MarkerAnimateType.drop);
        }
        mMarkerB = (Marker) (mBaiduMap.addOverlay(ooB));

        MarkerOptions ooC = new MarkerOptions().position(llC).icon(bdC)
                .zIndex(9).draggable(true);
        if (isFirstLoc == true) {
            // 掉下动画
            ooC.animateType(MarkerOptions.MarkerAnimateType.drop);
        }
        mMarkerC = (Marker) (mBaiduMap.addOverlay(ooC));

        MarkerOptions ooD = new MarkerOptions().position(llD).icon(bdD)
                .zIndex(9).draggable(true);
        if (isFirstLoc == true) {
            // 掉下动画
            ooD.animateType(MarkerOptions.MarkerAnimateType.drop);
        }
        mMarkerD = (Marker) (mBaiduMap.addOverlay(ooD));
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            latitude = location.getLatitude();//x坐标
            longitude = location.getLongitude();//y坐标
            LogUtil.w("--", latitude + "----" + longitude);


            //掉落效果
            initOverlay();
            //弹出框
            mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                public boolean onMarkerClick(final Marker marker) {
                    Button button = new Button(getApplicationContext());
                    button.setBackgroundResource(R.drawable.popup);
                    InfoWindow.OnInfoWindowClickListener listener = null;
                    if (marker == mMarkerA || marker == mMarkerD) {
                        button.setText("老师信息");
                        mMarkerInfoLy.setVisibility(View.VISIBLE);
                        listener = new InfoWindow.OnInfoWindowClickListener() {
                            public void onInfoWindowClick() {
                                mBaiduMap.hideInfoWindow();
                                mMarkerInfoLy.setVisibility(View.GONE);
                            }
                        };
                        LatLng ll = marker.getPosition();
                        mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(button), ll, -47, listener);
                        mBaiduMap.showInfoWindow(mInfoWindow);
                    }
                    return true;
                }
            });


            mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(16).build()));//设置缩放级别
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                            // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(latitude)
                    .longitude(longitude).build();

            mBaiduMap.setMyLocationData(locData);

            if (isFirstLoc) {
                isFirstLoc = false;



                ranges();
                //获取坐标，设置反地理编码
                LatLng pt5 = new LatLng(latitude, longitude);
                reverseGeoCode(pt5);


                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(16);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
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
                textView.setText(result.getAddress().toString());
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




    //圆形覆盖物
    public void ranges() {
        LatLng pt6 = new LatLng(latitude, longitude);
        CircleOptions options2 = new CircleOptions()
                .center(pt6)
                .radius(1450)
                .stroke(new Stroke(1, Color.BLACK))
                .fillColor(Color.argb(100, 255, 255, 255))
                .visible(true);
        Circle circle = (Circle) mBaiduMap.addOverlay(options2);
    }


    public void comeback (View view){
        finish();
    }

    BaiduMap.OnMapClickListener mapMap = new BaiduMap.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng latLng) {
            mBaiduMap.hideInfoWindow();
            mMarkerInfoLy.setVisibility(View.GONE);
        }

        @Override
        public boolean onMapPoiClick(MapPoi mapPoi) {
            return false;
        }
    };

//生命周期
    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }
}