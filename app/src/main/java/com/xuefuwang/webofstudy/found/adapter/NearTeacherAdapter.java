package com.xuefuwang.webofstudy.found.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.xuefuwang.webofstudy.R;
import com.xuefuwang.webofstudy.found.bean.NearTeacher;
import com.xuefuwang.webofstudy.home.utils.CirclelmageView;
import com.xuefuwang.webofstudy.home.utils.ImageLoader;
import com.xuefuwang.webofstudy.home.utils.TextUtil;

import java.util.List;

/**
 * Created by John on 2016/4/7.
 */
public class NearTeacherAdapter extends BaseAdapter {

//    private CirclelmageView circlelmageView;     //圆形图片

    private List<NearTeacher> list;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;

    public NearTeacherAdapter(Context context, List<NearTeacher> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
        //初始化imageLoader
        imageLoader = ImageLoader.getInstance(context);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list == null ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder hoalder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.found_item, null);

            hoalder = new Holder();

//            circlelmageView = (CirclelmageView) convertView.findViewById(R.id.home_item_iv);

            hoalder.ivIcon = (CirclelmageView) convertView.findViewById(R.id.home_item_iv);
            hoalder.tvName = (TextView) convertView.findViewById(R.id.home_item_name);
            hoalder.tvminPrice = (TextView) convertView.findViewById(R.id.home_item_minPrice);
            hoalder.tvschool = (TextView) convertView.findViewById(R.id.home_item_school);
            hoalder.tvschoolage = (TextView) convertView.findViewById(R.id.home_item_schoolage);
            hoalder.tvcourseTitle = (TextView) convertView.findViewById(R.id.home_item_courseTitle);
            hoalder.tvgethonor = (TextView) convertView.findViewById(R.id.home_item_gethonor);
            hoalder.tvlookCount = (TextView) convertView.findViewById(R.id.home_item_LookCount);
            hoalder.tvgethonoro = (TextView) convertView.findViewById(R.id.home_item_gethonoro);
            hoalder.tvgethonor = (TextView) convertView.findViewById(R.id.home_item_gethonoroo);
            hoalder.tvcourseCount = (TextView) convertView.findViewById(R.id.home_item_CourseCount);

            hoalder.tvcommentCount = (TextView) convertView.findViewById(R.id.home_item_CommentCount);

            hoalder.tvhome_item_jl = (TextView) convertView.findViewById(R.id.home_item_jl);

            hoalder.ratingbar = (RatingBar) convertView.findViewById(R.id.adapter_game_list_rb);

            convertView.setTag(hoalder);
        } else {
            hoalder = (Holder) convertView.getTag();
        }

        NearTeacher nearTeacher = list.get(position);
        hoalder.tvName.setText(nearTeacher.getTeacherName());

        hoalder.tvminPrice.setText("￥" + nearTeacher.getMinPrice() + "起");
        hoalder.tvschool.setText(nearTeacher.getGraduateSchool());
        hoalder.tvschoolage.setText(nearTeacher.getSchoolAge() + "年教龄");
        hoalder.tvcourseTitle.setText(nearTeacher.getCourseTitle());
        hoalder.tvgethonor.setText(nearTeacher.getGetHonor());

        imageLoader.displayImage("http://112.74.128.53:9997/" + nearTeacher.getPhoneLink(),hoalder.ivIcon,true);

        //拼接颜色
        String look = "浏览：" + nearTeacher.getLookCount();
        SpannableString spannableString = new SpannableString(look);
        spannableString = TextUtil.getColorSpan(spannableString, 3, look.length(), Color.RED);
        hoalder.tvlookCount.setText(spannableString);//浏览记录


        //拼接颜色
        String count = "课程：" + nearTeacher.getCourseCount();
        SpannableString spannable = new SpannableString(count);
        spannable = TextUtil.getColorSpan(spannable, 3, count.length(), Color.RED);

        hoalder.tvcourseCount.setText(spannable); //课程


        //拼接颜色
        String comm = "评论：" + nearTeacher.getCommentCount();
        SpannableString spannablee = new SpannableString(comm);
        spannablee = TextUtil.getColorSpan(spannablee, 3, comm.length(), Color.RED);
        hoalder.tvcommentCount.setText(spannablee);//评论

        hoalder.tvhome_item_jl.setText(nearTeacher.getDistance() + "km"); //距离


        //评分
        int score = 0;
        try {

            score = (int) Float.parseFloat(String.valueOf(nearTeacher.getStuCount() + 2));

        } catch (NumberFormatException e) {

            e.printStackTrace();

        }

        hoalder.ratingbar.setProgress(score);
        //设置圆形头像
//        circlelmageView.setBitmapUrl(nearTeacher.getPhoneLink());


        return convertView;
    }


    class Holder {
        CirclelmageView ivIcon;
        TextView tvName;
        TextView tvminPrice;
        TextView tvschool;
        TextView tvschoolage;
        TextView tvcourseTitle;
        TextView tvgethonor;
        TextView tvlookCount;
        TextView tvgethonoro;
        TextView tvgethonoroo;
        TextView tvcourseCount;
        TextView tvcommentCount;
        TextView tvhome_item_jl;
        RatingBar ratingbar;
    }
}
