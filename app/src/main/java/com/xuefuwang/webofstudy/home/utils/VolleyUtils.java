package com.xuefuwang.webofstudy.home.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Created by Administrator on 2016/4/2.
 */
public class VolleyUtils {


    private static RequestQueue queue;

    /**
     * 执行GET请求
     *
     * @param context
     *            上下文
     * @param url
     *            请求地址
     * @param callback
     *            回调
     */
    public static void doGet(Context context, String url,
                             AssistantTask.IRequestCallback callback)
    {
        doRequest(context, url, Request.Method.GET, null, callback);
    }

    /**
     * 执行POST请求
     *
     * @param context
     *            上下文
     * @param url
     *            请求地址
     * @param params
     *            请求参数
     * @param callback
     *            回调
     */
    public static void doPost(Context context, String url,
                              Map<String, String> params, AssistantTask.IRequestCallback callback)
    {
        doRequest(context, url, Request.Method.POST, params, callback);
    }

    /**
     * 执行请求
     *
     * @param context
     *            上下文
     * @param url
     *            请求地址
     * @param method
     *            请求方式
     * @param params
     *            请求参数
     * @param callback
     *            回调
     */
    private static void doRequest(Context context, String url, int method,
                                  final Map<String, String> params,
                                  final AssistantTask.IRequestCallback callback)
    {
        // 只需要创建一个队列就可以了，标准单例写法
        if (queue == null)
        {
            synchronized (VolleyUtils.class)
            {
                if (queue == null)
                {
                    queue = Volley.newRequestQueue(context);
                }
            }
        }

        StringRequest request = new StringRequest(method, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        LogUtil.w("response = " + response);
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                LogUtil.e("请求失败了 msg = " + error.getMessage());
                callback.onError(error.getMessage());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                return params;
            }
        };

        queue.add(request);
    }

    /**
     * 请求图片
     *
     * @param context
     *            上下文
     * @param url
     *            图片地址
     * @param width
     *            宽度
     * @param height
     *            高度
     * @param callback
     *            回调
     */
    public static void requestBitmap(Context context, String url, int width,
                                     int height, ImageView.ScaleType scaleType, final AssistantTask.IRequestCallback callback)
    {
        // 只需要创建一个队列就可以了，标准单例写法
        if (queue == null)
        {
            synchronized (VolleyUtils.class)
            {
                if (queue == null)
                {
                    queue = Volley.newRequestQueue(context);
                }
            }
        }

        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>()
                {
                    @Override
                    public void onResponse(Bitmap response)
                    {
                        LogUtil.w("response = " + response);
                        callback.onSuccess(response);
                    }
                }, width, height, scaleType, Bitmap.Config.RGB_565,
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        LogUtil.e("请求失败了 msg = " + error.getMessage());
                        callback.onError(error.getMessage());
                    }
                });

        queue.add(request);
    }

}
