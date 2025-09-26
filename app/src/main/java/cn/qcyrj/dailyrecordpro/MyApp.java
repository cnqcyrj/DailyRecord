package cn.qcyrj.dailyrecordpro;

import android.app.Application;

import com.hjq.toast.Toaster;

import lombok.Getter;


public class MyApp extends Application {

    @Getter
    private static MyApp instance;

    @Override
    public void onCreate() {
        instance = this;
        Toaster.init(this);
        super.onCreate();
    }
}
