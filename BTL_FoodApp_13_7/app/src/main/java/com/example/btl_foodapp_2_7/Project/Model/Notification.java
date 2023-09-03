package com.example.btl_foodapp_2_7.Project.Model;

public class Notification {
    private String avt_noti2;
    private int avt_noti;
    private String text_noti;
    private String time_noti;

    public Notification(){

    }

    public Notification(String avt_noti, String text_noti, String time_noti) {
        this.avt_noti2 = avt_noti;
        this.text_noti = text_noti;
        this.time_noti = time_noti;
    }
    public Notification(int avt_noti, String text_noti, String time_noti) {
        this.avt_noti = avt_noti;
        this.text_noti = text_noti;
        this.time_noti = time_noti;
    }

    public int getAvt_noti() {
        return avt_noti;
    }

    public void setAvt_noti(int avt_noti) {
        this.avt_noti = avt_noti;
    }

    public String getText_noti() {
        return text_noti;
    }

    public void setText_noti(String text_noti) {
        this.text_noti = text_noti;
    }

    public String getTime_noti() {
        return time_noti;
    }

    public void setTime_noti(String time_noti) {
        this.time_noti = time_noti;
    }

    public String getAvt_noti2() {
        return avt_noti2;
    }

    public void setAvt_noti2(String avt_noti) {
        this.avt_noti2 = avt_noti;
    }
}