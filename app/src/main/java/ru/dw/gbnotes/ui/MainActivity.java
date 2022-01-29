package ru.dw.gbnotes.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import ru.dw.gbnotes.App;
import ru.dw.gbnotes.R;
import ru.dw.gbnotes.domain.model.NotesEntity;
import ru.dw.gbnotes.ui.fragment.Controller;
import ru.dw.gbnotes.ui.fragment.CounterFragment;
import ru.dw.gbnotes.ui.fragment.NewNoteFragment;
import ru.dw.gbnotes.ui.fragment.NoteListFragment;
import ru.dw.gbnotes.ui.fragment.UpdateNoteFragment;
import ru.dw.gbnotes.utils.Util;


public class MainActivity
        extends AppCompatActivity
        implements
        NoteListFragment.Controller,
        Controller {

    private static final String TAG_LIST_FRAGMENT = "TAG_LIST_FRAGMENT";
    FrameLayout secondFrameLayout;
    private Boolean isShowCountFragment = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        secondFrameLayout = findViewById(R.id.activity_main__second_fragment_container);

        if (savedInstanceState == null) {
            showListInMainContainer();
            App.getInstance().getCounter().setCount();
        }

    }

    private void showListInMainContainer() {
        Fragment noteListFragment = new NoteListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main__main_fragment_container, noteListFragment, TAG_LIST_FRAGMENT)
                .commit();
    }

    private void showFragmentSecondary(Fragment updateNoteFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main__second_fragment_container, updateNoteFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void detailFinish() {
        getSupportFragmentManager().popBackStack();
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
        if (secondFrameLayout.requestFocus()) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.attention_title_alert_dialog)
                    .setMessage(R.string.message_your_data_is_not_saved)
                    .setPositiveButton(R.string.yes, (dialog, which) -> detailFinish())
                    .setNegativeButton(R.string.no, (dialog, which) -> {
                    })
                    .show();

        } else if (isShowCountFragment) {
            isShowCountFragment = false;
            detailFinish();

        } else finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_note) {
            showNoteDetails(Util.newNote());
            return true;
        }
        if (item.getItemId() == R.id.counter) {
            showFragmentCounter();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showNoteDetails(NotesEntity notesEntity) {
        getSupportFragmentManager().popBackStack();
        if (notesEntity.getHeading().isEmpty()) {
            Fragment newNoteFragment = NewNoteFragment.newInstance(notesEntity);
            showFragmentSecondary(newNoteFragment);
        } else {
            Fragment updateNoteFragment = UpdateNoteFragment.newInstance(notesEntity);
            showFragmentSecondary(updateNoteFragment);
        }
    }

    private void showFragmentCounter() {
        Fragment counterFragment = CounterFragment.newInstance();
        showFragmentSecondary(counterFragment);
        isShowCountFragment = true;
    }


}