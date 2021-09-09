package com.example.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.mareu.di.DI;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.MareuApiService;
import com.example.maru.R;
import com.example.maru.databinding.ActivityAddReunionBinding;
import com.example.maru.databinding.ActivityMainBinding;

public class AddReunionActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityAddReunionBinding binding;
    private MareuApiService mMareuApiService = DI.getMareuApiService();

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
    }

    @Override
    public void onClick(View view) {
        if (view == binding.SaveButton) {
            onSave();
        }
    }

    private void onSave() {

        Reunion reunion = new Reunion(
        binding.lieu.getEditText().getText().toString(),
        binding.email.getEditText().getText().toString(),
        binding.sujet.getEditText().getText().toString()

        );
        mMareuApiService.addReunion(reunion);
        finish();
    }
}