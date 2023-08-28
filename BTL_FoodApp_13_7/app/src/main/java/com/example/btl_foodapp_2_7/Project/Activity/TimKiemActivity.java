package com.example.btl_foodapp_2_7.Project.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_foodapp_2_7.Project.Adapter.FoodListAdapter;
import com.example.btl_foodapp_2_7.Project.Adapter.SlideAdapter;
import com.example.btl_foodapp_2_7.Project.Model.DatabaseHelper;
import com.example.btl_foodapp_2_7.Project.Model.Food;
import com.example.btl_foodapp_2_7.R;

import java.util.ArrayList;
import java.util.List;

public class TimKiemActivity extends AppCompatActivity {

    ArrayList<Food> searchResults = new ArrayList<>();
    private RecyclerView recyclerViewFood;
    private RecyclerView.Adapter adapterFoodList;
    ViewPager2 viewPager2;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        viewPager2 = findViewById(R.id.viewPager);
        Intent intent = getIntent();
        ArrayList<Food> receivedSearchResults = (ArrayList<Food>) intent.getSerializableExtra("searchResults");
        if (receivedSearchResults != null) {
            searchResults.clear();
            searchResults.addAll(receivedSearchResults);
        }
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


        recyclerViewFood = findViewById(R.id.view1);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(TimKiemActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewFood.setLayoutManager(linearLayoutManager);

        adapterFoodList = new FoodListAdapter(searchResults);
        recyclerViewFood.setAdapter(adapterFoodList);


        EditText editTextSearch = findViewById(R.id.editTextSearch);
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                if(editTextSearch.getText().toString().isEmpty()){
                    Toast.makeText(TimKiemActivity.this, "Nhập tên món ăn", Toast.LENGTH_SHORT).show();
                    return false;
                }
                performSearch(editTextSearch.getText().toString());
                recreate();
                return true;
            }

        });


    }
    private void performSearch(String searchText) {
        searchResults.clear(); // Xóa danh sách kết quả tìm kiếm trước đó
        DatabaseHelper db2 = new DatabaseHelper(TimKiemActivity.this);
        List<Food> yourFoodList = db2.getAllFoods();
        for (Food food : yourFoodList) {
            if (food.getTenMonAn().toLowerCase().contains(searchText.toLowerCase())) {
                searchResults.add(food);
            }
        }
        if(searchResults.isEmpty()){
            Toast.makeText(TimKiemActivity.this, "Không tìm thấy món ăn thêo yêu cầu", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(TimKiemActivity.this, TimKiemActivity.class);
            intent.putExtra("searchResults", new ArrayList<>(searchResults)); // Gửi danh sách như một ArrayList
            startActivity(intent);
        }
    }


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };


}