package com.example.btl_foodapp_2_7.Project.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.btl_foodapp_2_7.Project.Activity.DangBaiActivity;
import com.example.btl_foodapp_2_7.R;


public class Fragment_dang_bai extends Fragment {
    private LinearLayout btn_taoct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_bai, container, false);
        btn_taoct = view.findViewById(R.id.btn_taoct);
        btn_taoct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DangBaiActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }
}