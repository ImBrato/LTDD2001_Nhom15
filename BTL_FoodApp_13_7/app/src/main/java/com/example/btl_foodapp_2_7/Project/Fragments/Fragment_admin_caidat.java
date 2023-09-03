package com.example.btl_foodapp_2_7.Project.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.btl_foodapp_2_7.Project.Activity.LoginActivity;
import com.example.btl_foodapp_2_7.Project.Model.DatabaseHelper;
import com.example.btl_foodapp_2_7.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_admin_caidat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_admin_caidat extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView txtUserName, txtLogout;

    public Fragment_admin_caidat() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_admin_caidat.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_admin_caidat newInstance(String param1, String param2) {
        Fragment_admin_caidat fragment = new Fragment_admin_caidat();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_caidat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences preferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        DatabaseHelper db2 = new DatabaseHelper(getActivity());

//        String username = preferences.getString("username", "");
        if (preferences.contains("username")) {
            String username = preferences.getString("username", "");
            txtLogout = view.findViewById(R.id.textView11);
            txtLogout.setText(username.toString());

        } else {
            Toast.makeText(getContext(), "Chưa đăng nhập", Toast.LENGTH_SHORT).show();
        }
        txtLogout = view.findViewById(R.id.txtSettingLogout);
        txtLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            FirebaseAuth.getInstance().signOut();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            Toast.makeText(getContext(), "Đăng xuất thành công", Toast.LENGTH_SHORT).show();

            startActivity(intent);
        });


    }
}