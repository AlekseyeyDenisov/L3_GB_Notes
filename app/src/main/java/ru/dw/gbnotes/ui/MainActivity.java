package ru.dw.gbnotes.ui;

import android.app.AlertDialog;
import android.os.Bundle;
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
        Fragment noteListFragment = new NoteListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main__main_fragment_container, noteListFragment, TAG_LIST_FRAGMENT)
                .commit();
    }


    @Override
    public void showNoteDetails(NotesEntity notesEntity) {
        getSupportFragmentManager().popBackStack();
        Fragment noteFragmentDetail = NoteDetailFragment.newInstance(notesEntity);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main__second_fragment_container, noteFragmentDetail)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void detailFinish() {
        secondFrameLayout.removeAllViews();
        NoteListFragment noteListFragment =
                (NoteListFragment) getSupportFragmentManager()
                        .findFragmentByTag(TAG_LIST_FRAGMENT);

        if (noteListFragment == null) {
            throw new IllegalStateException(getString(R.string.note_list_fragment_not_on_screen));
        }
        noteListFragment.onResume();
    }

    @Override
    public void onBackPressed() {
        if (secondFrameLayout.requestFocus()){
            new AlertDialog.Builder(this)
                    .setTitle(R.string.attention_title_alert_dialog)
                    .setMessage(R.string.message_your_data_is_not_saved)
                    .setPositiveButton(R.string.yes,(dialog, which) -> detailFinish())
                    .setNegativeButton(R.string.no,(dialog, which) -> { })
                    .show();

        }else
            finish();
    }
}