package com.example.btl_foodapp_2_7.Project.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_foodapp_2_7.Project.Adapter.FoodListAdapter;
import com.example.btl_foodapp_2_7.Project.Adapter.Notification_Items;
import com.example.btl_foodapp_2_7.Project.Model.DatabaseHelper;
import com.example.btl_foodapp_2_7.Project.Model.Notification;
import com.example.btl_foodapp_2_7.R;
import java.util.ArrayList;
import java.util.List;

public class Fragment_yeu_thich extends Fragment {

    private RecyclerView.Adapter adapterFoodList;
    private RecyclerView recyclerViewFood;
    private RecyclerView view_noti;
    private List<Notification> notificationList;

    public Fragment_yeu_thich() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yeu_thich, container, false);





        return view;
    }
    @SuppressLint("Range")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DatabaseHelper db = new DatabaseHelper(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewFood = view.findViewById(R.id.view_noti);
        recyclerViewFood.setLayoutManager(linearLayoutManager);
        adapterFoodList = new Notification_Items(db.getAllThongBao());
        recyclerViewFood.setAdapter(adapterFoodList);
    }
}
