package com.xuefuwang.webofstudy.home.utils;

import android.os.AsyncTask;

/**
 *
 * 用异步任务封装的网络请求工具类
 * Created by Administrator on 2016/3/15.
 */
public class AssistantTask extends AsyncTask<Void,Void,Object> {


   private IRequest request;

    private IRequestCallback callback;

    public AssistantTask(IRequestCallback callback, IRequest request) {

       if(request==null || callback==null){

           throw new NullPointerException("request or callback can not be null!!!");
       }

        this.callback = callback;
        this.request = request;
    }







    @Override
    protected Object doInBackground(Void... params) {


        return request.doRequest();
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }






    @Override
    protected void onPostExecute(Object o) {

        if(o==null){
            callback.onError("请求失败");

        }
        else {

            callback.onSuccess(o);

        }

    }


    /**
     * 请求接口
     */
    public interface  IRequest
    {

        /**
         * 执行请求
         * @return
         */
        Object doRequest();


    }

    /**
     * 请求回调接口
     */

    public interface  IRequestCallback{


        /**
         * 请求成功的回调方法
         *
         * @param obj  请求结果
         */
        void onSuccess(Object obj);

        /**
         * 请求失败的回调方法
         *
         * @param msg  错误信息
         */
        void onError(String msg);

    }


    /**
     * 下载进度接口
     */
    public interface IDownLoadProgress{

        /**
         * 更新进度
         * @param progress 百分比
         */
        void updataProgress(int progress);

    }



}
