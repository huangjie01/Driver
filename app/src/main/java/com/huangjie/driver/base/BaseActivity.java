package com.huangjie.driver.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.huangjie.driver.R;
import com.huangjie.driver.databinding.BaseActivityBinding;
import com.huangjie.driver.utils.MyOnClickListener;
import com.huangjie.driver.utils.ResUtils;
import com.huangjie.driver.utils.StatusBarUtil;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by huangjie on 2017/1/15.
 */

public class BaseActivity<BT extends ViewDataBinding> extends AppCompatActivity {
    protected BT mBinder;
    private LinearLayout mLinearLoading;//正在加载布局
    private LinearLayout mLinearLoadError;//加载失败布局
    private RelativeLayout mRelContainer;
    private BaseActivityBinding mBaseBinding;
    private AnimationDrawable mAnimationDrawable;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mBaseBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.base_activity, null, false);
        mBinder = DataBindingUtil.inflate(LayoutInflater.from(this), layoutResID, null, false);
        mRelContainer = (RelativeLayout) mBaseBinding.getRoot().findViewById(R.id.base_rl_container);
        mRelContainer.addView(mBinder.getRoot());
       setContentView(mBaseBinding.getRoot());
        StatusBarUtil.setColor(this, ResUtils.getColor(R.color.colorTheme), 0);
        mLinearLoading = getView(R.id.base_ll_load);
        mLinearLoadError = getView(R.id.base_ll_error);
        ImageView imgLoad = getView(R.id.base_img_load);
        //加载动画
        mAnimationDrawable = (AnimationDrawable) imgLoad.getDrawable();
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        setToolBar();
        //加载失败，点击从新加载
        mLinearLoadError.setOnClickListener(new MyOnClickListener() {
            @Override
            protected void click(View view) {
                showLoading();
                onRefresh();
            }
        });
        mBinder.getRoot().setVisibility(View.GONE);
    }

    /**
     * 点击刷新
     */
    private void onRefresh() {
    }

    /**
     * 设置toolbar
     */
    private void setToolBar() {
        setSupportActionBar(mBaseBinding.toolbar);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayShowTitleEnabled(false);
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.icon_back);

        }
        mBaseBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    /**
     * 设置title
     */
    public void setTitle(String title) {
        mBaseBinding.toolbar.setTitle(title);
    }

    /**
     * 显示正在加载
     */
    protected void showLoading() {
        if (mLinearLoading.getVisibility() != View.VISIBLE) {
            mLinearLoading.setVisibility(View.VISIBLE);
        }
        // 开始动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        if (mBinder.getRoot().getVisibility() != View.GONE) {
            mBinder.getRoot().setVisibility(View.GONE);
        }
        if (mLinearLoadError.getVisibility() != View.GONE) {
            mLinearLoadError.setVisibility(View.GONE);
        }
    }

    /**
     * 设置ContentView
     */
    protected void showContentView() {
        if (mLinearLoading.getVisibility() != View.GONE) {
            mLinearLoading.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mLinearLoadError.getVisibility() != View.GONE) {
            mLinearLoadError.setVisibility(View.GONE);
        }
        if (mBinder.getRoot().getVisibility() != View.VISIBLE) {
            mBinder.getRoot().setVisibility(View.VISIBLE);
        }
    }

    /**
     * 显示错误信息
     */
    protected void showError() {
        if (mLinearLoading.getVisibility() != View.GONE) {
            mLinearLoading.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mLinearLoadError.getVisibility() != View.VISIBLE) {
            mLinearLoadError.setVisibility(View.VISIBLE);
        }
        if (mBinder.getRoot().getVisibility() != View.GONE) {
            mBinder.getRoot().setVisibility(View.GONE);
        }
    }

    protected <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }

    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            this.mCompositeSubscription.unsubscribe();
        }
    }

    public void removeSubscription() {
        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            this.mCompositeSubscription.unsubscribe();
        }
    }
}
