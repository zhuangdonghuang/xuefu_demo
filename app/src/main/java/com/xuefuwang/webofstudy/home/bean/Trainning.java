package com.xuefuwang.webofstudy.home.bean;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/2.
 */
public class Trainning {


    /**
     * TeacherID : 938
     * TeacherName : 黄耀桦
     * Sex : false
     * Profile : 本人有三年的家教经验，做过一对一、一对二、以及大小班的辅助，小学初中高中都辅导过，课堂气氛良好，不仅能辅导学生功课，且可以face to face与学生交谈，善于开导学生。
     * CourseTitle : 初中1—3年级（数学、英语、物理、化学、生物）,初中一元试课,高中1—3年级（数学、英语、化学、物理、生物）,高中一元试课,小学全科
     * PhoneLink : files/doctorhuang/2015-09-28/1p1a09214u218271a0r871s27udj1.jpg
     * SchoolAge : 3
     * TeacherArea : 南山区
     * TeachFeature : 幽默风趣,善于总结,重点明确,气氛活跃,条理清楚,
     * CourseType : 初中/初一,初二,初三,中考/数学,英语,物理,化学,生物,中考数学,中考英语,中考物理,中考化学,中考生物,中考理综.高中/高一,高二,高三,高考/数学,英语,化学.高中/高一,高二,高三,高考/数学,英语,物理,化学,生物.高中/高一,高二,高三,高考/数学,英语,物理,化学,生物,理综,高考数学,高考英语,高考物理,高考化学,高考生物,高考理综.小学/一年级,二年级,三年级,四年级,五年级,六年级/语文,数学,英语,奥数
     * CommentCount : 49
     * SumBoughtCourseCount : 49
     * CourseCount : 5
     * StuCount : 3
     * LookCount : 1704
     * IsApprovalCard : 2
     * IsApprovalDegree : 2
     * IsApprovalTC : 0
     * IsApprovalProfessional : 2
     * StarPoints : 1.00
     * TeachType : 1
     * TeacherType : 1
     * MinPrice : 60.00
     * IsOneYuanTest : 1.00
     * distance : 25180.83
     * RowNumber : 1
     * longitude : 113.944338
     * latitude : 22.539067
     * GetHonor : 经验丰富
     * city : 深圳市
     * area : 南山区
     * GraduateSchool : 深圳大学
     * Mobile : 13760115910
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
    private String city;
    private String area;
    private String GraduateSchool;
    private String Mobile;

    public static Trainning objectFromData(String str) {

        return new com.google.gson.Gson().fromJson(str, Trainning.class);
    }

    public static List<Trainning> arrayTrainningFromData(String str) {

        Type listType = new com.google.gson.reflect.TypeToken<ArrayList<Trainning>>() {
        }.getType();

        return new com.google.gson.Gson().fromJson(str, listType);
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGraduateSchool() {
        return GraduateSchool;
    }

    public void setGraduateSchool(String GraduateSchool) {
        this.GraduateSchool = GraduateSchool;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }
}
