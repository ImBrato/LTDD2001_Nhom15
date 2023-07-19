package com.example.btl_foodapp_2_7.Project.Model;

import java.io.Serializable;

public class ProductFoodShare implements Serializable {
    private String nameTxt;
    private String title;
    private String description;
    private String picUrl;

    private String time;
    private int energy;
    private int score;

    private int score1Txt;
    private int numberinCart;

    public ProductFoodShare(String nameTxt,String title, String description, String picUrl, String time, int score, int score1Txt) {
        this.picUrl = picUrl;
        this.time = time;
        this.title = title;
        this.score = score;
        this.nameTxt = nameTxt;
        this.score1Txt = score1Txt;
        this.description = description;
        this.energy = energy;

    }

    public int getScore1Txt() {
        return score1Txt;
    }

    public void setScore1Txt(int score1) {
        this.score1Txt = score1;
    }

    public String getNameTxt() {
        return nameTxt;
    }

    public void setNameTxt(String nameTxt) {
        this.nameTxt = nameTxt;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPicUrl() {
        return picUrl;
    }


    public String getTime() {
        return time;
    }

    public int getEnergy() {
        return energy;
    }

    public int getScore() {
        return score;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }


    public void setTime(String time) {
        this.time = time;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setNumberinCart(int numberinCart) {
        this.numberinCart = numberinCart;
    }
}
