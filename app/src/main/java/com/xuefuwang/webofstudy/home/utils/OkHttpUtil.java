package com.xuefuwang.webofstudy.home.utils;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/23.
 */
public class OkHttpUtil {



    /*public  static void  doGet(String url, final AssistantTask.IRequestCallback callback){

        OkHttpClient client = new OkHttpClient();
        //创建一个请求
        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);

        try {

        //直接起一个线程去执行(异步)
            Response response = call.execute();
        //判断下一个请求是否成功
            if (response.isSuccessful()){
                callback.onSuccess(response);
            }else {
                callback.onError(response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public  static void  doGet(String url, final Callback callback){
        OkHttpClient client = new OkHttpClient();
        //创建一个请求
        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);

        //放到队列里去执行（同步执行，一个任务执行完后再开始下一个任务）
        call.equals(callback);
    }


    *//**
     * 放到队列里去执行（同步执行，一个任务执行完后再开始下一个任务），异步执行,插队
     * @param url
     * @param body
     * @param callback
     *//*
    public static  void doPost(String url, RequestBody body,Callback callback){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).post(body).build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }


    *//**
     *同步请求
     * @param url
     * @param body
     * @param callback
     *//*
    public static  void doPost(String url, RequestBody body,AssistantTask.IRequestCallback callback){


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).post(body).build();

        try {
            Response response = client.newCall(request).execute();
            LogUtil.e("response = "+response);
            if (response.isSuccessful()){
                callback.onSuccess(response);
            }else {
                callback.onError(response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


    /**
     * GET请求
     *
     * @param url
     *            请求地址
     * @param callback
     *            回调
     */
    public static void doGet(String url,
                             final AssistantTask.IRequestCallback callback)
    {
        OkHttpClient client = new OkHttpClient();

        // 创建一个请求
        Request request = new Request.Builder().url(url).build();

        // 用client创建一个任务
        Call call = client.newCall(request);

        try
        {
            // 直接起一个线程去执行(异步执行)
            Response response = call.execute();
            LogUtil.w("response = " + response);
            // 判断一下请求是否成功
            if (response.isSuccessful())
            {
                callback.onSuccess(response);
            }
            else
            {
                callback.onError(response.message());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * GET请求
     *
     * @param url
     *            请求地址
     * @param callback
     *            回调
     */
    public static void doGet(String url, Callback callback)
    {
        OkHttpClient client = new OkHttpClient();

        // 创建一个请求
        Request request = new Request.Builder().url(url).build();

        // 用client创建一个任务
        Call call = client.newCall(request);
        // 放到任务队列里面去执行（同步执行：一个任务执行完后再执行下一个任务）
        call.enqueue(callback);
    }

    /**
     * POST请求
     *
     * @param url
     *            请求地址
     * @param body
     *            参数
     * @param callback
     *            请求回调
     */
    public static void doPost(String url, RequestBody body, Callback callback)
    {
        OkHttpClient client = new OkHttpClient();
        // 创建POST请求
        Request request = new Request.Builder().url(url).post(body).build();

        // 用client创建一个任务
        Call call = client.newCall(request);
        // 放到任务队列里面去执行（同步执行：一个任务执行完后再执行下一个任务）
        call.enqueue(callback);
    }

    /**
     * POST请求
     *
     * @param url
     *            请求地址
     * @param body
     *            参数
     * @param callback
     *            请求回调
     */
    public static void doPost(String url, RequestBody body,
                              AssistantTask.IRequestCallback callback)
    {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).post(body).build();

        try
        {
            Response response = client.newCall(request).execute();
            LogUtil.w("response = " + response);

            if (response.isSuccessful())
            {
                callback.onSuccess(response);
            }
            else
            {
                callback.onError(response.message());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
