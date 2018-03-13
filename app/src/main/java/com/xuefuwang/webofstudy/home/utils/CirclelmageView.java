package com.xuefuwang.webofstudy.home.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.widget.ImageView;



/**
 * 获取网络图片地址，变圆形图片
 * Created by Administrator on 2016/3/21.
 */
public class CirclelmageView extends ImageView {


     private float strokeWidth=3;


    public CirclelmageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setBitmapUrl(final String httpUrl){



        new AssistantTask(new AssistantTask.IRequestCallback() {
            @Override
            public void onSuccess(Object obj) {

                Bitmap circleBitmap = createCircleBitmap((Bitmap) obj);
                setImageBitmap(circleBitmap);
            }

            @Override
            public void onError(String msg) {
                LogUtil.e(msg);


            }
        }, new AssistantTask.IRequest() {
            @Override
            public Object doRequest() {
                return HttpUtil.requestBitmap(httpUrl);
            }
        }).execute();

    }




    @Override
    protected void onDraw(Canvas canvas) {


        super.onDraw(canvas);

        //设置画圆的画笔
        Paint strokePaint = new Paint();
        strokePaint.setColor(Color.RED);
        strokePaint.setStyle(Paint.Style.STROKE); //设置空心圆的样式
        strokePaint.setStrokeWidth(strokeWidth); //设置宽度
        strokePaint.setAntiAlias(true);

        //画圆的属性：圆心，半径,设置颜色等
       canvas.drawCircle(getWidth() / 2, getWidth() / 2, (getWidth() - strokeWidth) / 2, strokePaint);



    }


    private Bitmap createCircleBitmap(Bitmap resource){

        int width =resource.getWidth();

        int height = width;


        Paint paint = new Paint();
        //抗锯齿
        paint.setAntiAlias(true);


        //先创建一张空的Bitmap
        Bitmap target = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        //创建一张空的画布
        Canvas canvas = new Canvas(target);

        //画一个和原图片宽高一样的内切圆
        canvas.drawCircle(width/2,height/2,width/2,paint);

        //取交集的部分(也是重合的部分); SRC_IN 属性
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(resource,0,0,paint);


        return  target;

    }



}
