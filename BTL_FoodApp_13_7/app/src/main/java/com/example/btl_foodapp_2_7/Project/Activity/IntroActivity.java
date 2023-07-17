package com.example.btl_foodapp_2_7.Project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.btl_foodapp_2_7.R;

public class IntroActivity extends AppCompatActivity {
    private TextView btnstart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        btnstart = findViewById(R.id.btn_start);
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroActivity.this, LoginActivity.class));
            }
        });
    }
}