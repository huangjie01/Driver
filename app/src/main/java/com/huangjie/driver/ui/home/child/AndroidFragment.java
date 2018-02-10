package com.huangjie.driver.ui.home.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;

import com.huangjie.driver.R;
import com.huangjie.driver.adapter.AndroidAdapter;
import com.huangjie.driver.base.BaseFragment;
import com.huangjie.driver.bean.AndroidBean;
import com.huangjie.driver.bean.GankIoBean;
import com.huangjie.driver.constant.Constant;
import com.huangjie.driver.databinding.FragmentAndroidBinding;
import com.huangjie.driver.http.HttpclientManager;
import com.huangjie.driver.utils.LogUtils;
import com.huangjie.driver.utils.Utils;

import java.util.ArrayList;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by huangjie on 2017/1/18.
 */

public class AndroidFragment extends BaseFragment<FragmentAndroidBinding> implements SwipeRefreshLayout.OnRefreshListener {
    private boolean mHasInit;
    private AndroidAdapter mAdapter;
    private String mType = "Android";
    private int mCurrentPage = 1;
    private ArrayList<AndroidBean> mDataList;
    private boolean mLoadding;

    @Override
    public int setContentView() {
        return R.layout.fragment_android;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
    }

    @Override
    protected void loadData() {
        super.loadData();
        if (mIsViviable && mHasPrepare && !mHasInit) {
            if (mDataList != null && mDataList.size() > 0) {
                setAdapter();
            } else {
                showLoading();
                loadAndroidData();
            }
        }

    }

    /**
     * 加载android数据
     */
    private void loadAndroidData() {
        Subscription subscription = HttpclientManager.getInstance().getGankApi()
                .getIoData(mType, mCurrentPage, Constant.per_page_more)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankIoBean>() {
                    @Override
                    public void onCompleted() {
                        mLoadding = false;
                        if (mBindingView.androidSwiperefresh.isRefreshing()) {
                            mBindingView.androidSwiperefresh.setRefreshing(false);
                            Toast.makeText(Utils.getContext(), "数据刷新成功", Toast.LENGTH_SHORT).show();
                        }

                        if (mBindingView.androidProgressbar.getVisibility() != View.GONE) {
                            mBindingView.androidProgressbar.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mBindingView.androidProgressbar.getVisibility() == View.VISIBLE) {
                            mBindingView.androidProgressbar.setVisibility(View.GONE);
                        }
                        LogUtils.error("android 数据获取失败");
                        if (mBindingView.androidSwiperefresh.isRefreshing()) {
                            mBindingView.androidSwiperefresh.setRefreshing(false);
                        }
                        showError();
                    }

                    @Override
                    public void onNext(GankIoBean gankIoBean) {
                        if (!gankIoBean.isError()) {
                            if (gankIoBean.getResults() != null && gankIoBean.getResults().size() > 0) {
                                if (mDataList == null) {
                                    mDataList = new ArrayList<>();
                                }
                                if (!mHasInit) {
                                    LogUtils.error("mHasInit");
                                    for (AndroidBean androidBean : gankIoBean.getResults()) {
                                        LogUtils.error("androidbean:" + androidBean.toString());
                                    }
                                    mDataList.addAll(gankIoBean.getResults());
                                    setAdapter();
                                } else {
                                    LogUtils.error("刷新数据");
                                    mAdapter.addData(gankIoBean.getResults());
                                }
                            }
                        }
                    }
                });
        addSubscription(subscription);
    }

    /**
     * 设置adapter
     */
    private void setAdapter() {
        showContentView();
        mHasInit = true;
        if (mAdapter == null) {
            mAdapter = new AndroidAdapter(mDataList);
            mBindingView.androidListview.setAdapter(mAdapter);
            mBindingView.androidSwiperefresh.setColorSchemeColors(Utils.getColor(R.color.main_color));
            initEvent();
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 事件初始化
     */
    private void initEvent() {

        mBindingView.androidListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if ((firstVisibleItem + visibleItemCount) >= totalItemCount && !mLoadding) {
                    LogUtils.error("滑动到底部");
                    mBindingView.androidProgressbar.setVisibility(View.VISIBLE);
                    mLoadding = true;
                    mCurrentPage += 1;
                    loadAndroidData();
                }
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mCompositeSubscription != null && mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void onRefresh() {
        mCurrentPage += 1;
        loadAndroidData();

    }
}
