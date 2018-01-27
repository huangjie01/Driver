package com.huangjie.driver.ui.music;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.huangjie.driver.R;
import com.huangjie.driver.bean.music.MusicBean;
import com.huangjie.driver.databinding.ActivityMusicLocalBinding;
import com.huangjie.driver.utils.MusicUtil;
import com.huangjie.driver.utils.Utils;

import java.util.ArrayList;

/**
 * Created by huangjie on 2017/8/8.
 */

public class LocalMusicActivity extends AppCompatActivity {
    private ArrayList<MusicBean> mLocalMusicList;
    private ArrayList<Fragment> mFragmentList;
    private ActivityMusicLocalBinding mBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinder = DataBindingUtil.setContentView(this, R.layout.activity_music_local);
        initData();
        initView();
    }

    /**
     * 初始化 view
     */
    private void initView() {
        setSupportActionBar(mBinder.musicToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
        getSupportActionBar().setTitle(Utils.getString(R.string.local_music));

        mBinder.musicToolbar.setTitleTextAppearance(this, R.style.ToolBar_Title);

        final String[] title = new String[]{"单曲", "歌手", "专辑"};
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new MusicFragment());
        mFragmentList.add(new SingerFragment());
        mFragmentList.add(new AlbumFragment());
        mBinder.musicViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return title.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });
        mBinder.musicViewpager.setOffscreenPageLimit(2);
        mBinder.musicTablayout.setupWithViewPager(mBinder.musicViewpager);

    }

    /**
     * 初始化数据
     */
    private void initData() {
        mLocalMusicList = new ArrayList<>();
        MusicUtil.getMusicList(new MusicUtil.MusicCallBack() {
            @Override
            public void success(ArrayList<MusicBean> datalist) {
                if (datalist != null && datalist.size() > 0) {
                    mLocalMusicList.addAll(datalist);
                    datalist.clear();
                    datalist = null;
                }
            }
        });
    }
}
