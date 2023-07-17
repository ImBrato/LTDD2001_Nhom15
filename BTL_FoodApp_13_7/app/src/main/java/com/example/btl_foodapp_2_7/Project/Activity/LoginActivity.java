package com.example.btl_foodapp_2_7.Project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.example.btl_foodapp_2_7.R;

public class LoginActivity extends AppCompatActivity {
    private TextView tvDangky;
    private Button btnDangnhap;

    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvDangky = findViewById(R.id.tvBackLogin);
        tvDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SiginActivity.class));
            }
        });

        btnDangnhap = findViewById(R.id.btnDangnhap);
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });

        lottieAnimationView = findViewById(R.id.gif);
        lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);
//        lottieAnimationView.animate().setDuration(99999999);

    }
}