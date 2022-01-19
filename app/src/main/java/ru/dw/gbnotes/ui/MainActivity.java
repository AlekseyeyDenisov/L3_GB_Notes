package ru.dw.gbnotes.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import ru.dw.gbnotes.R;
import ru.dw.gbnotes.domain.model.NotesEntity;
import ru.dw.gbnotes.ui.fragment.NoteDetailFragment;
import ru.dw.gbnotes.ui.fragment.NoteListFragment;


public class MainActivity
        extends AppCompatActivity
        implements NoteListFragment.Controller, NoteDetailFragment.Controller {
    private static final String TAG_LIST_FRAGMENT = "TAG_LIST_FRAGMENT";
    private static final String TAG = "@@@";
    FrameLayout secondFrameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        secondFrameLayout = findViewById(R.id.activity_main__second_fragment_container);

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
        Fragment noteFragmentDetail = NoteDetailFragment.newInstance(notesEntity);

        int containerId = R.id.activity_main__second_fragment_container;

        getSupportFragmentManager()
                .beginTransaction()
                .add(containerId, noteFragmentDetail)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void detailFinish() {
        //secondFrameLayout.removeAllViews();
        getSupportFragmentManager().popBackStack();
        NoteListFragment noteListFragment =
                (NoteListFragment) getSupportFragmentManager()
                        .findFragmentByTag(TAG_LIST_FRAGMENT);

        if (noteListFragment == null){
            throw new IllegalStateException("NoteListFragment not on screen");
        }
        noteListFragment.onResume();

    }
}