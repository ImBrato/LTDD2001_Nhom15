package com.example.btl_foodapp_2_7.Project.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.btl_foodapp_2_7.Project.Model.Food;
import com.example.btl_foodapp_2_7.Project.Model.FoodDataSource;
import com.example.btl_foodapp_2_7.R;

public class DangBaiActivity extends AppCompatActivity {

    ImageView btnBack;
    private final int GALLEYRY_REQ_CODE = 1000;
    ImageView imgGallery;
    EditText tenMonAn, thoiGianNau, moTa;
    Button dangBai;


    //    Button btnCamera;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_bai);

        imgGallery = findViewById(R.id.imgGallery);
        ImageView btnGallery = findViewById(R.id.btnGallery);

        dangBai = findViewById(R.id.buttonDangBai);
        tenMonAn = findViewById(R.id.tenMonAn);
        thoiGianNau = findViewById(R.id.thoigianNau);
        moTa = findViewById(R.id.moTa);

        dangBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//public Food(String tenMonAn, String description, String picUrl, String time, int luotDanhGia, int luotTim, int userId) {
                    // Tạo một đối tượng Food từ dữ liệu người dùng nhập
                    Food newFood = new Food(getTextMess(tenMonAn), getTextMess(moTa), "", getTextMess(thoiGianNau), 1,5,1);

                    // Khởi tạo một đối tượng FoodDataSource
                    FoodDataSource dataSource = new FoodDataSource(DangBaiActivity.this);
                    dataSource.open();
                    long insertedId = dataSource.insertFood(newFood);

                    dataSource.close();

                    // Kiểm tra xem việc thêm vào CSDL có thành công hay không
                    if (insertedId != -1) {
                       showToast(getTextMess(tenMonAn));
                    } else {
                    showToast("that bai");
                    }
            }
        });




        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALLEYRY_REQ_CODE);

            }
        });

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLEYRY_REQ_CODE) {
                imgGallery.setImageURI(data.getData());
            }
        }
    }
    private void showToast(String message) {
        Toast.makeText(DangBaiActivity.this, message, Toast.LENGTH_SHORT).show();
    }
    public String getTextMess(EditText name){
        return name.getText().toString();
    }
}