package com.huangjie.driver.ui.music;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huangjie.driver.R;
import com.huangjie.driver.bean.music.MusicBean;
import com.huangjie.driver.constant.Constant;
import com.huangjie.driver.databinding.MusicBottomSheetBinding;

/**
 * Created by huangjie on 2017/9/18.
 */

public class MusicBottomSheet extends BottomSheetDialogFragment {
    private View contentView;
    private MusicBean musicBean;
    MusicBottomSheetBinding mBindView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.music_bottom_sheet, container, false);
            mBindView = DataBindingUtil.inflate(inflater, R.layout.music_bottom_sheet, container, false);
        }
        return mBindView.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        musicBean = arguments.getParcelable(Constant.SEND_DATA);
        updateView();
    }

    /**
     * 更新View
     */
    private void updateView() {
        mBindView.musicName.setText(musicBean.getName()+"");
        mBindView.musicSonger.setText(musicBean.getArtist());
        mBindView.musicAlbum.setText(musicBean.getAlbum()+"");
    }
}
