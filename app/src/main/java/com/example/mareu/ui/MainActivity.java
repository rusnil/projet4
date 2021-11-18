package com.example.mareu.ui;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MareuApiService;
import com.example.maru.R;
import com.example.maru.databinding.ActivityMainBinding;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MeetingEvent {

    private final MareuApiService mMareuApiService = DI.getMareuApiService();
    Calendar hourMeeting;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setButton();
        initCalendar();
        initRecycler();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initList(mMareuApiService.getMeetingList());
    }

    private void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
    }

    private void initList(List<Meeting> meetings) {
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(meetings, this);
        binding.recyclerview.setAdapter(recyclerViewAdapter);
    }

    private void setButton() {
        binding.fabAdd.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.filtreHeure) {
            filterHour();
            return true;
        } else if (itemId == R.id.filtreSalle) {
            filterRoom();
            return true;
        } else if (itemId == R.id.filtreReset) {
            filterReset();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void filterReset() {
        initList(mMareuApiService.getMeetingList());
    }

    private void initCalendar() {
        hourMeeting = Calendar.getInstance();
    }

    public void filterHour() {
        int mHour, mMinute;
        mHour = hourMeeting.get(Calendar.HOUR_OF_DAY);
        mMinute = hourMeeting.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (timePicker, hour, min) -> {
            hourMeeting.set(Calendar.HOUR_OF_DAY, hour);
            hourMeeting.set(Calendar.MINUTE, min);

            initList(mMareuApiService.getFilterHour(hour));
        }, mHour, mMinute, true);
        timePickerDialog.show();
    }

    private void filterRoom() {
        String[] listRoom = getResources().getStringArray(R.array.room_item);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle(R.string.pick_salle);
        mBuilder.setSingleChoiceItems(listRoom, -1, (dialogInterface, which) -> {
            dialogInterface.dismiss();
            initList(mMareuApiService.getFilterRoom(listRoom[which]));
        });
        AlertDialog alert = mBuilder.create();
        alert.show();
    }

    public void onClick(View view) {
        startActivity(new Intent(this, AddMeetingActivity.class));
    }

    @Override
    public void delete(Meeting meeting) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);
        builder.setPositiveButton(R.string.ok, (dialog, id) -> {
            mMareuApiService.deleteMeeting(meeting);
            initList(mMareuApiService.getMeetingList());
        });
        builder.setNegativeButton(R.string.cancel, (dialog, id) -> {
            // User cancelled the dialog
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}