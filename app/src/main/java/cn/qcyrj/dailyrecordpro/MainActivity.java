package cn.qcyrj.dailyrecordpro;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import cn.qcyrj.dailyrecordpro.tools.Start;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Start.init();


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