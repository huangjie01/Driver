package com.huangjie.driver.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huangjie.driver.R;
import com.huangjie.driver.bean.music.MusicBean;
import com.huangjie.driver.utils.Utils;

import java.util.ArrayList;

/**
 * Created by huangjie on 2017/8/8.
 */

public class MusicAdapter extends BaseAdapter {
    private ArrayList<MusicBean> mDataList;
    private MusicClickCallBack mCallBack;

    public void setCallBack(MusicClickCallBack callBack) {
        this.mCallBack = callBack;
    }

    public MusicAdapter(ArrayList<MusicBean> dataList) {
        this.mDataList = dataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = Utils.inflate(R.layout.music_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.musicName = (TextView) convertView.findViewById(R.id.music_name);
            viewHolder.musicSinger = (TextView) convertView.findViewById(R.id.music_singer);
            viewHolder.musicAlbum = (TextView) convertView.findViewById(R.id.music_album);
            viewHolder.musicMore = (ImageView) convertView.findViewById(R.id.music_more);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.musicName.setText(mDataList.get(position).getName() + "");
        viewHolder.musicSinger.setText(mDataList.get(position).getArtist() + "");
        viewHolder.musicAlbum.setText(mDataList.get(position).getAlbum() + "");
        onClick(viewHolder.musicMore, position);
        return convertView;
    }


    public static class ViewHolder {
        TextView musicName;
        TextView musicSinger;
        TextView musicAlbum;
        ImageView musicMore;
    }

    public void onClick(View view, final int posiotn) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallBack != null) {
                    mCallBack.onClick(posiotn);
                }
            }
        });
    }

    public interface MusicClickCallBack {
         void onClick(int position);
    }
}
