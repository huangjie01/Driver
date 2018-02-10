package com.huangjie.driver.ui.music;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

import com.huangjie.driver.R;
import com.huangjie.driver.bean.music.MusicBean;
import com.huangjie.driver.constant.Constant;
import com.huangjie.driver.databinding.ActivityMusicLocalBinding;
import com.huangjie.driver.databinding.ActivityPlayMusicBinding;

import java.util.ArrayList;

/**
 * Created by huangjie on 2017/7/11.
 */

public class PlayMusicActivity extends AppCompatActivity {
    private ActivityPlayMusicBinding mBindView;
    private ArrayList<MusicBean> mAllMusicList;
    private int mCurrentPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBindView = DataBindingUtil.setContentView(this, R.layout.activity_play_music);
        parseIntent();
        updateView();
        handEvent();
    }

    /**
     * 处理点击事件
     */
    private void handEvent() {
        mBindView.musicToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 更新View
     */
    private void updateView() {
        setSupportActionBar(mBindView.musicToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
        mBindView.musicToolbar.setTitleTextAppearance(this,R.style.ToolBar_Title);
        if (mAllMusicList == null || mAllMusicList.size() <= 0) {
            return;
        }
        mBindView.musicToolbar.setTitle(mAllMusicList.get(mCurrentPosition).getName());
        mBindView.musicToolbar.setSubtitle(mAllMusicList.get(mCurrentPosition).getArtist());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.play_music, menu);
        return true;

    }

    /**
     * 获取Intent 传递的MusicBean
     */
    private void parseIntent() {
        mCurrentPosition = getIntent().getIntExtra(Constant.SEND_POSITION, 0);
        mAllMusicList = getIntent().getParcelableArrayListExtra(Constant.SEND_DATA);
    }

    public static void launch(Context context, ArrayList<MusicBean> musicBeanList, int posiotn) {
        Intent intent = new Intent(context, PlayMusicActivity.class);
        intent.putExtra(Constant.SEND_DATA, musicBeanList);
        intent.putExtra(Constant.SEND_POSITION, posiotn);
        context.startActivity(intent);
    }


}
