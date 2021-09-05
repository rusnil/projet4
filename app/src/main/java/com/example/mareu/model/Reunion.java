package com.example.mareu.model;

public class Reunion {

    private String date;
    private String lieu;
    private String email;
    private String sujet;

    public Reunion(String date, String lieu, String email, String sujet) {
        this.date = date;
        this.lieu = lieu;
        this.email = email;
        this.sujet = sujet;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }
}
