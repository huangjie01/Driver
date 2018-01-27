package com.huangjie.driver.utils;
import android.view.View;

/**
 * Created by huangjie on 2017/1/15.
 */

public abstract class MyOnClickListener implements View.OnClickListener {
    private int mid = -1;
    private long mLastClickTime = 0;
    private final static int MIN_CLICK_DELAY_TIME = 1000;

    @Override
    public void onClick(View view) {
        long currentTime = System.currentTimeMillis();
        int id = view.getId();
        if (id != mid) {
            mLastClickTime = currentTime;
            mid = id;
            click(view);
            return;
        }

        if (currentTime - mLastClickTime > MIN_CLICK_DELAY_TIME) {
            click(view);
            mLastClickTime = currentTime;
        }

    }

    protected abstract void click(View view);
}
