package com.huangjie.driver.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.media.session.MediaSessionCompat;

import com.huangjie.driver.IplayMusic;
import com.huangjie.driver.bean.music.MusicTrack;


/**
 * Created by huangjie on 2017/7/11.
 */

public class PlayMusicService extends Service {
    private MediaSessionCompat mediaSessionCompat;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return manager;
    }

    private IplayMusic.Stub manager = new IplayMusic.Stub() {
        @Override
        public void playNext() throws RemoteException {

        }

        @Override
        public void stop() throws RemoteException {

        }

        @Override
        public void playPre() throws RemoteException {

        }

        @Override
        public void startPlay() throws RemoteException {

        }

        @Override
        public String getMusicAlum() throws RemoteException {
            return null;
        }

        @Override
        public String getMusicArtist() throws RemoteException {
            return null;
        }

        @Override
        public String getMusicName() throws RemoteException {
            return null;
        }

        @Override
        public MusicTrack getMusic() throws RemoteException {
            return null;
        }
    };




}
