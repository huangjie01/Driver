package com.huangjie.driver.ui.home.child;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.huangjie.driver.R;
import com.huangjie.driver.base.BaseFragment;
import com.huangjie.driver.databinding.FragmentCoderBinding;

/**
 * Created by huangjie on 2017/1/18.
 */

public class CoderFragment extends BaseFragment<FragmentCoderBinding> {
    @Override
    public int setContentView() {
        return R.layout.fragment_coder;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
    }
}
