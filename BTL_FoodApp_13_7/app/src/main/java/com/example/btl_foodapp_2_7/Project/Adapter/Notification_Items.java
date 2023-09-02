package com.example.btl_foodapp_2_7.Project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_foodapp_2_7.Project.Model.Notification;
import com.example.btl_foodapp_2_7.R;

import java.util.List;

public class Notification_Items extends RecyclerView.Adapter<Notification_Items.ViewHolder> {
    private List<Notification> notifications;
    private Context context;

    public Notification_Items(List<Notification> notifications, Context context) {
        this.notifications = notifications;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notification notification = notifications.get(position);

        holder.avatarImageView.setImageResource(notification.getAvt_noti());
        holder.textView.setText(notification.getText_noti());
        holder.timeTextView.setText(notification.getTime_noti());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarImageView;
        TextView textView;
        TextView timeTextView;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.avt_noti);
            textView = itemView.findViewById(R.id.text_noti);
            timeTextView = itemView.findViewById(R.id.time_noti);
            cardView = itemView.findViewById(R.id.card_view_cmt);
        }
    }
}