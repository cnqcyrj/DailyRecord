package cn.qcyrj.dailyrecordpro.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import cn.qcyrj.dailyrecordpro.R;
import jp.wasabeef.richeditor.RichEditor;

public class MessageActivity extends AppCompatActivity {
    private RichEditor editor;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
    }
}
