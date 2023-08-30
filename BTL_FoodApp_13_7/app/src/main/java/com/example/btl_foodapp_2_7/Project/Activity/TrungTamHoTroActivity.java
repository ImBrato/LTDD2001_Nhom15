package com.example.btl_foodapp_2_7.Project.Activity;

import static android.app.Notification.EXTRA_NOTIFICATION_ID;

import android.app.NotificationChannel;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.btl_foodapp_2_7.R;

public class TrungTamHoTroActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "my_channel";
    private static final int NOTIFICATION_ID = 1;
    private static final String ACTION_SNOOZE = "action";
    private Button btnTrungTamHoTro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trung_tam_ho_tro);
        Button btnTrungTamHoTro = findViewById(R.id.btnTrungTamHoTro);
        btnTrungTamHoTro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showNotification();
                Intent intent = new Intent(TrungTamHoTroActivity.this, PhanHoiActivity.class);
                startActivity(intent);
            }
        });
    }



}