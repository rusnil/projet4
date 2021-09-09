package com.example.mareu.service;

import com.example.mareu.model.Reunion;
import com.example.mareu.model.Salle;

import java.util.ArrayList;
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
    public List<Reunion> getFiltreDate() {
        return null;
    }

    @Override
    public List<Reunion> getFiltreLieu() {
        return null;
    }
}
