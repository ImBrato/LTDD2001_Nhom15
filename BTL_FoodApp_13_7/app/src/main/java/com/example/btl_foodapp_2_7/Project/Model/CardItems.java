package com.example.btl_foodapp_2_7.Project.Model;

public class CardItems {
    private String tenMon;
    private String ten_User;
    private String moTa;
    private int luotDanhGia;
    private int luotTim;
    private String image;

    public CardItems(String tenMon, String ten_User, String moTa, int luotDanhGia, int luotTim, String image) {
        this.tenMon = tenMon;
        this.ten_User = ten_User;
        this.moTa = moTa;
        this.luotDanhGia = luotDanhGia;
        this.luotDanhGia = luotDanhGia;
        this.luotTim = luotTim;
        this.image = image;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTen_User() {
        return ten_User;
    }

    public void setTen_User(String ten_User) {
        this.ten_User = ten_User;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getLuotDanhGia() {
        return luotDanhGia;
    }

    public void setLuotDanhGia(int luotDanhGia) {
        this.luotDanhGia = luotDanhGia;
    }

    public int getLuotTim() {
        return luotTim;
    }

    public void setLuotTim(int luotTim) {
        this.luotTim = luotTim;
    }


}
