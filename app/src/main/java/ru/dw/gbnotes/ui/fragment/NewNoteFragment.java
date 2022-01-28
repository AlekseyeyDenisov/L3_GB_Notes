package ru.dw.gbnotes.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import ru.dw.gbnotes.App;
import ru.dw.gbnotes.R;
import ru.dw.gbnotes.data.Repository;
import ru.dw.gbnotes.domain.model.NotesEntity;
import ru.dw.gbnotes.domain.model.RepositoryData;
import ru.dw.gbnotes.utils.Util;

public class NewNoteFragment extends Fragment implements RepositoryData {
    public static final String BUNDLE_FRAGMENT_DETAIL_KEY = "BUNDLE_FRAGMENT_DETAIL_KEY";
    private Repository repository;

    private EditText editTextHeadingNote;
    private EditText editTextDescriptionNote;
    private EditText editTextDateNote;
    private ImageButton imageButtonSaveNote;
    private ImageButton imageButtonDeleteNote;

    private NotesEntity notesEntity;

    private NewNoteFragment.Controller controller;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof NoteListFragment.Controller) {
            controller = (NewNoteFragment.Controller) context;
        } else {
            throw new IllegalStateException("Activity must implement  NoteFragmentDetail.Controller");
        }

    }

    public static NewNoteFragment newInstance(NotesEntity notesEntity) {
        NewNoteFragment newNoteFragment = new NewNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_FRAGMENT_DETAIL_KEY, notesEntity);
        newNoteFragment.setArguments(bundle);

        return newNoteFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_note, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        repository = App.getInstance().getRepository();
        initView(view);
        assert getArguments() != null;
        notesEntity = getArguments().getParcelable(BUNDLE_FRAGMENT_DETAIL_KEY);

        saveItemNotes(notesEntity);
        imageButtonDeleteNote.setOnClickListener(v -> controller.detailFinish());
    }


    private void initView(@NonNull View view) {
        editTextHeadingNote = view.findViewById(R.id.fragment_details__heading_note_edit_text);
        editTextDescriptionNote = view.findViewById(R.id.fragment_details__description_note_edit_text);
        editTextDateNote = view.findViewById(R.id.fragment_details__date_note_edit_text);
        imageButtonSaveNote = view.findViewById(R.id.fragment_details__button_save_entry_note);
        imageButtonDeleteNote = view.findViewById(R.id.fragment_details__button_delete_entry_note);
    }

    @Override
    public Boolean saveItemNotes(NotesEntity notesEntity) {
        imageButtonSaveNote.setOnClickListener(v -> newSaveRepo());
        return null;
    }

    private void newSaveRepo() {
        NotesEntity newNotesEntity = newDataView();
        if (!newNotesEntity.getHeading().equals("")) {
            repository.saveItemNotes(newNotesEntity);
            Util.systemToast(requireContext(),R.string.message_new_data_saved_note);
        }
        controller.detailFinish();
    }

    @Override
    public Boolean deleteItemNotes(NotesEntity notesEntity) {
        imageButtonDeleteNote.setOnClickListener(v -> returnFragment());
        return null;
    }

    @Override
    public Boolean upDataItemNote(NotesEntity notesEntity) {
        editTextHeadingNote.setText(notesEntity.getHeading());
        editTextDescriptionNote.setText(notesEntity.getDescription());
        editTextDateNote.setText(notesEntity.getDate());
        imageButtonSaveNote.setOnClickListener(v -> updateRepo());
        return null;
    }

    private void returnFragment() { controller.detailFinish();  }

    private void updateRepo() {
        if (repository.upDataItemNote(newDataView())) {
            controller.detailFinish();
            Util.systemToast(requireContext(),R.string.message_up_data_item);
        }
    }

    private NotesEntity newDataView() {
        notesEntity.setHeading(editTextHeadingNote.getText().toString());
        notesEntity.setDescription(editTextDescriptionNote.getText().toString());
        notesEntity.setDate(editTextDateNote.getText().toString());
        return notesEntity;
    }

    public interface Controller {
        void detailFinish();
    }

    @Override
    public void onPause() {
        super.onPause();
        controller.detailFinish();
    }

    @Override
    public List<NotesEntity> getAllNotes() {
        return null;
    }



    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.detail_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_update_note_menu_detail: newSaveRepo();
            return true;
            case R.id.delete_note_menu_detail: controller.detailFinish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu();
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.add_note).setVisible(false);
    }
}