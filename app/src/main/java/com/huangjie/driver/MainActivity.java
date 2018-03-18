package com.huangjie.driver;

import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.huangjie.driver.adapter.SideBarRecyclerViewAdapter;
import com.huangjie.driver.bean.SideBarBean;
import com.huangjie.driver.constant.Constant;
import com.huangjie.driver.databinding.ActivityMainBinding;
import com.huangjie.driver.ui.me.GuideMeFragment;
import com.huangjie.driver.ui.music.GuideMusicFragment;
import com.huangjie.driver.ui.home.GuideHomeFragment;
import com.huangjie.driver.utils.ResUtils;
import com.huangjie.driver.utils.StatusBarUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding mBinder;
    private ArrayList<SideBarBean> mDataList;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinder = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initData();
        init();
        initEvent();


    }

    /**
     * 初始化数据
     */
    private void initData() {
        mDataList = new ArrayList<>();
        SideBarBean one = new SideBarBean();
        one.setIcon(R.drawable.lay_icn_msg);
        one.setTitle("我的消息");
        mDataList.add(one);
        SideBarBean two = new SideBarBean();
        two.setIcon(R.drawable.lay_icn_shang);
        two.setTitle("赞赏杰哥");
        mDataList.add(two);
        SideBarBean bar = new SideBarBean();
        bar.setIcon(R.drawable.topmenu_icn_vip);
        bar.setTitle("会员中心");
        SideBarBean three = new SideBarBean();
        three.setIcon(R.drawable.lay_icn_similar);
        three.setTitle("积分商城");
        three.setHasChoise(true);
        three.setChoiseText("0积分");
        mDataList.add(three);
        SideBarBean four = new SideBarBean();
        four.setIcon(R.drawable.lay_icn_recording);
        four.setTitle("听歌识曲");
        mDataList.add(four);
        SideBarBean five = new SideBarBean();
        five.setIcon(R.drawable.topmenu_icn_skin);
        five.setTitle("主题换肤");
        five.setChoiseText("官方红");
        five.setHasChoise(true);
        mDataList.add(five);
        SideBarBean six = new SideBarBean();
        six.setIcon(R.drawable.lay_icn_document);
        six.setTitle("夜间模式");
        six.setHasBtn(true);
        mDataList.add(six);
        mBinder.mainRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mBinder.mainRecyclerview.setAdapter(new SideBarRecyclerViewAdapter(mDataList));
    }

    private void initEvent() {

        mBinder.mainViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeIcon(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBinder.mainViewpager.setCurrentItem(0);
        mBinder.mainIvSuggest.setOnClickListener(this);
        mBinder.mainIvMusic.setOnClickListener(this);
        mBinder.mainIvMe.setOnClickListener(this);
    }

    /**
     * 根据当前选中的viewpager切换顶部导航对应的图标
     *
     * @param position
     */
    private void changeIcon(int position) {
        resetIcon();
        if (position == 0) {
            mBinder.mainIvSuggest.setSelected(true);
        } else if (position == 1) {
            mBinder.mainIvMusic.setSelected(true);
        } else if (position == 2) {
            mBinder.mainIvMe.setSelected(true);
        }
    }

    /**
     * 重置图标
     */
    private void resetIcon() {
        mBinder.mainIvMe.setSelected(false);
        mBinder.mainIvSuggest.setSelected(false);
        mBinder.mainIvMusic.setSelected(false);

    }

    /**
     * 做初始化操作
     */
    private void init() {
        setSupportActionBar(mBinder.mainToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, mBinder.mainDrawerlayout, ResUtils.getColor(R.color.colorTheme));
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mBinder.mainDrawerlayout, mBinder.mainToolbar, R.string.open, R.string.close);
        mBinder.mainDrawerlayout.setDrawerListener(mActionBarDrawerToggle);
        mBinder.mainViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return getFragment(position);
            }

            @Override
            public int getCount() {
                return Constant.GUIDE_VIEWPAGER_COUNT;
            }
        });
        mBinder.mainViewpager.setOffscreenPageLimit(2);
        mBinder.mainIvSuggest.setSelected(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 根据position获取当前的fragment
     *
     * @param position
     * @return
     */
    private Fragment getFragment(int position) {
        if (position == 0) {
            return new GuideHomeFragment();
        } else if (position == 1) {
            return new GuideMusicFragment();
        } else {
            return new GuideMeFragment();
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        //目的在于让 DrawerLayout 和 ActionBarDrawerToggle 的状态同步
        mActionBarDrawerToggle.syncState();
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_iv_suggest:
                if (mBinder.mainViewpager.getCurrentItem() != 0) {
                    changeIcon(0);
                    mBinder.mainViewpager.setCurrentItem(0);
                }
                break;
            case R.id.main_iv_music:
                if (mBinder.mainViewpager.getCurrentItem() != 1) {
                    changeIcon(1);
                    mBinder.mainViewpager.setCurrentItem(1);
                }
                break;
            case R.id.main_iv_me:
                if (mBinder.mainViewpager.getCurrentItem() != 2) {
                    changeIcon(2);
                    mBinder.mainViewpager.setCurrentItem(2);
                }
                break;
            default:
                break;

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
