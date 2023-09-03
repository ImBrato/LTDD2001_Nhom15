package com.example.btl_foodapp_2_7.Project.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_foodapp_2_7.Project.Model.DatabaseHelper;
import com.example.btl_foodapp_2_7.Project.Model.User;
import com.example.btl_foodapp_2_7.R;

public class Activity_Profile extends AppCompatActivity {

    EditText edt_name, edt_email, edt_password;
    Button btnSubmit;
    DatabaseHelper db = new DatabaseHelper(Activity_Profile.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        edt_name = findViewById(R.id.edt_tenNguoiDung);
        edt_email = findViewById(R.id.edit_email);
        edt_password = findViewById(R.id.edit_matKhau);
        btnSubmit = findViewById(R.id.btnSubmit);
        SharedPreferences preferences = Activity_Profile.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String username = preferences.getString("username", "");
        int id = db.getIduserByName(username);
        User user = db.getUserById(id);

        if (user != null) {
            String name = user.getName();
            String email = user.getEmail();
            String password = user.getPassword();
            edt_name.setText(name);
            edt_email.setText(email);
            edt_password.setText(password);
        }
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(Activity_Profile.this);
              String name = edt_name.getText().toString();
              String email = edt_email.getText().toString();
              String pass = edt_password.getText().toString();
                SharedPreferences preferences = Activity_Profile.this.getSharedPreferences("login", Context.MODE_PRIVATE);
                String username = preferences.getString("username", "");
                int id = db.getIduserByName(username);
                if( db.updateUserInfo(id, name, email, pass)){
                    Toast.makeText(Activity_Profile.this, "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Activity_Profile.this, "That bai", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


}