package com.xuefuwang.webofstudy.my.utils;

import android.os.Environment;

/**
 * Created by Administrator on 2016/4/8.
 */
public class Tools {

    /**
     * 检查是否存在SDCard
     * @return
     */
    public static boolean hasSdcard(){
        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }else{
            return false;
        }
    }

}
