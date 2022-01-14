package ru.dw.gbnotes.domain;

import ru.dw.gbnotes.domain.model.NotesEntity;

public interface OnNoteListener {
    void onDeleteNoteItem(NotesEntity notesEntity, int position);
    void onUpDataNoteItem(NotesEntity notesEntity);
}
