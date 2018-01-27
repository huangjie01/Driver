package com.huangjie.driver.ui.music;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.huangjie.driver.R;
import com.huangjie.driver.base.BaseFragment;
import com.huangjie.driver.databinding.FragmentGuideMusicBinding;


/**
 * Created by huangjie on 2017/1/16.
 */

public class GuideMusicFragment extends BaseFragment<FragmentGuideMusicBinding> implements View.OnClickListener {
    protected boolean mInit;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        if (!mInit) {
            initEvent();
            mInit = true;
        }
    }

    private void initEvent() {
        mBindingView.musicLocalMusic.setOnClickListener(this);
    }

    @Override
    public int setContentView() {
        return R.layout.fragment_guide_music;
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.music_localMusic:
                intent.setClass(getContext(), LocalMusicActivity.class);
                startActivity(intent);
                break;

        }
    }
}
