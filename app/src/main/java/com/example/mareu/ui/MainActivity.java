package com.example.mareu.ui;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;

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
import com.example.mareu.model.Reunion;
import com.example.mareu.service.MareuApiService;
import com.example.maru.R;
import com.example.maru.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ReunionEvent {

    private ActivityMainBinding binding;
    private final MareuApiService mMareuApiService = DI.getMareuApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setButton();
        initRecycler();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initList(mMareuApiService.getReunionList());
    }

    private void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
    }

    private void initList(List<Reunion> reunions) {
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(reunions, this);
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
            filtreHeure();
            return true;
        } else if (itemId == R.id.filtreSalle) {
            filtreSalle();
            return true;
        } else if (itemId == R.id.filtreReset) {
            filtreReset();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void filtreReset() {
        initList(mMareuApiService.getReunionList());
    }

    public void filtreHeure() {
        TimePickerDialog.OnTimeSetListener timeSetListener = (view, hourOfDay, minute) -> {

        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, timeSetListener, HOUR_OF_DAY, MINUTE, true);
        timePickerDialog.show();
    }

    private void filtreSalle() {
        String[] listSalle = getResources().getStringArray(R.array.salle_item);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle(R.string.pick_salle);
        mBuilder.setSingleChoiceItems(listSalle, -1, (dialogInterface, which) -> {
            dialogInterface.dismiss();
            initList(mMareuApiService.getFiltreSalle(listSalle[which]));
        });
        AlertDialog alert = mBuilder.create();
        alert.show();

    }

    public void onClick(View view) {
            startActivity(new Intent(this, AddReunionActivity.class));
    }

    @Override
    public void delete(Reunion reunion) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this  );

        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);
        builder.setPositiveButton(R.string.ok, (dialog, id) -> {
            mMareuApiService.deleteReunion(reunion);
            initList(mMareuApiService.getReunionList());
        });
        builder.setNegativeButton(R.string.cancel, (dialog, id) -> {
            // User cancelled the dialog
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}