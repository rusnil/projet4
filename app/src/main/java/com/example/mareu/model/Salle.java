package com.example.mareu.model;

public class Salle {

    private String nom;

    public Salle(String nom) {
        this.nom = nom;
    }

    private String getNom(){
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
