package com.example.btl_foodapp_2_7.Project.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_foodapp_2_7.Project.Fragments.Flagments_admin_reply;
import com.example.btl_foodapp_2_7.Project.Fragments.Fragment_admin_caidat;
import com.example.btl_foodapp_2_7.Project.Fragments.fragment_admin_mail;
import com.example.btl_foodapp_2_7.R;

public class AdminActivity extends AppCompatActivity {
    private int selecTab = 1;

    private LinearLayout mailLayout;
    private LinearLayout caiDatLayout;

    private LinearLayout repLyLayout;


    private ImageView mailImg;
    private ImageView caiDatImg;

    private ImageView repLyImg;

    private TextView mailTxt;
    private TextView caiDatTxt;

    private TextView repLyTxt;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


//        code botom
        mailLayout = findViewById(R.id.mailLayout);
        caiDatLayout = findViewById(R.id.caiDatLayout);
        repLyLayout = findViewById(R.id.repLyLayout);


        mailImg = findViewById(R.id.mailLayoutImg);
        caiDatImg = findViewById(R.id.caiDatLayoutImg);
        repLyImg = findViewById(R.id.repLyLayoutImg);


        mailTxt = findViewById(R.id.mailLayoutTxt);
        caiDatTxt = findViewById(R.id.caiDatLayoutTxt);
        repLyTxt = findViewById(R.id.repLyLayoutTxt);




        getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, fragment_admin_mail.class, null).commit();
//set fragments

        mailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecTab != 1) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, fragment_admin_mail.class, null).commit();

                    caiDatTxt.setVisibility(View.GONE);
                    repLyTxt.setVisibility(View.GONE);

                    caiDatImg.setImageResource(R.drawable.test4);
                    repLyImg.setImageResource(R.drawable.ic_admin_reply);

                    caiDatLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    repLyLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));


                    mailTxt.setVisibility(View.VISIBLE);
                    mailImg.setImageResource(R.drawable.ic_admin_thongbao2);
                    mailLayout.setBackgroundResource(R.drawable.bg_bottom_item);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    mailLayout.startAnimation(scaleAnimation);

                    selecTab = 1;

                }
            }
        });

        repLyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecTab != 2) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, Flagments_admin_reply.class, null).commit();

                    mailTxt.setVisibility(View.GONE);
                    caiDatTxt.setVisibility(View.GONE);

                    mailImg.setImageResource(R.drawable.ic_admin_thongbao);
                    caiDatImg.setImageResource(R.drawable.test4);

                    mailLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    caiDatLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));


                    repLyTxt.setVisibility(View.VISIBLE);
                    repLyImg.setImageResource(R.drawable.ic_admin_reply2);
                    repLyLayout.setBackgroundResource(R.drawable.bg_bottom_item);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    repLyLayout.startAnimation(scaleAnimation);

                    selecTab = 2;

                }
            }
        });

        caiDatLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (selecTab != 3) {

                    getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, Fragment_admin_caidat.class, null).commit();

                    mailTxt.setVisibility(View.GONE);
                    repLyTxt.setVisibility(View.GONE);

                    mailImg.setImageResource(R.drawable.ic_admin_thongbao);
                    repLyImg.setImageResource(R.drawable.ic_admin_reply);

                    mailLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    repLyLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));



                    caiDatTxt.setVisibility(View.VISIBLE);
                    caiDatImg.setImageResource(R.drawable.test4_bold);
                    caiDatLayout.setBackgroundResource(R.drawable.bg_bottom_item);



                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    caiDatLayout.startAnimation(scaleAnimation);

                    selecTab = 3;


                }
            }
        });

//end



    }

}
