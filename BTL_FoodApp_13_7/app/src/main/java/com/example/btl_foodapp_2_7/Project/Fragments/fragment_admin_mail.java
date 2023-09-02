package com.example.btl_foodapp_2_7.Project.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.btl_foodapp_2_7.Project.Model.DatabaseHelper;
import com.example.btl_foodapp_2_7.Project.Model.ThongBao;
import com.example.btl_foodapp_2_7.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_admin_mail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_admin_mail extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button btnThongBao;
    EditText edtNoiDungThongBao;

    public fragment_admin_mail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_admin_mail.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_admin_mail newInstance(String param1, String param2) {
        fragment_admin_mail fragment = new fragment_admin_mail();
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

        View view = inflater.inflate(R.layout.fragment_admin_mail, container, false);
        btnThongBao = view.findViewById(R.id.buttonThongBao); // Thay "buttonId" bằng ID thực tế của nút
        edtNoiDungThongBao = view.findViewById(R.id.edit_noiDungTB);
        DatabaseHelper db2 = new DatabaseHelper(getActivity());
        SharedPreferences preferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String username = preferences.getString("username", "");
        int id = db2.getIduserByName(username);

        // Gán sự kiện click cho nút Button
        btnThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThongBao thongbao = new ThongBao(edtNoiDungThongBao.getText().toString(), db2.getIduserByName(username));

                db2.saveThongBao(thongbao);
                Toast.makeText(getActivity(), "Nút đã được nhấn!" + edtNoiDungThongBao.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_admin_mail, container, false);
    }
}