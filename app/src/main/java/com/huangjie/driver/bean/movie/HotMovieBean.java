package com.huangjie.driver.bean.movie;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by huangjie on 2017/6/6.
 */

public class HotMovieBean implements Serializable {

    @SerializedName("count")
    private int count;
    @SerializedName("start")
    private int start;
    @SerializedName("total")
    private int total;
    @SerializedName("subjects")
    private ArrayList<SubjectBean> mSubjects;
    @SerializedName("title")
    private String title;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<SubjectBean> getmSubjects() {
        return mSubjects;
    }

    public void setmSubjects(ArrayList<SubjectBean> mSubjects) {
        this.mSubjects = mSubjects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

