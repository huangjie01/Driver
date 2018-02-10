package com.huangjie.driver.ui.home;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.huangjie.driver.R;
import com.huangjie.driver.bean.movie.MovieDetailBean;
import com.huangjie.driver.bean.movie.SubjectBean;
import com.huangjie.driver.constant.Constant;
import com.huangjie.driver.databinding.ActivityMovieDetailBinding;
import com.huangjie.driver.http.HttpclientManager;
import com.huangjie.driver.utils.ImageLoadUtils;
import com.huangjie.driver.utils.LogUtils;
import com.huangjie.driver.utils.StatusBarUtil;
import com.huangjie.driver.utils.Utils;
import com.huangjie.driver.widget.MyNestedScrollView;

import jp.wasabeef.glide.transformations.BlurTransformation;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by huangjie on 2017/6/8.
 */

public class MovieDetailActivity extends AppCompatActivity {
    private ActivityMovieDetailBinding mBinDingView;
    private SubjectBean mSubjectBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucentForImageView(this,null);
        mBinDingView = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        mSubjectBean = (SubjectBean) getIntent().getSerializableExtra(Constant.SEND_DATA);
        if (mSubjectBean != null) {
            mBinDingView.setSubjectsBean(mSubjectBean);
            mBinDingView.include.setSubjectsBean(mSubjectBean);
        }
        updateView();
        initData();
        iniEvent();
    }

    private void iniEvent() {
        mBinDingView.nsvScrollview.setmListener(new MyNestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                LogUtils.error("l" + l + "t" + t);
            }
        });


    }

    /**
     * 加载电影的详细信息
     */
    private void initData() {

        HttpclientManager.getInstance().getDouBanApi().getMovieDetail(mSubjectBean.getId())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MovieDetailBean movieDetailBean) {
                        LogUtils.error("电影详细数据：" + movieDetailBean.toString());
                        mBinDingView.setMovieDetailBean(movieDetailBean);
                        mBinDingView.include.setMovieDetailBean(movieDetailBean);
                        refreshView(movieDetailBean);
                    }
                });
    }

    /**
     * 刷新界面
     *
     * @param movieDetailBean
     */
    private void refreshView(MovieDetailBean movieDetailBean) {
        StringBuilder country = new StringBuilder();
        country.append("制片国家/地区:");
        for (int i = 0; i < movieDetailBean.getCountries().size(); i++) {
            country.append(movieDetailBean.getCountries().get(i) + "/");
        }
        if (country.length() > 1) {
            country.deleteCharAt(country.length() - 1);
        }
        mBinDingView.include.tvOneCity.setText(country.toString());
    }

    private void updateView() {
        ImageLoadUtils.loadImage(mBinDingView.include.ivOnePhoto,
                mSubjectBean.getAwatarBean().getMedium());
        Glide.with(this).load(mSubjectBean.getAwatarBean().getLarge())
                .error(R.drawable.stackblur_default)
                .bitmapTransform(new BlurTransformation(this, 23, 4)).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                mBinDingView.movieToolbar.setBackgroundColor(Color.TRANSPARENT);

                return false;
            }
        }).into(mBinDingView.include.imgItemBg);
        setSupportActionBar(mBinDingView.movieToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        mBinDingView.movieToolbar.setTitleTextAppearance(this, R.style.ToolBar_Title);
        mBinDingView.movieToolbar.setSubtitleTextAppearance(this, R.style.Toolbar_SubTitle);
        mBinDingView.movieToolbar.setTitle(mSubjectBean.getTitle());
        mBinDingView.movieToolbar.setOverflowIcon(Utils.getDrawable(R.drawable.actionbar_more));

    }
}
