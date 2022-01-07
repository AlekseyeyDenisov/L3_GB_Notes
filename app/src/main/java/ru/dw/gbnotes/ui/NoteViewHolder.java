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
    private NotesEntity notesEntity = null;
    private final TextView heading = itemView.findViewById(R.id.heading);
    private final TextView description = itemView.findViewById(R.id.description);
    private final TextView date = itemView.findViewById(R.id.date);
    private final ImageButton editEntry = itemView.findViewById(R.id.edit_entry);
    private final ImageButton deleteEntry = itemView.findViewById(R.id.delete_entry);



    public NoteViewHolder(
            LayoutInflater inflater,
            @NonNull ViewGroup parent) {
        super(inflater.inflate(R.layout.item_notes, parent, false));

    }

    public void bind(NotesEntity notes) {
        notesEntity = notes;
        heading.setText(notes.getHeading());
        description.setText(notes.getDescription());
        date.setText(notes.getDate());


    }
}
