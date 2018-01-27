
package com.huangjie.driver;
import com.huangjie.driver.bean.music.MusicTrack;

interface IplayMusic {

       void playNext();
       void stop();
       void playPre();
       void startPlay();
       String getMusicAlum();
       String getMusicArtist();
       String getMusicName();
       MusicTrack getMusic();

}
