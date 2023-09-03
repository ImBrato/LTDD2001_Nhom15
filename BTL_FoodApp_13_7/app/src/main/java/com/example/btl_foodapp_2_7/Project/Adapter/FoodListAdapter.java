package com.example.btl_foodapp_2_7.Project.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.btl_foodapp_2_7.Project.Activity.DaLuuActivity;
import com.example.btl_foodapp_2_7.Project.Activity.DetailActivity;
import com.example.btl_foodapp_2_7.Project.Activity.MainActivity;
import com.example.btl_foodapp_2_7.Project.Model.DatabaseHelper;
import com.example.btl_foodapp_2_7.Project.Model.Food;
import com.example.btl_foodapp_2_7.Project.Model.MonAn_DaLuu;
import com.example.btl_foodapp_2_7.R;

import java.util.ArrayList;
import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.ViewHolder> {
    List<Food> items;
    Context context;


    public FoodListAdapter(List<Food> items) {
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

    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.titleTxt.setText(items.get(position).getTenMonAn());
        holder.nameTxt.setText(items.get(position).getTenTacGia());
        holder.timeTxt.setText(items.get(position).getTime());
        holder.scoreTxt.setText("" + items.get(position).getLuotDanhGia());
        holder.score1Txt.setText("" + items.get(position).getLuotTim());
        int currentScore = holder.getCurrentScore1();
//        Food category = items.get(position);
        int idFood = items.get(position).getId();


        holder.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idFood = position+ 1;
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("idBuaAn", idFood);
                if (idFood >= 0) {
                    Toast.makeText(v.getContext(), String.valueOf(idFood), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(v.getContext(), "Invalid item position", Toast.LENGTH_SHORT).show();
                }
                v.getContext().startActivity(intent);
            }
        });

        String imageUrl = items.get(position).getPicUrl(); // Đường dẫn URL của hình ảnh

        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .override(500,500 ) // Chỉnh kích thước ảnh ở đây
                .into(holder.pic);

        DatabaseHelper db2 = new DatabaseHelper(context);
        SharedPreferences preferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        String username = preferences.getString("username", "");
        int id = db2.getIduserByName(username);
        int clickedPosition = position+1;
        boolean isSaved = db2.checkIfFoodIsSaved(id, clickedPosition);
        Log.i("boolean", String.valueOf(isSaved));
        holder.btnLike.setChecked(isSaved);
        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
                String username = preferences.getString("username", "");
                int id = db2.getIduserByName(username);
                boolean isChecked = holder.btnLike.isChecked();
                int clickedPosition = position+1;
                Food food= db2.getFoodById(clickedPosition);
                int luotTim = food.getLuotTim();

                if(isChecked){

                    int luotTim2 = luotTim+1;
                    food.setLuotTim(luotTim2);
                    db2.updateLuotTim(clickedPosition, luotTim2);
                    Toast.makeText(view.getContext(), String.valueOf(luotTim2), Toast.LENGTH_SHORT).show();
                    db2.saveFood(id, clickedPosition);
                }
                else {
                    db2.unsaveFood(id, clickedPosition);
                    int luotTim2 = luotTim-1;
                    food.setLuotTim(luotTim2);
                    db2.updateLuotTim(clickedPosition, luotTim2);

                    Toast.makeText(view.getContext(), String.valueOf(luotTim2), Toast.LENGTH_SHORT).show();
                }
            }
        });

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
            btnLike = itemView.findViewById(R.id.btn_like);

            currentScore1 = Integer.parseInt(score1Txt.getText().toString());


        }
    }
}

