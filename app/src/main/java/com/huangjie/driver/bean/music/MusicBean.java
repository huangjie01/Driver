package com.huangjie.driver.bean.music;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by huangjie on 2017/8/6.
 */

public class MusicBean implements Parcelable {
    private long id;
    private String name;
    private long album_id;
    private String album;
    private String artist;
    private long artist_id;
    private long duration;
    private long size;

    protected MusicBean(Parcel in) {
        id = in.readLong();
        name = in.readString();
        album_id = in.readLong();
        album = in.readString();
        artist = in.readString();
        artist_id = in.readLong();
        duration = in.readLong();
        size = in.readLong();
    }

    public MusicBean() {

    }


    public static final Creator<MusicBean> CREATOR = new Creator<MusicBean>() {
        @Override
        public MusicBean createFromParcel(Parcel in) {

            return new MusicBean(in);
        }

        @Override
        public MusicBean[] newArray(int size) {
            return new MusicBean[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(long album_id) {
        this.album_id = album_id;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(long artist_id) {
        this.artist_id = artist_id;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(id);
        dest.writeString(name);
        dest.writeLong(album_id);
        dest.writeString(album);
        dest.writeString(artist);
        dest.writeLong(artist_id);
        dest.writeLong(duration);
        dest.writeLong(size);
    }
}
