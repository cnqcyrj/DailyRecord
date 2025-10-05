package cn.qcyrj.dailyrecordpro.tools;

import static android.content.Context.WINDOW_SERVICE;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.WindowMetrics;

import com.hjq.toast.Toaster;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
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


    public static void writeToFile(String content, String filePath) {
        ThreadManager.executeInBackground(() -> {
            try {
                File file = new File(filePath);
                File parentDir
                        = file.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    parentDir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(file);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
                writer.write(content);
                writer.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void writeToFile(byte[] imageData, String filePath) {
        ThreadManager.executeInBackground(() -> {
            try {
                File file = new File(filePath);
                File parentDir = file.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    parentDir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(imageData);
                fos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    public static String readFromFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return null;
            }
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
            br.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] readImageFromFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return null;
            }
            FileInputStream fis = new FileInputStream(file);
            byte[] imageData = new byte[(int) file.length()];
            fis.read(imageData);
            fis.close();
            return imageData;
        } catch (Exception e) {
            return null;
        }
    }
}
