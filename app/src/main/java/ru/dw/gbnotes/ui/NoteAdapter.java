package ru.dw.gbnotes.ui;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


import ru.dw.gbnotes.domain.model.NotesEntity;


public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    private ArrayList<NotesEntity> data  = new ArrayList<>();
    private OnNoteListener onNoteListener;

    public void setOnDeleteClickListener(OnNoteListener onNoteListener) {
        this.onNoteListener = onNoteListener;
    }


    public void setData(ArrayList<NotesEntity> noteList) {
        data = noteList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new NoteViewHolder(inflater,parent,onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(data.get(position));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
