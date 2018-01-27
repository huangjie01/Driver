package com.huangjie.driver.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by huangjie on 2017/5/28.
 */

public class WelfareBean implements Serializable {
    @SerializedName("error")
    private boolean error;
    @SerializedName("results")
    private ArrayList<MeiziBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ArrayList<MeiziBean> getResults() {
        return results;
    }

    public void setResults(ArrayList<MeiziBean> results) {
        this.results = results;
    }
}
