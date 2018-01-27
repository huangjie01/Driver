package com.huangjie.driver.ui.home.child;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.huangjie.driver.R;
import com.huangjie.driver.adapter.GlideBannderImageLoader;
import com.huangjie.driver.base.BaseFragment;
import com.huangjie.driver.bean.BannerBean;
import com.huangjie.driver.constant.Constant;
import com.huangjie.driver.databinding.FragmentSuggestBinding;
import com.huangjie.driver.databinding.HomeEverdayHeaderBinding;
import com.huangjie.driver.global.DriverApplication;
import com.huangjie.driver.http.cache.ACache;
import com.huangjie.driver.utils.LogUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Subscription;

/**
 * Created by huangjie on 2017/1/17.
 */

public class SuggestFragment extends BaseFragment<FragmentSuggestBinding> {
    private ACache mCache;
    private ArrayList<String> mBannerImg;
    private HomeEverdayHeaderBinding mHomeEverdayHeaderBinding;
    private boolean mIsPrepare;
    private RotateAnimation mRotateAnimation;

    @Override
    public int setContentView() {

        return R.layout.fragment_suggest;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoading();
        init();
        showContentView();
    }

    /**
     * 初始化
     */
    private void init() {

        mBindingView.llLoading.setVisibility(View.VISIBLE);
        mRotateAnimation = new RotateAnimation(0, 360f, Animation.RELATIVE_TO_SELF
                , 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateAnimation.setDuration(3000);
        mRotateAnimation.setInterpolator(new LinearInterpolator());
        mRotateAnimation.setRepeatCount(10);
        mBindingView.ivLoading.startAnimation(mRotateAnimation);
        mRotateAnimation.startNow();
        mCache = ACache.get(getActivity());
       // mBannerImg = (ArrayList<String>) mCache.getAsObject(Constant.CACHE_BANNER_IMG);
        mHomeEverdayHeaderBinding = DataBindingUtil.inflate(LayoutInflater.from(DriverApplication.getContext()),
                R.layout.home_everday_header, null, false);

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("dd");
        mHomeEverdayHeaderBinding.homeDateView.setDate(format.format(date));
       // mEverdayModel = new EverdayModel();
        mIsPrepare = true;
        loadData();
    }

    @Override
    protected void loadData() {
        if (!mIsPrepare || !mIsViviable) {
            return;
        }
        if (mHomeEverdayHeaderBinding != null && mHomeEverdayHeaderBinding.homeBanner != null) {
            mHomeEverdayHeaderBinding.homeBanner.isAutoPlay(true);
            mHomeEverdayHeaderBinding.homeBanner.setDelayTime(4000);
        }
        LogUtils.error(getClass().getName()+" onActivityCreated");

        showLoading();


    }

    @Override
    protected void onRefresh() {
        super.onRefresh();
        mBindingView.ivLoading.setVisibility(View.VISIBLE);
        mBindingView.ivLoading.setAnimation(mRotateAnimation);
        mRotateAnimation.startNow();
        mBindingView.ivLoading.setVisibility(View.GONE);
        showContentView();
    }

}
