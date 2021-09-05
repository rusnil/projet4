package com.example.mareu.service;

import com.example.mareu.model.Reunion;
import com.example.mareu.model.Salle;

import java.util.List;

public interface MareuApiService {

    List<Reunion> getReunionList();
    void addReunion(Reunion reunion);
    void deleteReunion(Reunion reunion);
    List<Salle> getSalleList();
    List<Reunion> getFiltreDate();
    List<Reunion> getFiltreLieu();
}
