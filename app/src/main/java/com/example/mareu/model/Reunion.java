package com.example.mareu.model;

import java.util.Date;

public class Reunion {

    String sujet, lieu, email;
    Date mDate;

    public Reunion(String lieu, String email, String sujet) {
        this.lieu = lieu;
        this.email = email;
        this.sujet = sujet;
        this.mDate = new Date();
    }

    public Reunion(String lieu, String email, String sujet, Date date) {
        this.lieu = lieu;
        this.email = email;
        this.sujet = sujet;
        mDate = date;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
