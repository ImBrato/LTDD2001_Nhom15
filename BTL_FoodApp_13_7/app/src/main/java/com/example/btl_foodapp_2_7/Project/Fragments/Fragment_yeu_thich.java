package com.example.btl_foodapp_2_7.Project.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btl_foodapp_2_7.Project.Adapter.Notification_Items;
import com.example.btl_foodapp_2_7.Project.Model.DatabaseHelper;
import com.example.btl_foodapp_2_7.Project.Model.Notification;
import com.example.btl_foodapp_2_7.R;
import java.util.ArrayList;
import java.util.List;

public class Fragment_yeu_thich extends Fragment {

    private RecyclerView recyclerView;
    private Notification_Items adapter;
    private List<Notification> notificationList;

    public Fragment_yeu_thich() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yeu_thich, container, false);
        DatabaseHelper db = new DatabaseHelper(getContext());



        // Khởi tạo danh sách thông báo
        notificationList = new ArrayList<>();

        notificationList.add(new Notification(R.drawable.profile, "Minh Hoàng: Vừa đăng một công thức mới", "10 giờ"));
        notificationList.add(new Notification(R.drawable.profile, "Đức Hoàng: Vừa đăng một công thức mới", "8 giờ"));
        notificationList.add(new Notification(R.drawable.profile, "Mỹ: Vừa đăng một công thức mới", "9 giờ"));
        notificationList.add(new Notification(R.drawable.profile, "Mỹ: Vừa đăng một công thức mới", "9 giờ"));




        // Thêm các thông báo khác vào danh sách ở đây

        // Khởi tạo RecyclerView và Adapter
        recyclerView = view.findViewById(R.id.view_noti);
        adapter = new Notification_Items(notificationList, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}
