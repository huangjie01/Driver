package com.huangjie.driver.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by huangjie on 2016/12/21.
 */

public class MyViewPager extends ViewPager {
    private float mLastX;
    private float mLastY;

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.sqrt((x - mLastX) * (x - mLastX) + (y - mLastY) * (y - mLastY)) - ViewConfiguration.get(getContext()).getScaledTouchSlop() > 0) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (Math.sqrt((x - mLastX) * (x - mLastX) + (y - mLastY) * (y - mLastY)) - ViewConfiguration.get(getContext()).getScaledTouchSlop() > 0) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }
}
