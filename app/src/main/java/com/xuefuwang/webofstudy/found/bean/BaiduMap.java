package com.xuefuwang.webofstudy.found.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2016/4/7.
 */
public class BaiduMap {

    /**
     * TeacherID : 602
     * TeacherName : RashaAltaher
     * Sex : true
     * Profile : 掌握语言：阿拉伯语：母语
     法语：第二语言，能流利地说
     英语：获得雅思证书，能流利地说
     土耳其语：较好，一般水平
     * CourseTitle : 初中英语,高中英语,小学英语,幼儿园英语
     * PhoneLink : files/admin/2015-08-29/p19trl3itulpj1a2h1lpofmb1vmt1.jpg
     * SchoolAge : 0
     * TeacherArea : 南山区
     * TeachFeature : 因材施教,气氛活跃,教态得体,
     * CourseType : 初中/初三/英语.高中/高考/英语.小学/六年级/英语.学前/幼儿园/英语
     * CommentCount : 0
     * SumBoughtCourseCount : 0
     * CourseCount : 4
     * StuCount : 0
     * LookCount : 120
     * IsApprovalCard : 2
     * IsApprovalDegree : 0
     * IsApprovalTC : 0
     * IsApprovalProfessional : 0
     * StarPoints : 0.00
     * TeachType : 1
     * TeacherType : 3
     * MinPrice : 200.00
     * IsOneYuanTest : 200.00
     * distance : 0.39
     * RowNumber : 1
     * longitude : 113.950168
     * latitude : 22.538813
     * GetHonor : 因材施教,气氛活跃,教态得体,
     * GraduateSchool : 北京大学
     */

    private int TeacherID;
    private String TeacherName;
    private boolean Sex;
    private String Profile;
    private String CourseTitle;
    private String PhoneLink;
    private int SchoolAge;
    private String TeacherArea;
    private String TeachFeature;
    private String CourseType;
    private int CommentCount;
    private int SumBoughtCourseCount;
    private int CourseCount;
    private int StuCount;
    private int LookCount;
    private int IsApprovalCard;
    private int IsApprovalDegree;
    private int IsApprovalTC;
    private int IsApprovalProfessional;
    private String StarPoints;
    private String TeachType;
    private int TeacherType;
    private String MinPrice;
    private String IsOneYuanTest;
    private double distance;
    private int RowNumber;
    private double longitude;
    private double latitude;
    private String GetHonor;
    private String GraduateSchool;

    public static BaiduMap objectFromData(String str) {

        return new Gson().fromJson(str, BaiduMap.class);
    }

    public static List<BaiduMap> arrayBaiduMapFromData(String str) {

        Type listType = new TypeToken<ArrayList<BaiduMap>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public int getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(int TeacherID) {
        this.TeacherID = TeacherID;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String TeacherName) {
        this.TeacherName = TeacherName;
    }

    public boolean isSex() {
        return Sex;
    }

    public void setSex(boolean Sex) {
        this.Sex = Sex;
    }

    public String getProfile() {
        return Profile;
    }

    public void setProfile(String Profile) {
        this.Profile = Profile;
    }

    public String getCourseTitle() {
        return CourseTitle;
    }

    public void setCourseTitle(String CourseTitle) {
        this.CourseTitle = CourseTitle;
    }

    public String getPhoneLink() {
        return PhoneLink;
    }

    public void setPhoneLink(String PhoneLink) {
        this.PhoneLink = PhoneLink;
    }

    public int getSchoolAge() {
        return SchoolAge;
    }

    public void setSchoolAge(int SchoolAge) {
        this.SchoolAge = SchoolAge;
    }

    public String getTeacherArea() {
        return TeacherArea;
    }

    public void setTeacherArea(String TeacherArea) {
        this.TeacherArea = TeacherArea;
    }

    public String getTeachFeature() {
        return TeachFeature;
    }

    public void setTeachFeature(String TeachFeature) {
        this.TeachFeature = TeachFeature;
    }

    public String getCourseType() {
        return CourseType;
    }

    public void setCourseType(String CourseType) {
        this.CourseType = CourseType;
    }

    public int getCommentCount() {
        return CommentCount;
    }

    public void setCommentCount(int CommentCount) {
        this.CommentCount = CommentCount;
    }

    public int getSumBoughtCourseCount() {
        return SumBoughtCourseCount;
    }

    public void setSumBoughtCourseCount(int SumBoughtCourseCount) {
        this.SumBoughtCourseCount = SumBoughtCourseCount;
    }

    public int getCourseCount() {
        return CourseCount;
    }

    public void setCourseCount(int CourseCount) {
        this.CourseCount = CourseCount;
    }

    public int getStuCount() {
        return StuCount;
    }

    public void setStuCount(int StuCount) {
        this.StuCount = StuCount;
    }

    public int getLookCount() {
        return LookCount;
    }

    public void setLookCount(int LookCount) {
        this.LookCount = LookCount;
    }

    public int getIsApprovalCard() {
        return IsApprovalCard;
    }

    public void setIsApprovalCard(int IsApprovalCard) {
        this.IsApprovalCard = IsApprovalCard;
    }

    public int getIsApprovalDegree() {
        return IsApprovalDegree;
    }

    public void setIsApprovalDegree(int IsApprovalDegree) {
        this.IsApprovalDegree = IsApprovalDegree;
    }

    public int getIsApprovalTC() {
        return IsApprovalTC;
    }

    public void setIsApprovalTC(int IsApprovalTC) {
        this.IsApprovalTC = IsApprovalTC;
    }

    public int getIsApprovalProfessional() {
        return IsApprovalProfessional;
    }

    public void setIsApprovalProfessional(int IsApprovalProfessional) {
        this.IsApprovalProfessional = IsApprovalProfessional;
    }

    public String getStarPoints() {
        return StarPoints;
    }

    public void setStarPoints(String StarPoints) {
        this.StarPoints = StarPoints;
    }

    public String getTeachType() {
        return TeachType;
    }

    public void setTeachType(String TeachType) {
        this.TeachType = TeachType;
    }

    public int getTeacherType() {
        return TeacherType;
    }

    public void setTeacherType(int TeacherType) {
        this.TeacherType = TeacherType;
    }

    public String getMinPrice() {
        return MinPrice;
    }

    public void setMinPrice(String MinPrice) {
        this.MinPrice = MinPrice;
    }

    public String getIsOneYuanTest() {
        return IsOneYuanTest;
    }

    public void setIsOneYuanTest(String IsOneYuanTest) {
        this.IsOneYuanTest = IsOneYuanTest;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getRowNumber() {
        return RowNumber;
    }

    public void setRowNumber(int RowNumber) {
        this.RowNumber = RowNumber;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getGetHonor() {
        return GetHonor;
    }

    public void setGetHonor(String GetHonor) {
        this.GetHonor = GetHonor;
    }

    public String getGraduateSchool() {
        return GraduateSchool;
    }

    public void setGraduateSchool(String GraduateSchool) {
        this.GraduateSchool = GraduateSchool;
    }
}
