package ru.dw.gbnotes.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import ru.dw.gbnotes.App;
import ru.dw.gbnotes.R;
import ru.dw.gbnotes.data.Repository;
import ru.dw.gbnotes.domain.model.NotesEntity;

public class NoteActivity extends AppCompatActivity {
    public static final String NOTE_EXTRA_KEY = "NOTE_EXTRA_KEY";
    Repository repository;

    EditText editTextHeadingNote;
    EditText editTextDescriptionNote;
    EditText editTextDateNote;
    ImageButton bottomSaveNote;
    ImageButton bottomDeleteNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        repository = App.get(this).getRepository();
        initView();
        newOrUpNone();
    }

    private void newOrUpNone() {
        NotesEntity notesEntity = getIntent().getParcelableExtra(NOTE_EXTRA_KEY);
        if (notesEntity != null)
            updateNote(notesEntity);
        else newNote();
    }

    private void initView() {
        editTextHeadingNote = findViewById(R.id.heading_note_edit_text);
        editTextDescriptionNote = findViewById(R.id.description_note_edit_text);
        editTextDateNote = findViewById(R.id.date_note_edit_text);

        bottomSaveNote = findViewById(R.id.bottom_save_entry_note);
        bottomDeleteNote = findViewById(R.id.bottom_delete_entry_note);


        bottomSaveNote.setOnClickListener(v -> {
            NotesEntity notesEntity = new NotesEntity(
                    "15",
                    "15",
                    "dfbsfsd",
                    "01.01.2022"
            );
            if (repository.setItemNotes(notesEntity))
                finish();
        });

    }

    private void newNote() {
        final Random random = new Random();
        NotesEntity notesEntity = new NotesEntity(
                "" + random.nextInt(),
                "",
                "",
                ""
        );
        bottomSaveNote.setOnClickListener(v -> {
            repository.setItemNotes(upDataView(notesEntity));
            finish();
        });
        bottomDeleteNote.setOnClickListener(v -> finish());
    }

    private void updateNote(NotesEntity notesEntity) {
        editTextHeadingNote.setText(notesEntity.getHeading());
        editTextDescriptionNote.setText(notesEntity.getDescription());
        editTextDateNote.setText(notesEntity.getDate());

        bottomDeleteNote.setOnClickListener(v -> {
            if (repository.deleteItemNotes(notesEntity))
                finish();
        });
        bottomSaveNote.setOnClickListener(v -> {
            if (repository.upDataItemNote(upDataView(notesEntity)))
                finish();
        });
    }

    private NotesEntity upDataView(NotesEntity notesEntity) {
        notesEntity.setHeading(editTextHeadingNote.getText().toString());
        notesEntity.setDescription(editTextDescriptionNote.getText().toString());
        notesEntity.setDate(editTextDateNote.getText().toString());
        return notesEntity;
    }


}