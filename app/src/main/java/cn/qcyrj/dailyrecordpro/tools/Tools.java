package cn.qcyrj.dailyrecordpro.tools;

import static android.content.Context.WINDOW_SERVICE;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.WindowMetrics;

import com.google.android.material.snackbar.Snackbar;
import com.hjq.toast.Toaster;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cn.qcyrj.dailyrecordpro.MyApp;
public class Tools {

    public static Context getContext() {
        return MyApp.getInstance();
    }

    public static File getOutFilePath() {
        return MyApp.getInstance().getExternalFilesDir(null);
    }

    public static File getOutCachePath() {
        return MyApp.getInstance().getExternalCacheDir();
    }


    public static void showToast(String message){
        Toaster.show(message);
    }

    public static int getScreenWidth() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(WINDOW_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Android 11及以上
            WindowMetrics windowMetrics = windowManager.getCurrentWindowMetrics();
            return windowMetrics.getBounds().width();
        } else {
            // Android 11以下
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.widthPixels;
        }
    }

    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d-HH-mm", Locale.getDefault());
        return sdf.format(new Date());
    }
    public static int getScreenHeight() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(WINDOW_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Android 11及以上
            WindowMetrics windowMetrics = windowManager.getCurrentWindowMetrics();
            return windowMetrics.getBounds().height();
        } else {
            // Android 11以下
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.heightPixels;
        }
    }
}
