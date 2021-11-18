package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.Calendar;
import java.util.List;

public interface MareuApiService {

    List<Meeting> getMeetingList();

    void addMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

    List<Meeting> getFilterHour(int hour);

    List<Meeting> getFilterRoom(String room);

    boolean checkMeetingAvailable(String room, Calendar startHour, Calendar endHour);
}
