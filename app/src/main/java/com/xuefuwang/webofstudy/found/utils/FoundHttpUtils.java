package com.xuefuwang.webofstudy.found.utils;

import android.content.Context;

import com.xuefuwang.webofstudy.home.utils.AssistantTask;
import com.xuefuwang.webofstudy.home.utils.VolleyUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by John on 2016/4/7.
 */
public class FoundHttpUtils {

    //附近老师的URL
    public static final  String URL_NEARTEACHER="http://112.74.128.53:9528/APP_Action.ashx?VerSafe=26D3C2B92BE93727851FC108194D73C9&action=SearchNearTeachers&longitude=113.951449&latitude=22.53977&distance=10&pageSize=10&pageIndex=";


    //百度地图URL
    public static final  String URL_BAIDUMAP = "http://112.74.128.53:9528/APP_Action.ashx?VerSafe=26D3C2B92BE93727851FC108194D73C9&action=SearchNearTeachers&longitude=113.95161437988281&latitude=22.540302276611328&distance=2&pageIndex=1&pageSize=10000";



    public static void requestNearTeacherListByVolley(Context context, int page, AssistantTask.IRequestCallback callback) {
        Map<String, String> params = new HashMap<>();
        VolleyUtils.doGet(context, URL_NEARTEACHER+ page, callback);
    }

    public static void requestBaiduMapByVolley(Context context, AssistantTask.IRequestCallback callback) {
        Map<String, String> params = new HashMap<>();
        VolleyUtils.doPost(context, URL_BAIDUMAP, params, callback);
    }

}
