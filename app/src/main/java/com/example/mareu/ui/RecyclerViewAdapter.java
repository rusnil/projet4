package com.example.mareu.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.model.Reunion;
import com.example.maru.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final List<Reunion> mReunion;
    private final ReunionEvent mReunionEvent;

    public RecyclerViewAdapter(List<Reunion> reunionList, ReunionEvent reunionEvent) {
        this.mReunion = reunionList;
        this.mReunionEvent = reunionEvent;
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

        Reunion reunion = mReunion.get(position);
        holder.date.setText(reunion.getHeure());
        holder.lieu.setText(reunion.getLieu());
        holder.email.setText(reunion.getEmail());
        holder.sujet.setText(reunion.getSujet());

        holder.deleteReu.setOnClickListener(view -> mReunionEvent.delete(reunion));
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
