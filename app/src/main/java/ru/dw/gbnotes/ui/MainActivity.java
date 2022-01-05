package ru.dw.gbnotes.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArrayMap;

import ru.dw.gbnotes.R;
import ru.dw.gbnotes.domain.Repository;
import ru.dw.gbnotes.domain.RepositoryData;
import ru.dw.gbnotes.domain.model.NotesEntity;


public class MainActivity extends AppCompatActivity implements RepositoryData {
    Repository repository = new Repository();
    ArrayMap<String, NotesEntity> data = getNoteData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deleteItemNotes("2");
        setItemNotes("25",new NotesEntity("25","ttt","","2131"));
        upDataItemNote("3",new NotesEntity("3","","","31"));

        for (int i = 0; i < data.size(); i++) {
            Log.d("@@@", "id " + data.valueAt(i).getId());
            Log.d("@@@", "date " + data.valueAt(i).getDate());
        }


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