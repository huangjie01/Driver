package com.huangjie.driver.http.api;

import com.huangjie.driver.bean.GankIoBean;
import com.huangjie.driver.bean.WelfareBean;

import retrofit2.http.GET;

import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by huangjie on 2017/5/26.
 */

public interface GankApi {

    @GET("api/data/福利/10/{page}")
    Observable<WelfareBean> getMeizhiData(@Path("page") int page);

    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     * eg: http://gank.io/api/data/Android/10/1
     */
    @GET("api/data/{type}/{pre_page}/{page}")
    Observable<GankIoBean> getIoData(@Path("type") String type, @Path("page") int page, @Path("pre_page") int pre_page);

}
