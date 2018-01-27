package com.huangjie.driver.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huangjie on 2017/2/8.
 */

public class GankIoBean implements Serializable {

    private boolean error;
    /**
     * _id : 5832662b421aa929b0f34e99
     * createdAt : 2016-11-21T11:12:43.567Z
     * desc :  深入Android渲染机制
     * publishedAt : 2016-11-24T11:40:53.615Z
     * source : web
     * type : Android
     * url : http://blog.csdn.net/ccj659/article/details/53219288
     * used : true
     * who : Chauncey
     */
    private List<AndroidBean> results;

    public boolean isError() {
        return error;
    }

    public List<AndroidBean> getResults() {
        return results;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setResults(List<AndroidBean> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "GankIoBean{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }

}
