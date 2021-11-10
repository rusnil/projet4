package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DummyMareuApiService implements MareuApiService {

    private final List<Meeting> mMeetingList = MareuGenerator.generateReunion();

    @Override
    public List<Meeting> getMeetingList() {
        return mMeetingList;
    }

    @Override
    public void addMeeting(Meeting meeting) {
        if (checkMeetingAvailable(meeting.getRoom(), meeting.getStartHour(), meeting.getEndHour())) {
            mMeetingList.add(meeting);
        }
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetingList.remove(meeting);
    }

    @Override
    public List<Meeting> getFilterHour(int hour) {
        List<Meeting> filterHour = new ArrayList<>();
        for (Meeting meeting : mMeetingList) {
            if (meeting.getStartHour().get(Calendar.HOUR_OF_DAY) == hour) {
                filterHour.add(meeting);
            }
        }
        return filterHour;
    }

    @Override
    public List<Meeting> getFilterRoom(String room) {
        List<Meeting> filterRoom = new ArrayList<>();
        for (Meeting meeting : mMeetingList) {
            if (meeting.getRoom().equals(room)) {
                filterRoom.add(meeting);
            }
        }
        return filterRoom;
    }

    @Override
    public boolean checkMeetingAvailable(String room, Calendar startHour, Calendar endHour) {
        for (Meeting meeting : mMeetingList) {
            if (room.equals(meeting.getRoom())) {
               /* if (startHour.after(meeting.getStartHour()) && startHour.before(meeting.getEndHour())) {
                    return false;
                }
                if (endHour.after(meeting.getStartHour()) && endHour.before(meeting.getEndHour())) {
                    return false;
                }
                if (endHour.(meeting.getStartHour()) && endHour.before(meeting.getEndHour())) {
                    return false;
                } */
                if(startHour.getTime().getTime() <= meeting.getEndHour().getTime().getTime()
                        && meeting.getStartHour().getTime().getTime() <= endHour.getTime().getTime()) {
                    return false;
                }
            }
        }
        return true;
    }
}
