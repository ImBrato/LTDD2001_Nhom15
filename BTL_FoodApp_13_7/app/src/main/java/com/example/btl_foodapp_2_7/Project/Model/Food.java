package com.example.btl_foodapp_2_7.Project.Model;

import java.io.Serializable;

public class Food implements Serializable {

    private static int id;
    private String tenTacGia;
    private String tenMonAn;
    private String description;
    private String picUrl;

    private String time;
    private int energy;
    private int luotDanhGia;

    private int luotTim;

    private int userId;

    {
        id++;
    }

    public Food(String tenMonAn, String description, String picUrl, String time, int luotDanhGia, int luotTim, int userId) {
        this.id = id;
        this.picUrl = picUrl;
        this.time = time;
        this.tenMonAn = tenMonAn;
        this.setLuotDanhGia(luotDanhGia);
        this.luotTim = luotTim;
        this.description = description;
        this.setUserId(userId);



    }

    public int getLuotTim() {
        return luotTim;
    }

    public void setLuotTim(int score1) {
        this.luotTim = score1;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public String getTenMonAn() {
        return tenMonAn;
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

    public int getLuotDanhGia() {
        return luotDanhGia;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
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

    public void setLuotDanhGia(int luotDanhGia) {
        this.luotDanhGia = luotDanhGia;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
