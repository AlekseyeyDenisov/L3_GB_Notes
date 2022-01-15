package ru.dw.gbnotes.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

import ru.dw.gbnotes.App;
import ru.dw.gbnotes.R;
import ru.dw.gbnotes.data.Repository;
import ru.dw.gbnotes.domain.model.NotesEntity;

public class NoteFragmentDetail extends Fragment {
    public static final String BUNDLE_KEY = "BUNDLE_KEY";
    Repository repository;

    EditText editTextHeadingNote;
    EditText editTextDescriptionNote;
    EditText editTextDateNote;
    ImageButton bottomSaveNote;
    ImageButton bottomDeleteNote;

    NotesEntity notesEntity;

    public static NoteFragmentDetail newInstance(NotesEntity notesEntity) {
        NoteFragmentDetail fragmentDetail = new NoteFragmentDetail();

        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_KEY,notesEntity);

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

        notesEntity = getArguments().getParcelable(BUNDLE_KEY);

        updateNote(notesEntity);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


    private void initView(@NonNull View view) {
        editTextHeadingNote = view.findViewById(R.id.heading_note_edit_text);
        editTextDescriptionNote = view.findViewById(R.id.description_note_edit_text);
        editTextDateNote = view.findViewById(R.id.date_note_edit_text);

        bottomSaveNote = view.findViewById(R.id.bottom_save_entry_note);
        bottomDeleteNote = view.findViewById(R.id.bottom_delete_entry_note);


    }

//    private void newNote() {
//        final Random random = new Random();
//        NotesEntity notesEntity = new NotesEntity(
//                random.nextLong(),
//                "",
//                "",
//                ""
//        );
//        bottomSaveNote.setOnClickListener(v -> {
//            repository.setItemNotes(upDataView(notesEntity));
//            //finish();
//        });
//        //bottomDeleteNote.setOnClickListener(v -> finish());
//    }

    private void updateNote(NotesEntity notesEntity) {
        editTextHeadingNote.setText(notesEntity.getHeading());
        editTextDescriptionNote.setText(notesEntity.getDescription());
        editTextDateNote.setText(notesEntity.getDate());

        bottomDeleteNote.setOnClickListener(v -> {
            ///if (repository.deleteItemNotes(notesEntity))
            // finish();
        });
        bottomSaveNote.setOnClickListener(v -> {
            // if (repository.upDataItemNote(upDataView(notesEntity)))
            //  finish();
        });
    }

    private NotesEntity upDataView(NotesEntity notesEntity) {
        notesEntity.setHeading(editTextHeadingNote.getText().toString());
        notesEntity.setDescription(editTextDescriptionNote.getText().toString());
        notesEntity.setDate(editTextDateNote.getText().toString());
        return notesEntity;
    }
}
