package com.xuefuwang.webofstudy.home.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuefuwang.webofstudy.R;
import com.xuefuwang.webofstudy.home.bean.TeacherClassInfo;
import com.xuefuwang.webofstudy.home.bean.TeacherEducationExperience;
import com.xuefuwang.webofstudy.home.bean.TeacherInfo;
import com.xuefuwang.webofstudy.home.bean.TeacherWorkExperience;
import com.xuefuwang.webofstudy.home.utils.AssistantTask;
import com.xuefuwang.webofstudy.home.utils.PxHttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 陪优的老师简介
 * Created by Administrator on 2016/4/2.
 */
public class IntroduceFragmentPt extends Fragment {

//    private TextView mTextView;

    //授课教龄
    private TextView tvSchoolAge;

    //老师国籍
    private TextView tvCountry;

    //最高学历
    private TextView tvDegree;

    //毕业院校
    private TextView tvGraduateSchool1;
    private TextView tvGraduateSchool2;

    //自我介绍
    private TextView tvProfile;

    //授课范围
    private TextView tvArea;

    //授课方式
    private TextView tvTeachWay;

    //授课科目
    private TextView tvSubjectStudy;

    //起止时间
    private TextView tvStartAndEndDate1;
    private TextView tvStartAndEndDate2;

    //所学专业
    private TextView tvSubject;

    //具体描述
    private TextView tvIntroduce;

    //工作单位
    private TextView tvWorkPlace;

    //就任职位
    private TextView tvJob;

    //职业描述
    private TextView tvWorkIntroduce;

    //教师id
    private int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        id = getArguments().getInt("id", 0);
    }

    private void requestInfo() {
        PxHttpUtil.requestTeacherInfoByVolley(getActivity(), id, new AssistantTask.IRequestCallback() {
            @Override
            public void onSuccess(Object obj) {
                JSONObject root = null;
                try {
                    root = new JSONObject(obj.toString());

                    /**
                     * 教师个人信息
                     */
                    JSONArray arrayTeacherInfo = root.getJSONArray("jsonUserInfo");
                    JSONObject teacherInfo = arrayTeacherInfo.getJSONObject(0);

                    TeacherInfo teacheroInfo = TeacherInfo.objectFromData(teacherInfo.toString());
                    tvSchoolAge.setText("授课教龄：" + teacheroInfo.getSchoolAge());

                    tvCountry.setText("老师国籍：" + teacheroInfo.getCountry());

                    tvDegree.setText("最高学历：" + teacheroInfo.getDegree());

                    String graduateSchool = getString(R.string.graduateSchool, teacheroInfo.getGraduateSchool());
                    tvGraduateSchool1.setText(graduateSchool);

                    tvProfile.setText(teacheroInfo.getProfile());

                    tvArea.setText("授课范围：" + teacheroInfo.getArea());

                    /**
                     * 授课相关信息
                     */
                    JSONArray teachClassInfo = root.getJSONArray("jsonTeachClassInfo");
                    List<TeacherClassInfo> teacherClassInfos = TeacherClassInfo.arrayTeacherClassInfoFromData(teachClassInfo.toString());
                    TeacherClassInfo teacherClassInfo = teacherClassInfos.get(0);

                    tvTeachWay.setText("授课方式：" + teacherClassInfo.getClassType());

                    tvSubjectStudy.setText("授课科目：" + teacherClassInfo.getCourseCategoryName());

                    /**
                     * 教学经验相关信息
                     */
                    JSONArray jsonEducationExperience = root.getJSONArray("jsonEducationExperience");
                    JSONObject jsonEducationExperienceJSONObject = jsonEducationExperience.getJSONObject(0);
                    TeacherEducationExperience teacherEducationExperience = TeacherEducationExperience.objectFromData(jsonEducationExperienceJSONObject.toString());

                    String endDate = teacherEducationExperience.getEndDate();

                    if ("-1".equals(endDate)) {
                        endDate = "至今";
                    } else {
                        endDate = "至" + endDate;
                    }

                    String startAndEndDate = "起止时间：" + teacherEducationExperience.getStartDate() + endDate;

                    tvStartAndEndDate1.setText(startAndEndDate);
                    graduateSchool = getString(R.string.graduateSchool, teacherEducationExperience.getPlaceName());
                    tvGraduateSchool2.setText(graduateSchool);

                    tvSubject.setText("所学专业：" + teacherEducationExperience.getSubject());

                    tvIntroduce.setText("具体描述：" + teacherEducationExperience.getDesctiption());

                    tvStartAndEndDate2.setText(startAndEndDate);

                    /**
                     * 工作经验相关信息
                     */
                    JSONArray jsonWorkExperience = root.getJSONArray("jsonWorkExperience");

                    JSONObject jsonWorkExperienceJSONObject = jsonWorkExperience.getJSONObject(0);
                    TeacherWorkExperience teacherWorkExperience = TeacherWorkExperience.objectFromData(jsonWorkExperienceJSONObject.toString());

                    tvWorkPlace.setText("工作单位:" + teacherWorkExperience.getPlaceName());

                    tvJob.setText("就任职位：" + teacherWorkExperience.getSubject());

                    tvWorkIntroduce.setText("职业描述：" + teacherWorkExperience.getDesctiption());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String msg) {

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_introduce_py, container, false);
//        mTextView = (TextView) view.findViewById(R.id.id_info);
        tvSchoolAge = (TextView) view.findViewById(R.id.fragment_jl);
        tvCountry = (TextView) view.findViewById(R.id.fragment_gj);
        tvDegree = (TextView) view.findViewById(R.id.fragment_xl);
        tvGraduateSchool1 = (TextView) view.findViewById(R.id.fragment_yx);
        tvProfile = (TextView) view.findViewById(R.id.fragment_js);
        tvArea = (TextView) view.findViewById(R.id.fragment_qy);
        tvTeachWay = (TextView) view.findViewById(R.id.fragment_fs);
        tvSubjectStudy = (TextView) view.findViewById(R.id.fragment_km);
        tvStartAndEndDate1 = (TextView) view.findViewById(R.id.fragment_sj);
        tvGraduateSchool2 = (TextView) view.findViewById(R.id.fragment_school);
        tvSubject = (TextView) view.findViewById(R.id.fragment_zy);
        tvIntroduce = (TextView) view.findViewById(R.id.fragment_ms);
        tvStartAndEndDate2 = (TextView) view.findViewById(R.id.fragment_gzsj);
        tvWorkPlace = (TextView) view.findViewById(R.id.fragment_work_place);
        tvJob = (TextView) view.findViewById(R.id.fragment_job);
        tvWorkIntroduce = (TextView) view.findViewById(R.id.fragment_work_introduce);

        if (id != 0) {
            requestInfo();
        }
        return view;
    }

}
