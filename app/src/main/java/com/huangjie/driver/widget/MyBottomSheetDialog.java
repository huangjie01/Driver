package com.huangjie.driver.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import com.huangjie.driver.R;
import com.huangjie.driver.utils.Utils;

/**
 * Created by huangjie on 2017/8/1.
 */

public class MyBottomSheetDialog extends BottomSheetDialog {
    private View mContentView;


    public MyBottomSheetDialog(@NonNull Context context) {
        super(context);
        mContentView = Utils.inflate(R.layout.music_bottom_sheet);
        setContentView(mContentView);
       /* BottomSheetBehavior mDialogBehavior = BottomSheetBehavior.from((View) mContentView.getParent());
        mDialogBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);//展开*/
        getWindow().setBackgroundDrawable(new ColorDrawable(Utils.getColor(R.color.transparent)));

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int screenHeight = getScreenHeight(getOwnerActivity());
        int statusBarHeight = getStatusBarHeight(getContext());
        int dialogHeight = screenHeight - statusBarHeight;
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, dialogHeight == 0 ?
                ViewGroup.LayoutParams.MATCH_PARENT : dialogHeight);
        setCancelable(false);
        inits();
    }

    /**
     * 初始化
     */
    private void inits() {


    }

    private static int getScreenHeight(Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }

    private static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }


    /**
     * 关闭回掉接口
     */
    public interface CloseCallBack {
        void close();
    }
}
