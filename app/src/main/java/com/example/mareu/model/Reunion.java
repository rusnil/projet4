package com.example.mareu.model;

import java.util.Calendar;

public class Reunion {

    String sujet, lieu, email;
    private Calendar debut;
    private Calendar fin;

    public Reunion(String lieu, String email, String sujet, Calendar debut, Calendar fin) {
        this.lieu = lieu;
        this.email = email;
        this.sujet = sujet;
        this.debut = debut;
        this.fin = fin;
    }

    public String getSujet() {
        return sujet;
    }

    public String getLieu() {
        return lieu;
    }

    public String getEmail() {
        return email;
    }

    public String getHeure() {
        String heure = debut.get(Calendar.HOUR_OF_DAY)+":"+debut.get(Calendar.MINUTE);
        return heure;
    }

    public Calendar getDebut() {
        return debut;
    }

    public Calendar getFin() {
        return fin;
    }
}