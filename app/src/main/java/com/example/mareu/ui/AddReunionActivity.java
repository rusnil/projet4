package com.example.mareu.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;

import com.example.mareu.di.DI;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.MareuApiService;
import com.example.maru.R;
import com.example.maru.databinding.ActivityAddReunionBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class AddReunionActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityAddReunionBinding binding;
    private final MareuApiService mMareuApiService = DI.getMareuApiService();
    String[] listSalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddReunionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setButton();
    }

    private void setButton() {
        binding.SaveButton.setOnClickListener(this);
        binding.txtBtnSalle.setOnClickListener(this);
        binding.realbtnheure.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.SaveButton) {
            onSave();
        }
        if (view == binding.txtBtnSalle) {
            buttonListSalle();
        }
        if (view == binding.realbtnheure) {
            buttonHeure();
        }
    }

    private void onSave() {
        boolean hasError = false;
        String heure = binding.realbtnheure.getText().toString();
        String salle = binding.txtBtnSalle.getText().toString();
        String sujet = binding.sujet.getEditText().getText().toString();
        String mail = binding.email.getEditText().getText().toString();

        hasError = checkError(binding.btnHeure, heure, getString(R.string.pick_date));
        hasError = hasError || checkError(binding.txtBtnSalle2, salle, getString(R.string.pick_salle));
        hasError = hasError || checkError(binding.sujet, sujet, getString(R.string.pick_sujet));

        if (Utilis.isEmailValid(mail)) {
            binding.email.setError("");
        } else {
            binding.email.setError(getString(R.string.invalid_email));
            hasError = true;
        }
        if (!hasError){
            mMareuApiService.addReunion(new Reunion(salle, mail, sujet, heure));
            finish();
        }
    }

    private boolean checkError(TextInputLayout editText, String texte,String message) {
        if (texte.isEmpty()) {
            editText.setError(message);
            return true;
        } else {
            editText.setError(null);
            return false;
        }
    }

    private void buttonListSalle() {
        listSalle = getResources().getStringArray(R.array.salle_item);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle(R.string.pick_salle);
        mBuilder.setSingleChoiceItems(listSalle, -1, (dialogInterface, which) -> {
            binding.txtBtnSalle.setText(listSalle[which]);
            dialogInterface.dismiss();
        });
        AlertDialog alert = mBuilder.create();
        alert.show();
    }

    private void buttonHeure() {
        int mHour, mMminute;
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMminute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (timePicker, hour, min) -> binding.realbtnheure.setText(hour + ":" + min),mHour,mMminute,true);
        timePickerDialog.show();
    }
}