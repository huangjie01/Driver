package com.huangjie.driver.ui.music;

import com.huangjie.driver.R;
import com.huangjie.driver.base.BaseFragment;
import com.huangjie.driver.databinding.FragmentSingerListBinding;
import com.huangjie.driver.utils.MusicUtil;

/**
 * Created by huangjie on 2017/8/9.
 * 歌手fragment
 */

public class SingerFragment extends BaseFragment<FragmentSingerListBinding> {
    @Override
    public int setContentView() {
        return R.layout.fragment_singer_list;
    }

    @Override
    protected void loadData() {
        super.loadData();

    }
}
