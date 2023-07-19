package com.example.btl_foodapp_2_7.Project.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.example.btl_foodapp_2_7.R;

public class LoginActivity extends AppCompatActivity {
    private TextView tvDangky;
    private Button btnDangnhap;
    private EditText username, password;
    SQLiteDatabase db;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onResume() {
        super.onResume();
//        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
//        String username = preferences.getString("username", "");
//        if (!username.isEmpty()) {
//            Toast.makeText(LoginActivity.this, "Login da dang nhap ", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(LoginActivity.this, "Login chua dang nhap", Toast.LENGTH_SHORT).show();
//        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = openOrCreateDatabase("Cookpad.db", MODE_PRIVATE, null);
        username = findViewById(R.id.inputUsername);
        password = findViewById(R.id.editTextTextPassword);

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
                String usernameTxt = username.getText().toString();
                String passwordTxt = password.getText().toString();
                String[] projection = {"id"};
                String selection = "username = ? AND password = ?";
                String[] selectionArgs = {usernameTxt, passwordTxt};
                Cursor cursor = db.query("user", projection, selection, selectionArgs, null, null, null);
//                Cursor res = db.query("user", null,null,null,null,null,null);
//                res.moveToNext();
                if (cursor.getCount() == 0) {
                    Toast.makeText(LoginActivity.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    Toast.makeText(LoginActivity.this, "Login thanh cong", Toast.LENGTH_SHORT).show();
                    SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username", usernameTxt);
                    editor.apply();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                }

            }
        });

        lottieAnimationView = findViewById(R.id.gif);
        lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);
//        lottieAnimationView.animate().setDuration(99999999);

    }
}