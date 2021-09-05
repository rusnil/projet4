package com.example.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.maru.R;
import com.example.maru.databinding.ActivityAddReunionBinding;
import com.example.maru.databinding.ActivityMainBinding;

public class AddReunionActivity extends AppCompatActivity {

    private ActivityAddReunionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddReunionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}