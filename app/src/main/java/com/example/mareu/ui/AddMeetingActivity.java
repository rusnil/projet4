package com.example.mareu.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArraySet;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MareuApiService;
import com.example.maru.R;
import com.example.maru.databinding.ActivityAddReunionBinding;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Objects;
import java.util.Set;

public class AddMeetingActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityAddReunionBinding binding;
    private final MareuApiService mMareuApiService = DI.getMareuApiService();
    String[] listRoom;
    String[] durationMeeting;
    Calendar hourMeeting;
    String duration;
    Set<String> listEmail = new ArraySet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddReunionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setButton();
        initCalendar();
    }

    private void initCalendar() {
        hourMeeting = Calendar.getInstance();
    }

    private void setButton() {
        binding.SaveButton.setOnClickListener(this);
        binding.btnRoom.setOnClickListener(this);
        binding.btnHour.setOnClickListener(this);
        binding.btnDuration.setOnClickListener(this);
        binding.addEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.SaveButton) {
            onSave();
        }
        if (view == binding.btnRoom) {
            buttonRoom();
        }
        if (view == binding.btnHour) {
            buttonHour();
        }
        if (view == binding.btnDuration) {
            buttonDuration();
        }
        if (view.getId() == R.id.addEmail) {
            buttonAddEmail();
        }
    }

    private void buttonAddEmail() {
        String mail = Objects.requireNonNull(binding.txtEmail.getEditText()).getText().toString();
        if (Utilis.isEmailValid(mail)) {
            binding.txtEmail.setError("");
            Objects.requireNonNull(binding.txtEmail.getEditText()).setText("");
            if (!listEmail.contains(mail)) {
                addChip(mail);
                listEmail.add(mail);
            }
        } else {
            binding.txtEmail.setError(getString(R.string.invalid_email));
        }
    }

    private void addChip(String mail) {
        Chip chip = new Chip(this);
        chip.setCloseIconVisible(true);
        chip.setCheckable(false);
        chip.setOnCloseIconClickListener(v -> {
            binding.chipGroup.removeView(chip);
            listEmail.remove(chip.getText().toString());
        });
        chip.setText(mail);
        binding.chipGroup.addView(chip);
    }

    private void onSave() {
        boolean hasError;
        String hour = Objects.requireNonNull(binding.btnHour.getText()).toString();
        String duration = Objects.requireNonNull(binding.btnDuration.getText()).toString();
        String room = Objects.requireNonNull(binding.btnRoom.getText()).toString();
        String subject = Objects.requireNonNull(binding.txtSubject.getEditText()).getText().toString();

        hasError = checkError(binding.txtHour, hour, getString(R.string.pick_date));
        hasError = hasError || checkError(binding.txtDuration, duration, getString(R.string.pick_duration_meeting));
        hasError = hasError || checkError(binding.txtRoom, room, getString(R.string.pick_salle));
        hasError = hasError || checkError(binding.txtSubject, subject, getString(R.string.pick_sujet));

        if (!listEmail.isEmpty()) {
            binding.txtEmail.setError("");
        } else {
            binding.txtEmail.setError(getString(R.string.invalid_email));
            hasError = true;
        }
        if (!hasError) {
            Calendar endHour = (Calendar) hourMeeting.clone();
            endHour.add(Calendar.MINUTE, Integer.parseInt(this.duration));
            if (mMareuApiService.checkMeetingAvailable(room, hourMeeting, endHour)) {
                mMareuApiService.addMeeting(new Meeting(room, joinEmail(), subject, duration, hourMeeting, endHour));
                finish();
            } else {
                Toast.makeText(this, getString(R.string.check_reu_dispo), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String joinEmail() {
        StringBuilder txtEmail = new StringBuilder();
        for (String email : listEmail) {
            if (txtEmail.length() == 0) {
                txtEmail.append(email);
            } else {
                txtEmail.append(", ").append(email);
            }
        }
        return txtEmail.toString();
    }

    private boolean checkError(TextInputLayout editText, String texte, String message) {
        if (texte.isEmpty()) {
            editText.setError(message);
            return true;
        } else {
            editText.setError(null);
            return false;
        }
    }

    private void buttonHour() {
        int mHour, mMinute;
        mHour = hourMeeting.get(Calendar.HOUR_OF_DAY);
        mMinute = hourMeeting.get(Calendar.MINUTE);

        @SuppressLint("SetTextI18n") TimePickerDialog timePickerDialog = new TimePickerDialog(this, (timePicker, hour, min) -> {
            hourMeeting.set(Calendar.HOUR_OF_DAY, hour);
            hourMeeting.set(Calendar.MINUTE, min);

            binding.btnHour.setText(hour + getString(R.string.h) + min);
        }, mHour, mMinute, true);
        timePickerDialog.show();
    }

    private void buttonDuration() {
        durationMeeting = getResources().getStringArray(R.array.duration_meeting);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle(R.string.pick_duration_meeting);
        mBuilder.setSingleChoiceItems(durationMeeting, -1, (dialogInterface, which) -> {
            binding.btnDuration.setText(durationMeeting[which]);
            duration = durationMeeting[which];
            dialogInterface.dismiss();
        });
        AlertDialog alert = mBuilder.create();
        alert.show();
    }

    private void buttonRoom() {
        listRoom = getResources().getStringArray(R.array.room_item);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle(R.string.pick_salle);
        mBuilder.setSingleChoiceItems(listRoom, -1, (dialogInterface, which) -> {
            binding.btnRoom.setText(listRoom[which]);
            dialogInterface.dismiss();
        });
        AlertDialog alert = mBuilder.create();
        alert.show();
    }
}