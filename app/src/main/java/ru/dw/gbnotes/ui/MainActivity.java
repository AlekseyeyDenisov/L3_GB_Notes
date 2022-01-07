package ru.dw.gbnotes.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.dw.gbnotes.App;
import ru.dw.gbnotes.R;
import ru.dw.gbnotes.data.Repository;
import ru.dw.gbnotes.domain.model.NotesEntity;


public class MainActivity extends AppCompatActivity implements OnNoteListener {
    Repository repository;
    NoteAdapter adapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repository = App.get(this).getRepository();
        initRecycler();
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoteAdapter();
        adapter.setData(repository.getNoteData());
        adapter.setOnDeleteClickListener(this);
        recyclerView.setAdapter(adapter);


    }


    @Override
    public void onDeleteNoteItem(NotesEntity notesEntity) {
        if (repository.deleteItemNotes(notesEntity))
            adapter.setData(repository.getNoteData());
    }

    @Override
    public void onUpDataNoteItem(NotesEntity notesEntity) {

    }
}