package com.example.maru;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MareuApiService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;
import java.util.List;

/**
 * Unit test on Mareu service
 */
@RunWith(JUnit4.class)
public class MareuApiServiceUnitTest {

    private MareuApiService mMareuApiService;
    Calendar startHour = Calendar.getInstance();
    Calendar endHour = (Calendar) startHour.clone();
    Meeting mMeeting = new Meeting("salle 1", "test@test.test", "testUnit","30", startHour, endHour);

    @Before
    public void setup() {
        mMareuApiService = DI.getNewsInstanceApiService();
    }

    @Test
    public void addMeetingWithSuccess() {
        mMareuApiService.addMeeting(mMeeting);
        assertTrue(mMareuApiService.getMeetingList().contains(mMeeting));
    }

    @Test
    public void addMeetingFailsIfRoomIsBusy() {
        mMareuApiService.addMeeting(mMeeting);
        assertEquals(3, mMareuApiService.getMeetingList().size());

        mMareuApiService.addMeeting(mMeeting);
        assertEquals(3, mMareuApiService.getMeetingList().size());
    }

    @Test
    public void checkMeetingAvailable() {
        Calendar startHourAvailable = Calendar.getInstance();
        Calendar endHourAvailable = (Calendar) startHourAvailable.clone();
        endHourAvailable.add(Calendar.HOUR_OF_DAY, 1);
        Meeting mMeeting1 = new Meeting("salle 1", "test@test.test", "testUnit","30", startHourAvailable, endHourAvailable);
        mMareuApiService.addMeeting(mMeeting1);

        Calendar startHourAvailable1 = Calendar.getInstance();
        Calendar endHourAvailable1 = (Calendar) startHourAvailable.clone();
        endHourAvailable1.add(Calendar.HOUR_OF_DAY, 1);
        boolean available = mMareuApiService.checkMeetingAvailable("salle 1", startHourAvailable1, endHourAvailable1);
        assertFalse(available);
        available = mMareuApiService.checkMeetingAvailable("salle 2", startHourAvailable1, endHourAvailable1);
        assertTrue(available);
    }

    @Test
    public void deleteMeetingWithSuccess() {
        mMareuApiService.addMeeting(mMeeting);
        mMareuApiService.deleteMeeting(mMeeting);
        assertFalse(mMareuApiService.getMeetingList().contains(mMeeting));
    }

    @Test
    public void filterHourWithSuccess() {
        int hour = startHour.get(Calendar.HOUR_OF_DAY);
        mMareuApiService.addMeeting(mMeeting);
        List<Meeting> compare = mMareuApiService.getFilterHour(hour);
        assertTrue(compare.contains(mMeeting));
    }

    @Test
    public void filterRoomWithSuccess() {
        String room = String.valueOf(R.array.room_item);
        Meeting meeting = new Meeting(room, "test@test.test", "testUnit","30", startHour, endHour);
        mMareuApiService.addMeeting(meeting);
        List<Meeting> compare = mMareuApiService.getFilterRoom(room);
        assertTrue(compare.contains(meeting));
    }
}
