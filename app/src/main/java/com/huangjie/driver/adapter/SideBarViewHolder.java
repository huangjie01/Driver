package com.huangjie.driver.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by huangjie on 2017/1/21.
 */

public class SideBarViewHolder extends RecyclerView.ViewHolder {
    protected ViewDataBinding mViewBinder;

    public SideBarViewHolder(View itemView) {
        super(itemView);
    }

    public void setmViewBinder(ViewDataBinding ViewBinder) {
        this.mViewBinder = ViewBinder;
    }

    public ViewDataBinding getViewBinder() {
        return mViewBinder;
    }
}
