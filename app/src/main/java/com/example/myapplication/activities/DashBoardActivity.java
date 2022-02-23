package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;

public class DashBoardActivity extends AppCompatActivity {
    TextView Add_attenence_txt_btn,feed_back_txt_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        startProcess();
        continueProcess();
    }

    private void startProcess() {
        Add_attenence_txt_btn = findViewById(R.id.Add_attenence_txt_btn);
                feed_back_txt_btn  = findViewById(R.id.feed_back_txt_btn);

    }

    private void continueProcess() {
        feed_back_txt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoardActivity.this,FeedBackActivity.class));
            }
        });
        Add_attenence_txt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoardActivity.this,AttendenceActivity.class));
            }
        });
    }
}