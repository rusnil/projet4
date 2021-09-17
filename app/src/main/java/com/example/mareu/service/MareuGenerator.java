package com.example.mareu.service;

import com.example.mareu.model.Salle;
import com.example.mareu.model.Reunion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MareuGenerator {

    public static List<Salle> SALLE_LIST = Arrays.asList(
            new Salle("Salle 1"),
            new Salle("Salle 2"),
            new Salle("Salle 3"),
            new Salle("Salle 4"),
            new Salle("Salle 5"),
            new Salle("Salle 6"),
            new Salle("Salle 7"),
            new Salle("Salle 8"),
            new Salle("Salle 9"),
            new Salle("Salle 10")
    );

    static List<Salle> generateSalle() {
        return new ArrayList<>(SALLE_LIST);
    }

    public static List<Reunion> REUNION_LIST = Arrays.asList();

    static List<Reunion> generateReunion() {
        return new ArrayList<>(REUNION_LIST);
    }
}
