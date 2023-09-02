package com.example.btl_foodapp_2_7.Project.Model;

public class MonAn_DaLuu {
    private static int idTest;
    private int id;
    private int idMonAn;
    private int idUser;

    {
        idTest++;
    }

    public MonAn_DaLuu(){

    }
    public MonAn_DaLuu(int idB, int idU){
        this.setId(idTest);
        this.setIdMonAn(idB);
        this.setIdUser(idU);


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
