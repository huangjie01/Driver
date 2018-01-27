package com.huangjie.driver.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.huangjie.driver.R;
import com.huangjie.driver.base.BaseFragment;
import com.huangjie.driver.constant.Constant;
import com.huangjie.driver.databinding.FragmentGuideHomeBinding;
import com.huangjie.driver.ui.home.child.AndroidFragment;
import com.huangjie.driver.ui.home.child.MovieFragment;
import com.huangjie.driver.ui.home.child.SuggestFragment;
import com.huangjie.driver.ui.home.child.WelfareFragment;

/**
 * Created by huangjie on 2017/1/16.
 */

public class GuideHomeFragment extends BaseFragment<FragmentGuideHomeBinding> {

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
        final String titls[] = new String[]{"个性推荐", "福利", "电影", "安卓"};
        mBindingView.viewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                return getFragmentByPosition(position);
            }

            @Override
            public int getCount() {
                return Constant.HOME_FRAGMENT_VIEWPAGER_COUNT;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titls[position];
            }
        });
        mBindingView.viewpager.setOffscreenPageLimit(2);
        mBindingView.tablayout.setupWithViewPager(mBindingView.viewpager);
        mBindingView.tablayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private Fragment getFragmentByPosition(int position) {
        if (position == 0) {
            return new SuggestFragment();
        } else if (position == 1) {
            return new WelfareFragment();
        } else if (position == 2) {
            return new MovieFragment();
        } else {
            return new AndroidFragment();

        }
    }

    @Override
    public int setContentView() {
        return R.layout.fragment_guide_home;
    }
}
