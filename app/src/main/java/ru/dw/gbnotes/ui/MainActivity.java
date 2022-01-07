package ru.dw.gbnotes.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArrayMap;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.dw.gbnotes.App;
import ru.dw.gbnotes.R;
import ru.dw.gbnotes.domain.Repository;
import ru.dw.gbnotes.domain.RepositoryData;
import ru.dw.gbnotes.domain.model.NotesEntity;


public class MainActivity extends AppCompatActivity implements RepositoryData {
    Repository repository;
    ArrayMap<String, NotesEntity> data = new ArrayMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repository = App.get(this).getRepository();
        data = getNoteData();
        initRecycler();


    }

    private void initRecycler() {
        NoteAdapter adapter = new NoteAdapter();


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setData(data);

        recyclerView.setAdapter(adapter);


    }


    @Override
    public ArrayMap<String, NotesEntity> getNoteData() {
        return repository.getNoteData();
    }

    @Override
    public void setItemNotes(String id, NotesEntity notesEntity) {
        repository.setItemNotes(id, notesEntity);
    }

    @Override
    public void deleteItemNotes(String id) {
        repository.deleteItemNotes(id);
    }

    @Override
    public void upDataItemNote(String id, NotesEntity notesEntity) {
        repository.upDataItemNote(id, notesEntity);

    }
}