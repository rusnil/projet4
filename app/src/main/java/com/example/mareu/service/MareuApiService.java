package com.example.mareu.service;

import com.example.mareu.model.Reunion;
import com.example.mareu.model.Salle;

import java.util.Calendar;
import java.util.List;

public interface MareuApiService {

    List<Reunion> getReunionList();
    void addReunion(Reunion reunion);
    void deleteReunion(Reunion reunion);
    List<Salle> getSalleList();
    List<Reunion> getFiltreHeure();
    List<Reunion> getFiltreSalle(String salle);
    boolean checkReunionDispo(String lieu, Calendar dateDebut);
}
