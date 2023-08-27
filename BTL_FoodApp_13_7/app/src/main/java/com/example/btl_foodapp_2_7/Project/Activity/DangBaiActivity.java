package com.example.btl_foodapp_2_7.Project.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cloudinary.Cloudinary;
import com.cloudinary.android.MediaManager;
import com.cloudinary.utils.ObjectUtils;
import com.example.btl_foodapp_2_7.Project.Model.DatabaseHelper;
import com.example.btl_foodapp_2_7.Project.Model.Food;
import com.example.btl_foodapp_2_7.Project.Model.FoodDataSource;
import com.example.btl_foodapp_2_7.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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
        MediaManager.init(this);
        imgGallery = findViewById(R.id.imgGallery);
        ImageView btnGallery = findViewById(R.id.btnGallery);

        dangBai = findViewById(R.id.buttonDangBai);
        tenMonAn = findViewById(R.id.tenMonAn);
        thoiGianNau = findViewById(R.id.thoigianNau);
        moTa = findViewById(R.id.moTa);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        dangBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    // Tạo một đối tượng Food từ dữ liệu người dùng nhập
                    String currentTime = sdf.format(new Date());
                    Log.i("tag", currentTime);
                    Food newFood = new Food(getTextMess(tenMonAn), getTextMess(moTa), "", getTextMess(thoiGianNau), 1,1,currentTime, 1,1);


                    DatabaseHelper db = new DatabaseHelper(DangBaiActivity.this);
                    db.insertFood(newFood);
                    showToast("Đăng món ăn thành công");


            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLEYRY_REQ_CODE && resultCode == RESULT_OK) {
            // Lấy URI của ảnh từ Intent
            Uri selectedImageUri = data.getData();

            // Hiển thị ảnh lên ImageView
            imgGallery.setImageURI(selectedImageUri);

            // Lưu URI của ảnh để sử dụng sau này (nếu cần)
            // Do bạn muốn lưu đường dẫn ảnh lên Cloudinary, bạn cần sử dụng selectedImageUri.getPath() để lấy đường dẫn thực sự của ảnh
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImageUri, projection, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                String imagePath = cursor.getString(columnIndex);
                cursor.close();

                // Tiếp tục với việc tải ảnh lên Cloudinary và lưu đường dẫn
                String requestId = MediaManager.get().upload(imagePath).dispatch();
                Log.i("url", requestId);
            }

            // Tiếp tục với việc tải ảnh lên Cloudinary và lưu đường dẫn
        }
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == GALLEYRY_REQ_CODE && resultCode == RESULT_OK && data != null) {
//            Uri selectedImageUri = data.getData();
//            String imagePath = getRealPathFromUri(selectedImageUri);
//            Log.i("img path", imagePath);
//            // Lưu đường dẫn ảnh vào CSDL hoặc upload lên cloud ở đây
//        }
//    }




    private void showToast(String message) {
        Toast.makeText(DangBaiActivity.this, message, Toast.LENGTH_SHORT).show();
    }
    public String getTextMess(EditText name){
        return name.getText().toString();
    }
}