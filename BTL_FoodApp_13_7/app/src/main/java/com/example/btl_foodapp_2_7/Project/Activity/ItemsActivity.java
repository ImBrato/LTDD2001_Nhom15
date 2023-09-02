package com.example.btl_foodapp_2_7.Project.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_foodapp_2_7.Project.Adapter.CategoryListAdapter;
import com.example.btl_foodapp_2_7.Project.Adapter.Category_items;
import com.example.btl_foodapp_2_7.Project.Adapter.FoodListAdapter;
import com.example.btl_foodapp_2_7.Project.Model.CardItems;
import com.example.btl_foodapp_2_7.Project.Model.DatabaseHelper;
import com.example.btl_foodapp_2_7.R;

import java.util.ArrayList;
import java.util.List;

public class ItemsActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterFoodList;
    private RecyclerView recyclerViewFood;
    private TextView tieuDe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        @SuppressLint("Range")
        DatabaseHelper db2 = new DatabaseHelper(ItemsActivity.this);
        int itemId = getIntent().getIntExtra("buaAnId", 0);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ItemsActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewFood = findViewById(R.id.list_items);
        recyclerViewFood.setLayoutManager(linearLayoutManager);
        adapterFoodList = new FoodListAdapter(db2.getFoodsByBuaAnId(itemId));

        recyclerViewFood.setAdapter(adapterFoodList);
        tieuDe = findViewById(R.id.tieuDe);



    }



}
