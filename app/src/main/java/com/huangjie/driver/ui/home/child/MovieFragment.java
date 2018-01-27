package com.huangjie.driver.ui.home.child;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.huangjie.driver.R;
import com.huangjie.driver.adapter.CommonAdapter;
import com.huangjie.driver.adapter.MovieAdapter;
import com.huangjie.driver.adapter.ViewHolder;
import com.huangjie.driver.base.BaseFragment;
import com.huangjie.driver.bean.movie.HotMovieBean;
import com.huangjie.driver.bean.movie.SubjectBean;
import com.huangjie.driver.constant.Constant;
import com.huangjie.driver.databinding.FragmentMovieBinding;
import com.huangjie.driver.http.HttpclientManager;
import com.huangjie.driver.ui.home.MovieDetailActivity;
import com.huangjie.driver.utils.LogUtils;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by huangjie on 2017/1/17.
 */

public class MovieFragment extends BaseFragment<FragmentMovieBinding> {
    private HotMovieBean mHotMovieBean;
    private MovieAdapter mAdapter;


    @Override
    public int setContentView() {
        return R.layout.fragment_movie;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoading();
    }

    @Override
    protected void loadData() {
        super.loadData();
        if (mIsViviable && mHasPrepare) {
            if (mHotMovieBean == null) {
                loadMovieData();
            } else {
                setAdapter();
            }
        }
    }

    private void loadMovieData() {

        Subscription subscription = HttpclientManager.getInstance().getDouBanApi().getHotMovie()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HotMovieBean>() {
                    @Override
                    public void onCompleted() {
                        showContentView();

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.error("电影数据加载失败");
                        showError();
                    }

                    @Override
                    public void onNext(HotMovieBean hotMovieBean) {
                        if (hotMovieBean != null && hotMovieBean.getmSubjects().size() > 0) {
                            mHotMovieBean = hotMovieBean;
                            setAdapter();
                        }
                    }
                });
        addSubscription(subscription);
    }

    /**
     * 设置adapter
     */
    private void setAdapter() {
        if (mAdapter == null) {
            if (mHotMovieBean != null) {
                mAdapter = new MovieAdapter(mHotMovieBean.getmSubjects(), getActivity());
                mBindingView.movieListview.setAdapter(mAdapter);
                initEvent();
            }

        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     *
     */
    private void initEvent() {
        mBindingView.movieListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View image = view.findViewById(R.id.movie_icon);
                Intent intent = new Intent(getContext(), MovieDetailActivity.class);
                intent.putExtra(Constant.SEND_DATA, mHotMovieBean.getmSubjects().get(position));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                                    image, "transition_movie_img").toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });
    }


}
