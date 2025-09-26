package cn.qcyrj.dailyrecordpro.tools;

import android.util.Log;
import android.view.Gravity;

import com.google.gson.Gson;
import com.hjq.toast.Toaster;
import com.hjq.toast.style.CustomToastStyle;
import com.hjq.toast.style.WhiteToastStyle;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import cn.qcyrj.dailyrecordpro.R;
import cn.qcyrj.dailyrecordpro.data.Diary;
import cn.qcyrj.dailyrecordpro.data.User;
public class Start {
    public static void init(){
        initDate();
        initToast();
    }





    private static User getUser(){
        try {
            // 检查文件是否存在
            File file = new File(Tools.getOutFilePath(), "user.json");
            if (!file.exists()) {
                Log.d("MainActivity", "JSON文件不存在: " + file.getAbsolutePath());
            }else{
                FileReader reader = new FileReader(file);
                Log.d("MainActivity", "JSON文件已存在: " + file.getAbsolutePath());
                Gson gson = new Gson();
                return gson.fromJson(reader, User.class);
            }
            return null;
        } catch (IOException e) {
            Log.e("TAG", "从JSON文件加载User对象时出错", e);
            return null;
        }
    }


    private static void initToast(){
        Toaster.setStyle(new CustomToastStyle(R.layout.custom_toast_layout));
        Toaster.setGravity(Gravity.CENTER|Gravity.BOTTOM,0,100);
    }
    private static void initDate(){
        DataOperate.baseLoadData();

    }
}
