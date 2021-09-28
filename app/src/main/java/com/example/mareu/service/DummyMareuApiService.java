package com.example.mareu.service;

import com.example.mareu.model.Reunion;
import com.example.mareu.model.Salle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DummyMareuApiService implements MareuApiService {

    private final List<Reunion> mReunionList = MareuGenerator.generateReunion();
    private final List<Salle> mSalleList = MareuGenerator.generateSalle();

    @Override
    public List<Reunion> getReunionList() {
        return mReunionList;
    }

    @Override
    public void addReunion(Reunion reunion) {
        mReunionList.add(reunion);
    }

    @Override
    public void deleteReunion(Reunion reunion) {
        mReunionList.remove(reunion);
    }

    @Override
    public List<Salle> getSalleList() {
        return mSalleList;
    }

    @Override
    public List<Reunion> getFiltreHeure() {
        return null;
    }

    @Override
    public List<Reunion> getFiltreSalle(String salle) {

        List<Reunion> filtreSalle = new ArrayList<>();
        for (Reunion reunion: mReunionList) {
            if (reunion.getLieu().equals(salle)) {
                filtreSalle.add(reunion);
            }
        }
        return filtreSalle;
    }

    @Override
    public boolean checkReunionDispo(String lieu, Calendar dateDebut) {
        for (Reunion reunion:mReunionList) {
            if (lieu.equals(reunion.getLieu())) {
                if (reunion.getDebut().before(dateDebut) && reunion.getFin().after(dateDebut)) {
                    return  false;
                }
            }
        }
        return true;
    }
}
