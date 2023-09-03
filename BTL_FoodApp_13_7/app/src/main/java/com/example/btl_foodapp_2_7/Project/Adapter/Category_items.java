package com.example.btl_foodapp_2_7.Project.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_foodapp_2_7.Project.Model.CardItems;
import com.example.btl_foodapp_2_7.R;

import java.util.List;

public class Category_items extends RecyclerView.Adapter<Category_items.ViewHolder> {

    private List<CardItems> cardItemsList;

    public Category_items(List<CardItems> cardItemsList) {
        this.cardItemsList = cardItemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_category_list_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardItems cardItems = cardItemsList.get(position);

        holder.tenMonTextView.setText(cardItems.getTenMon());
        holder.tenUserTextView.setText(cardItems.getTen_User());
        holder.moTaTextView.setText(cardItems.getMoTa());
        holder.luotDanhGiaTextView.setText(String.valueOf(cardItems.getLuotDanhGia()));
        holder.luotTimTextView.setText(String.valueOf(cardItems.getLuotTim()));


        // Thay thế bằng các hành động liên quan đến like/favorite
        // holder.likeCheckBox.setChecked(cardItems.isLiked());
        // holder.favoriteCheckBox.setChecked(cardItems.isFavorite());
    }

    @Override
    public int getItemCount() {
        return cardItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tenMonTextView;
        TextView tenUserTextView;
        TextView moTaTextView;
        TextView luotDanhGiaTextView;
        TextView luotTimTextView;
        CheckBox likeCheckBox;
        CheckBox favoriteCheckBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            tenMonTextView = itemView.findViewById(R.id.tenMon);
            tenUserTextView = itemView.findViewById(R.id.ten_User);
            moTaTextView = itemView.findViewById(R.id.moTa);
            luotDanhGiaTextView = itemView.findViewById(R.id.luotDanhGia);
            luotTimTextView = itemView.findViewById(R.id.luotTim);
            likeCheckBox = itemView.findViewById(R.id.btn_like);
            favoriteCheckBox = itemView.findViewById(R.id.favoriteBtn);
        }
    }
}
