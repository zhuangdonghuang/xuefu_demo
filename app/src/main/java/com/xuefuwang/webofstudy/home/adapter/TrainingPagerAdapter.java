package com.xuefuwang.webofstudy.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/4/3.
 */
public class TrainingPagerAdapter extends FragmentPagerAdapter {



    private List<Fragment> list;

    public TrainingPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    public TrainingPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list==null ? null : list.get(position);
    }

    @Override
    public int getCount() {
        return list==null ? 0: list.size();
    }
}
