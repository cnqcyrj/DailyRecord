package cn.qcyrj.dailyrecordpro;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import cn.qcyrj.dailyrecordpro.ui.dashboard.DashboardFragment;
import cn.qcyrj.dailyrecordpro.ui.home.HomeFragment;
import cn.qcyrj.dailyrecordpro.ui.user.UserFragment;

public class MainFragmentStateAdapter extends FragmentStateAdapter {

    public MainFragmentStateAdapter(MainActivity activity) {
        super(activity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new DashboardFragment();
            case 2:
                return new UserFragment();
            case 0:
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
