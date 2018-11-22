package com.bwie.xiaqin.yuekao1121lx.sousuobuju;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bwie.xiaqin.yuekao1121lx.MainActivity;
import com.bwie.xiaqin.yuekao1121lx.R;

public class SuoActivity extends AppCompatActivity {

    private EditText sousuo_edit;
    private Button sousuo_btn;
    private LiuShi liuShi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suo_activity);
        sousuo_btn = findViewById(R.id.sousuo_btn);
        sousuo_edit = findViewById(R.id.sousuo_edit);
        liuShi = findViewById(R.id.liushi);
        sousuo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = sousuo_edit.getText().toString();
                TextView textView = new TextView(SuoActivity.this);
                textView.setPadding(5,5,5,5);
                textView.setText(s);
                liuShi.addView(textView);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SuoActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }
}
