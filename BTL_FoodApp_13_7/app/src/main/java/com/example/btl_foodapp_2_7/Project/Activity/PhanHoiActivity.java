package com.example.btl_foodapp_2_7.Project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.btl_foodapp_2_7.R;

public class PhanHoiActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "my_channel";
    private static final int NOTIFICATION_ID = 1;
    private static final String ACTION_SNOOZE = "action";
    private Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phan_hoi);
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification();
                Intent intent = new Intent(PhanHoiActivity.this, TrungTamHoTroActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager);
        }
        Intent intent = new Intent(this, PhanHoiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(bitmap)
                .setColor(200)
                .setContentTitle("Trung Tâm Hỗ Trợ")
                .setContentText("Chúng tôi đã nhận được yêu cầu hỗ trợ của bạn!")
                .setContentIntent(pIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Cảm ơn bạn đã đưa ra ý kiến giúp app chúng tôi hoàn thiện hơn !!!"));
        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(NotificationManager notificationManager) {
        CharSequence name = "My Channel";
        String description = "My Notification Channel";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        notificationManager.createNotificationChannel(channel);
    }
}