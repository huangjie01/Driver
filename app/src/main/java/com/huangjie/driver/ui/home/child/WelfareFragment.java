package com.huangjie.driver.ui.home.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.huangjie.driver.R;
import com.huangjie.driver.adapter.WelfareAdapter;
import com.huangjie.driver.base.BaseFragment;
import com.huangjie.driver.bean.MeiziBean;
import com.huangjie.driver.bean.WelfareBean;
import com.huangjie.driver.databinding.FragmentWelfareBinding;
import com.huangjie.driver.http.HttpclientManager;
import com.huangjie.driver.utils.LogUtils;
import com.huangjie.driver.utils.Utils;

import java.util.ArrayList;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by huangjie on 2017/1/17.
 */

public class WelfareFragment extends BaseFragment<FragmentWelfareBinding> {
    private RecyclerView.OnScrollListener mListener;
    private ArrayList<MeiziBean> mDataList;
    private LinearLayoutManager mLinearLayoutManager;
    private boolean mIsLoading;
    private int mIndex = 1;
    private boolean mHasInit;
    private WelfareAdapter mAdapter;
    private WelfareBean mWelfareBean;

    @Override
    public int setContentView() {
        return R.layout.fragment_welfare;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.error("WelfareFragment onActivityCreated:" + mHasInit);
    }

    @Override
    protected void loadData() {
        super.loadData();
        if (mIsViviable && mHasPrepare && !mHasInit) {
            if (mWelfareBean != null && mWelfareBean.getResults() != null && mWelfareBean.getResults().size() > 0) {
                setAdapter();
            } else {
                showLoading();
                loadWelfareData();
            }
        }
    }


    /**
     * 加载数据
     */
    private void loadWelfareData() {
        mBindingView.welfareProgressbar.setVisibility(View.VISIBLE);
        Subscription subscription = HttpclientManager.getInstance().getGankApi().getMeizhiData(mIndex)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WelfareBean>() {
                    @Override
                    public void onCompleted() {
                        mBindingView.welfareProgressbar.setVisibility(View.GONE);

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.error("数据获取错误");
                        mBindingView.welfareProgressbar.setVisibility(View.GONE);

                    }

                    @Override
                    public void onNext(WelfareBean welfareBean) {
                        LogUtils.error("数据获取：" + welfareBean.isError() + welfareBean.getResults().size());
                        if (!welfareBean.isError()) {
                            mWelfareBean = welfareBean;
                            if (mDataList == null) {
                                mDataList = new ArrayList<MeiziBean>();
                            }
                            if (!mHasInit) {
                                mDataList.addAll(welfareBean.getResults());
                                setAdapter();
                            } else {
                                LogUtils.error("zengjia数据：" + welfareBean.getResults().size());
                                LogUtils.error("数据添加");
                                mAdapter.addData(welfareBean.getResults());
                                mIsLoading = false;
                            }

                        }
                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.error(" WelfareFragment ondestory");
        mBindingView.welfareRecyclerview.removeOnScrollListener(mListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.error(" WelfareFragment onDestroyView");

    }

    /**
     * 设置adapter
     */
    private void setAdapter() {
        showContentView();
        mHasInit = true;
        if (mAdapter == null) {
            mLinearLayoutManager = new LinearLayoutManager(Utils.getContext());
            mListener = new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (dy > 0) {
                        int visibleItemCount = mLinearLayoutManager.getChildCount();
                        int totalItemCount = mLinearLayoutManager.getItemCount();
                        int pastVisiblesItems = mLinearLayoutManager.findFirstVisibleItemPosition();

                        if (!mIsLoading && (visibleItemCount + pastVisiblesItems >= totalItemCount)) {
                            mIsLoading = true;
                            mIndex += 1;
                            loadWelfareData();
                        }
                    }
                }
            };
            mBindingView.welfareRecyclerview.setItemAnimator(new DefaultItemAnimator());
            mBindingView.welfareRecyclerview.addOnScrollListener(mListener);
            mBindingView.welfareRecyclerview.setLayoutManager(mLinearLayoutManager);
            mAdapter = new WelfareAdapter(mDataList, getContext());
            mBindingView.welfareRecyclerview.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}
