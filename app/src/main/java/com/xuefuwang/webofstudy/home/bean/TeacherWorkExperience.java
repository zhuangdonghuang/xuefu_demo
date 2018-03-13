package com.xuefuwang.webofstudy.home.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class TeacherWorkExperience {

    /**
     * ID : 6571
     * UserID : 938
     * TypeID : 2
     * StartDate : 2012-07
     * EndDate : 2015-08
     * PlaceName : 新启航教育机构
     * Subject : 大学生
     * Desctiption : 本人有三年的家教经验，做过一对一、一对二、以及大小班的辅助，小学初中高中都辅导过，课堂气氛良好，不仅能辅导学生功课，且可以face to face与学生交谈，善于开导学生。
     * CreateTime : 2016-03-13T10:43:38.34
     */

    private int ID;
    private int UserID;
    private int TypeID;
    private String StartDate;
    private String EndDate;
    private String PlaceName;
    private String Subject;
    private String Desctiption;
    private String CreateTime;

    public static TeacherWorkExperience objectFromData(String str) {

        return new Gson().fromJson(str, TeacherWorkExperience.class);
    }

    public static List<TeacherWorkExperience> arrayTeacherWorkExperienceFromData(String str) {

        Type listType = new TypeToken<ArrayList<TeacherWorkExperience>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int TypeID) {
        this.TypeID = TypeID;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String EndDate) {
        this.EndDate = EndDate;
    }

    public String getPlaceName() {
        return PlaceName;
    }

    public void setPlaceName(String PlaceName) {
        this.PlaceName = PlaceName;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public String getDesctiption() {
        return Desctiption;
    }

    public void setDesctiption(String Desctiption) {
        this.Desctiption = Desctiption;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }
}
