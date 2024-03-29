package com.example.mareu.model;

import java.util.Calendar;

public class Meeting {

    private final int duration;
    private final String subject;
    private final String room;
    private final String email;
    private final Calendar startHour;
    private final Calendar endHour;

    public Meeting(String room, String email, String subject, int duration, Calendar startHour, Calendar endHour) {
        this.room = room;
        this.email = email;
        this.subject = subject;
        this.startHour = startHour;
        this.endHour = endHour;
        this.duration = duration;
    }

    public String getSubject() {
        return subject;
    }

    public String getRoom() {
        return room;
    }

    public String getEmail() {
        return email;
    }

    public String getHour() {
        return startHour.get(Calendar.HOUR_OF_DAY) + "h" + startHour.get(Calendar.MINUTE);
    }

    public Calendar getStartHour() {
        return startHour;
    }

    public Calendar getEndHour() {
        return endHour;
    }

    public int getDuration() {
        return duration;
    }
}