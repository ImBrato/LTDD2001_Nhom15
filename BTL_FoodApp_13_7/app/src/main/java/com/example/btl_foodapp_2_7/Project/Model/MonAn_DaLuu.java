package com.example.btl_foodapp_2_7.Project.Model;

public class MonAn_DaLuu {
    private static int id;
    private int idMonAn;
    private int idUser;

    {
        id++;
    }

    public MonAn_DaLuu(){

    }
    public MonAn_DaLuu(int idB, int idU){
        this.setIdMonAn(idB);
        this.setIdUser(idU);


    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        MonAn_DaLuu.id = id;
    }

    public int getIdMonAn() {
        return idMonAn;
    }

    public void setIdMonAn(int idMonAn) {
        this.idMonAn = idMonAn;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
