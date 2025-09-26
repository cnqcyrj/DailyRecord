package cn.qcyrj.dailyrecordpro.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingtoolbar.FloatingToolbarLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.qcyrj.dailyrecordpro.R;
import cn.qcyrj.dailyrecordpro.tools.Tools;
import jp.wasabeef.richeditor.RichEditor;

public class MessageActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, View.OnClickListener,RichEditor.OnTextChangeListener{
    private RichEditor editor;

    private final List<LinearLayout> toolbarLayouts=new ArrayList<>();

    private int toolbarLayoutIndex = 0;
    private boolean isEdit = false;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        MaterialToolbar toolbar = findViewById(R.id.checkMessage_toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());//设置返回
        toolbar.setOnMenuItemClickListener(this);
        editor = findViewById(R.id.editor);
        editor.setPlaceholder("请输入内容...");
        editor.setFontSize(18);
        editor.setPadding(50,10,50,0);
        setOnClick(R.id.floating_toolbar_child_top);
        setOnClick(R.id.floating_toolbar_child_bottom);
        setOnClick(R.id.floating_toolbar_child_left);
        setOnClick(R.id.floating_toolbar_child_right);
        toolbarLayouts.add(findViewById(R.id.floating_toolbar_left));
        toolbarLayouts.add(findViewById(R.id.floating_toolbar_right));
        toolbarLayouts.add(findViewById(R.id.floating_toolbar_top));
        toolbarLayouts.add(findViewById(R.id.floating_toolbar_bottom));
        showToolbarLayout();

    }
    public void setOnClick(int id){
        LinearLayout toolbarLayout = findViewById(id);
        if (toolbarLayout != null) {
            for (int i = 0; i < toolbarLayout.getChildCount(); i++) {
                View child = toolbarLayout.getChildAt(i);
                child.setOnClickListener(this);
            }
        }
    }



    private void changeToolbarLayout() {
        toolbarLayoutIndex++;
        if (toolbarLayoutIndex >= toolbarLayouts.size()+1) {
            toolbarLayoutIndex = 0;
        }
        showToolbarLayout();
    }

    private void showToolbarLayout() {
        for(LinearLayout toolbarLayout : toolbarLayouts){
            toolbarLayout.setVisibility(toolbarLayoutIndex == toolbarLayouts.indexOf(toolbarLayout) ? View.VISIBLE : View.GONE);
        }
    }





    private final List<Boolean> isEditList=new ArrayList<>(Arrays.asList(false,false,false,false,false));
    private final List<String> editTextList= List.of("粗体","斜体","删除线","缩进","删除");

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.underline){
            Tools.showToast("已"+(isEditList.get(0)?"取消" : "开启")+"下划线");
            isEditList.set(0,!isEditList.get(0));
            editor.setUnderline();
        } else if (v.getId() == R.id.boldface) {
            Tools.showToast("已"+(isEditList.get(1)?"取消" : "开启")+"粗体");
            isEditList.set(1,!isEditList.get(1));
            editor.setBold();
        } else if (v.getId() == R.id.italic_type) {
            Tools.showToast("已"+(isEditList.get(2)?"取消" : "开启")+"斜体");
            isEditList.set(2,!isEditList.get(2));
            editor.setItalic();
        } else if (v.getId() == R.id.strikethrough) {
            Tools.showToast("已"+(isEditList.get(3)?"取消" : "开启")+"删除线");
            isEditList.set(3,!isEditList.get(3));
            editor.setStrikeThrough();
        } else if (v.getId() == R.id.cancel) {
            editor.undo();
        } else if (v.getId() == R.id.rework) {
            editor.redo();
        } else if (v.getId() == R.id.center) {
            Tools.showToast("已居中");
            editor.setAlignCenter();
        } else if (v.getId() ==R.id.left_justify) {
            Tools.showToast("已左对齐");
            editor.setAlignLeft();
        } else if (v.getId() == R.id.right_justify) {
            Tools.showToast("已右对齐");
            editor.setAlignRight();
        } else if(v.getId() == R.id.ordered_list){
            Tools.showToast("有序列表");
            editor.setNumbers();
        } else if (v.getId() == R.id.unordered_list) {
            Tools.showToast("无序列表");
            editor.setBullets();
        }
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.manage) {
            changeToolbarLayout();
        }
        return false;
    }

    @Override
    public void finish() {
        if(isEdit){
            Tools.showToast("修改成功");
        }
        super.finish();
        overridePendingTransition(R.anim.scale_in,
                R.anim.exit_to_top);
    }

    @Override
    public void onTextChange(String text) {
        isEdit = true;
    }
}