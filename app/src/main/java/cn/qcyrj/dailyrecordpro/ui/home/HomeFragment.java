package cn.qcyrj.dailyrecordpro.ui.home;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import cn.qcyrj.dailyrecordpro.R;
import cn.qcyrj.dailyrecordpro.activity.MessageActivity;
import cn.qcyrj.dailyrecordpro.store.DiaryList;
import cn.qcyrj.dailyrecordpro.tools.HomeOperate;
import cn.qcyrj.dailyrecordpro.ui.adapter.DiaryAdapter;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private DiaryAdapter adapter;
    private TextInputEditText searchEditText;
    private TabLayout tabLayout;
    private boolean isListView = HomeOperate.getViewType();
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

    @SuppressLint("ClickableViewAccessibility")
    private void initView(View view) {
        recyclerView = view.findViewById(R.id.diaryList);
        searchEditText = view.findViewById(R.id.searchEditText);
        tabLayout = view.findViewById(R.id.tabLayout);
        view.findViewById(R.id.changeView).setOnClickListener(this);
        SwipeRefreshLayout swipeRefresh = view.findViewById(R.id.diaryRefresh);
        swipeRefresh.setOnRefreshListener(() -> {
            update();
            swipeRefresh.setRefreshing(false);
        });
        FloatingActionButton fab = view.findViewById(R.id.home_floatingActionButton);
        fab.setOnClickListener(this::startActivity);
        view.findViewById(R.id.coordinatorLayout).setOnTouchListener((v, event) -> {
            clearFocusAndHideKeyboard();
            return false;
        });
    }


    @SuppressLint("ClickableViewAccessibility")
    private void initViewOperate() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                clearFocusAndHideKeyboard();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                clearFocusAndHideKeyboard();
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                clearFocusAndHideKeyboard();
            }
        });
        recyclerView.setOnTouchListener((v, event) -> {
            clearFocusAndHideKeyboard();
            return false;
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initViewOperate();
        setupRecyclerView();
        return view;
    }

    @Override
    public void onClick(View v) {
        clearFocusAndHideKeyboard();
        if (v.getId() == R.id.changeView) {
            switchViewStyle();
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    public void update() {
        adapter.setDiaryList(DiaryList.getInstance().getDiaryList());
    }


    @Override
    public void onResume() {
        super.onResume();
        update();
    }

    private void setupRecyclerView() {
        adapter = new DiaryAdapter(isListView ? 0 : 1);
        adapter.setDiaryList(DiaryList.getInstance().getDiaryList());
        if (isListView) {
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        } else {
            // 使用StaggeredGridLayoutManager实现真正的独立高度
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(staggeredGridLayoutManager);
        }
        recyclerView.setAdapter(adapter);
    }


    private void switchViewStyle() {
        isListView = !isListView;
        HomeOperate.updateViewType(isListView);
        if (isListView) {
            // 切换到列表视图
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            adapter.setDisplayStyle(0);
        } else {
            // 切换到块状视图
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(staggeredGridLayoutManager);
            adapter.setDisplayStyle(1);
        }
    }

    private void clearFocusAndHideKeyboard() {
        if (searchEditText != null && searchEditText.hasFocus()) {
            searchEditText.clearFocus();
            InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
            }
        }
    }



}
