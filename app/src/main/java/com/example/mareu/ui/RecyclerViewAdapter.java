package com.example.mareu.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.model.Meeting;
import com.example.maru.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeeting;
    private final MeetingEvent mMeetingEvent;

    public RecyclerViewAdapter(List<Meeting> meetingList, MeetingEvent meetingEvent) {
        this.mMeeting = meetingList;
        this.mMeetingEvent = meetingEvent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_meeting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Meeting meeting = mMeeting.get(position);
        holder.hour.setText(meeting.getHour());
        holder.room.setText(meeting.getRoom());
        holder.email.setText(meeting.getEmail());
        holder.subject.setText(meeting.getSubject());
        holder.duration.setText(meeting.getDuration() + " minutes ");

        holder.deleteMeeting.setOnClickListener(view -> mMeetingEvent.delete(meeting));
    }

    @Override
    public int getItemCount() {
        return mMeeting.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView hour;
        public final TextView room;
        public final TextView email;
        public final TextView subject;
        public final TextView duration;
        public final ImageButton deleteMeeting;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hour = itemView.findViewById(R.id.hour);
            room = itemView.findViewById(R.id.room);
            email = itemView.findViewById(R.id.email);
            subject = itemView.findViewById(R.id.subject);
            duration = itemView.findViewById(R.id.duration);
            deleteMeeting = itemView.findViewById(R.id.delete_reunion);
        }
    }
}
