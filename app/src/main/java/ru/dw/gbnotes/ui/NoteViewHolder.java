package ru.dw.gbnotes.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.dw.gbnotes.R;
import ru.dw.gbnotes.domain.model.NotesEntity;

public  class NoteViewHolder extends RecyclerView.ViewHolder {
    private final TextView heading = itemView.findViewById(R.id.heading);
    private final TextView description = itemView.findViewById(R.id.description);
    private final TextView date = itemView.findViewById(R.id.date);
    private final ImageButton editEntry = itemView.findViewById(R.id.edit_entry);
    private final ImageButton deleteEntry = itemView.findViewById(R.id.delete_entry);

    private final OnNoteListener onNoteListener;



    public NoteViewHolder(
            LayoutInflater inflater,
            @NonNull ViewGroup parent,
            OnNoteListener onNoteListener) {
        super(inflater.inflate(R.layout.item_notes, parent, false));
        this.onNoteListener = onNoteListener;

    }

    public void bind(NotesEntity notes) {
        deleteEntry.setOnClickListener(v-> onNoteListener.onDeleteNoteItem(notes));
        editEntry.setOnClickListener(v-> onNoteListener.onUpDataNoteItem(notes) );

        heading.setText(notes.getHeading());
        description.setText(notes.getDescription());
        date.setText(notes.getDate());


    }
}
