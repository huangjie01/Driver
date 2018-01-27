package com.huangjie.driver.global;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by huangjie on 2017/1/15.
 */

public class DriverApplication extends Application {
    private static Context mContext;
    private static Handler mHandler;
    private static int mMainThreadID;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mHandler = new Handler();
        mMainThreadID = android.os.Process.myTid();

    }

    /**
     * 获取Application Context
     *
     * @return
     */
    public static Context getContext() {
        return mContext;
    }

    public static Handler getHander() {
        return mHandler;
    }

    /**
     * 获取主线程ID
     *
     * @return
     */
    public static int getMainThreadID() {
        return mMainThreadID;
    }

    public static String getCurrentProcessName() {
        ActivityManager manager =
                (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo info : manager.getRunningAppProcesses()) {
            if (info.pid == getMainThreadID()) {
                return info.processName;
            }
        }
        return null;
    }
}
