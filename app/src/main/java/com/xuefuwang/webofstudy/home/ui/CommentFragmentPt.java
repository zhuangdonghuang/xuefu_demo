package com.xuefuwang.webofstudy.home.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.xuefuwang.webofstudy.R;


/**
 * Created by Administrator on 2016/4/3.
 */
public class CommentFragmentPt extends Fragment {


    private ListView mListView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabfagmenttt_tab, container, false);
        /*mListView = (ListView) view
                .findViewById(R.id.id_stickynavlayout_innerscrollview);*/


        return view;
    }





}