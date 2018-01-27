package com.huangjie.driver.utils;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.huangjie.driver.global.DriverApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by huangjie on 2017/5/26.
 */

public class Utils {

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
     * 获取字符串
     *
     * @param id
     * @return
     */
    public static String getString(int id) {
        return getContext().getResources().getString(id);
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

    /**
     * 加载view
     *
     * @param layoutid
     * @param parent
     * @param status
     * @return
     */
    public static View inflate(int layoutid, ViewGroup parent, boolean status) {
        View view = LayoutInflater.from(getContext()).inflate(layoutid, parent, status);
        return view;
    }

    /**
     * 获取drawable
     *
     * @param id
     * @return
     */
    public static Drawable getDrawables(int id) {
        Drawable drawable = getContext().getResources().getDrawable(id);
        return drawable;
    }


    /**
     * 判断是否在ui线程中运行
     *
     * @return
     */
    public static boolean isRunUiThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }

    /**
     * 在主线下中运行
     *
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        //先判断当期是否就是说在主线程中运行
        if (isRunUiThread()) {
            runnable.run();
        } else {
            //handler除了可以发送message 消息之外，还可以发送一个runable 对象，也是在主线程中运行
            getHandler().post(runnable);
        }
    }

    //获取handler
    public static Handler getHandler() {
        return DriverApplication.getHander();
    }

    //获取主线程ID
    public static int getMainThreadId() {
        return DriverApplication.getMainThreadID();
    }

    public static int getCurrentYear() {
        long current = System.currentTimeMillis();
        Date date = new Date(current);
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return Integer.parseInt(format.format(date));
    }

    /**
     * 把px转换成dp
     *
     * @param pxvalue
     * @return
     */
    public static int px2dp(int pxvalue) {
        float scale = Utils.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxvalue / scale + 0.5f);
    }

    /**
     * dp转换成px
     *
     * @param dpvalue
     * @return
     */
    public static int dp2px(int dpvalue) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpvalue * scale + 0.5f);
    }

    /**
     * sp 转换成px
     *
     * @param spvalue
     * @return
     */
    public static int sp2px(int spvalue) {
        float scale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (scale * spvalue + 0.5f);
    }

    /**
     * px 转换成sp
     *
     * @param value
     * @return
     */
    public static int px2sp(int value) {
        float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (value / fontScale + 0.5f);
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getWindowWidth() {
        Point point = new Point();
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(point);
        return point.x;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static int getWindowHeight() {
        Point point = new Point();
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(point);
        return point.y;
    }

    public static void showToast(String data) {
        Toast.makeText(Utils.getContext(), data, Toast.LENGTH_SHORT).show();
    }


}

