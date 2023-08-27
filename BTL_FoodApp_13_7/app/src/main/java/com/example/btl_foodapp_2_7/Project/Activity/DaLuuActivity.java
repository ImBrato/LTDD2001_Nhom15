package com.example.btl_foodapp_2_7.Project.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.btl_foodapp_2_7.Project.Adapter.FoodListAdapter;
import com.example.btl_foodapp_2_7.Project.Adapter.SlideAdapter;
import com.example.btl_foodapp_2_7.Project.Model.DatabaseHelper;
import com.example.btl_foodapp_2_7.R;

import java.util.ArrayList;
import java.util.List;

public class DaLuuActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterFoodList;
    private RecyclerView recyclerViewFood;
    ViewPager2 viewPager2;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_da_luu);
        Bundle args = new Bundle();

        viewPager2 = findViewById(R.id.viewPager);

        List<SlideItem> slideItems = new ArrayList<>();
        slideItems.add(new SlideItem(R.drawable.slide1));
        slideItems.add(new SlideItem(R.drawable.slide2));
        slideItems.add(new SlideItem(R.drawable.slide3));
        slideItems.add(new SlideItem(R.drawable.slide4));
        slideItems.add(new SlideItem(R.drawable.slide5));

        viewPager2.setAdapter(new SlideAdapter(slideItems, viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(5);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(30));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);


            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 2000);
            }
        });

        DatabaseHelper db2 = new DatabaseHelper(DaLuuActivity.this);
        SharedPreferences preferences = DaLuuActivity.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(DaLuuActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewFood = findViewById(R.id.view1);
        recyclerViewFood.setLayoutManager(linearLayoutManager);
        if (preferences.contains("username")) {
            String username = preferences.getString("username", "");
            int id = db2.getIduserByName(username);
            adapterFoodList= new FoodListAdapter(db2.getLikedFoodsByUserId(id));
            recyclerViewFood.setAdapter(adapterFoodList);
        } else {
            adapterFoodList= new FoodListAdapter(db2.getAllFoods());
            recyclerViewFood.setAdapter(adapterFoodList);
        }


    }


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };
}