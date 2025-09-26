package cn.qcyrj.dailyrecordpro.tools;



import org.json.JSONException;
import org.json.JSONObject;
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
import cn.qcyrj.dailyrecordpro.data.Diary;
import cn.qcyrj.dailyrecordpro.store.DiaryList;

public class DataOperate {

    private static final String basePath = "/data";
    private static final String baseData = "data.json";
    private static final String storePath = basePath + "/store";

    private static int index = 0;


    private static JSONObject dataJson;

    public static void baseLoadData(){
        File file = new File(Tools.getOutFilePath()+basePath, baseData);
        if(!file.exists()){
            //首次打开
            dataJson = new JSONObject();
            try {
                dataJson.put("index", index);
                updateData();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }else {
            parseJsonData(readFromFile(file.toString()));
        }
    }

    public static void updateData(){
        writeToFile(dataJson.toString(),Tools.getOutFilePath()+basePath+"/"+baseData);
    }

    public static void saveData(Diary  diary) {
        try {
            index++;
            dataJson.put(index+"", index);
            dataJson.put("index", index);
            String path = Tools.getOutFilePath()+storePath+"/"+index;
            writeToFile(diary.getTitle(), path+"/title");
            writeToFile(diary.getContent(), path+"/content");
            writeToFile(Tools.getCurrentTime(), path+"/updateTime");
            writeToFile(diary.getCreateTime(), path+"/createTime");
            updateData();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static void parseJsonData(String jsonString) {
        try {
            dataJson = new JSONObject(jsonString);
            // 读取index值
            index = dataJson.getInt("index");
            for (int i = 1; i <= index; i++) {
                String path = Tools.getOutFilePath()+storePath+"/"+i;
                String title=readFromFile( path+"/title");
                String content=readFromFile( path+"/content");
                String time=readFromFile( path+"/updateTime");
                String createTime = readFromFile( path+"/createTime");
                Diary diary = new Diary(title, content, time, createTime);
                DiaryList.getInstance().getDiaryList().add(diary);
            }
        } catch (JSONException e) {

        }
    }

    private static void writeToFile(String content, String filePath) {
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





    private static String readFromFile(String filePath) {
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

}
