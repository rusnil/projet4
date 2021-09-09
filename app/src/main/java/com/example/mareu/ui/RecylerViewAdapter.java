package com.example.mareu.ui;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.di.DI;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.MareuApiService;
import com.example.maru.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.ViewHolder> {

    private  List<Reunion> mReunion;
    private MareuApiService mMareuApiService = DI.getMareuApiService();


    public RecylerViewAdapter(List<Reunion> reunionList) {
        this.mReunion = reunionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_reunion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

        Reunion reunion = mReunion.get(position);
        holder.date.setText(dateFormat.format(reunion.getDate()));
        holder.lieu.setText(reunion.getLieu());
        holder.email.setText(reunion.getEmail());
        holder.sujet.setText(reunion.getSujet());

        holder.deleteReu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMareuApiService.deleteReunion(reunion);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mReunion.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView date;
        public final TextView lieu;
        public final TextView email;
        public final TextView sujet;
        public final ImageButton deleteReu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            lieu = itemView.findViewById(R.id.lieu);
            email = itemView.findViewById(R.id.email);
            sujet = itemView.findViewById(R.id.sujet);
            deleteReu = (ImageButton) itemView.findViewById(R.id.delete_reunion);
        }
    }
}
