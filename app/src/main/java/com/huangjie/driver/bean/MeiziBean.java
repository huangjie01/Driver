package com.huangjie.driver.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by huangjie on 2017/5/26.
 */

public class MeiziBean implements Serializable {

    @SerializedName("_id")
    private String id;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("desc")
    private String desc;
    @SerializedName("publishedAt")
    private String publishedAt;
    @SerializedName("source")
    private String source;
    @SerializedName("type")
    private String type;
    @SerializedName("url")
    private String url;
    @SerializedName("used")
    private String used;
    @SerializedName("who")
    private String who;
    public boolean hasFadedIn = false;

    public boolean isHasFadedIn() {
        return hasFadedIn;
    }

    public void setHasFadedIn(boolean hasFadedIn) {
        this.hasFadedIn = hasFadedIn;
    }

    /**
     * "_id":"5927bc01421aa92c79463324",
     * "createdAt":"2017-05-26T13:24:17.785Z",
     * "desc":"5-26",
     * "publishedAt":"2017-05-26T13:43:32.128Z",
     * "source":"chrome",
     * "type":"福利",
     * "url":"https://ws1.sinaimg.cn/large/610dc034ly1ffyp4g2vwxj20u00tu77b.jpg",
     * "used":true,
     * "who":"daimajia"
     *
     * @return
     */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    @Override
    public String toString() {
        return "MeziBean{" +
                "id='" + id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", desc='" + desc + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used='" + used + '\'' +
                ", who='" + who + '\'' +
                '}';
    }
}
