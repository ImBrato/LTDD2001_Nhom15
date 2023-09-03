package com.example.btl_foodapp_2_7.Project.Model;

public class BuaAn {
    private static int idTest;
    private int id;
    private String tenBuaAn;
    String image;


    public BuaAn() {

    }
    {
        idTest++;
    }
    public BuaAn(String tenBuaAn, String image) {
        this.setId(idTest);
        this.setTenBuaAn(tenBuaAn);
        this.image = image;
    }

    // Getter và setter cho các thuộc tính

    @Override
    public String toString() {
        return getTenBuaAn();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTenBuaAn() {
        return tenBuaAn;
    }

    public void setTenBuaAn(String tenBuaAn) {
        this.tenBuaAn = tenBuaAn;
    }


}
