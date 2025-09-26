package cn.qcyrj.dailyrecordpro;


import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import cn.qcyrj.dailyrecordpro.tools.DataOperate;
import cn.qcyrj.dailyrecordpro.tools.Start;
import cn.qcyrj.dailyrecordpro.tools.Tools;


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
        Start.init();
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