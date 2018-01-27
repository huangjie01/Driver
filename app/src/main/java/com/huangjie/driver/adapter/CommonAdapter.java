package com.huangjie.driver.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by huangjie on 2016/8/14.
 * 通用的adapter
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    private List<T> mData;
    private int mLayoutid;
    private ViewHolder viewHolder;

    public CommonAdapter(List<T> data, int layoutid) {
        this.mData = data;
        this.mLayoutid = layoutid;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = ViewHolder.getInstance(mLayoutid, parent, convertView, position);
        if (mData.size() > position) {
            convert(viewHolder, mData.get(position), position);
        }
        return viewHolder.getConvertView();
    }

    /**
     * 为ViewHolder中的View 赋值
     *
     * @param viewHolder
     * @param data
     */
    public abstract void convert(ViewHolder viewHolder, T data, int position);

}
