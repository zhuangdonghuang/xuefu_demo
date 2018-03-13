package com.xuefuwang.webofstudy.home.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class TeacherEducationExperience {

    /**
     * ID : 6570
     * UserID : 938
     * TypeID : 1
     * StartDate : 2015-09
     * EndDate : -1
     * PlaceName : 深圳大学
     * Subject : 传播学院
     * Desctiption : 本人虽然主修文科类专业，但是理工科出身，在对数学、化学、物理以及英语的学习有较好的心得体会
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

    public static TeacherEducationExperience objectFromData(String str) {

        return new Gson().fromJson(str, TeacherEducationExperience.class);
    }

    public static List<TeacherEducationExperience> arrayTeacherEducationExperienceFromData(String str) {

        Type listType = new TypeToken<ArrayList<TeacherEducationExperience>>() {
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
