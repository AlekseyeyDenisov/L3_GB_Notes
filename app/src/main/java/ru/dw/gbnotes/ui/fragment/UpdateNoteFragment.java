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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.dw.gbnotes.App;
import ru.dw.gbnotes.R;
import ru.dw.gbnotes.data.Repository;
import ru.dw.gbnotes.domain.model.NotesEntity;
import ru.dw.gbnotes.utils.Util;

public class UpdateNoteFragment extends Fragment {
    public static final String BUNDLE_FRAGMENT_DETAIL_KEY = "BUNDLE_FRAGMENT_DETAIL_KEY";
    private Repository repository;

    private EditText editTextHeadingNote;
    private EditText editTextDescriptionNote;
    private EditText editTextDateNote;
    private ImageButton imageButtonSaveNote;
    private ImageButton imageButtonDeleteNote;

    private NotesEntity notesEntity;

    private UpdateNoteFragment.Controller controller;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof NoteListFragment.Controller) {
            controller = (UpdateNoteFragment.Controller) context;
        } else {
            throw new IllegalStateException("Activity must implement  NoteFragmentDetail.Controller");
        }

    }

    public static UpdateNoteFragment newInstance(NotesEntity notesEntity) {
        UpdateNoteFragment updateNoteFragment = new UpdateNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_FRAGMENT_DETAIL_KEY, notesEntity);
        updateNoteFragment.setArguments(bundle);

        return updateNoteFragment;
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

        upDataItemNote();
        deleteItemNotes();

    }

    private void initView(@NonNull View view) {
        editTextHeadingNote = view.findViewById(R.id.fragment_details__heading_note_edit_text);
        editTextDescriptionNote = view.findViewById(R.id.fragment_details__description_note_edit_text);
        editTextDateNote = view.findViewById(R.id.fragment_details__date_note_edit_text);

        imageButtonSaveNote = view.findViewById(R.id.fragment_details__button_save_entry_note);
        imageButtonDeleteNote = view.findViewById(R.id.fragment_details__button_delete_entry_note);


    }

    public void deleteItemNotes() {
        imageButtonDeleteNote.setOnClickListener(v -> deleteDateRepo());
    }


    public void upDataItemNote() {
        editTextHeadingNote.setText(notesEntity.getHeading());
        editTextDescriptionNote.setText(notesEntity.getDescription());
        editTextDateNote.setText(notesEntity.getDate());

        imageButtonSaveNote.setOnClickListener(v -> updateRepo());
    }

    private void updateRepo() {
        if (repository.upDataItemNote(newDataView())) {
            controller.detailFinish();
            Util.systemToast(requireContext(),R.string.message_up_data_item);
        }

    }
    private void deleteDateRepo() {
        if (repository.deleteItemNotes(notesEntity)){
            controller.detailFinish();
            Util.systemToast(requireContext(),R.string.message_delete_data_item);
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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.detail_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_update_note_menu_detail: {
                updateRepo();
            }
            return true;
            case R.id.delete_note_menu_detail: {
                deleteDateRepo();
            }
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
