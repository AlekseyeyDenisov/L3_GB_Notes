package ru.dw.gbnotes.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import ru.dw.gbnotes.R;
import ru.dw.gbnotes.domain.model.NotesEntity;
import ru.dw.gbnotes.ui.fragment.NoteFragmentDetail;
import ru.dw.gbnotes.ui.fragment.NoteListFragment;


public class MainActivity
        extends AppCompatActivity
        implements NoteListFragment.Controller, NoteFragmentDetail.Controller {
    private static final String TAG_LIST_FRAGMENT = "TAG_LIST_FRAGMENT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Fragment noteListFragment = new NoteListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_main_fragment_container, noteListFragment, TAG_LIST_FRAGMENT)
                    .commit();
        }

    }


    @Override
    public void showNoteDetails(NotesEntity notesEntity) {
        Fragment noteFragmentDetail = NoteFragmentDetail.newInstance(notesEntity);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main_fragment_container, noteFragmentDetail)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void detailFinish() {
        getSupportFragmentManager().popBackStack();
        NoteListFragment noteListFragment =
                (NoteListFragment) getSupportFragmentManager()
                        .findFragmentByTag(TAG_LIST_FRAGMENT);

        if (noteListFragment == null)
            throw new IllegalStateException("NoteListFragment not on screen");
        noteListFragment.onResume();

    }
}