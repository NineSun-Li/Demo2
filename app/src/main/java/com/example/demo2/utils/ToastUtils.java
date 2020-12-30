package com.example.demo2.utils;

import android.content.Context;
import android.widget.Toast;

public final class ToastUtils {
    public static void s(Context context, String s) {
        if (!isShowToast()) {
            Toast.makeText(context.getApplicationContext(), s, Toast.LENGTH_SHORT)
                    .show();
        }
    }

    /**
     * Prevent continuous click, jump two pages
     */
    private static long lastToastTime;
    private final static long TIME = 1500;

    public static boolean isShowToast() {
        long time = System.currentTimeMillis();
        if (time - lastToastTime < TIME) {
            return true;
        }
        lastToastTime = time;
        return false;
    }
}