package com.example.btl_foodapp_2_7.Project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl_foodapp_2_7.Project.API.APIRequestHelper;
import com.example.btl_foodapp_2_7.Project.Fragments.Fragment_cai_dat;
import com.example.btl_foodapp_2_7.Project.Fragments.Fragment_dang_bai;
import com.example.btl_foodapp_2_7.Project.Fragments.Fragment_trang_chu;
import com.example.btl_foodapp_2_7.Project.Fragments.Fragment_yeu_thich;
import com.example.btl_foodapp_2_7.Project.Model.DatabaseHelper;
import com.example.btl_foodapp_2_7.Project.Model.Food;
import com.example.btl_foodapp_2_7.Project.Model.FoodDataSource;
import com.example.btl_foodapp_2_7.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MainActivity extends AppCompatActivity {



    //    code bottom
    private int selecTab = 1;

    private LinearLayout trangchuLayout;
    private LinearLayout dangbaiLayout;
    private LinearLayout caidatLayout1;
    private LinearLayout caidatLayout;

    private ImageView trangchuImg;
    private ImageView dangbaiImg;
    private ImageView caidatImg1;
    private ImageView caidatImg;

    private TextView trangchuTxt;
    private TextView dangbaiTxt;
    private TextView caidatTxt1;
    private TextView caidatTxt;

    protected APIRequestHelper apiRequestHelper;

    protected DatabaseHelper db;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences  prefs = getPreferences(this.MODE_PRIVATE);
        if(prefs.getBoolean("firstRun", true)) {
            DatabaseHelper db = new DatabaseHelper(MainActivity.this);
            db.addFood();
            db.addUser();
            db.addBuaAn();
            db.addComment();
            prefs.edit().putBoolean("firstRun", false).commit();
        }
        db = new DatabaseHelper(MainActivity.this);
        db.recreateDatabase();
        db.addFood();
        db.addUser();
        db.addBuaAn();
        db.addComment();
        db.saveComment("day la comment1", 1,1);

        url = "https://64f161580e1e60602d23bce1.mockapi.io/api/food";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Food food = new Food();
                                food.setId(jsonObject.getInt("id"));
                                food.setTenMonAn(jsonObject.getString("food_name"));
                                food.setDescription(jsonObject.getString("description"));
                                food.setNguyenLieu(jsonObject.getString("nguyen_lieu"));
                                food.setCachLam(jsonObject.getString("cach_lam"));
                                food.setPicUrl(jsonObject.getString("picUrl"));
                                food.setTime(jsonObject.getString("time"));
                                food.setIdBuaAn(jsonObject.getInt( "buaAnId"));

                                food.setUserId(jsonObject.getInt("user_id"));

                                // Lưu food vào CSDL
                                db.insertFood(food);
                                Log.i("food", String.valueOf(food));
//                                Toast.makeText(MainActivity.this, "dc r nhe ", Toast.LENGTH_SHORT).show();


                            }
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "Lỗi xử lý dữ liệu JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Lỗi khi fetch API", Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(this).add(request);

        //        fetchAndInsertFoodData();




//        code botom
        trangchuLayout = findViewById(R.id.trangchuLayout);
        dangbaiLayout = findViewById(R.id.dangbaiLayout);
        caidatLayout1 = findViewById(R.id.caidatLayout1);
        caidatLayout = findViewById(R.id.caidatLayout);

        trangchuImg = findViewById(R.id.trangchuLayoutImg);
        dangbaiImg = findViewById(R.id.dangbaiLayoutImg);
        caidatImg1 = findViewById(R.id.caidatLayoutImg1);
        caidatImg = findViewById(R.id.caidatLayoutImg);

        trangchuTxt = findViewById(R.id.trangchuLayoutTxt);
        dangbaiTxt = findViewById(R.id.dangbaiLayoutTxt);
        caidatTxt1 = findViewById(R.id.caidatLayoutTxt1);
        caidatTxt = findViewById(R.id.caidatLayoutTxt);


