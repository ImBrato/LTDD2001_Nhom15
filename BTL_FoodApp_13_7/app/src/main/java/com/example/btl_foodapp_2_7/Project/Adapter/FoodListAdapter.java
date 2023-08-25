package com.example.btl_foodapp_2_7.Project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.btl_foodapp_2_7.Project.Model.Food;
import com.example.btl_foodapp_2_7.R;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.ViewHolder> {
    ArrayList<Food> items;
    Context context;

    public FoodListAdapter(ArrayList<Food> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_food_list,parent,false);
        context = parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTenMonAn());
        holder.nameTxt.setText(items.get(position).getTenTacGia());
        holder.timeTxt.setText(items.get(position).getTime() + " min");
        holder.scoreTxt.setText("" + items.get(position).getLuotDanhGia());
        holder.score1Txt.setText("" + items.get(position).getLuotTim());
        int currentScore = holder.getCurrentScore1();

        int drawableResourceID = holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).load(drawableResourceID).transform(new GranularRoundedCorners(0,0,0,0)).into(holder.pic);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleTxt,timeTxt,scoreTxt,score1Txt, nameTxt;
        CheckBox btnLike;
        ImageView pic;
        int currentScore1;

        public int getCurrentScore1() {
            return currentScore1;
        }
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTxt = itemView.findViewById(R.id.nameTxt);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            timeTxt = itemView.findViewById(R.id.timeTxt);
            scoreTxt = itemView.findViewById(R.id.luotDanhGia);
            score1Txt = itemView.findViewById(R.id.luotTim);
            pic = itemView.findViewById(R.id.pic);
            currentScore1 = Integer.parseInt(score1Txt.getText().toString());
            btnLike = itemView.findViewById(R.id.btn_like);
            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int clickedPosition = getAdapterPosition();

                    if (clickedPosition != RecyclerView.NO_POSITION) {
                        if (btnLike.isChecked()) {
                            currentScore1++;
                        } else {
                            currentScore1--;
                        }

                        score1Txt.setText(String.valueOf(currentScore1));
//                        boolean isChecked = btnLike.isChecked();
//                        String message = isChecked ? "Đã thích mục " : "Bỏ thích mục ";
//                        Toast.makeText(context, message + clickedPosition, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
