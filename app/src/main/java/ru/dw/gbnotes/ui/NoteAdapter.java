package ru.dw.gbnotes.ui;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.recyclerview.widget.RecyclerView;

import ru.dw.gbnotes.domain.model.NotesEntity;


public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    private ArrayMap<String, NotesEntity> data;


    public void setData(ArrayMap<String, NotesEntity> noteList) {
        data = noteList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return new NoteViewHolder(inflater,parent);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(data.valueAt(position));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
