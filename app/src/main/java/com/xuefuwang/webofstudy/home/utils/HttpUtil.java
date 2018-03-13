package com.xuefuwang.webofstudy.home.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 网络请求工具类
 * Created by Administrator on 2016/3/15.
 */
public class HttpUtil {


    public static Object doGet(String httpUrl) {


        InputStream inputStream = null;
        try {

            //创建一个URL
            URL url = new URL(httpUrl);

            //打开网络连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置连接的属性
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setUseCaches(true);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            //开始连接
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {

                inputStream = connection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line = null;

                StringBuffer buffer = new StringBuffer();

                while ((line = reader.readLine()) != null) {

                    buffer.append(line);

                }


                String result = buffer.toString();

                LogUtil.d("tag", "result=" + result);


                return buffer.toString();


            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }


        LogUtil.e("请求失败");
        return null;

    }


    public static final String doPost(String httpUrl, Map<String, String> params) {

        if (httpUrl == null || params == null) {

            throw new NullPointerException("URL or Params are not be null");


        }


        //把map转化为set为迭代器做准备
        Set<Map.Entry<String, String>> entrySet = params.entrySet();

        Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();

        StringBuffer paramsBuffer = new StringBuffer();


        //迭代器获取Velus 和Key
        while (iterator.hasNext()) {

            Map.Entry<String, String> entry = iterator.next();

            //从对象里面获取Key
            String key = entry.getKey();

            //拼接Key
            paramsBuffer.append(key);
            paramsBuffer.append("=");

            //从对象里面获取Value
            String value = entry.getValue();
            //拼接value
            paramsBuffer.append(value);
            paramsBuffer.append("&");

        }

        //去掉最后的屁股"&"
        String paramsString = paramsBuffer.toString();
        paramsString.substring(0, paramsString.length() - 1);

        LogUtil.d("params=", paramsString);


        OutputStream outputStream = null;
        InputStream inputStream = null;

        try {
            URL url = new URL(httpUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(true);

            connection.connect();


            //获取输出流
            outputStream = connection.getOutputStream();

            //写入参数
            outputStream.write(paramsString.getBytes());

            //刷新
            outputStream.flush();

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {


                inputStream = connection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line = null;

                StringBuffer buffer = new StringBuffer();

                while ((line = reader.readLine()) != null) {

                    buffer.append(line);

                }


                String result = buffer.toString();
                LogUtil.d(result);
                return result;


            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {

                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        LogUtil.e("请求失败");
        return null;
    }


    public static File downloadFile(String httpUrl, File dir, String fileName, AssistantTask.IDownLoadProgress listenr) {


        InputStream inputStream = null;

        FileOutputStream fileOutputStream = null;


        //判断目录是否存在
        if (!dir.exists()) {

            dir.mkdirs();
        }

        File renameFile = new File(dir, fileName);


        try {
            URL url = new URL(httpUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            ;

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                inputStream = connection.getInputStream();

                fileOutputStream = new FileOutputStream(renameFile);

                byte[] buffer = new byte[256];


                long length = connection.getContentLength();
                LogUtil.e("tag", "开始下载 length=" + length);


                long down = 0;


                while (true) {

                    int real = inputStream.read(buffer);

                    if (real == -1) {

                        break;
                    }

                    down += real;

                    fileOutputStream.write(buffer, 0, real);

                    fileOutputStream.flush();

                    //计算下载进度百分比
                    int per = (int) (down * 100 / length);

                    LogUtil.d("down=" + down + ",per=" + per);

                    if (listenr != null) {
                        listenr.updataProgress(per);
                    }


                }

                LogUtil.d("tag", "下载成功");

                return renameFile;


            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        LogUtil.e("tag", "请求失败");

        return null;

    }


    /**
     * 图片下载
     *
     * @param httpUrl
     * @return
     */
    public static Bitmap requestBitmap(String httpUrl) {

        InputStream inputStream = null;
        try {
            URL url = new URL(httpUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                inputStream = connection.getInputStream();

                LogUtil.d("下载成功");

                return BitmapFactory.decodeStream(inputStream);

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LogUtil.e("tag", "请求失败");

        return null;
    }



}
