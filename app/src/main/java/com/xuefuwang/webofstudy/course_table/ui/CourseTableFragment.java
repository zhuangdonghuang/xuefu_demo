package com.xuefuwang.webofstudy.course_table.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xuefuwang.webofstudy.R;
import com.xuefuwang.webofstudy.course_table.uitls.MonthDateView;
import com.xuefuwang.webofstudy.other.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class CourseTableFragment extends BaseFragment {
    private ImageView iv_left;
    private ImageView iv_right;
    private TextView tv_date;
    private TextView tv_week;
    private TextView tv_today;
    private MonthDateView monthDateView;
    private TextView tv;

//日历页面
    @Override
    protected int getLayout() {
        return R.layout.fragment_course_table;
    }

    @Override
    protected void initViews() {


        tv = (TextView) root.findViewById(R.id.course_tv);

        iv_left = (ImageView) root.findViewById(R.id.iv_left);
        iv_right = (ImageView) root.findViewById(R.id.iv_right);
        monthDateView = (MonthDateView) root.findViewById(R.id.monthDateView);
        tv_date = (TextView)root.findViewById(R.id.date_text);
        tv_week  =(TextView)root.findViewById(R.id.week_text);
        tv_today = (TextView) root.findViewById(R.id.tv_today);

    }

    @Override
    protected void initEvents() {

        List<Integer> list = new ArrayList<Integer>();
        list.add(10);
        list.add(12);
        list.add(15);
        list.add(16);
        monthDateView.setTextView(tv_date, tv_week);
        monthDateView.setDaysHasThingList(list);
        monthDateView.setDateClick(new MonthDateView.DateClick() {

            @Override
            public void onClickOnDate() {



                tv.setText(monthDateView.getmSelYear() + "-" + (monthDateView.getmSelMonth()+1)+ "-" + monthDateView.getmSelDay() + "-0节");

                //Toast.makeText(getActivity(), "点击了：" + monthDateView.getmSelDay(), Toast.LENGTH_SHORT).show();
            }
        });


        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthDateView.onLeftClick();
                tv.setText(monthDateView.getmSelYear() + "-" + (monthDateView.getmSelMonth()+1) + "-" + monthDateView.getmSelDay() + "-0节");
            }
        });


        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthDateView.onRightClick();
                tv.setText(monthDateView.getmSelYear() + "-" +(monthDateView.getmSelMonth()+1)+ "-" + monthDateView.getmSelDay() + "-0节");

            }
        });


        tv_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthDateView.setTodayToView();
                tv.setText(monthDateView.getmSelYear()+"-"+(monthDateView.getmSelMonth()+1)+"-"+ monthDateView.getmSelDay()+"-0节");

            }
        });


    }



    @Override
    protected void initData() {

    }
}
