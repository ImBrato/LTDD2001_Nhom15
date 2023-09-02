package com.example.btl_foodapp_2_7.Project.Adapter;

import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_foodapp_2_7.Project.Activity.ItemsActivity;
import com.example.btl_foodapp_2_7.Project.Model.CardItems;
import com.example.btl_foodapp_2_7.Project.Model.Comments;
import com.example.btl_foodapp_2_7.Project.Model.User_Comment;
import com.example.btl_foodapp_2_7.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Comment_Itemts extends RecyclerView.Adapter<Comment_Itemts.ViewHolder> {
    private List<User_Comment> cardComments;

    public Comment_Itemts(List<User_Comment> cardComments) {
        this.cardComments = cardComments;
    }

    @NonNull
    @Override
    public Comment_Itemts.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_comments, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User_Comment cardComment = cardComments.get(position);

        holder.text_cmt.setText(cardComment.getNoiDung());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Comment_Itemts.class);
                intent.putExtra("itemName", cardComment.getId());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardComments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_cmt;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_cmt = itemView.findViewById(R.id.text_cmt);

        }
    }
}


