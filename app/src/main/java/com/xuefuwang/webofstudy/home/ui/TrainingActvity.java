package com.xuefuwang.webofstudy.home.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.xuefuwang.webofstudy.R;
import com.xuefuwang.webofstudy.home.adapter.TrainningAdapter;
import com.xuefuwang.webofstudy.home.bean.Trainning;
import com.xuefuwang.webofstudy.home.dropmenu.ConstellationAdapter;
import com.xuefuwang.webofstudy.home.dropmenu.DropDownMenu;
import com.xuefuwang.webofstudy.home.dropmenu.GirdDropDownAdapter;
import com.xuefuwang.webofstudy.home.dropmenu.ListDropDownAdapter;
import com.xuefuwang.webofstudy.home.utils.AssistantTask;
import com.xuefuwang.webofstudy.home.utils.LoadMoreListView;
import com.xuefuwang.webofstudy.home.utils.PxHttpUtil;
import com.xuefuwang.webofstudy.other.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 培学内容老师展示页面
 *
 * Created by Administrator on 2016/4/1.
 */
public class TrainingActvity extends Activity {



  //筛选栏

    @InjectView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    private String headers[] = {"全部课程", "人气排序","筛选"};
    private List<View> popupViews = new ArrayList<>();

    private GirdDropDownAdapter cityAdapter;
    private ListDropDownAdapter ageAdapter;
    private ConstellationAdapter constellationAdapter;

    private String citys[] = {"不限", "语文", "数学", "英语", "奥数", "物理", "化学", "生物", "理综", "历史", "政治", "地理","文综"};
    private String ages[] = {"不限","价格由高到底", "价格由低到高", "好评数排序", "人气排序", "离我最近"};
    private String constellations[] = {"不限", "男", "女", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"};

    private int constellationPosition = 0;





    //下拉刷新及listView

    private LoadMoreListView listView;
    private SwipeRefreshLayout refreshLayout;

    private List<Trainning> list;

    private EditText et;
    private TextView tv;

    private TrainningAdapter adapter;

    private int page=1;
    private TextView tvLoad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train_comm);

        ButterKnife.inject(this);



        initview();

        initDate();
        initEvents();

        initData();

    }


    private void initDate() {



        //搜索
        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainingActvity.this, SearchActivity.class);
                startActivity(intent);
            }
        });


        //返回
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }



    private void initview() {


        tv = (TextView) findViewById(R.id.home_comm_break);
        et = (EditText) findViewById(R.id.et_search);

        listView = (LoadMoreListView) findViewById(R.id.home_comm_lv);
        tvLoad = new TextView(TrainingActvity.this);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.sf);

        //下拉刷新变换颜色
         refreshLayout.setColorSchemeResources(android.R.color.holo_purple, android.R.color.holo_blue_bright, android.R.color.holo_orange_light,
                 android.R.color.holo_red_light);









        //全部课程menu
        final ListView cityView = new ListView(this);
        cityAdapter = new GirdDropDownAdapter(this, Arrays.asList(citys));
        cityView.setDividerHeight(0);
        cityView.setAdapter(cityAdapter);

        //人气排序menu
        final ListView ageView = new ListView(this);
        ageView.setDividerHeight(0);
        ageAdapter = new ListDropDownAdapter(this, Arrays.asList(ages));
        ageView.setAdapter(ageAdapter);



        //筛选menu
        final View constellationView = getLayoutInflater().inflate(R.layout.custom_layout, null);
        GridView constellation = ButterKnife.findById(constellationView, R.id.constellation);
        constellationAdapter = new ConstellationAdapter(this, Arrays.asList(constellations));
        constellation.setAdapter(constellationAdapter);
        TextView ok = ButterKnife.findById(constellationView, R.id.ok);



        //筛选  确定
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDropDownMenu.setTabText(constellationPosition == 0 ? headers[2] : constellations[constellationPosition]);
                mDropDownMenu.closeMenu();
            }
        });

        // 适配器添加入popupViews集合中

        popupViews.add(cityView);
        popupViews.add(ageView);
        popupViews.add(constellationView);



        //全部课程的监听事件
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : citys[position]);
                mDropDownMenu.closeMenu();
            }
        });



        //人气排序监听事件
        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ageAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : ages[position]);
                mDropDownMenu.closeMenu();
            }
        });


        //筛选的监听事件
        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                constellationAdapter.setCheckItem(position);
                constellationPosition = position;
            }
        });

        //init context view
      /*  TextView contentView = new TextView(this);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        contentView.setText("内容显示区域");
        contentView.setGravity(Gravity.CENTER);

        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);*/



        TextView contentView = new TextView(this);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        contentView.setText("当前筛选：暂无");
        contentView.setGravity(Gravity.CENTER);

        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);



        //tabs 所有标题，popupViews  所有菜单，contentView 内容
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews,contentView);





    }



    private void initEvents() {


        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                page=1;
                //下拉刷新先清空数据
             list.clear();

                //再请求网络
                requestList();


            }
        });


        listView.setLoadMoreListener(new LoadMoreListView.ILoadMoreListener() {
            @Override
            public void onLoadMore() {

                page++;
                tvLoad.setText("正在加载");
                listView.addFooterView(tvLoad);
                requestList();


            }
        });





        //点击详情页面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(TrainingActvity.this,TrainingcommActivity.class);
                int idInt = list.get(position).getTeacherID();
                intent.putExtra("id",idInt);
                startActivity(intent);

            }
        });



    }



    protected void initData() {
        list = new ArrayList<>();
        adapter = new TrainningAdapter(TrainingActvity.this,list);
        listView.setAdapter(adapter);
        listView.addFooterView(tvLoad);
        requestList();
    }

    /**
     * Volley
     *
     */

    private void requestList() {
        PxHttpUtil.requestTeacherListByVolley(this, page, new AssistantTask.IRequestCallback() {
            @Override
            public void onSuccess(Object obj) {
                try {
                    JSONObject root = new JSONObject(obj.toString());

                    JSONArray info = root.getJSONArray(Constants.RESPONSE_RESULT_VALUE);

                    List<Trainning> train = Trainning.arrayTrainningFromData(info.toString());

                    list.addAll(train);

                    adapter.notifyDataSetChanged();

                    //刷新完数据就关闭刷新
                    refreshLayout.setRefreshing(false);

                    listView.removeFooterView(tvLoad);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String msg) {


                //隐藏正在刷新的效果
                refreshLayout.setRefreshing(false);

                listView.removeFooterView(tvLoad);
            }
        });


    }



    //筛选栏
    @Override
    public void onBackPressed() {
        //退出activity前关闭菜单
        if (mDropDownMenu.isShowing()) {
            mDropDownMenu.closeMenu();
        } else {
            super.onBackPressed();
        }

    }

}
