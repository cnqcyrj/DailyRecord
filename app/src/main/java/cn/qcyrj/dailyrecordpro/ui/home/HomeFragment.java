package cn.qcyrj.dailyrecordpro.ui.home;

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

    public void openActivity(){
        Context context= requireContext();
        Intent intent = new Intent(context, MessageActivity.class);
        ActivityCompat.startActivity(context, intent,null);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        SwipeRefreshLayout swipeRefresh = view.findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(() -> {

        });
        FloatingActionButton fab = view.findViewById(R.id.home_floatingActionButton);
        fab.setOnClickListener(view1 -> {
            openActivity();
        });
        return view;
    }




}
