package com.xuefuwang.webofstudy.home.utils;

import com.google.gson.Gson;
import com.xuefuwang.webofstudy.home.bean.ChatMessage;
import com.xuefuwang.webofstudy.home.bean.Result;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/4.
 */
public class ServiceUtils {



    private static final String URL="http://www.tuling123.com/openapi/api";
    private static final String API_KEY="c2d4d3251edbf7db920eeb58ee566ac0";



    public static ChatMessage sendMessage(String msg){

        ChatMessage chatMessage = new ChatMessage();
        String jsonRes =doGet(msg);
        Gson gson = new Gson();
        Result result =null;

        try {
            result = gson.fromJson(jsonRes,Result.class);
            chatMessage.setMsg(result.getText());
        }catch (Exception e){
            chatMessage.setMsg("服务器繁忙，请稍候再试");

        }
        chatMessage.setDate(new Date());
        chatMessage.setType(ChatMessage.Type.INCOMING);

        return chatMessage;

    }



    public static String doGet(String msg)
    {
        String result = "";
        String url = setParams(msg);
        ByteArrayOutputStream baos = null;
        InputStream is = null;
        try
        {
            java.net.URL urlNet = new java.net.URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlNet
                    .openConnection();
            conn.setReadTimeout(5 * 1000);
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            is = conn.getInputStream();
            int len = -1;
            byte[] buf = new byte[128];
            baos = new ByteArrayOutputStream();

            while ((len = is.read(buf)) != -1)
            {
                baos.write(buf, 0, len);
            }
            baos.flush();
            result = new String(baos.toByteArray());
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (baos != null)
                    baos.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            try
            {
                if (is != null)
                {
                    is.close();
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }




    private static String setParams(String msg){

        String url="";

        try {
            url= URL +"?key="+API_KEY+"&info="+ URLEncoder.encode(msg, "UTF-8");



        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return url;
    }



}
