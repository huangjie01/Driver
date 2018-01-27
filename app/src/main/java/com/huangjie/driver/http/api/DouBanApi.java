package com.huangjie.driver.http.api;

import com.huangjie.driver.bean.movie.HotMovieBean;
import com.huangjie.driver.bean.movie.MovieDetailBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by huangjie on 2017/6/7.
 */

public interface DouBanApi {

    @GET("v2/movie/in_theaters")
    Observable<HotMovieBean> getHotMovie();

    @GET("/v2/movie/subject/{id}")
    Observable<MovieDetailBean> getMovieDetail(@Path("id") String id);
}
