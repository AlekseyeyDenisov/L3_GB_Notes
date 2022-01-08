package ru.dw.gbnotes.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ru.dw.gbnotes.App;
import ru.dw.gbnotes.R;
import ru.dw.gbnotes.data.Repository;
import ru.dw.gbnotes.domain.model.NotesEntity;


public class MainActivity extends AppCompatActivity implements OnNoteListener {
    Repository repository;
    NoteAdapter adapter;
    RecyclerView recyclerView;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.fab);
        repository = App.get(this).getRepository();
        initRecycler();
        newNote();
    }

    private void newNote() {
        fab.setOnClickListener(v->{
            Intent intent = new Intent(this, NoteActivity.class);
            startActivity(intent);
        });
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
        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra(NoteActivity.NOTE_EXTRA_KEY, notesEntity);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setData(repository.getNoteData());
    }
}