package cn.qcyrj.dailyrecordpro;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import cn.qcyrj.dailyrecordpro.data.User;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private ViewPager2 viewPager;
    private TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = findViewById(R.id.main_title);
        bottomNavigationView = findViewById(R.id.main_bottom_navigation);
        MainFragmentStateAdapter adapter = new MainFragmentStateAdapter(this);
        viewPager = findViewById(R.id.main_viewpager);
        viewPager.setAdapter(adapter);
        setBottomSelected();
        File externalFilesDir = getExternalFilesDir(null);
        File externalCacheDir = getExternalCacheDir();


        Log.d("MainActivity", "externalFilesDir: " + externalFilesDir);
        Log.d("MainActivity", "externalCacheDir: " + externalCacheDir);
        User user = new User("admin", "123456",true, true, true, 0);
        JSONObject jsonObject = (JSONObject) JSONObject.wrap(user);
        Log.d("MainActivity", "jsonObject: " + jsonObject);
        try {
            // 检查文件是否存在
            File file = new File(externalFilesDir, "user.json");
            if (!file.exists()) {
                Log.d("MainActivity", "JSON文件不存在: " + file.getAbsolutePath());
            }else{
                FileReader reader = new FileReader(file);
                Gson gson = new Gson();
                User user1 = gson.fromJson(reader, User.class);
                reader.close();
                Log.d("MainActivity", "JSON文件内容: " + user1);
            }

            // 读取文件内容


        } catch (IOException e) {
            Log.e("TAG", "从JSON文件加载User对象时出错", e);
        }
//        Gson gson = new Gson();
//        String json = gson.toJson(user);
//        try(FileWriter writer=new FileWriter(new File(externalFilesDir, "user.json"))){
//            writer.write(json);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }




    public void setBottomSelected() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if(itemId==R.id.navigation_home) {
                title.setText("首页");
                viewPager.setCurrentItem(0);
            } else if (itemId == R.id.navigation_dashboard) {
                title.setText("面板");
                viewPager.setCurrentItem(1);
            } else if (itemId == R.id.navigation_user) {
                title.setText("用户");
                viewPager.setCurrentItem(2);
            }
            return true;
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
            super.onPageSelected(position);
            switch (position) {
                case 0:
                    bottomNavigationView.setSelectedItemId(R.id.navigation_home);
                    break;
                case 1:
                    bottomNavigationView.setSelectedItemId(R.id.navigation_dashboard);
                    break;
                case 2:
                    bottomNavigationView.setSelectedItemId(R.id.navigation_user);
                    break;
            }
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }

}