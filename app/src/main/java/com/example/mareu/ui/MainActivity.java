package com.example.mareu.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.mareu.di.DI;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.MareuApiService;
import com.example.maru.R;
import com.example.maru.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ReunionEvent {

    private ActivityMainBinding binding;
    private List<Reunion> mReunionList;
    private MareuApiService mMareuApiService = DI.getMareuApiService();

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
        initList();
    }

    private void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
    }

    private void initList() {
        mReunionList = mMareuApiService.getReunionList();
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(mReunionList, this);
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filtreDate:
            case R.id.filtreLieu:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
            initList();
        });
        builder.setNegativeButton(R.string.cancel, (dialog, id) -> {
            // User cancelled the dialog
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}