package com.example.mareu.model;

import android.widget.Button;

import java.util.Date;

public class Reunion {

    String sujet, lieu, email, heure;

    public Reunion(String lieu, String email, String sujet, String heure) {
        this.lieu = lieu;
        this.email = email;
        this.sujet = sujet;
        this.heure = heure;
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

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }
}