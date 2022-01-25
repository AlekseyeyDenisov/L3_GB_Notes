package ru.dw.gbnotes.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
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

public class NoteDetailFragment extends Fragment implements RepositoryData {
    public static final String BUNDLE_FRAGMENT_DETAIL_KEY = "BUNDLE_FRAGMENT_DETAIL_KEY";
    private Repository repository;

    private EditText editTextHeadingNote;
    private EditText editTextDescriptionNote;
    private EditText editTextDateNote;
    private ImageButton imageButtonSaveNote;
    private ImageButton imageButtonDeleteNote;

    private NotesEntity notesEntity;

    private NoteDetailFragment.Controller controller;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof NoteListFragment.Controller) {
            controller = (NoteDetailFragment.Controller) context;
        } else {
            throw new IllegalStateException("Activity must implement  NoteFragmentDetail.Controller");
        }

    }

    public static NoteDetailFragment newInstance(NotesEntity notesEntity) {
        NoteDetailFragment fragmentDetail = new NoteDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_FRAGMENT_DETAIL_KEY, notesEntity);

        fragmentDetail.setArguments(bundle);

        return fragmentDetail;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_note, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        repository = App.get().getRepository();
        initView(view);

        notesEntity = getArguments().getParcelable(BUNDLE_FRAGMENT_DETAIL_KEY);

        if (!notesEntity.getHeading().equals("")) {
            upDataItemNote(notesEntity);
            deleteItemNotes(notesEntity);
        } else {
            saveItemNotes(notesEntity);
            imageButtonDeleteNote.setOnClickListener(v -> controller.detailFinish());
        }

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
        newDataView();
        imageButtonSaveNote.setOnClickListener(v -> {
            NotesEntity newNotesEntity = newDataView();
            if (!newNotesEntity.getHeading().equals("")) {
                repository.saveItemNotes(newNotesEntity);
                systemToast(R.string.message_new_data_saved_note);
            }
            controller.detailFinish();
        });

        return null;
    }

    @Override
    public Boolean deleteItemNotes(NotesEntity notesEntity) {
        imageButtonDeleteNote.setOnClickListener(v -> {
            if (repository.deleteItemNotes(notesEntity))
                controller.detailFinish();
            systemToast(R.string.message_delete_data_item);

        });
        return null;
    }

    @Override
    public Boolean upDataItemNote(NotesEntity notesEntity) {

        editTextHeadingNote.setText(notesEntity.getHeading());
        editTextDescriptionNote.setText(notesEntity.getDescription());
        editTextDateNote.setText(notesEntity.getDate());

        imageButtonSaveNote.setOnClickListener(v -> {
            if (repository.upDataItemNote(newDataView()))
                controller.detailFinish();
            systemToast(R.string.message_up_data_item);
        });
        return null;
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
    public List<NotesEntity> getNoteData() {
        return null;
    }

    private void systemToast(int id) {
        Toast.makeText(
                requireContext(),
                requireActivity().getString(id),
                Toast.LENGTH_SHORT).show();
    }



}
