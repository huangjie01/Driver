package com.huangjie.driver.bean.movie;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by huangjie on 2017/6/6.
 */

public class RatingBean implements Serializable {
    @SerializedName("max")
    private int max;
    @SerializedName("average")
    private double average;
    @SerializedName("stars")
    private String stars;
    @SerializedName("min")
    private int min;
    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
