package com.example.btl_foodapp_2_7.Project.Activity;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.example.btl_foodapp_2_7.Project.Adapter.CategoryListAdapter;
import com.example.btl_foodapp_2_7.Project.Adapter.FoodListAdapter;
import com.example.btl_foodapp_2_7.Project.Model.Category;
import com.example.btl_foodapp_2_7.Project.Model.ProductFoodShare;
import com.example.btl_foodapp_2_7.R;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterFoodList;
    private RecyclerView recyclerViewFood;
    RecyclerView homeHorizontalRec, homeVerticalRec;
    List<Category> homeHormodelList;
    CategoryListAdapter homeHorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ArrayList<ProductFoodShare> items = new ArrayList<>();
//        items.add(new ProductFoodShare("Huỳnh Minh Hoàng","Chân gà chiên giòn","Cách làm chân gà chiên sốt giòn", "share",75000,15,5,5,123));
//        items.add(new ProductFoodShare("Nguyễn Thanh Thuyền","Gà nướng muối ớt","Cách làm gà nướng muối ớt ngon hết nấc", "share",205000,35,5,5,55));
//        items.add(new ProductFoodShare("Nguyễn Toàn Mỹ","Lòng heo xào chua ngọt","Lòng heo xào chua ngọt", "share",30000,10,5,5,225));
//        items.add(new ProductFoodShare("Huỳnh Minh Hoàng","Chân gà chiên giòn","Cách làm chân gà chiên sốt giòn", "share",75000,15,5,5,123));
//        items.add(new ProductFoodShare("Nguyễn Thanh Thuyền","Gà nướng muối ớt","Cách làm gà nướng muối ớt ngon hết nấc", "share",205000,35,5,5,55));
//        items.add(new ProductFoodShare("Nguyễn Toàn Mỹ","Lòng heo xào chua ngọt","Lòng heo xào chua ngọt", "share",30000,10,5,5,225));
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewFood = findViewById(R.id.view6);
        recyclerViewFood.setLayoutManager(linearLayoutManager);


        adapterFoodList= new FoodListAdapter(items);
        recyclerViewFood.setAdapter(adapterFoodList);


    }




}