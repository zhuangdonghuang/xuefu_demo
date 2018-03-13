package com.xuefuwang.webofstudy.home.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * 自定义控件的三种方法：
 *
 * 1.继承原生控件。重写某些方法。或者添加一些方法
 *
 * 2，把多个控件组合在一起。形成一个控件
 *
 * 3，继承View重写onDraw方法，画出来
 *
 *
 * Created by Administrator on 2016/3/16.
 */
public class LoadMoreListView extends ListView {


    private float lastY;

    private ILoadMoreListener loadMoreListener;



    public LoadMoreListView(Context context, AttributeSet attrs){

        super(context,attrs);

        init();

    }

    private void init(){


        /*TextView footer = new TextView(getContext());
        footer.setText("正在加载中");
        addFooterView(footer);*/






        //下拉刷新
       setOnScrollListener(new OnScrollListener() {

           @Override
           public void onScrollStateChanged(AbsListView view, int scrollState) {


               //如果拉不动的时候判断
               if(scrollState == SCROLL_STATE_IDLE){

                   if (view.getLastVisiblePosition() == (view.getCount() - 1)) {

                       //获取该ListView总共的item数量
                       int count = view.getCount();


                       //获取最后一个可见的item索引
                       int lastVisiblePosition = view.getLastVisiblePosition();


                       //如果是已经显示到最后一个item了
                       if (lastVisiblePosition == count - 1) {

                           LogUtil.d("lastVisiblePosition=" + lastVisiblePosition);

                           //如果随便滑动一个位置，停下了的最后一个Item的Y坐标
                           //等于上一次停下了的最后一个item的Y坐标
                           // 那么我们认定这个位置就是ListView到底了
                           //然后我们就触发加载下一页的操作


                           //获取ListView所有复用的Item个数
                           int childCount = view.getChildCount();

                           //最后一个
                           View item = view.getChildAt(childCount - 1);

                           float currY = item.getY();

                           if (currY == lastY) {

                               //到底了。触发加载下一页的操作
                               LogUtil.d("到底了。。。。");

                               if (loadMoreListener != null) {

                                   loadMoreListener.onLoadMore();
                               }
                           }


                           //记录上一次的Y坐标
                           lastY = currY;
                       }
                   }
               }

           }


           @Override
           public void onScroll(AbsListView view, int firstVisibleItem,
                                int visibleItemCount, int totalItemCount) {




           }
       });


    }












    /**
     * 设置加载更多的监听器
     * @param listener
     */
    public void setLoadMoreListener(ILoadMoreListener listener){

        this.loadMoreListener=listener;

    }





    /**
     * 加载更多的监听
     */
    public interface  ILoadMoreListener{

        /**
         *加载更多的回调
         */
        void onLoadMore();

    }



}
