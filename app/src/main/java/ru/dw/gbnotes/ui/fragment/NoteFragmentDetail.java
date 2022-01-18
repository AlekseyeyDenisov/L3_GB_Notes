package ru.dw.gbnotes.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
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

public class NoteFragmentDetail extends Fragment {
    public static final String BUNDLE_FRAGMENT_DETAIL_KEY = "BUNDLE_FRAGMENT_DETAIL_KEY";
    private Repository repository;

    private EditText editTextHeadingNote;
    private EditText editTextDescriptionNote;
    private EditText editTextDateNote;
    private ImageButton buttonSaveNote;
    private ImageButton buttonDeleteNote;

    private NotesEntity notesEntity;

    private NoteFragmentDetail.Controller controller;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof NoteListFragment.Controller) {
            controller = (NoteFragmentDetail.Controller) context;
        } else {
            throw new IllegalStateException("Activity must implement  NoteFragmentDetail.Controller");
        }

    }

    public static NoteFragmentDetail newInstance(NotesEntity notesEntity) {
        NoteFragmentDetail fragmentDetail = new NoteFragmentDetail();

        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_FRAGMENT_DETAIL_KEY, notesEntity);

        fragmentDetail.setArguments(bundle);

        return fragmentDetail;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_insert_update_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        repository = App.get().getRepository();
        initView(view);

        notesEntity = getArguments().getParcelable(BUNDLE_FRAGMENT_DETAIL_KEY);

        if (!notesEntity.getHeading().equals("")){
            updateNote(notesEntity);
        } else{
            newNote();
        }

    }


    private void initView(@NonNull View view) {
        editTextHeadingNote = view.findViewById(R.id.fragment_details__heading_note_edit_text);
        editTextDescriptionNote = view.findViewById(R.id.fragment_details__description_note_edit_text);
        editTextDateNote = view.findViewById(R.id.fragment_details__date_note_edit_text);

        buttonSaveNote = view.findViewById(R.id.fragment_details__button_save_entry_note);
        buttonDeleteNote = view.findViewById(R.id.fragment_details__button_delete_entry_note);


    }

    private void newNote() {
        buttonSaveNote.setOnClickListener(v -> {
            NotesEntity newNotesEntity = upDataView(notesEntity);
            if (!newNotesEntity.getHeading().equals(""))
            repository.setItemNotes(newNotesEntity);
            controller.detailFinish();
        });
        buttonDeleteNote.setOnClickListener(v -> controller.detailFinish());
    }

    private void updateNote(NotesEntity notesEntity) {
        editTextHeadingNote.setText(notesEntity.getHeading());
        editTextDescriptionNote.setText(notesEntity.getDescription());
        editTextDateNote.setText(notesEntity.getDate());

        buttonDeleteNote.setOnClickListener(v -> {
            if (repository.deleteItemNotes(notesEntity))
                controller.detailFinish();

        });
        buttonSaveNote.setOnClickListener(v -> {
            if (repository.upDataItemNote(upDataView(notesEntity)))
                controller.detailFinish();
        });
    }

    private NotesEntity upDataView(NotesEntity notesEntity) {
        notesEntity.setHeading(editTextHeadingNote.getText().toString());
        notesEntity.setDescription(editTextDescriptionNote.getText().toString());
        notesEntity.setDate(editTextDateNote.getText().toString());
        return notesEntity;
    }

    public interface Controller { void detailFinish(); }

    @Override
    public void onPause() {
        super.onPause();
        controller.detailFinish();
    }
}
