package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public abstract class MareuGenerator {

    static Calendar startHour = Calendar.getInstance();
    static Calendar endHour = (Calendar) startHour.clone();

    public static List<Meeting> sMeetingList = Arrays.asList(
            new Meeting("Salle 1", "JustinA@gmail.fr, JustinB@gmail.fr, JustinC@gmail.fr", "test generator", 30, startHour, endHour),
            new Meeting("Salle 2", "JustinA@gmail.fr, JustinB@gmail.fr, JustinC@gmail.fr, JustinD@gmail.fr", "test generator", 30, startHour, endHour)
    );

    static List<Meeting> generateReunion() {
        return new ArrayList<>(sMeetingList);
    }
}
