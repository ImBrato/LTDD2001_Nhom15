package com.example.btl_foodapp_2_7.Project.Model;

public class BuaAn {
    private static int id;
    private String tenBuaAn;


    public BuaAn() {

    }
    {
        id++;
    }
    public BuaAn(String tenBuaAn) {
        this.setId(id);
        this.setTenBuaAn(tenBuaAn);
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

    public String getTenBuaAn() {
        return tenBuaAn;
    }

    public void setTenBuaAn(String tenBuaAn) {
        this.tenBuaAn = tenBuaAn;
    }
}
