package com.smorzhok.todolist;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Note> notes = new ArrayList<>();
    private OnNoteClickListener onNoteClickListener;

    public OnNoteClickListener getOnNoteClickListener() {
        return onNoteClickListener;
    }

    public List<Note> getNotes() {
        return new ArrayList<>(notes);
    }

    public void setOnNoteClickListener(OnNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }

    public void setNotes(List<Note> notes){
        this.notes = notes;
        Log.d("NotesAdapter", "Мы туть в setNotes");
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.note_item,
                parent,
                false);

        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotesViewHolder viewHolder, int position) {
        Note note = notes.get(position);
        viewHolder.textViewNote.setText(note.getText());
        switch (note.getPriority()){
            case 0:
                viewHolder.textViewNote.setBackgroundColor(ContextCompat.getColor(
                        viewHolder.itemView.getContext(),android.R.color.holo_green_light));
                break;
            case 1:
                viewHolder.textViewNote.setBackgroundColor(ContextCompat.getColor(
                        viewHolder.itemView.getContext(),android.R.color.holo_blue_light));
                break;
            default:
                viewHolder.textViewNote.setBackgroundColor(ContextCompat.getColor(
                        viewHolder.itemView.getContext(),android.R.color.holo_red_dark));
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                if (onNoteClickListener!=null)
                onNoteClickListener.onNoteClick(note);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNote;

        public NotesViewHolder(@NonNull View itemView){
            super(itemView);
            textViewNote = itemView.findViewById(R.id.textViewNote);
        }

    }
    interface OnNoteClickListener
    {
        void onNoteClick(Note note);
    }
}
