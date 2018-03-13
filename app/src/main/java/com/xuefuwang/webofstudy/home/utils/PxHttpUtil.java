package com.xuefuwang.webofstudy.home.utils;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *  培学网络请求
 * Created by Administrator on 2016/4/2.
 */
public class PxHttpUtil {


    /**
     * 老师的详细信息
     */
    private static String URL_TEACHER_INFO = "http://112.74.128.53:9528/APP_Action.ashx?VerSafe=26D3C2B92BE93727851FC108194D73C9&action=GetUserDetailss&type=18&TeacherID=";

    /**
     * 培学老师列表
     */
    public static final String URL_TRAINING = "http://112.74.128.53:9528/APP_Action.ashx?VerSafe=26D3C2B92BE93727851FC108194D73C9&action=GetTeacherSearchs4&userid=9531&Text=&CourseType=&Region=%E6%B7%B1%E5%9C%B3%E5%B8%82&Sex=-1&TeachType=-1&TeacherType=1&OneYuanTest=-1&OrderBy=3&longitude=113.9516830444336&latitude=22.54024887084961&distance=-1&PageSize=10&PageIndex=";

    /**
     * 培学老师简介
     */
    public static final String URL_ING = "http://112.74.128.53:9528/APP_Action.ashx?VerSafe=26D3C2B92BE93727851FC108194D73C9&action=GetUserDetailss&type=9&TeacherID=";
    /**
     * 培优老师列表
     */
    public static final  String URL_EXCELLENT="http://112.74.128.53:9528/APP_Action.ashx?VerSafe=26D3C2B92BE93727851FC108194D73C9&action=GetTeacherSearchs4&userid=-1&Text=&CourseType=&Region=%E6%B7%B1%E5%9C%B3&Sex=-1&TeachType=-1&TeacherType=2&OneYuanTest=-1&OrderBy=3&longitude=0.0&latitude=0.0&distance=-1&PageSize=10&Classtype=-1&priceMi=0&priceMa=0&PageIndex=";

    /**
     * 培特老师列表
     *
     */

    public static  String URL_PETTER="http://112.74.128.53:9528/APP_Action.ashx?VerSafe=26D3C2B92BE93727851FC108194D73C9&action=GetTeacherSearchs4&userid=-1&Text=&CourseType=&Region=%E6%B7%B1%E5%9C%B3&Sex=-1&TeachType=-1&TeacherType=3&OneYuanTest=-1&OrderBy=3&longitude=0.0&latitude=0.0&distance=-1&PageSize=10&PageIndex=1&Classtype=-1&priceMi=0&priceMa=0";


    /**
     * Okhttp 培学列表
     *
     */




    public static void requesttrainingOkhttp(int page,AssistantTask.IRequestCallback callback){


      /*  FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("VerSafe", "ACA52656D4989DD0FF2358953CC68EDB");
        builder.add("action", "GetTeacherSearchs4");
        builder.add("PageIndex",""+page);*/

      //  OkHttpUtil.doGet(URL_TRAINING_LIST,callback);

    }


    /**
     * 培学
     * 用Volley请求老师列表
     *
     */
    public static void requestTeacherListByVolley(Context context, int page,
                                                  AssistantTask.IRequestCallback callback) {
        VolleyUtils.doGet(context, URL_TRAINING + page, callback);
    }



    /**
     * 老师简介数据
     */

    public static void requestGameListByVolleycom(Context context, int id,
                                                  AssistantTask.IRequestCallback callback) {
        VolleyUtils.doGet(context, URL_ING + id, callback);
    }



    /**
     * 老师的个人详情
     * @param context
     * @param id
     * @param callback
     */
    public static void requestTeacherInfoByVolley(Context context, int id,
                                                  AssistantTask.IRequestCallback callback) {
        LogUtil.w("url = " + URL_TEACHER_INFO + id);
        VolleyUtils.doGet(context, URL_TEACHER_INFO + id, callback);
    }

    /**
     * 培优
     * 用Volley请求老师列表
     *
     * @param context
     * @param page
     * @param callback
     */
    public static void requestExcellentByVolley(Context context, int page,
                                               AssistantTask.IRequestCallback callback)
    {
        VolleyUtils.doGet(context, URL_EXCELLENT + page, callback);
    }


    /**
     * 培特
     * 用Volley请求老师列表
     *
     * @param context
     * @param page
     * @param callback
     */
    public static void requestPetterByVolley(Context context, int page,
                                                AssistantTask.IRequestCallback callback)
    {
        Map<String, String> params = new HashMap<>();
        params.put("PageIndex", "" + page);
        VolleyUtils.doPost(context, URL_PETTER, params, callback);
    }


}
