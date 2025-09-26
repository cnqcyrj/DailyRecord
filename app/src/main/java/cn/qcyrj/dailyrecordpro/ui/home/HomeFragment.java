package cn.qcyrj.dailyrecordpro.ui.home;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.qcyrj.dailyrecordpro.R;
import cn.qcyrj.dailyrecordpro.activity.MessageActivity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

//
//    private void startActivity(View sourceView) {
//        Intent intent = new Intent(requireContext(), MessageActivity.class);
//
//        // 使用缩放动画
//        ActivityOptions options = ActivityOptions.makeScaleUpAnimation(
//                sourceView,
//                sourceView.getWidth() / 2,
//                sourceView.getHeight() / 2,
//                0, // 开始宽度
//                0  // 开始高度
//        );
//        startActivity(intent, options.toBundle());
//    }
    private void startActivity(View sourceView) {
        Context context = requireContext();
        Intent intent = new Intent(context, MessageActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
                requireContext(),
                R.anim.enter_from_buttom,  // 新 Activity 进入动画
                R.anim.exit_to_top   // 当前 Activity 退出动画
        );
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        SwipeRefreshLayout swipeRefresh = view.findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(() -> {

        });
        FloatingActionButton fab = view.findViewById(R.id.home_floatingActionButton);
        fab.setOnClickListener(this::startActivity);
        return view;
    }




}
