package cn.qcyrj.dailyrecordpro.tools;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import cn.qcyrj.dailyrecordpro.data.Diary;
import cn.qcyrj.dailyrecordpro.store.DiaryList;
import lombok.Getter;

public class DataOperate {
    private static final String basePath = "/data";
    private static final String baseData = "data.json";
    private static final String storePath = basePath + "/store";

    private static int index = 0;

    @Getter
    public static int toolbarIndex = 0;


    private static JSONObject dataJson;

    public static void baseLoadData(){
        File file = new File(Tools.getOutFilePath()+basePath, baseData);
        if(!file.exists()){
            //首次打开
            dataJson = new JSONObject();
            try {
                dataJson.put("index", index);
                dataJson.put("toolbarIndex", toolbarIndex);
                updateData();
            } catch (JSONException e) {
                Log.e("DataOperate", "baseLoadData: " + e);
            }
        }else {
            parseJsonData(Tools.readFromFile(file.toString()));
        }
    }

    public static void updateToolbarIndex(int index) {
        toolbarIndex = index;
        try {
            dataJson.put("toolbarIndex", toolbarIndex);
        } catch (JSONException e) {
            Log.e("DataOperate", "updateToolbarIndex: " + e);
        }
        updateData();
    }


    public static void updateData(){
        Tools.writeToFile(dataJson.toString(), Tools.getOutFilePath() + basePath + "/" + baseData);
    }

    public static void saveData(Diary  diary) {
        try {
            DiaryList.getInstance().getDiaryList().add(diary);
            index++;
            dataJson.put(index+"", index);
            dataJson.put("index", index);
            String path = Tools.getOutFilePath()+storePath+"/"+index;
            Tools.writeToFile(diary.getTitle(), path + "/title");
            Tools.writeToFile(diary.getContent(), path + "/content");
            Tools.writeToFile(Tools.getCurrentTime(), path + "/updateTime");
            Tools.writeToFile(diary.getCreateTime(), path + "/createTime");
            updateData();
        } catch (JSONException e) {
            Log.e("DataOperate", "saveData: " + e);
        }
    }

    private static void parseJsonData(String jsonString) {
        try {
            dataJson = new JSONObject(jsonString);
            // 读取index值
            index = dataJson.getInt("index");
            toolbarIndex = dataJson.getInt("toolbarIndex");
            for (int i = 1; i <= index; i++) {
                String path = Tools.getOutFilePath()+storePath+"/"+i;
                String title = Tools.readFromFile(path + "/title");
                String content = Tools.readFromFile(path + "/content");
                String time = Tools.readFromFile(path + "/updateTime");
                String createTime = Tools.readFromFile(path + "/createTime");
                Diary diary = new Diary(title, content, time, createTime);
                DiaryList.getInstance().getDiaryList().add(diary);
            }
        } catch (JSONException e) {
            Log.e("DataOperate", "parseJsonData: " + e);
        }
    }


}
