package com.huangjie.driver.bean.music;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by huangjie on 2017/7/11.
 */

public class MusicTrack implements Parcelable {
    private long mId;//歌曲ID
    private int mPosition;//位置
    private String mTitle;//歌曲名字
    private String mArtist;//歌唱者
    private String mAlbum;//专辑

    protected MusicTrack(Parcel in) {
        mId = in.readLong();
        mPosition = in.readInt();
        mTitle = in.readString();
        mArtist = in.readString();
        mAlbum = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeInt(mPosition);
        dest.writeString(mTitle);
        dest.writeString(mArtist);
        dest.writeString(mAlbum);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MusicTrack> CREATOR = new Creator<MusicTrack>() {
        @Override
        public MusicTrack createFromParcel(Parcel in) {
            return new MusicTrack(in);
        }

        @Override
        public MusicTrack[] newArray(int size) {
            return new MusicTrack[size];
        }
    };

    public void readFromParcel(Parcel parcel) {
        mId = parcel.readLong();
        mPosition = parcel.readInt();
        mArtist = parcel.readString();
        mTitle = parcel.readString();
        mAlbum = parcel.readString();
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getmArtist() {
        return mArtist;
    }

    public void setmArtist(String mArtist) {
        this.mArtist = mArtist;
    }

    public String getAlbum() {
        return mAlbum;
    }

    public void setAlbum(String album) {
        mAlbum = album;
    }

    public static Creator<MusicTrack> getCREATOR() {
        return CREATOR;
    }
}
