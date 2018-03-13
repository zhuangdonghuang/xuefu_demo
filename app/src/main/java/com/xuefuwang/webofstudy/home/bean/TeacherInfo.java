package com.xuefuwang.webofstudy.home.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class TeacherInfo {

    /**
     * UserID : 938
     * LoginName : doctorhuang
     * UserName : 黄耀桦
     * NickName : 黄先生
     * Sex : false
     * Province : 广东省
     * City : 深圳市
     * Area : 南山区
     * address : 深圳大学凌霄斋201
     * Degree : 本科
     * IdentityCardLink : files/doctorhuang/2015-09-28/p1a08tqsl21c6t1hob1s6rvmq4931.jpg
     * IdentityCard : 441881199612180213
     * IsApprovalCard : 2
     * DegreeLink : files/doctorhuang/2015-09-28/p1a08tpsd219sscbpv4l15a0rvs1.jpg
     * IsApprovalDegree : 2
     * TearcherCertificateLink :
     * IsApprovalTC : 0
     * BirthDate : 1996-12-18
     * GraduateSchool : 深圳大学
     * Professional : 大学生
     * UserQQ : 347715471
     * TeachFeature : 幽默风趣,善于总结,重点明确,气氛活跃,条理清楚,
     * Profile : 本人有三年的家教经验，做过一对一、一对二、以及大小班的辅助，小学初中高中都辅导过，课堂气氛良好，不仅能辅导学生功课，且可以face to face与学生交谈，善于开导学生。
     * ISdelete : false
     * SchoolAge : 3
     * PhoneLink : files/doctorhuang/2015-09-28/1p1a09214u218271a0r871s27udj1.jpg
     * IsApproval_phlink : 2
     * GetHonor : 经验丰富
     * LikeCourse :
     * Country : 中国
     * StuCount : 3
     * CourseCount : 5
     * Commpercent : 100
     * IsApprovalprofession : 1
     */

    private int UserID;
    private String LoginName;
    private String UserName;
    private String NickName;
    private boolean Sex;
    private String Province;
    private String City;
    private String Area;
    private String address;
    private String Degree;
    private String IdentityCardLink;
    private String IdentityCard;
    private int IsApprovalCard;
    private String DegreeLink;
    private int IsApprovalDegree;
    private String TearcherCertificateLink;
    private int IsApprovalTC;
    private String BirthDate;
    private String GraduateSchool;
    private String Professional;
    private String UserQQ;
    private String TeachFeature;
    private String Profile;
    private boolean ISdelete;
    private int SchoolAge;
    private String PhoneLink;
    private int IsApproval_phlink;
    private String GetHonor;
    private String LikeCourse;
    private String Country;
    private int StuCount;
    private int CourseCount;
    private int Commpercent;
    private int IsApprovalprofession;

    public static TeacherInfo objectFromData(String str) {

        return new Gson().fromJson(str, TeacherInfo.class);
    }

    public static List<TeacherInfo> arrayTeacherInfoFromData(String str) {

        Type listType = new TypeToken<ArrayList<TeacherInfo>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String LoginName) {
        this.LoginName = LoginName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    public boolean isSex() {
        return Sex;
    }

    public void setSex(boolean Sex) {
        this.Sex = Sex;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String Province) {
        this.Province = Province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String Area) {
        this.Area = Area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String Degree) {
        this.Degree = Degree;
    }

    public String getIdentityCardLink() {
        return IdentityCardLink;
    }

    public void setIdentityCardLink(String IdentityCardLink) {
        this.IdentityCardLink = IdentityCardLink;
    }

    public String getIdentityCard() {
        return IdentityCard;
    }

    public void setIdentityCard(String IdentityCard) {
        this.IdentityCard = IdentityCard;
    }

    public int getIsApprovalCard() {
        return IsApprovalCard;
    }

    public void setIsApprovalCard(int IsApprovalCard) {
        this.IsApprovalCard = IsApprovalCard;
    }

    public String getDegreeLink() {
        return DegreeLink;
    }

    public void setDegreeLink(String DegreeLink) {
        this.DegreeLink = DegreeLink;
    }

    public int getIsApprovalDegree() {
        return IsApprovalDegree;
    }

    public void setIsApprovalDegree(int IsApprovalDegree) {
        this.IsApprovalDegree = IsApprovalDegree;
    }

    public String getTearcherCertificateLink() {
        return TearcherCertificateLink;
    }

    public void setTearcherCertificateLink(String TearcherCertificateLink) {
        this.TearcherCertificateLink = TearcherCertificateLink;
    }

    public int getIsApprovalTC() {
        return IsApprovalTC;
    }

    public void setIsApprovalTC(int IsApprovalTC) {
        this.IsApprovalTC = IsApprovalTC;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String BirthDate) {
        this.BirthDate = BirthDate;
    }

    public String getGraduateSchool() {
        return GraduateSchool;
    }

    public void setGraduateSchool(String GraduateSchool) {
        this.GraduateSchool = GraduateSchool;
    }

    public String getProfessional() {
        return Professional;
    }

    public void setProfessional(String Professional) {
        this.Professional = Professional;
    }

    public String getUserQQ() {
        return UserQQ;
    }

    public void setUserQQ(String UserQQ) {
        this.UserQQ = UserQQ;
    }

    public String getTeachFeature() {
        return TeachFeature;
    }

    public void setTeachFeature(String TeachFeature) {
        this.TeachFeature = TeachFeature;
    }

    public String getProfile() {
        return Profile;
    }

    public void setProfile(String Profile) {
        this.Profile = Profile;
    }

    public boolean isISdelete() {
        return ISdelete;
    }

    public void setISdelete(boolean ISdelete) {
        this.ISdelete = ISdelete;
    }

    public int getSchoolAge() {
        return SchoolAge;
    }

    public void setSchoolAge(int SchoolAge) {
        this.SchoolAge = SchoolAge;
    }

    public String getPhoneLink() {
        return PhoneLink;
    }

    public void setPhoneLink(String PhoneLink) {
        this.PhoneLink = PhoneLink;
    }

    public int getIsApproval_phlink() {
        return IsApproval_phlink;
    }

    public void setIsApproval_phlink(int IsApproval_phlink) {
        this.IsApproval_phlink = IsApproval_phlink;
    }

    public String getGetHonor() {
        return GetHonor;
    }

    public void setGetHonor(String GetHonor) {
        this.GetHonor = GetHonor;
    }

    public String getLikeCourse() {
        return LikeCourse;
    }

    public void setLikeCourse(String LikeCourse) {
        this.LikeCourse = LikeCourse;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public int getStuCount() {
        return StuCount;
    }

    public void setStuCount(int StuCount) {
        this.StuCount = StuCount;
    }

    public int getCourseCount() {
        return CourseCount;
    }

    public void setCourseCount(int CourseCount) {
        this.CourseCount = CourseCount;
    }

    public int getCommpercent() {
        return Commpercent;
    }

    public void setCommpercent(int Commpercent) {
        this.Commpercent = Commpercent;
    }

    public int getIsApprovalprofession() {
        return IsApprovalprofession;
    }

    public void setIsApprovalprofession(int IsApprovalprofession) {
        this.IsApprovalprofession = IsApprovalprofession;
    }
}
