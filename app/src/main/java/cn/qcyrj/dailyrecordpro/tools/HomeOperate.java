package cn.qcyrj.dailyrecordpro.tools;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class HomeOperate {

    private static final String basePath = "/home";
    private static final String baseData = "home.json";
    public static boolean viewType = false;
    private static JSONObject dataJson;

    public static void baseLoadData() {
        File file = new File(Tools.getOutFilePath() + basePath, baseData);
        if (!file.exists()) {
            //首次打开
            dataJson = new JSONObject();
            try {
                dataJson.put("viewType", viewType);
                updateData();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } else {
            parseJsonData(Tools.readFromFile(file.toString()));
        }
    }

    public static boolean getViewType() {
        return viewType;
    }

    public static void updateViewType(boolean boo) {
        viewType = boo;
        try {
            dataJson.put("viewType", viewType);
            updateData();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        updateData();
    }


    public static void updateData() {
        Tools.writeToFile(dataJson.toString(), Tools.getOutFilePath() + basePath + "/" + baseData);
    }


    private static void parseJsonData(String jsonString) {
        try {
            dataJson = new JSONObject(jsonString);
            // 读取index值
            viewType = dataJson.getBoolean("viewType");
        } catch (JSONException e) {
            Log.d("HomeOperate", "parseJsonData: " + e);
        }
    }

}
