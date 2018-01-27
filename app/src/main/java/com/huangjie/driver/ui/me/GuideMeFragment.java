package com.huangjie.driver.ui.me;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.huangjie.driver.R;
import com.huangjie.driver.base.BaseFragment;
import com.huangjie.driver.databinding.FragmentGuideMeBinding;

/**
 * Created by huangjie on 2017/1/16.
 */

public class GuideMeFragment extends BaseFragment<FragmentGuideMeBinding> {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
    }

    @Override
    public int setContentView() {
        return R.layout.fragment_guide_me;
    }
}
