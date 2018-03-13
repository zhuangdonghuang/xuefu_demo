package com.xuefuwang.webofstudy.home.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class TeacherClassInfo {

    /**
     * CourseTitle : 初中1—3年级（数学、英语、物理、化学、生物）,初中一元试课,高中1—3年级（数学、英语、化学、物理、生物）,高中一元试课,小学全科
     * ClassType : 老师上门/学生上门/协商地点
     * TeacherClassAddress : 南山区
     * CourseCategoryName : 高中/高一,高二,高三,高考/数学,英语,物理,化学,生物
     */

    private String CourseTitle;
    private String ClassType;
    private String TeacherClassAddress;
    private String CourseCategoryName;

    public static TeacherClassInfo objectFromData(String str) {

        return new Gson().fromJson(str, TeacherClassInfo.class);
    }

    public static List<TeacherClassInfo> arrayTeacherClassInfoFromData(String str) {

        Type listType = new TypeToken<ArrayList<TeacherClassInfo>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getCourseTitle() {
        return CourseTitle;
    }

    public void setCourseTitle(String CourseTitle) {
        this.CourseTitle = CourseTitle;
    }

    public String getClassType() {
        return ClassType;
    }

    public void setClassType(String ClassType) {
        this.ClassType = ClassType;
    }

    public String getTeacherClassAddress() {
        return TeacherClassAddress;
    }

    public void setTeacherClassAddress(String TeacherClassAddress) {
        this.TeacherClassAddress = TeacherClassAddress;
    }

    public String getCourseCategoryName() {
        return CourseCategoryName;
    }

    public void setCourseCategoryName(String CourseCategoryName) {
        this.CourseCategoryName = CourseCategoryName;
    }
}
