package com.huangjie.driver.bean.movie;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huangjie on 2017/6/6.
 */

public class SubjectBean implements Serializable {

    @SerializedName("rating")
    private RatingBean rating;
    @SerializedName("title")
    private String title;
    @SerializedName("collect_count")
    private int collect_count;
    @SerializedName("original_title")
    private String original_title;
    @SerializedName("subtype")
    private String subtype;
    @SerializedName("year")
    private String year;
    @SerializedName("images")
    private PersonBean.AwatarBean awatarBean;
    @SerializedName("alt")
    private String alt;
    @SerializedName("id")
    private String id;
    @SerializedName("genres")
    private List<String> genres;
    @SerializedName("casts")
    private List<PersonBean> casts;
    @SerializedName("directors")
    private List<PersonBean> directors;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public PersonBean.AwatarBean getAwatarBean() {
        return awatarBean;
    }

    public void setAwatarBean(PersonBean.AwatarBean awatarBean) {
        this.awatarBean = awatarBean;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<PersonBean> getCasts() {
        return casts;
    }

    public void setCasts(List<PersonBean> casts) {
        this.casts = casts;
    }

    public List<PersonBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<PersonBean> directors) {
        this.directors = directors;
    }
}
