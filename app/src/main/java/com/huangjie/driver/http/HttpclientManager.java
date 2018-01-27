package com.huangjie.driver.http;

import com.huangjie.driver.http.api.DouBanApi;
import com.huangjie.driver.http.api.GankApi;
import com.huangjie.driver.utils.LogUtils;
import com.huangjie.driver.utils.NetworkUtils;
import com.huangjie.driver.utils.Utils;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by huangjie on 2017/5/26.
 */

public class HttpclientManager {


    /**
     * http拦截器
     */
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (NetworkUtils.isNetWorkAvailable(Utils.getContext())) {
                int maxAge = 60; // 在线缓存在1分钟内可读取
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };


    /**
     * 缓存配置
     */
    private static File cacheFile = new File(Utils.getContext().getCacheDir(), "cacheFile");
    private static int cacheSize = 10 * 1024 * 1024; // 10 MB
    private static Cache cache = new Cache(cacheFile, cacheSize);
    private OkHttpClient mClient = new OkHttpClient.Builder()
            .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .cache(cache)
            .build();
    private static HttpclientManager mHttpclientManager;

    /**
     * 单例模式
     *
     * @return
     */
    public static HttpclientManager getInstance() {
        if (mHttpclientManager == null) {
            synchronized (HttpclientManager.class) {
                if (mHttpclientManager == null) {
                    mHttpclientManager = new HttpclientManager();
                }
            }
        }
        return mHttpclientManager;
    }

    private GankApi mGankApi;

    private DouBanApi mDouBanApi;

    /**
     * 获取福利retrofit
     *
     * @return
     */
    public GankApi getGankApi() {
        if (mGankApi == null) {
            mGankApi = new Retrofit.Builder()
                    .baseUrl("http://gank.io/")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(mClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(GankApi.class);
        }
        return mGankApi;
    }


    public DouBanApi getDouBanApi() {
        if (mDouBanApi == null) {
            mDouBanApi = new Retrofit.Builder()
                    .baseUrl("https://api.douban.com/")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(mClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(DouBanApi.class);
        }
        return mDouBanApi;
    }

}
