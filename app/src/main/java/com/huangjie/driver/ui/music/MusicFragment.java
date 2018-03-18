package com.huangjie.driver.ui.music;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;

import com.huangjie.driver.R;
import com.huangjie.driver.adapter.MusicAdapter;
import com.huangjie.driver.base.BaseFragment;
import com.huangjie.driver.bean.music.MusicBean;
import com.huangjie.driver.constant.Constant;
import com.huangjie.driver.databinding.FragmentMusicListBinding;
import com.huangjie.driver.utils.LogUtils;
import com.huangjie.driver.utils.MusicUtil;
import com.huangjie.driver.widget.SideBar;

import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by huangjie on 2017/8/9.
 */

public class MusicFragment extends BaseFragment<FragmentMusicListBinding> {
    private ArrayList<MusicBean> mMusicList;
    private MusicAdapter mMusicAdapter;
    private MusicBottomSheet mMusicBottomSheet;
    private FragmentManager mManager;


    @Override
    public int setContentView() {
        return R.layout.fragment_music_list;
    }

    @Override
    protected void loadData() {
        super.loadData();
        if (mMusicList == null) {
            mMusicList = new ArrayList<>();
            MusicUtil.getMusicList(new MusicUtil.MusicCallBack() {
                @Override
                public void success(ArrayList<MusicBean> datalist) {
                    LogUtils.error("获取音乐数据" + datalist.toString());
                    mMusicList.addAll(datalist);
                    setAdapter();
                    datalist.clear();
                    datalist = null;
                }
            });
        } else {
            setAdapter();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    protected void handView() {
        super.handView();

    }

    @Override
    protected void handEvent() {
        super.handEvent();
        mBindingView.musicListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlayMusicActivity.launch(getContext(), mMusicList,position);
            }
        });

        mBindingView.musicSidebar.setView(mBindingView.tvDialog);
        mBindingView.musicSidebar.setSelected(false);
        mBindingView.musicSidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                mBindingView.musicSidebar.setSelected(s);
            }
        });
    }

    private void setAdapter() {
        showContentView();
        if (mMusicAdapter == null) {
            mMusicAdapter = new MusicAdapter(mMusicList);
            mBindingView.musicListview.setAdapter(mMusicAdapter);
            mMusicAdapter.setCallBack(new MusicAdapter.MusicClickCallBack() {
                @Override
                public void onClick(int position) {
                    showMusicBottomSheet(position);
                }
            });

        } else {
            mMusicAdapter.notifyDataSetChanged();
        }
    }


    public void showMusicBottomSheet(int position) {

        if (mManager == null) {
            mManager = getChildFragmentManager();
        }
        Fragment preview = mManager.findFragmentByTag("MusicBottomSheet");
        FragmentTransaction transaction = mManager.beginTransaction();
        if (preview != null) {
            transaction.remove(preview);
        }
        mMusicBottomSheet = new MusicBottomSheet();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.SEND_DATA, mMusicList.get(position));
        mMusicBottomSheet.setArguments(bundle);
        mMusicBottomSheet.show(mManager, "MusicBottomSheet");
    }
}
