package ru.dw.gbnotes.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import ru.dw.gbnotes.R;


import ru.dw.gbnotes.domain.model.NotesEntity;
import ru.dw.gbnotes.ui.fragment.NoteFragmentDetail;
import ru.dw.gbnotes.ui.fragment.NoteListFragment;



public class MainActivity extends AppCompatActivity implements NoteListFragment.Controller  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
        Fragment noteListFragment = new NoteListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main_fragment_container,noteListFragment)
                .commit();
        }

    }


    @Override
    public void showNoteDetails(NotesEntity notesEntity) {
        Fragment noteFragmentDetail =  NoteFragmentDetail.newInstance(notesEntity);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main_fragment_container,noteFragmentDetail)
                .addToBackStack(null)
                .commit();
    }

}