package ru.dw.gbnotes.ui;

import android.os.Bundle;
import android.util.Log;

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
    private static final String TAG = "@@@";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            showListInMainContainer();
        }

    }

    private void showListInMainContainer() {
        Log.d(TAG, "showListInMainContainer: ");
        Fragment noteListFragment = new NoteListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main__main_fragment_container, noteListFragment, TAG_LIST_FRAGMENT)
                .commit();

    }


    @Override
    public void showNoteDetails(NotesEntity notesEntity) {
        Log.d(TAG, "showNoteDetails: " + notesEntity.getHeading());
        Fragment noteFragmentDetail = NoteFragmentDetail.newInstance(notesEntity);

        int containerId = R.id.activity_main__second_fragment_container;

        getSupportFragmentManager()
                .beginTransaction()
                .add(containerId, noteFragmentDetail)
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