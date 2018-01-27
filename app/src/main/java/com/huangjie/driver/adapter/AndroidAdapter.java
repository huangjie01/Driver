package com.huangjie.driver.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huangjie.driver.R;
import com.huangjie.driver.bean.AndroidBean;
import com.huangjie.driver.constant.Constant;
import com.huangjie.driver.ui.webview.WebViewActivity;
import com.huangjie.driver.utils.ImageLoadUtils;
import com.huangjie.driver.utils.LogUtils;
import com.huangjie.driver.utils.TimeUtil;
import com.huangjie.driver.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangjie on 2017/5/29.
 */

public class AndroidAdapter extends BaseAdapter {
    private ArrayList<AndroidBean> mDataList;

    public AndroidAdapter(ArrayList<AndroidBean> dataList) {
        this.mDataList = new ArrayList<>();
        mDataList.addAll(dataList);
    }


    public void addData(List<AndroidBean> datalist) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        mDataList.addAll(datalist);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public AndroidBean getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = Utils.inflate(R.layout.android_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mIcon = (ImageView) convertView.findViewById(R.id.android_icon);
            viewHolder.mContent = (TextView) convertView.findViewById(R.id.android_content);
            viewHolder.mDate = (TextView) convertView.findViewById(R.id.android_date);
            viewHolder.mAuthor = (TextView) convertView.findViewById(R.id.android_author);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.error("android item 点击");
                    Intent intent = new Intent(parent.getContext(), WebViewActivity.class);
                    intent.putExtra(Constant.SEND_DATA, mDataList.get(position).getUrl());
                    intent.putExtra(WebViewActivity.SEND_TITle, mDataList.get(position).getDesc());
                    parent.getContext().startActivity(intent);
                }
            });
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mContent.setText(mDataList.get(position).getDesc());
        viewHolder.mAuthor.setText(mDataList.get(position).getWho());
        viewHolder.mDate.setText(TimeUtil.getTranslateTime(
                mDataList.get(position).getPublishedAt()));
        if (mDataList.get(position).getImages() != null &&
                mDataList.get(position).getImages().size() > 0) {
            ImageLoadUtils.displayGif(viewHolder.mIcon, mDataList.get(position).getImages().get(0));
        } else {
            viewHolder.mIcon.setVisibility(View.GONE);
        }

        return convertView;
    }


    public static class ViewHolder {
        ImageView mIcon;
        TextView mContent;
        TextView mAuthor;
        TextView mDate;
    }
}
