package com.huangjie.driver.utils;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.huangjie.driver.bean.music.MusicBean;

import java.util.ArrayList;

/**
 * Created by huangjie on 2017/8/6.
 * 查询音乐相关信息工具类
 */

public class MusicUtil {

    /**
     * 获取音乐信息sql
     */
    public static String[] music = new String[]{
            MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ALBUM, MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ARTIST_ID, MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.SIZE};

    /**
     * 获取音乐作者信息sql
     */
    public static String[] music_artist = new String[]{
            MediaStore.Audio.Artists.ARTIST,
            MediaStore.Audio.Artists.NUMBER_OF_TRACKS,
            MediaStore.Audio.Artists._ID
    };

    /**
     * 获取音乐专辑sql
     */
    private static String[] music_album = new String[]{MediaStore.Audio.Albums._ID,
            MediaStore.Audio.Albums.ALBUM_ART,
            MediaStore.Audio.Albums.ALBUM,
            MediaStore.Audio.Albums.NUMBER_OF_SONGS,
            MediaStore.Audio.Albums.ARTIST};


    public static void getMusicList(final MusicCallBack callBack) {
        final Uri url = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        final ArrayList<MusicBean> musicList = new ArrayList<>();
        new Thread() {
            @Override
            public void run() {
                super.run();
                Cursor cursor = Utils.getContext().getContentResolver()
                        .query(url, music, null, null, null);
                try {
                    while (cursor.moveToNext()) {
                        MusicBean musicBean = new MusicBean();
                        musicBean.setId(cursor.getLong(
                                cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
                        musicBean.setName(cursor.getString(
                                cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                        musicBean.setAlbum(cursor.getString(
                                cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
                        musicBean.setAlbum_id(cursor.getLong(
                                cursor.getColumnIndex(MediaStore.Audio.Media
                                        .ALBUM_ID)));
                        musicBean.setArtist_id(cursor.getLong(
                                cursor.getColumnIndex(MediaStore.Audio.Media
                                        .ARTIST_ID)));
                        musicBean.setArtist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media
                                .ARTIST)));
                        musicBean.setDuration(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media
                                .DURATION)));
                        musicBean.setSize(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media
                                .SIZE)));
                        musicList.add(musicBean);
                    }
                    if (callBack != null) {
                        Utils.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                callBack.success(musicList);
                            }
                        });

                    }
                } finally {
                    cursor.close();
                }
            }
        }.start();

    }

    /**
     * 获取本地音乐数据回掉接口
     */
    public interface MusicCallBack {
        void success(ArrayList<MusicBean> datalist);
    }

}
