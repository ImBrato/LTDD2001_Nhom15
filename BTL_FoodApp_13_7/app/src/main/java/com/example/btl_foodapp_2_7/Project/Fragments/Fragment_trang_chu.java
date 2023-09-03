package com.example.btl_foodapp_2_7.Project.Fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.btl_foodapp_2_7.Project.Activity.DetailActivity;
import com.example.btl_foodapp_2_7.Project.Activity.ItemsActivity;
import com.example.btl_foodapp_2_7.Project.Activity.SlideItem;
import com.example.btl_foodapp_2_7.Project.Activity.TimKiemActivity;
import com.example.btl_foodapp_2_7.Project.Adapter.CategoryListAdapter;
import com.example.btl_foodapp_2_7.Project.Adapter.FoodListAdapter;
import com.example.btl_foodapp_2_7.Project.Adapter.SlideAdapter;
import com.example.btl_foodapp_2_7.Project.Model.Category;
import com.example.btl_foodapp_2_7.Project.Model.DatabaseHelper;
import com.example.btl_foodapp_2_7.Project.Model.Food;
import com.example.btl_foodapp_2_7.R;

import java.util.ArrayList;
import java.util.List;


public class Fragment_trang_chu extends Fragment {
    private RecyclerView.Adapter adapterFoodList;
    private RecyclerView recyclerViewFood;
    private RecyclerView recyclerViewFood2;
    private RecyclerView recyclerViewFood3;
    ViewPager2 viewPager2;

    private RecyclerView homeHorizontalRec,view1,view2, view3;
    private RecyclerView.Adapter homeHormodelList;

    //    MyDAO mydao;
    CheckBox checkFav;

    private Handler handler = new Handler();
    List<Food> searchResults = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);

        homeHorizontalRec = view.findViewById(R.id.home_hor_rec);
        view1 = view.findViewById(R.id.view1);
        homeHorizontalRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ItemsActivity.class);
                startActivity(intent);

            }
        });

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);

            }
        });

        //Hiển thị danh sách danh mục món ăn



        viewPager2 = view.findViewById(R.id.viewPager);

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

        EditText editTextSearch = view.findViewById(R.id.editTextSearch);
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                if(editTextSearch.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Nhập tên món ăn", Toast.LENGTH_SHORT).show();
                    return false;
                }
                performSearch(editTextSearch.getText().toString());
                return true;
            }

        });

        return view;


    }


    //foodshare
    @SuppressLint("Range")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseHelper db2 = new DatabaseHelper(getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerViewFood = view.findViewById(R.id.view1);
//        recyclerViewFood2 = view.findViewById(R.id.view2);
//        recyclerViewFood3 = view.findViewById(R.id.view3);

        recyclerViewFood.setLayoutManager(linearLayoutManager);
//        recyclerViewFood2.setLayoutManager(linearLayoutManager);
//        recyclerViewFood3.setLayoutManager(linearLayoutManager);

        adapterFoodList = new FoodListAdapter(db2.getAllFoods());
        recyclerViewFood.setAdapter(adapterFoodList);
//        recyclerViewFood2.setAdapter(adapterFoodList);
//        recyclerViewFood3.setAdapter(adapterFoodList);



        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        homeHorizontalRec = view.findViewById(R.id.home_hor_rec);
        homeHorizontalRec.setLayoutManager(linearLayoutManager2);
        homeHormodelList = new CategoryListAdapter(db2.getAllBuaAn());

        homeHorizontalRec.setAdapter(homeHormodelList);

    }


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }
    private void performSearch(String searchText) {
        searchResults.clear(); // Xóa danh sách kết quả tìm kiếm trước đó
        DatabaseHelper db2 = new DatabaseHelper(getActivity());
        List<Food> yourFoodList = db2.getAllFoods();
        for (Food food : yourFoodList) {
            if (food.getTenMonAn().toLowerCase().contains(searchText.toLowerCase())) {
                searchResults.add(food);
            }
        }
        if(searchResults.isEmpty()){
            Toast.makeText(getContext(), "Không tìm thấy món ăn thêo yêu cầu", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(getContext(), TimKiemActivity.class);
            intent.putExtra("searchResults", new ArrayList<>(searchResults)); // Gửi danh sách như một ArrayList
            startActivity(intent);
        }

    }
}