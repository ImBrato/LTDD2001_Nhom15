package com.example.btl_foodapp_2_7.Project.Model;

public class ThongBao {
    private static int id;
    private String noiDung;
    private int id_admin;

    public ThongBao(){

    }
    public ThongBao(String noiDung, int id_admin){
        this.setNoiDung(noiDung);
        this.setId_admin(id_admin);

    }


    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        ThongBao.id = id;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }
}
