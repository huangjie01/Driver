package com.huangjie.driver.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.ColorMatrixColorFilter;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.huangjie.driver.R;
import com.huangjie.driver.bean.MeiziBean;
import com.huangjie.driver.constant.Constant;
import com.huangjie.driver.ui.home.child.WelfareDetailActivity;
import com.huangjie.driver.utils.Help;
import com.huangjie.driver.utils.ImageLoadUtils;
import com.huangjie.driver.utils.LogUtils;
import com.huangjie.driver.utils.Utils;

import java.util.ArrayList;
import java.util.UnknownFormatConversionException;

/**
 * Created by huangjie on 2017/5/27.
 */

public class WelfareAdapter extends RecyclerView.Adapter<WelfareAdapter.ViewHolder> {
    private ArrayList<MeiziBean> mDataList;
    private OnItemClickListener listner;
    private Context mContext;

    public WelfareAdapter(ArrayList<MeiziBean> dataList, Context context) {
        this.mContext = context;
        mDataList = new ArrayList<>();
        mDataList.addAll(dataList);
        LogUtils.error("数据大小：" + mDataList.size());
    }

    public void addData(ArrayList<MeiziBean> datalist) {
        mDataList.addAll(datalist);
        notifyDataSetChanged();
    }

    @Override
    public WelfareAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(Utils.getContext()).
                inflate(R.layout.welfare_item, parent,false);
        return new WelfareAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final WelfareAdapter.ViewHolder holder, final int position) {

        bindView(holder, position);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WelfareDetailActivity.class);
                intent.putExtra(Constant.SEND_DATA, mDataList.get(position).getUrl());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    final android.support.v4.util.Pair<View, String>[] pairs = Help.createSafeTransitionParticipants
                            ((Activity) mContext, false, new android.support.v4.util.Pair<>((
                                    holder.imageView), mContext.getString(R.string.image_share)));
                    ActivityOptionsCompat options =
                            ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)
                                    mContext, pairs);
                    mContext.startActivity(intent, options.toBundle());
                } else {
                    mContext.startActivity(intent);
                }
            }
        });
    }

    private void bindView(final ViewHolder holder, int position) {
        final MeiziBean meiziBean = mDataList.get(position);
        Glide.with(Utils.getContext())
                .load(meiziBean.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class OnItemClickListener implements View.OnClickListener {


        @Override
        public void onClick(View v) {

        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.welfare_imageview);
        }


    }


}
