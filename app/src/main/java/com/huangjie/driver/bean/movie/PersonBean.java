package com.huangjie.driver.bean.movie;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by huangjie on 2017/6/6.
 */

public class PersonBean implements Serializable {
    @SerializedName("alt")
    private String alt;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private String id;
    @SerializedName("avatars")
    private AwatarBean awatarBean;
    @SerializedName("type")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class AwatarBean implements Serializable {
        @SerializedName("small")
        private String small;
        @SerializedName("large")
        private String large;
        @SerializedName("medium")
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AwatarBean getAwatarBean() {
        return awatarBean;
    }

    public void setAwatarBean(AwatarBean awatarBean) {
        this.awatarBean = awatarBean;
    }
}
