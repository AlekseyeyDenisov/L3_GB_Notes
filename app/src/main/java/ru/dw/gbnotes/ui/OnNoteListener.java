package ru.dw.gbnotes.ui;

import ru.dw.gbnotes.domain.model.NotesEntity;

public interface OnNoteListener {
    void onDeleteNoteItem(NotesEntity notesEntity);
    void onUpDataNoteItem(NotesEntity notesEntity);
}
