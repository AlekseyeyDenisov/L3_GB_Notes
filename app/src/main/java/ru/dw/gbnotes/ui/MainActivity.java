package ru.dw.gbnotes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import ru.dw.gbnotes.App;
import ru.dw.gbnotes.R;
import ru.dw.gbnotes.data.Repository;
import ru.dw.gbnotes.domain.OnNoteListener;
import ru.dw.gbnotes.domain.model.NotesEntity;
import ru.dw.gbnotes.ui.adapter.NoteAdapter;


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

    private void initRecycler() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoteAdapter();
        adapter.setOnDeleteClickListener(this);
        getItemTouchHelper().attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
    }

    private void newNote() {
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, NoteActivity.class);
            startActivity(intent);
        });
    }


    @Override
    public void onDeleteNoteItem(NotesEntity notesEntity) {
        List<NotesEntity> repoData = repository.getNoteData();
        for (int i = 0; i < repoData.size(); i++) {
            if (repoData.get(i).equals(notesEntity)){
                repository.getNoteData().remove(repoData.get(i));
                adapter.deleteItem(i);
            }
        }
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

    private ItemTouchHelper getItemTouchHelper() {
        return new ItemTouchHelper((new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                List<NotesEntity> list = repository.getNoteData();
                repository.getNoteData().remove(list.get(viewHolder.getAdapterPosition()));
                adapter.deleteItem(viewHolder.getAdapterPosition());


            }
        }));
    }

}