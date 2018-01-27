package com.huangjie.driver.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import com.huangjie.driver.global.DriverApplication;

/**
 * Created by huangjie on 2017/1/15.
 */

public class ResUtils {
    /**
     * 获取context
     *
     * @return
     */
    public static Context getContext() {
        return DriverApplication.getContext();
    }

    /**
     * 获取color
     *
     * @param id
     * @return
     */
    public static int getColor(int id) {
        return getContext().getResources().getColor(id);
    }

    /**
     * 获取drawable
     *
     * @param id
     * @return
     */
    public static Drawable getDrawable(int id) {

        return getContext().getResources().getDrawable(id);

    }

    /**
     * 加载view
     *
     * @param layoutid
     * @return
     */
    public static View inflate(int layoutid) {

        View view = LayoutInflater.from(getContext()).inflate(layoutid, null);
        return view;
    }

   
}
