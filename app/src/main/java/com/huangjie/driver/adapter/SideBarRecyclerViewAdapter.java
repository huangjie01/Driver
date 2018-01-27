package com.huangjie.driver.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.huangjie.driver.R;
import com.huangjie.driver.bean.SideBarBean;
import java.util.ArrayList;

/**
 * Created by huangjie on 2017/1/21.
 */

public class SideBarRecyclerViewAdapter extends RecyclerView.Adapter<SideBarViewHolder> {
    private ArrayList<SideBarBean> mDataList;

    public SideBarRecyclerViewAdapter(ArrayList<SideBarBean> DataList) {
        mDataList=new ArrayList<>();
        mDataList.addAll(DataList);

    }

    @Override
    public SideBarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binder = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.sidebar_recyclerview_item, parent, false);
        SideBarViewHolder holder = new SideBarViewHolder(binder.getRoot());
        holder.setmViewBinder(binder);
        return holder;
    }

    @Override
    public void onBindViewHolder(SideBarViewHolder holder, int position) {
        SideBarBean bean=mDataList.get(position);
       holder.getViewBinder().setVariable(com.huangjie.driver.BR.sidebar,bean);
        holder.getViewBinder().executePendingBindings();
    }

    @Override
    public int getItemCount() {

        return mDataList.size();
    }
}
