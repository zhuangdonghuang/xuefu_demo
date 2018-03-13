package com.xuefuwang.webofstudy.home.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class HomeAdvertisementImage {

    /**
     * PKID : 4
     * IndexType : 1
     * PageLink : http://xuef.com/RegisterChoice.html
     * Adddate : 2015-08-26
     * ImageLinkAPP : files/kefu3/2016-01-07/p1a8cudjn0dcb1icqbhhvbb17g32.png
     * PageLinkAPP : http://m.xuef.com/activity/Share.html
     */

    private int PKID;
    private int IndexType;
    private String PageLink;
    private String Adddate;
    private String ImageLinkAPP;
    private String PageLinkAPP;

    public static HomeAdvertisementImage objectFromData(String str) {

        return new Gson().fromJson(str, HomeAdvertisementImage.class);
    }

    public static List<HomeAdvertisementImage> arrayHomeAdvertisementImageFromData(String str) {

        Type listType = new TypeToken<ArrayList<HomeAdvertisementImage>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public int getPKID() {
        return PKID;
    }

    public void setPKID(int PKID) {
        this.PKID = PKID;
    }

    public int getIndexType() {
        return IndexType;
    }

    public void setIndexType(int IndexType) {
        this.IndexType = IndexType;
    }

    public String getPageLink() {
        return PageLink;
    }

    public void setPageLink(String PageLink) {
        this.PageLink = PageLink;
    }

    public String getAdddate() {
        return Adddate;
    }

    public void setAdddate(String Adddate) {
        this.Adddate = Adddate;
    }

    public String getImageLinkAPP() {
        return ImageLinkAPP;
    }

    public void setImageLinkAPP(String ImageLinkAPP) {
        this.ImageLinkAPP = ImageLinkAPP;
    }

    public String getPageLinkAPP() {
        return PageLinkAPP;
    }

    public void setPageLinkAPP(String PageLinkAPP) {
        this.PageLinkAPP = PageLinkAPP;
    }
}