//        up cong thuc
        dangbaiLayout.findViewById(R.id.dangbaiLayout);
        dangbaiLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, Fragment_trang_chu.class, null).commit();
            }
        });


        getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, Fragment_trang_chu.class, null).commit();
//set fragments

        trangchuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecTab != 1) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, Fragment_trang_chu.class, null).commit();

                    dangbaiTxt.setVisibility(View.GONE);
                    caidatTxt1.setVisibility(View.GONE);
                    caidatTxt.setVisibility(View.GONE);

                    dangbaiImg.setImageResource(R.drawable.test2_selec);
                    caidatImg1.setImageResource(R.drawable.test3_selec);
                    caidatImg.setImageResource(R.drawable.test4_selec);

                    dangbaiLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    caidatLayout1.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    caidatLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    trangchuTxt.setVisibility(View.VISIBLE);
                    trangchuImg.setImageResource(R.drawable.test1_bold);
                    trangchuLayout.setBackgroundResource(R.drawable.bg_bottom_item);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    trangchuLayout.startAnimation(scaleAnimation);

                    selecTab = 1;

                }
            }
        });

        dangbaiLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (selecTab != 2) {

                    getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, Fragment_dang_bai.class, null).commit();

                    trangchuTxt.setVisibility(View.GONE);
                    caidatTxt1.setVisibility(View.GONE);
                    caidatTxt.setVisibility(View.GONE);

                    trangchuImg.setImageResource(R.drawable.test1_selec);
                    caidatImg1.setImageResource(R.drawable.test3_selec);
                    caidatImg.setImageResource(R.drawable.test4_selec);

                    trangchuLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    caidatLayout1.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    caidatLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));


                    dangbaiTxt.setVisibility(View.VISIBLE);
                    dangbaiImg.setImageResource(R.drawable.test2_bold);
                    dangbaiLayout.setBackgroundResource(R.drawable.bg_bottom_item);



                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    dangbaiLayout.startAnimation(scaleAnimation);

                    selecTab = 2;


                }
            }
        });

        caidatLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecTab != 3) {

                    getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, Fragment_yeu_thich.class, null).commit();

                    trangchuTxt.setVisibility(View.GONE);
                    dangbaiTxt.setVisibility(View.GONE);
                    caidatTxt.setVisibility(View.GONE);

                    trangchuImg.setImageResource(R.drawable.test1_selec);
                    dangbaiImg.setImageResource(R.drawable.test2_selec);
                    caidatImg.setImageResource(R.drawable.test4_selec);

                    trangchuLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    dangbaiLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    caidatLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    caidatTxt1.setVisibility(View.VISIBLE);
                    caidatImg1.setImageResource(R.drawable.test3_bold);
                    caidatLayout1.setBackgroundResource(R.drawable.bg_bottom_item);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    caidatLayout1.startAnimation(scaleAnimation);

                    selecTab = 3;
                }
            }
        });

        caidatLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecTab != 4) {

                    getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, Fragment_cai_dat.class, null).commit();

                    trangchuTxt.setVisibility(View.GONE);
                    dangbaiTxt.setVisibility(View.GONE);
                    caidatTxt1.setVisibility(View.GONE);

                    trangchuImg.setImageResource(R.drawable.test1_selec);
                    dangbaiImg.setImageResource(R.drawable.test2_selec);
                    caidatImg1.setImageResource(R.drawable.test3_selec);

                    trangchuLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    dangbaiLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    caidatLayout1.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    caidatTxt.setVisibility(View.VISIBLE);
                    caidatImg.setImageResource(R.drawable.test4_bold);
                    caidatLayout.setBackgroundResource(R.drawable.bg_bottom_item);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    caidatLayout.startAnimation(scaleAnimation);

                    selecTab = 4;
                }


            }
        });

//end




    }
    private void fetchAndInsertFoodData() {
    }


}