package com.example.btl_foodapp_2_7.Project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.example.btl_foodapp_2_7.R;

public class SiginActivity extends AppCompatActivity {
    private TextView tvBackLogin;
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin);

        tvBackLogin = findViewById(R.id.tvBackLogin);
        tvBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SiginActivity.this, LoginActivity.class));
            }
        });

        lottieAnimationView = findViewById(R.id.gif);
        lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);

    }
}