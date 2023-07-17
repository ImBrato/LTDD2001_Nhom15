package com.example.btl_foodapp_2_7.Project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.btl_foodapp_2_7.Project.Model.ProductFoodShare;
import com.example.btl_foodapp_2_7.R;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.ViewHolder> {
    ArrayList<ProductFoodShare> items;
    Context context;

    public FoodListAdapter(ArrayList<ProductFoodShare> items) {
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
        holder.titleTxt.setText(items.get(position).getTitle());
        holder.nameTxt.setText(items.get(position).getNameTxt());
        holder.timeTxt.setText(items.get(position).getTime() + " min");
        holder.scoreTxt.setText("" + items.get(position).getScore());
        holder.score1Txt.setText("" + items.get(position).getScore1Txt());

        int drawableResourceID = holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).load(drawableResourceID).transform(new GranularRoundedCorners(0,0,0,0)).into(holder.pic);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleTxt,timeTxt,scoreTxt,score1Txt, nameTxt;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTxt = itemView.findViewById(R.id.nameTxt);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            timeTxt = itemView.findViewById(R.id.timeTxt);
            scoreTxt = itemView.findViewById(R.id.scoreTxt);
            score1Txt = itemView.findViewById(R.id.score1Txt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
