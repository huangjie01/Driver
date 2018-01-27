package com.huangjie.driver.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huangjie.driver.R;
import com.huangjie.driver.bean.movie.PersonBean;
import com.huangjie.driver.bean.movie.SubjectBean;
import com.huangjie.driver.constant.Constant;
import com.huangjie.driver.ui.home.MovieDetailActivity;
import com.huangjie.driver.utils.ImageLoadUtils;
import com.huangjie.driver.utils.LogUtils;
import com.huangjie.driver.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangjie on 2017/6/7.
 */

public class MovieAdapter extends BaseAdapter {
    private ArrayList<SubjectBean> mDataList;
    private Context mContext;

    public MovieAdapter(ArrayList<SubjectBean> dataList, Context context) {

        mDataList = dataList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public SubjectBean getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        SubjectBean subjectBean = mDataList.get(position);
        if (convertView == null) {
            convertView = Utils.inflate(R.layout.movie_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.movie_title);
            viewHolder.director = (TextView) convertView.findViewById(R.id.movie_director);
            viewHolder.cast = (TextView) convertView.findViewById(R.id.movie_cast);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.movie_icon);
            viewHolder.type = (TextView) convertView.findViewById(R.id.movie_type);
            viewHolder.grade = (TextView) convertView.findViewById(R.id.movie_grade);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ImageLoadUtils.loadImage(viewHolder.icon, subjectBean.getAwatarBean().getSmall());
        viewHolder.title.setText(subjectBean.getTitle());
        viewHolder.grade.setText(subjectBean.getRating().getAverage() + "");
        List<PersonBean> cast = subjectBean.getCasts();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < cast.size(); i++) {
            builder.append(cast.get(i).getName() + "/");
        }
        if (builder.length() > 1) {
            builder.deleteCharAt(builder.length() - 1);
        }
        viewHolder.cast.setText(builder.toString());
        viewHolder.director.setText(subjectBean.getDirectors().get(0).getName());
        StringBuilder typeBuilder = new StringBuilder();
        List<String> genres = subjectBean.getGenres();
        for (int j = 0; j < genres.size(); j++) {
            typeBuilder.append(genres.get(j) + "/");
        }
        typeBuilder.deleteCharAt(typeBuilder.length() - 1);
        viewHolder.type.setText(typeBuilder.toString());

        return convertView;
    }

    public static class ViewHolder {
        ImageView icon;
        TextView title;
        TextView director;
        TextView cast;
        TextView type;
        TextView grade;

    }
}
