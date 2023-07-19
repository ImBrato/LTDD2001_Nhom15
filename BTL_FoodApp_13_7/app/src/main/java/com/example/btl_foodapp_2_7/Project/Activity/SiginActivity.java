package com.example.btl_foodapp_2_7.Project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
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

public class SiginActivity extends AppCompatActivity {
    private TextView tvBackLogin;
    private Button btnSubmit;
    private EditText inputName, inputUserName, inputPassword, inputConfirmpassword;

    private static SQLiteDatabase db;
    public static SQLiteDatabase getDatabase() {
        return db;
    }
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin);
        db = openOrCreateDatabase("Cookpad.db", MODE_PRIVATE, null);
        btnSubmit = findViewById(R.id.btnDangKy);
        tvBackLogin = findViewById(R.id.tvBackLogin);
        inputName = findViewById(R.id.inputTen);
        inputUserName = findViewById(R.id.inputUsername);
        inputPassword = findViewById(R.id.inputPassword1);
        inputConfirmpassword = findViewById(R.id.inputPassword2);
        tvBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SiginActivity.this, LoginActivity.class));
            }
        });
        btnSubmit.setOnClickListener(v -> {
            if (!inputPassword.getText().toString().equals(inputConfirmpassword.getText().toString())) {
                Toast.makeText(this, "Mật khẩu xác thực không giống nhau", Toast.LENGTH_SHORT).show();
            }
            else{
                ContentValues values = new ContentValues();
                values.put("name", inputName.getText().toString());
                values.put("username", inputUserName.getText().toString());
                values.put("password", inputPassword.getText().toString());

                String[] projection = { "username" };
                String selection = "username = ?";
                String[] selectionArgs = { inputUserName.getText().toString() };

                Cursor cursor = db.query(
                        "user",   // Tên bảng cần truy vấn
                        projection,  // Các cột cần lấy thông tin
                        selection,   // Điều kiện lọc dữ liệu
                        selectionArgs,  // Giá trị của điều kiện lọc
                        null,  // Group by
                        null,  // Having
                        null   // Order by
                );
                if (cursor.moveToFirst()) {
                    Toast.makeText(this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                } else {
                    long result = db.insert("user", null, values);
                    if (result == -1) {
                        Toast.makeText(this, "Thêm tài khoản không thành công khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Thêm tài khoản thành công", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        lottieAnimationView = findViewById(R.id.gif);
        lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);

    }
}