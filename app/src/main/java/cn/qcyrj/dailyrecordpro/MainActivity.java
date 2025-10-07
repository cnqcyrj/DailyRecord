package cn.qcyrj.dailyrecordpro;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import cn.qcyrj.dailyrecordpro.tools.Start;
import lombok.Getter;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Getter
    private static MainActivity instance;


    @Getter
    private CircularProgressIndicator progressIndicator;
    private ViewPager2 viewPager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);
        Start.init();
        progressIndicator = findViewById(R.id.main_progress_circular);
        bottomNavigationView = findViewById(R.id.main_bottom_navigation);
        MainFragmentStateAdapter adapter = new MainFragmentStateAdapter(this);
        viewPager = findViewById(R.id.main_viewpager);
        viewPager.setAdapter(adapter);
        setBottomSelected();
    }




    public void setBottomSelected() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if(itemId==R.id.navigation_home) {
                viewPager.setCurrentItem(0);
            } else if (itemId == R.id.navigation_dashboard) {
                viewPager.setCurrentItem(1);
            } else if (itemId == R.id.navigation_user) {
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