package com.huangjie.driver.utils;

import android.util.Log;

/**
 * Created by huangjie on 2017/5/26.
 */

public class LogUtils {
    private static final boolean isDebug = true;

    public static void error(String text) {
        if (isDebug) {
            Log.e("huangjie", text);
        }
    }

    public static void infor(String text) {
        if (isDebug) {
            Log.i("huangjie", text);
        }
    }
}
