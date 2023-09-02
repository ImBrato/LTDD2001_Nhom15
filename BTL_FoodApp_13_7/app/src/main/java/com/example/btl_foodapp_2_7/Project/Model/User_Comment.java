package com.example.btl_foodapp_2_7.Project.Model;

public class User_Comment {
    private int id;
    private static  int idTest;
    private String noiDung;
    private int idUser;
    private int idFood;

    public User_Comment(){

    }
    {
        idTest++;
    }
    public User_Comment(String noiDung, int idUser, int idFood){
        this.setId(idTest);
        this.setNoiDung(noiDung);
        this.setIdUser(idUser);
        this.setIdFood(idFood);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }
}
