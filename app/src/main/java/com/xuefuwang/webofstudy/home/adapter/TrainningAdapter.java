package com.xuefuwang.webofstudy.home.adapter;

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
import com.xuefuwang.webofstudy.home.bean.Trainning;
import com.xuefuwang.webofstudy.home.utils.CirclelmageView;
import com.xuefuwang.webofstudy.home.utils.ImageLoader;
import com.xuefuwang.webofstudy.home.utils.TextUtil;

import java.util.List;

/**
 *
 * 培学适配器
 * Created by Administrator on 2016/4/2.
 */
public class TrainningAdapter extends BaseAdapter {


    //圆形图片
    private CirclelmageView circlelmageView;

    private List<Trainning> trainlist;
    private LayoutInflater inflater;

    private ImageLoader imageLoader;

    public TrainningAdapter(Context context,List<Trainning> trainlist) {
        this.trainlist = trainlist;
         inflater =LayoutInflater.from(context);

        //初始化imageLoader
        imageLoader = ImageLoader.getInstance(context);
    }

    @Override
    public int getCount() {
        return trainlist == null ? 0 : trainlist.size();
    }

    @Override
    public Object getItem(int position) {
        return trainlist== null ? null : trainlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TrainningHolder hoalder =null;
        if(convertView ==null){

            convertView =inflater.inflate(R.layout.home_item,null);

            hoalder = new TrainningHolder();

            circlelmageView= (CirclelmageView) convertView.findViewById(R.id.home_item_iv);

            hoalder.ivIcon = (CirclelmageView) convertView.findViewById(R.id.home_item_iv);
            hoalder.tvName = (TextView) convertView.findViewById(R.id.home_item_name);
            hoalder.tvminPrice = (TextView) convertView.findViewById(R.id.home_item_minPrice);
            hoalder.tvschool = (TextView) convertView.findViewById(R.id.home_item_school);
            hoalder.tvschoolage = (TextView) convertView.findViewById(R.id.home_item_schoolage);
            hoalder.tvcourseTitle = (TextView) convertView.findViewById(R.id.home_item_courseTitle);
            hoalder. tvgethonor = (TextView) convertView.findViewById(R.id.home_item_gethonor);
            hoalder. tvlookCount = (TextView) convertView.findViewById(R.id.home_item_LookCount);
            hoalder. tvgethonoro = (TextView) convertView.findViewById(R.id.home_item_gethonoro);
            hoalder. tvgethonor = (TextView) convertView.findViewById(R.id.home_item_gethonoroo);
            hoalder. tvcourseCount = (TextView) convertView.findViewById(R.id.home_item_CourseCount);

            hoalder. tvcommentCount = (TextView) convertView.findViewById(R.id.home_item_CommentCount);

            hoalder.tvhome_item_jl = (TextView) convertView.findViewById(R.id.home_item_jl);

            hoalder.ratingbar = (RatingBar) convertView.findViewById(R.id.adapter_game_list_rb);

            convertView.setTag(hoalder);

        }else {


            hoalder  = (TrainningHolder) convertView.getTag();

        }


        Trainning trainning = trainlist.get(position);

        hoalder.tvName.setText(trainning.getTeacherName());


      /*  //截取字符串
        String snap = new String(trainning.getMinPrice());
        String a[] =snap.split(".");

        final String str = a[0];*/

       /* //拼接颜色
        String price ="￥"+trainning.getMinPrice()+"起";
        SpannableString sp = new SpannableString(price);

        sp= TextUtil.getColorSpan(sp, 7, price.length(), Color.BLACK);
*/

        hoalder.tvminPrice.setText("￥"+trainning.getMinPrice()+"起");
        hoalder.tvschool.setText(trainning.getGraduateSchool());
        hoalder.tvschoolage.setText(trainning.getSchoolAge()+"年教龄");
        hoalder.tvcourseTitle.setText(trainning.getCourseTitle());
        hoalder.tvgethonor.setText(trainning.getGetHonor());


        //拼接颜色
        String look = "浏览："+trainning.getLookCount();
        SpannableString spannableString = new SpannableString(look);
        spannableString= TextUtil.getColorSpan(spannableString, 3, look.length(), Color.RED);
        hoalder.tvlookCount.setText(spannableString);//浏览记录



        //拼接颜色
        String count = "课程："+trainning.getCourseCount();
        SpannableString spannable = new SpannableString(count);
        spannable= TextUtil.getColorSpan(spannable, 3,count.length(), Color.RED);

       hoalder.tvcourseCount.setText(spannable); //课程



        //拼接颜色
        String comm = "评论："+trainning.getCommentCount();
        SpannableString spannablee = new SpannableString(comm);
        spannablee= TextUtil.getColorSpan(spannablee, 3, comm.length(), Color.RED);
        hoalder.tvcommentCount.setText( spannablee);//评论

        hoalder.tvhome_item_jl.setText(trainning.getDistance()+"km"); //距离











        //评分
        int score=0;
        try {

            score = (int) Float.parseFloat(String.valueOf(trainning.getStuCount()+2));

        } catch (NumberFormatException e){

            e.printStackTrace();
        }

        hoalder.ratingbar.setProgress(score);


        //头像
       imageLoader.displayImage("http://112.74.128.53:9997/" + trainning.getPhoneLink(), hoalder.ivIcon, true);

        //设置圆形头像
//        circlelmageView.setBitmapUrl(trainning.getPhoneLink());


        return convertView;

    }


    class TrainningHolder{

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
