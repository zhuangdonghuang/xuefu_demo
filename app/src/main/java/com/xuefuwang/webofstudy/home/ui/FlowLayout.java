package com.xuefuwang.webofstudy.home.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/2.
 */
public class FlowLayout extends ViewGroup {

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    //测量屏幕宽高值
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //设置测量模式
        int sizeWidth=MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth =MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight =MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight =MeasureSpec.getMode(heightMeasureSpec);



        //如果模式是wrap_content
        int width=0;
        int height=0;


        //记录每一行的宽度与高度
        int lineWidth =0;
        int lineHeight=0;

        //得到内部元素的个数
        int cCount = getChildCount();
        for(int i=0;i<cCount;i++){

            View child =getChildAt(i);

            //测量于View的宽和高
            measureChild(child,widthMeasureSpec,heightMeasureSpec);

            //得到LayoutParams
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            //子View占据的宽
            int childWidth = child.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;

            //子View占据的高度
            int childHeight =child.getMeasuredHeight()+lp.topMargin+lp.bottomMargin;


            //如果当前行的宽度+ 子view的宽度大于 容器（屏幕）宽度，那么就换行

            if(lineWidth +childHeight >sizeWidth -getPaddingLeft()-getPaddingRight()){

                //对比得到最大的宽度
                width =Math.max(width,lineWidth);

                //重置lineWidth
                lineWidth =childWidth;
                //记录行高
                height +=lineHeight;
                lineHeight=childHeight;

            }else {

                //未换行


                //叠加行宽
                lineWidth +=childWidth;

                //得到当前行最大的高度
                lineHeight=Math.max(lineHeight,childHeight);

            }


            //最后一个控件，需要叠加高和宽

            if(i==cCount -1){

                //对比一下
                width =Math.max(lineWidth,width);
                height +=lineHeight;

            }

        }


        Log.e("Tag", "sizeHeight=" + sizeHeight);
        Log.e("Tag","sizeWidth="+sizeWidth);


        //如果当前模式
        setMeasuredDimension(

                //wrap_conten 或者是match_parent
                modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width+getPaddingLeft()+getPaddingRight(),


                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height +getPaddingTop()+ getPaddingBottom()
        );

    }


    /**
     * 存储所有的View
     */
    private List<List<View>> mAllViews =new ArrayList<List<View>>();


    /**
     * 每一行高度
     */
    private List<Integer> mLineHeight = new ArrayList<Integer>();




    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {


        mAllViews.clear();
        mLineHeight.clear();

        //当前ViewGroup的宽度
        int width =getWidth();

        int lineWidth=0;
        int lineHeight=0;

        //存放每一行的View
        List<View> lineViews= new ArrayList<View>();

        //子View的个数
        int cCount = getChildCount();

        for (int i=0;i<cCount;i++){

            View child = getChildAt(i);

            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            int childWidth =child.getMeasuredWidth();
            int childHeight =child.getMeasuredHeight();

            //如果需要换行
            if(childWidth + lineWidth + lp.leftMargin+lp.rightMargin >width -getPaddingLeft()-getPaddingRight()){

                //记录LineHeigt
                mLineHeight.add(lineHeight);

                //记录当前行的Views
                mAllViews.add(lineViews);

                //重置我们的行宽和行高
                lineWidth=0;

                lineHeight =childHeight + lp.topMargin + lp.bottomMargin;

                //重置我们的View集合
                lineViews = new ArrayList<View>();

            }


            //不需要换行

            lineWidth += childWidth + lp.leftMargin+ lp.rightMargin;
            lineHeight =Math.max(lineHeight,childHeight+lp.topMargin
                    +lp.bottomMargin);

            lineViews.add(child);

        }

        //处理最后一行
        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);


        //设置子View的位置
        int left =getPaddingLeft();
        int top =getPaddingTop();

        //行数
        int lineNum =mAllViews.size();

        for (int i=0;i<lineNum;i++){

            //当前行的所有的View
            lineViews = mAllViews.get(i);

            lineHeight = mLineHeight.get(i);

            for(int j=0;j<lineViews.size();j++){

                View child =lineViews.get(j);

                //判断child的状态
                if(child.getVisibility() == View.GONE){

                    continue;
                }

                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

                int lc = left + lp.leftMargin;
                int tc =top +lp.topMargin;
                int rc =lc+child.getMeasuredWidth();
                int bc =tc +child.getMeasuredHeight();

                //为子View进行布局
                child.layout(lc,tc,rc,bc);


                left +=child.getMeasuredWidth() +lp.leftMargin+lp.rightMargin
                        +lp.rightMargin;

            }


            //重置
            left =getPaddingLeft();
            top +=lineHeight;

        }





    }


    /**
     * 与当前ViewGroup对应的LayoutParams
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {

        return new MarginLayoutParams(getContext(),attrs);
    }



}
