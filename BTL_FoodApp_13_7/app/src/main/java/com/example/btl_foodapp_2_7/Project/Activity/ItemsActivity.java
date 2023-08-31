package com.example.btl_foodapp_2_7.Project.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_foodapp_2_7.Project.Adapter.Category_items;
import com.example.btl_foodapp_2_7.Project.Model.CardItems;
import com.example.btl_foodapp_2_7.R;

import java.util.ArrayList;
import java.util.List;

public class ItemsActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private Category_items adapter;
    private List<CardItems> cardItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        cardItemsList = generateDummyCardItems();

        adapter = new Category_items(cardItemsList);
        recyclerView = findViewById(R.id.list_items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<CardItems> generateDummyCardItems() {
        List<CardItems> dummyList = new ArrayList<>();

        dummyList.add(new CardItems("Bánh Mì Thịt", "Huỳnh Minh Hoàng", "Sơ chế nguyên liệu, Ướp thịt heo và chế biến nước sốt", 5, 50));
        dummyList.add(new CardItems("Bún Chả Hà Nội", "Đức Hoàng", "Ướp Thịt, Nướng thịt, Pha nước chấm ", 5, 20));


        return dummyList;
    }
}
