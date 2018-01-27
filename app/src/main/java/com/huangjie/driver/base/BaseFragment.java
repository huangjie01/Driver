package com.huangjie.driver.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.huangjie.driver.R;
import com.huangjie.driver.utils.MyOnClickListener;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by huangjie on 2017/1/12.
 */

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {
    protected T mBindingView;
    protected boolean mIsViviable = false;
    protected boolean mHasPrepare = false;
    private LinearLayout mLinearLoading;//正在加载布局
    private LinearLayout mLinearLoadError;//加载失败布局
    private RelativeLayout mRelaContainer;//内容布局
    private AnimationDrawable mAniLoadDrawble;//加载动画drawable
    protected CompositeSubscription mCompositeSubscription;
    private View mContentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mContentView == null) {
            mContentView = inflater.inflate(R.layout.base_fragment, null, false);
            mBindingView = DataBindingUtil.inflate(getActivity().getLayoutInflater(), setContentView(), null, false);
            mRelaContainer = (RelativeLayout) mContentView.findViewById(R.id.base_rl_container);
            mRelaContainer.addView(mBindingView.getRoot());
            handEvent();
            handView();
        }
        mHasPrepare = true;
        return mContentView;
    }

    /**
     * 初始化view
     */
    protected void handView() {
    }

    /**
     * 事件初始化
     */
    protected void handEvent() {
    }


    /**
     * 是在oncreateView之前调用
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            mIsViviable = true;
            onVisiable();
        } else {
            mIsViviable = false;
            onInVisiable();
        }

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLinearLoading = getView(R.id.base_ll_load);
        mLinearLoadError = getView(R.id.base_ll_error);
        ImageView imgLoad = getView(R.id.base_img_load);
        mAniLoadDrawble = (AnimationDrawable) imgLoad.getDrawable();
        if (!mAniLoadDrawble.isRunning()) {
            mAniLoadDrawble.start();
        }
        mLinearLoadError.setOnClickListener(new MyOnClickListener() {
            @Override
            protected void click(View view) {
                showLoading();
                onRefresh();
            }
        });
        mBindingView.getRoot().setVisibility(View.GONE);
    }

    /**
     * 加载失败后在加载
     */
    protected void onRefresh() {
        loadData();
    }


    protected void onInVisiable() {

    }


    /**
     * 视图可见，加载数据
     */
    protected void onVisiable() {
        loadData();
    }

    /**
     * 加载数据
     */
    protected void loadData() {

    }

    /**
     * 根据id获取view
     *
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T getView(int id) {
        return (T) getView().findViewById(id);
    }

    /**
     * 正在加载中
     */
    protected void showLoading() {
        if (mLinearLoading.getVisibility() != View.VISIBLE) {
            mLinearLoading.setVisibility(View.VISIBLE);
        }
        if (mAniLoadDrawble != null && !mAniLoadDrawble.isRunning()) {
            mAniLoadDrawble.start();
        }
        if (mBindingView.getRoot().getVisibility() != View.GONE) {
            mBindingView.getRoot().setVisibility(View.GONE);
        }
        if (mLinearLoadError.getVisibility() != View.GONE) {
            mLinearLoadError.setVisibility(View.GONE);
        }

    }

    /**
     * 当加载完成显示
     */
    protected void showContentView() {
        if (mAniLoadDrawble.isRunning()) {
            mAniLoadDrawble.stop();
        }
        if (mLinearLoading.getVisibility() != View.GONE) {
            mLinearLoading.setVisibility(View.GONE);
        }
        if (mLinearLoadError.getVisibility() != View.GONE) {
            mLinearLoadError.setVisibility(View.GONE);
        }
        if (mBindingView.getRoot().getVisibility() != View.VISIBLE) {
            mBindingView.getRoot().setVisibility(View.VISIBLE);
        }
    }

    /**
     * 加载失败点击重新加载的状态
     */
    protected void showError() {
        if (mLinearLoading.getVisibility() != View.GONE) {
            mLinearLoading.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAniLoadDrawble.isRunning()) {
            mAniLoadDrawble.stop();
        }
        if (mLinearLoadError.getVisibility() != View.VISIBLE) {
            mLinearLoadError.setVisibility(View.VISIBLE);
        }
        if (mBindingView.getRoot().getVisibility() != View.GONE) {
            mBindingView.getRoot().setVisibility(View.GONE);
        }
    }


    /**
     * 设置布局文件
     *
     * @return
     */
    public abstract int setContentView();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            this.mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    protected void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    protected void removeSubscription() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }

    }
}
