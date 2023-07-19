package com.example.btl_foodapp_2_7.Project.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_foodapp_2_7.Project.Activity.LoginActivity;
import com.example.btl_foodapp_2_7.R;

import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_cai_dat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_cai_dat extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView txtUserName, txtLogout;

    public Fragment_cai_dat() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_cai_dat.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_cai_dat newInstance(String param1, String param2) {
        Fragment_cai_dat fragment = new Fragment_cai_dat();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_cai_dat, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences preferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
//        String username = preferences.getString("username", "");
        if (preferences.contains("username")) {
            String username = preferences.getString("username", "");
            txtLogout = view.findViewById(R.id.textView11);
            txtLogout.setText(username.toString());
            Toast.makeText(getContext(), "Login đã đăng nhập với tên đăng nhập: " + username, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Chưa đăng nhập", Toast.LENGTH_SHORT).show();
        }
        txtLogout = view.findViewById(R.id.txtSettingLogout);
        txtLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            Toast.makeText(getContext(), "Đăng xuất thành công", Toast.LENGTH_SHORT).show();

            startActivity(intent);
        });


//        EditText editText2 = view.findViewById(R.id.editText2);
    }
}