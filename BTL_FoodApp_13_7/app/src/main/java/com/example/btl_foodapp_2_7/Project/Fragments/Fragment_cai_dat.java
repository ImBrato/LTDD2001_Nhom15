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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_foodapp_2_7.Project.Activity.Activity_Profile;
import com.example.btl_foodapp_2_7.Project.Activity.ChinhSachActivity;
import com.example.btl_foodapp_2_7.Project.Activity.DaLuuActivity;
import com.example.btl_foodapp_2_7.Project.Activity.DangBaiActivity;
import com.example.btl_foodapp_2_7.Project.Activity.LoginActivity;
import com.example.btl_foodapp_2_7.Project.Activity.MainActivity;
import com.example.btl_foodapp_2_7.Project.Activity.TrungTamHoTroActivity;
import com.example.btl_foodapp_2_7.Project.Activity.VeChungToiActivity;
import com.example.btl_foodapp_2_7.Project.Model.DatabaseHelper;
import com.example.btl_foodapp_2_7.R;
import com.google.firebase.auth.FirebaseAuth;

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
    LinearLayout profile_setting;

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

        View view = inflater.inflate(R.layout.fragment_cai_dat, container, false);
        TextView veChungToiButton = view.findViewById(R.id.VeChungToi);
        TextView trungTamHoTroButton = view.findViewById(R.id.TrungTamHoTro);
        TextView daLuuButton = view.findViewById(R.id.imageViewDaLuu);
        TextView chinhSachButton = view.findViewById(R.id.ChinhSachNguoiDung);

        profile_setting = view.findViewById(R.id.profile_setting);

        profile_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_Profile.class);
                startActivity(intent);

            }
        });


        daLuuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DaLuuActivity.class);
                startActivity(intent);
            }
        });
        veChungToiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VeChungToiActivity.class);
                startActivity(intent);
            }
        });

        trungTamHoTroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TrungTamHoTroActivity.class);
                startActivity(intent);
            }
        });
        chinhSachButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChinhSachActivity.class);
                startActivity(intent);
            }
        });
        return view;
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
            Toast.makeText(getContext(), "Login đã đăng nhập với tên đăng nhập: " + username, Toast.LENGTH_SHORT).show();
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