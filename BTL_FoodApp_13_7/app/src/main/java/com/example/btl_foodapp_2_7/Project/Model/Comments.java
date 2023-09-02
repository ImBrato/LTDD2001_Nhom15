package com.example.btl_foodapp_2_7.Project.Model;

import android.net.Uri;
import android.widget.ImageView;

public class Comments {

    String text_cmt;

    public Comments( String text_cmt) {

        this.text_cmt = text_cmt;
    }



    public String getText_cmt() {
        return text_cmt;
    }

    public void setText_cmt(String text_cmt) {
        this.text_cmt = text_cmt;
    }
}


